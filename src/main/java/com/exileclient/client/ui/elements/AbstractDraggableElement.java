package com.exileclient.client.ui.elements;

import lombok.Setter;
import org.lwjgl.input.Mouse;
import sun.javafx.geom.Vec2d;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public abstract class AbstractDraggableElement extends AbstractElement {

    private final Vec2d position = new Vec2d();
    @Setter private boolean dragging;

    /**
     * When you drag the element, this is what happens.
     */
    protected void handleDrag(float x, float y) {
        if (this.dragging) {
            if (!Mouse.isButtonDown(0)) {
                this.setDragging(false);
                return;
            }

            double xPos = (double) x - this.position.x;
            double yPos = (double) y - this.position.y;
            this.setSize((float) xPos, (float) yPos, this.width, this.height);
        }
    }

    /**
     * Sets the position of the element.
     */
    protected void setPosition(float mouseX, float mouseY) {
        this.position.set(mouseX - this.xPosition, mouseY - this.yPosition);
        this.setDragging(true);
    }

}
