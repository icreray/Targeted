package io.creray.targeted.client;

public final class Animation {
    public float maxTime;
    public float minTime = 0;
    public State state = State.PAUSED;
    private float time = 0;


    public Animation(float maxTime) {
        this.maxTime = maxTime;
    }

    public void forward() {
        state = State.FORWARD;
    }

    public void pause() {
        state = State.PAUSED;
    }

    public void backward() {
        state = State.BACKWARD;
    }

    public void setRange(float minTime, float maxTime) {
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public void update(float delta) {
        switch (state) {
            case FORWARD -> {
                time += delta;
                if (time > maxTime) {
                    time = maxTime;
                    pause();
                }
            }
            case BACKWARD -> {
                time -= delta;
                if (time < minTime) {
                    time = minTime;
                    pause();
                }
            }
            default -> {}
        }
    }

    public float time() {
        return time;
    }

    public boolean isDone() {
        return state == State.PAUSED;
    }

    public enum State {
        PAUSED,
        FORWARD,
        BACKWARD;
    }
}
