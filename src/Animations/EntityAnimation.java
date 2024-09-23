package Animations;

import GameFiles.GamePanel;

public class EntityAnimation {
    public AnimationInstance instance;
    public long animationStartTime;
    public boolean animationActive;
    public long animationDuration;

    public EntityAnimation(String animationName) {
        AnimationInstance animationData = AllAnimationData.getInstanceByAnimationname(animationName);
        this.instance = animationData.copy();
        this.animationDuration = 0;
    }

    public EntityAnimation(String animationName, long animationDuration) {
        AnimationInstance animationData = AllAnimationData.getInstanceByAnimationname(animationName);
        this.instance = animationData.copy();
        this.animationDuration = animationDuration;
    }

    public void start(long timeAtStart) {
        animationStartTime = timeAtStart;
        animationActive = true;
        System.out.println("START - Anim");
    }

    public boolean update() {
        if (animationActive) {
            long timeSinceStartOfAnimation = GamePanel.currentTime - animationStartTime;
            boolean updateStatus = instance.updateInstance(timeSinceStartOfAnimation);
            if (!updateStatus) {
                cancel();
                return false;
            }
            return true; // Update success
        }
        return false;
    }

    public void cancel() {
        animationActive = false;
        System.out.println("CANCEL - Anim");
    }
}
