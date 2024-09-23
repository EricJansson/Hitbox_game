package Animations;

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
        for (int ii = 1; ii < frameDurations.size(); ii++) {
            double resultingTime = frameDurations.get(ii - 1) + this.frameDurations.get(ii);
            this.frameDurations.set(ii, resultingTime);
        }
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

    public boolean updateInstance(long timeSinceStart) {
        if (frameDurations.isEmpty()) { return false; }
        while (!frameDurations.isEmpty() && timeSinceStart > frameDurations.get(0)) {
            frameDurations.remove(0);
            subImageFrames.remove(0);
            frameSubImageIndexPairs.remove(0);
        }
        if (frameDurations.isEmpty()) { return false; }
        return true;
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

    public void calcNormalizedList() {
        double sum = 0;
        for (double value : this.frameDurations) {
            sum += value;
        }
        for (double value : this.frameDurations) {
            normalizedFrameDurations.add(value / sum);
        }
    }
}
