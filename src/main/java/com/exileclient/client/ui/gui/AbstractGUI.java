package com.exileclient.client.ui.gui;

import com.exileclient.client.ui.elements.AbstractElement;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CheatBreaker/Noxiuam
 *
 * Half of it was redone to suit our needs the only thing that's cb is
 */
public abstract class AbstractGUI extends GuiScreen {
    @Getter public ScaledResolution scaledResolution;

    @Getter protected List<AbstractElement> elements;
    protected List<AbstractElement> eventButton;

    protected int lastMouseEvent = 0;

    @Getter private float scaledWidth;
    @Getter private float scaledHeight;

    @Override
    public void setWorldAndResolution(Minecraft mc, int width, int height) {
        this.mc = mc;
        this.fontRendererObj = mc.fontRendererObj;
        this.width = width;
        this.height = height;
        this.buttonList.clear();
        this.scaledResolution = new ScaledResolution(this.mc);

        float scale = this.getScaleFactor();
        this.scaledWidth = (float) width / scale;
        this.scaledHeight = (float) height / scale;

        this.initGui();
    }

    public void addElement(AbstractElement... elements) {
        this.elements.addAll(Arrays.asList(elements));
        this.initGui();
    }

    public void removeElement(AbstractElement... elements) {
        this.elements.removeAll(Arrays.asList(elements));
        this.initGui();
    }

    protected void addElements(AbstractElement... elements) {
        this.eventButton = new ArrayList<>();
        this.eventButton.addAll(Arrays.asList(elements));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        float var4 = this.getScaleFactor();
        GL11.glPushMatrix();
        GL11.glScalef(var4, var4, var4);
        this.drawMenu((float) mouseX / var4, (float) mouseY / var4);
        GL11.glPopMatrix();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        float var4 = this.getScaleFactor();
        this.mouseClicked((float) mouseX / var4, (float) mouseY / var4, mouseButton);
    }

    @Override
    protected void mouseReleased(int x, int y, int button) {
        float scale = this.getScaleFactor();
        this.mouseMovedOrUp((float) x / scale, (float) y / scale, button);
    }

    public abstract void drawMenu(float x, float y);

    protected abstract void mouseClicked(float x, float y, int button);

    public abstract void mouseMovedOrUp(float x, float y, int button);

    public float getScaleFactor() {
        return 1.0f / (this.scaledResolution.getScaleFactor() / 2.0f);
    }

    protected void closeElements() {
        this.elements.forEach(AbstractElement::handleClose);
    }

    protected void updateElements() {
        this.elements.forEach(AbstractElement::handleUpdate);
    }

    protected void handleElementKeyTyped(char c, int n) {
        for (AbstractElement element : this.elements) {
            element.onKeyTyped(c, n);
        }
    }

    protected void handleElementMouse() {
        this.elements.forEach(AbstractElement::handleMouse);
    }

    protected void drawElementHover(float x, float y, AbstractElement... elements) {
        List<AbstractElement> elementList = Arrays.asList(elements);
        for (AbstractElement element : this.elements) {
            if (elementList.contains(element)) continue;
            element.drawWhileHovering(x, y, this.mouseClicked(element, x, y));
        }
    }

    protected void onMouseMoved(float x, float y, int button) {
        for (AbstractElement element : elements) {
            if (element.isMouseInside(x, y)) {
                element.onMouseMove(x, y, button, this.mouseClicked(element, x, y));
            }
        }
    }

    protected boolean mouseClicked(AbstractElement element, float x, float y, AbstractElement... elements) {
        AbstractElement finalElement;
        List<AbstractElement> var5 = Arrays.asList(elements);
        boolean clicked = true;
        for (int var7 = this.elements.size() - 1; var7 >= 0 && (finalElement = this.elements.get(var7)) != element; --var7) {
            if (var5.contains(finalElement) || !finalElement.isMouseInside(x, y)) continue;
            clicked = false;
            break;
        }
        return clicked;
    }

    protected void setElements(AbstractElement... elements) {
        this.elements = new ArrayList<>();
        this.elements.addAll(Arrays.asList(elements));
        this.lastMouseEvent = this.elements.size();
    }

    public void setResolution(ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }
}

