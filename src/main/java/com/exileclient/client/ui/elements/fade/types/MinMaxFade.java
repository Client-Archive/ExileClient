package com.exileclient.client.ui.elements.fade.types;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class MinMaxFade extends FloatFade {

    public MinMaxFade(long l) {
        super(l);
    }

    @Override
    protected float getValue() {
        float value = super.getValue();
        return (double) value < 0.5 ? 2F * value * value : -1F + (4F - 2F * value) * value;
    }

}
