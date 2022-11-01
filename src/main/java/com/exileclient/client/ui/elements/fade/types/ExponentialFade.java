package com.exileclient.client.ui.elements.fade.types;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class ExponentialFade extends FloatFade {

    public ExponentialFade(long duration) {
        super(duration);
    }

    @Override
    public float getValue() {
        float value = super.getValue();
        return (float) Math.pow(value * (2.0F - value), 1.0);
    }

}
