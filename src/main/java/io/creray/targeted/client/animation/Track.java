package io.creray.targeted.client.animation;

public final class Track {

    @FunctionalInterface
    public interface Driver {
        Driver STATIC = () -> 1.0F;
        float get();
    }

    public final float DURATION;
    private boolean isPaused;
    private float currentTime;
    private float goalTime;

    {
        isPaused = true;
        currentTime = 0;
    }

    public Track(float duration) {
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

    public float get() {
        return currentTime / DURATION;
    }

    public float limitedBy(Track other) {
        return Math.min(get(), other.get());
    }
}
