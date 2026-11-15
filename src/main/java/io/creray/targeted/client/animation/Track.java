package io.creray.targeted.client.animation;

public final class Track {

    @FunctionalInterface
    public interface Driver {
        Driver STATIC = () -> 1.0F;

        static Driver limitedBy(Track track, Track limiter) {
            return () -> Math.min(track.get(), limiter.get());
        }

        float get();
    }

    public static final float MAX_DURATION = 1000.0F;

    public final float duration;
    private boolean isPaused;
    private float currentTime;
    private float goalTime;

    {
        isPaused = true;
        currentTime = 0;
    }

    public Track(float duration) {
        assert duration > 0 && duration < MAX_DURATION;
        this.duration = duration;
        this.goalTime = duration;
    }

    public void runForward() {
        isPaused = false;
        goalTime = duration;
    }

    public void runBackward() {
        isPaused = false;
        goalTime = 0;
    }

    public void runTowards(float progress) {
        isPaused = false;
        goalTime = progress * duration;
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
        return currentTime / duration;
    }
}
