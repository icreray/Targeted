package io.creray.targeted.client.animation;

public final class AnimationProgress {

    public final float DURATION;
    private boolean isPaused;
    private float currentTime;
    private float goalTime;

    {
        isPaused = true;
        currentTime = 0;
    }

    public AnimationProgress(float duration) {
        assert duration >= 0;
        DURATION = duration;
        goalTime = DURATION;
    }

    public void runForward() {
        isPaused = false;
        goalTime = DURATION;
    }

    public void runBackward() {
        isPaused = false;
        goalTime = 0;
    }

    public void runTowards(float progress) {
        isPaused = false;
        goalTime = progress * DURATION;
    }

    public void pause() {
        isPaused = true;
    }

    public void update(float delta) {
        if (isPaused) return;
        if (currentTime < goalTime) {
            currentTime += delta;
            if (currentTime >= goalTime) {
                currentTime = goalTime;
                pause();
            }
        }
        else {
            currentTime -= delta;
            if (currentTime <= goalTime) {
                currentTime = goalTime;
                pause();
            }
        }
    }

    public boolean isZero() {
        return currentTime == 0;
    }

    public float getProgress() {
        return currentTime / DURATION;
    }

    public float getMinProgress(AnimationProgress other) {
        return Math.min(getProgress(), other.getProgress());
    }
}
