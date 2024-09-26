package Animations;

import java.util.ArrayList;

public class AllAnimationData {
    public static ArrayList<AnimationTemplate> templates = new ArrayList<>();
    public static ArrayList<AnimationInstance> instances = new ArrayList<>();

    public AllAnimationData() {
        templates.add(new AnimationTemplate("slimeBounce", 4, 4) );
        templates.add(new AnimationTemplate("redSlimeMovement", 3, 4) );
        templates.add(new AnimationTemplate("chickenMovement", 3, 4) );
        templates.add(new AnimationTemplate("batFlying", 3, 1) );
        templates.add(new AnimationTemplate("explode", 6, 2) );

        instances.add(new AnimationInstance(
                "slimeMovement", "slimeBounce",
                this.createFrameDurationList(new double[]{100, 100, 100, 100, 100}),
                this.createFrameSubImageList(new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 0}, new int[]{0, 0})
        ));

        instances.add(new AnimationInstance(
                "chickenMoveUp", "chickenMovement",
                this.createFrameDurationList(new double[]{100, 100, 100, 100, 100}),
                this.createFrameSubImageList(new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{1, 0}, new int[]{0, 0})
        ));
        instances.add(new AnimationInstance(
                "chickenMoveRight", "chickenMovement",
                this.createFrameDurationList(new double[]{100, 100, 100, 100, 100}),
                this.createFrameSubImageList(new int[]{0, 1}, new int[]{1, 1}, new int[]{2, 1}, new int[]{1, 1}, new int[]{0, 1})
        ));
        instances.add(new AnimationInstance(
                "chickenMoveDown", "chickenMovement",
                this.createFrameDurationList(new double[]{100, 100, 100, 100, 100}),
                this.createFrameSubImageList(new int[]{0, 2}, new int[]{1, 2}, new int[]{2, 2}, new int[]{1, 2}, new int[]{0, 2})
        ));
        instances.add(new AnimationInstance(
                "chickenMoveLeft", "chickenMovement",
                this.createFrameDurationList(new double[]{100, 100, 100, 100, 100}),
                this.createFrameSubImageList(new int[]{0, 3}, new int[]{1, 3}, new int[]{2, 3}, new int[]{1, 3}, new int[]{0, 3})
        ));

        instances.add(new AnimationInstance(
                "redSlimeMoveUp", "redSlimeMovement",
                this.createFrameDurationList(new double[]{100, 100, 100, 100, 100}),
                this.createFrameSubImageList(new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{1, 0}, new int[]{0, 0})
        ));
        instances.add(new AnimationInstance(
                "redSlimeMoveRight", "redSlimeMovement",
                this.createFrameDurationList(new double[]{100, 100, 100, 100, 100}),
                this.createFrameSubImageList(new int[]{0, 1}, new int[]{1, 1}, new int[]{2, 1}, new int[]{1, 1}, new int[]{0, 1})
        ));
        instances.add(new AnimationInstance(
                "redSlimeMoveDown", "redSlimeMovement",
                this.createFrameDurationList(new double[]{100, 100, 100, 100, 100}),
                this.createFrameSubImageList(new int[]{0, 2}, new int[]{1, 2}, new int[]{2, 2}, new int[]{1, 2}, new int[]{0, 2})
        ));
        instances.add(new AnimationInstance(
                "redSlimeMoveLeft", "redSlimeMovement",
                this.createFrameDurationList(new double[]{100, 100, 100, 100, 100}),
                this.createFrameSubImageList(new int[]{0, 3}, new int[]{1, 3}, new int[]{2, 3}, new int[]{1, 3}, new int[]{0, 3})
        ));

