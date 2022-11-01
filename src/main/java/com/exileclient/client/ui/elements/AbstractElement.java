package com.exileclient.client.ui.elements;

import lombok.Getter;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public abstract class AbstractElement {

    @Getter protected float xPosition;
    @Getter protected float yPosition;
    @Getter protected float width;
    @Getter protected float height;

    public boolean isMouseInside(float mouseX, float mouseY) {
        return mouseX > this.xPosition
                && mouseX < this.xPosition + this.width
                && mouseY > this.yPosition
                && mouseY < this.yPosition + this.height;
    }

    public boolean drawWhileHovering(float mouseX, float mouseY, boolean hovering) {
        this.handleDraw(mouseX, mouseY, hovering);
        return this.isMouseInside(mouseX, mouseY);
    }

    public void setSize(float x, float y, float width, float height) {
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
    }

    public void handleUpdate() { }

    public void handleClose() { }

    public void onKeyTyped(char c, int n) { }

    public void handleMouse() { }

    public abstract void handleDraw(float x, float y, boolean isHovering);

    public boolean handleMouseClicked(float x, float y, int n, boolean isHovering) {
        return false;
    }

    public boolean onMouseMove(float x, float y, int button, boolean isHovering) {
        return false;
    }

    public boolean onMouseClick(float x, float y, int mouseButton) {
        return false;
    }

}

