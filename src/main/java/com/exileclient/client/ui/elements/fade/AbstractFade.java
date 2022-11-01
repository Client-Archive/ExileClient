package com.exileclient.client.ui.elements.fade;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public abstract class AbstractFade {

    protected final float color;
    protected float amount;

    protected long time;
    protected long duration;
    protected long duration2;
    private long duration3;

    protected boolean running = true;
    private boolean loopAnimation;
    private boolean hovered;

    private int runTime = 1;
    private int runLength = 1;

    public AbstractFade(long duration, float color) {
        this.duration = duration;
        this.color = color;
    }

    protected abstract float getValue();

    public void startAnimation() {
        this.time = System.currentTimeMillis();
        this.running = true;
        this.duration3 = 0L;
    }

    public void startAnimationFromStartOrEnd(float f) {
        this.time = System.currentTimeMillis();
        this.duration3 = f == 0F ? 0L : (long) ((float) this.duration * (1F - f));
        this.running = true;
    }

    public void loopAnimation() {
        this.loopAnimation = true;
    }

    public boolean isTimeNotAtZero() {
        return this.time != 0L;
    }

    public boolean isOver() {
        return this.getTimeLeft() <= 0L && this.running;
    }

    public void reset() {
        this.time = 0L;
        this.runTime = 1;
    }

    public float inOutFade(boolean hovering) {
        if (hovering && !this.hovered) {
            this.hovered = true;
            this.startAnimationFromStartOrEnd(this.getFinalizedValue());
        } else if (this.hovered && !hovering) {
            this.hovered = false;
            this.startAnimationFromStartOrEnd(this.getFinalizedValue());
        }

        if (this.time == 0L) {
            return 0F;
        }

        float f = this.getFinalizedValue();
        return this.hovered ? f : 1F - f;
    }

    public boolean isZeroOrLess() {
        return this.time != 0L && this.getTimeLeft() > 0L;
    }

    private float getFinalizedValue() {
        if (this.time == 0L) {
            return 0F;
        }

        if (this.getTimeLeft() <= 0L) {
            return 1F;
        }

        return this.getValue();
    }

    public float getFadeAmount() {
        if (this.time == 0L) {
            return 0F;
        }

        if (this.isOver()) {
            if (this.loopAnimation || this.runLength >= 1 && this.runTime < this.runLength) {
                this.startAnimation();
                ++this.runTime;
            }
            return this.color;
        }

        if (this.running) {
            return this.getValue();
        }

        return this.amount;
    }

    public void stop() {
        this.running = false;
        this.amount = this.getValue();
        this.duration2 = System.currentTimeMillis() - this.time;
    }

    public void run() {
        this.time = System.currentTimeMillis() - this.duration2;
        this.running = true;
    }

    public long getMinimumDuration() {
        long l = this.running ? this.getTimeLeft() : System.currentTimeMillis() - this.duration2 + this.duration - System.currentTimeMillis();
        return Math.min(this.duration, Math.max(0L, l));
    }

    protected long getTimeLeft() {
        return this.time + this.duration - this.duration3 - System.currentTimeMillis();
    }

    public long getCorrectedDuration() {
        return this.duration - this.getMinimumDuration();
    }

    public long getDuration2() {
        return this.duration2;
    }

}

