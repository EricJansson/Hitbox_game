package Animations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationInstance {
    public AnimationTemplate template;
    public String animationName;
    public ArrayList<BufferedImage> subImageFrames = new ArrayList<>();
    public ArrayList<Double> originalFrameDurations;
    public ArrayList<Double> frameDurations;
    public ArrayList<Double> normalizedFrameDurations = new ArrayList<>();
    public ArrayList<int[]> frameSubImageIndexPairs;

    public AnimationInstance(String animName, String animTemplateName, ArrayList<Double> frameDurationList, ArrayList<int[]> frameSubImageList) {
        this.animationName = animName;
        this.template = AllAnimationData.getTemplate(animTemplateName);
        this.frameSubImageIndexPairs = new ArrayList<>(frameSubImageList);
        this.frameDurations = new ArrayList<>(frameDurationList);
        this.originalFrameDurations = new ArrayList<>(frameDurationList);
        // Add first time to all consecutive times -> Results in list of values that relate to animationStartTime
        processFrameDurations();
        calcNormalizedList();
        generateSubImageFrameList();
    }

    public AnimationInstance copy() {
        // Deep copy of frame durations
        ArrayList<Double> copiedFrameDurations = new ArrayList<>(originalFrameDurations);
        // Deep copy of frameSubImageIndexPairs (since it contains arrays, we need to clone each array)
        ArrayList<int[]> copiedFrameSubImageIndexPairs = new ArrayList<>();
        for (int[] pair : frameSubImageIndexPairs) {
            copiedFrameSubImageIndexPairs.add(pair.clone());  // Clone each int array
        }
        // Create a new AnimationInstance with the copied data
        return new AnimationInstance(animationName, template.imageName, copiedFrameDurations, copiedFrameSubImageIndexPairs);
    }

    public int getInstanceFrame (long timeSinceStart) {
        for (int ii = 0; ii < frameDurations.size(); ii++) {
            if (timeSinceStart < frameDurations.get(ii)) { return ii; }
        }
        return frameDurations.size() - 1; // When animation is over, return last frame
    }

    public BufferedImage getSubImage(int xIndex, int yIndex) {
        return template.getSubImage(xIndex, yIndex);
    }

    public void generateSubImageFrameList() {
        for (int ii = 0; ii < frameSubImageIndexPairs.size(); ii++) {
            BufferedImage temp = getSubImage(frameSubImageIndexPairs.get(ii)[0], frameSubImageIndexPairs.get(ii)[1]);
            subImageFrames.add(temp);
        }
    }

    public void resizeSubImageFrameList(int targetWidth, int targetHeight) {
        for (int ii = 0; ii < subImageFrames.size(); ii++) {
            BufferedImage temp = subImageFrames.get(ii);
            temp = resizeImageTo(temp, targetWidth, targetHeight);
            subImageFrames.set(ii, temp);
        }
    }

    public static BufferedImage resizeImageTo(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        // Draw the original image, scaled to the new width and height
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        // Dispose of the Graphics2D object to free resources
        g2d.dispose();
        return resizedImage;
    }

    public void calcNormalizedList() {
        double sum = 0;
        for (double value : this.frameDurations) {
            sum += value;
        }
        for (double value : this.frameDurations) {
            normalizedFrameDurations.add(value / sum);
        }
    }

    /**
     * Add first time to all consecutive times -> Results in list of values that relate to animationStartTime
     */
    public void processFrameDurations() {
        for (int ii = 1; ii < frameDurations.size(); ii++) {
            double resultingTime = frameDurations.get(ii - 1) + this.frameDurations.get(ii);
            this.frameDurations.set(ii, resultingTime);
        }
    }

    public void calcFrameDurations(double fullAnimationRunTimeSeconds) {
        this.frameDurations = new ArrayList<>();
        for (double value : this.normalizedFrameDurations) {
            // Calc and update new frameDurations based on a total time
            frameDurations.add(value * fullAnimationRunTimeSeconds * 1000);
        }
    }
}