        instances.add(new AnimationInstance(
                "batFlyingAround", "batFlying",
                this.createFrameDurationList(new double[]{200, 100, 200, 100, 200}),
                this.createFrameSubImageList(new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{1, 0}, new int[]{0, 0})
        ));
        instances.add(new AnimationInstance(
                "batFlyingUpDown", "batFlying",
                this.createFrameDurationList(new double[]{200, 100, 200, 100, 200}),
                this.createFrameSubImageList(new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{1, 0}, new int[]{0, 0})
        ));
        instances.add(new AnimationInstance(
                "slimeExplode", "explode",
                this.createFrameDurationList(new double[]{100, 100, 100, 100, 100, 100, 100}),
                this.createFrameSubImageList(new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 0}, new int[]{4, 0}, new int[]{5, 0}, new int[]{0, 1})
        ));

        this.testAnimationInstaces();
    }

    public void testAnimationInstaces() {
        for (int ii = 0; ii < instances.size(); ii++) {
            AnimationInstance tempInst = instances.get(ii);
            if (tempInst.frameDurations.size() != tempInst.frameSubImageIndexPairs.size()) {
                System.out.println("AllAnimationData/testAnimationInstaces: FAILED (" + tempInst.animationName + ") - DurationList & SubImageList missmatch. Requires same length! -> (" + tempInst.frameDurations.size() + " != " + tempInst.frameSubImageIndexPairs.size() + ")\n");
                return;
            }
            if (getTemplate(tempInst.template.imageName) == null) {
                System.out.println("AllAnimationData/testAnimationInstaces: FAILED (" + tempInst.animationName + ") - Can't find matching animationTemplate.\n");
                return;
            }
            int maxXIndex = getTemplate(tempInst.template.imageName).subImageHorizontalCount;
            int maxYIndex = getTemplate(tempInst.template.imageName).subImageVerticalCount;
            for (int kk = 0; kk < tempInst.frameSubImageIndexPairs.size(); kk++) {
                if (tempInst.frameSubImageIndexPairs.get(kk)[0] >= maxXIndex || tempInst.frameSubImageIndexPairs.get(kk)[0] < 0) {
                    System.out.println("AllAnimationData/testAnimationInstaces: FAILED (" + tempInst.animationName + ") - Subimage[" + kk + "] X-index out of bounds! -> Val: " + tempInst.frameSubImageIndexPairs.get(kk)[0] + "   (Max: " + (maxXIndex-1) + ")\n");
                    return;
                }
                if (tempInst.frameSubImageIndexPairs.get(kk)[1] >= maxYIndex || tempInst.frameSubImageIndexPairs.get(kk)[1] < 0) {
                    System.out.println("AllAnimationData/testAnimationInstaces: FAILED (" + tempInst.animationName + ") - Subimage[" + kk + "] Y-index out of bounds! -> Val: " + tempInst.frameSubImageIndexPairs.get(kk)[1] + "   (Max: " + (maxYIndex-1) + ")\n");
                    return;
                }
            }
        }
    }

    public AnimationInstance getInstanceByFilename(String imageName) {
        for (int ii = 0; ii < instances.size(); ii++) {
            if (instances.get(ii).template.imageName == imageName) {
                return instances.get(ii);
            }
        }
        System.out.println("ERROR: getInstanceByFilename() - Can't find instance with filename: \"" + imageName + "\"");
        System.exit(1);
        return null;
    }

    public static AnimationInstance getInstanceByAnimationname(String imageName) {
        for (int ii = 0; ii < instances.size(); ii++) {
            if (instances.get(ii).animationName == imageName) {
                return instances.get(ii);
            }
        }
        System.out.println("ERROR: getInstanceByAnimationname() - Can't find instance with Animation name: \"" + imageName + "\"");
        System.exit(1);
        return null;
    }


    public static AnimationTemplate getTemplate(String imageName) {
        for (int ii = 0; ii < templates.size(); ii++) {
            if (templates.get(ii).imageName == imageName) {
                return templates.get(ii);
            }
        }
        System.out.println("ERROR: getTemplate() - Can't find template with image name: \"" + imageName + "\"");
        System.exit(1);
        return null;
    }

    public ArrayList<Double> createFrameDurationList(double[] frameList) {
        ArrayList<Double> vectorList = new ArrayList<>();
        for (double frame : frameList) {
            vectorList.add(frame);
        }
        return vectorList;
    }

    public ArrayList<int[]> createFrameSubImageList(int[]... indexPairList) {
        ArrayList<int[]> vectorList = new ArrayList<>();
        for (int[] indexPair : indexPairList) {
            vectorList.add(new int[]{indexPair[0], indexPair[1]});
        }
        return vectorList;
    }

}
