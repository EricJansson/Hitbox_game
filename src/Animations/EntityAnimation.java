package Animations;

import GameFiles.GamePanel;

public class EntityAnimation {
    public AnimationInstance instance;
    public long animationStartTime;
    public boolean animationActive;
    // Animation duration 0 -> Use preset instance times
    public double animationDuration;
    // Priority 0 -> Lowest priority
    public int priority;

    public EntityAnimation(String animationName) { this(animationName, 0.0, 0); }
    public EntityAnimation(String animationName, int priority) { this(animationName, 0.0, priority); }
    public EntityAnimation(String animationName, double animationDuration) { this(animationName, animationDuration, 0); }
    /**
     * @param animationName Required
     * @param animationDuration Default = 0.0 (This will use the hardcoded instance time values)
     * @param priority Default = 0 (The lowest priority. Will only run if no other animation is active.)
     */
    public EntityAnimation(String animationName, double animationDuration, int priority) {
        AnimationInstance animationData = AllAnimationData.getInstanceByAnimationname(animationName);
        this.animationDuration = animationDuration;
        this.priority = priority;
        this.instance = animationData.copy();
        if (animationDuration <= 0) { return; }
        // Calculates the duration of each frame based on the normalized values -> Makes the animation complete in X seconds
        this.instance.calcFrameDurations(this.animationDuration);
    }

    public void start(long timeAtStart) {
        animationStartTime = timeAtStart;
        animationActive = true;
    }

    public void cancel() {
        animationActive = false;
    }

    public boolean isAnimationFinished(long timeNow) {
        long timeSinceStart = timeNow - animationStartTime;
        if (timeSinceStart < instance.frameDurations.get(instance.frameDurations.size()-1)) { return false; }
        return true;
    }

}
