package com.exileclient.client.ui.elements.fade.types;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class CosineFade extends FloatFade {

    public CosineFade(long duration) {
        super(duration, 0F);
    }

    @Override
    protected float getValue() {
        float value = super.getValue();
        float f2 = value * 2F - 1F;
        return (float) (Math.cos((double) f2 * Math.PI) + 1.0) / 2F;
    }

}
