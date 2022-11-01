package com.exileclient.client.util.layout.gui;
/*
-----------------------------
    AccountManager.java
      made by Savrien
       @ExileClient
-----------------------------
*/

import com.exileclient.client.ExileClient;
import com.exileclient.client.util.Extra;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class AccountManager extends GuiScreen {

    private GuiTextField email;
    private GuiTextField password;

    private String loginPassword = "";

    @Override
    public void initGui() {
        ExileClient.getInstance().getDiscordRP().update("Idle", "Switching Accounts."); //[OPTIONAL] DiscordRPC
        // Initialize the email input box
        email = new GuiTextField(5456, fontRendererObj, this.width / 2 - 100, this.height / 2 + 30, 200, 20);
        email.setFocused(true);
        email.setText("");
        // Initialize the password input box
        password = new GuiTextField(5456, fontRendererObj, this.width / 2 - 100, this.height / 2 + 60, 200, 20);
        password.setFocused(false);
        password.setText("");
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        email.textboxKeyTyped(typedChar, keyCode);
        password.textboxKeyTyped(typedChar, keyCode);
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null); // Exit back to main menu if ESC is pressed
        } else if(keyCode == Keyboard.KEY_TAB) {
            // If tab is pressed, swap between the two text boxes
            email.setFocused(!email.isFocused());
            password.setFocused(!password.isFocused());
        }
        if(keyCode == Keyboard.KEY_RETURN && password.isFocused()) {
            // If password is focused and enter is pressed, attempt a login.
            // This is done this way hoping the user does not press enter with the email box empty.
            Extra.login(email.getText(), loginPassword);
            // After a login, send them back to the title screen
            mc.displayGuiScreen(null);
        }
        if(keyCode == Keyboard.KEY_RETURN && email.isFocused()) {
            email.setFocused(!email.isFocused());
            password.setFocused(!password.isFocused());
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Draw the background image
        mc.getTextureManager().bindTexture(new ResourceLocation("com/MainMenu.png")); // Replace Exile/MainMenu.png with the path to your file.
        this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
        this.drawRect(0, 0, this.width, this.height, 0x000);
        this.drawCenteredString(fontRendererObj, "Account Switcher", this.width / 2, this.height / 2 + 10, -1);
        // Draw the text boxes
        email.drawTextBox();
        password.drawTextBox();
    }

    @Override
    public void updateScreen() {
        // Update the cursor position every screen update
        email.updateCursorCounter();
        password.updateCursorCounter();
        // Password censorship
        if(!(password.getText().length() == 0)) {
            if (password.getText().length() > loginPassword.length()) {
                // If the length of the contents of the password box becomes greater than the
                // length of the current password, we update the password by adding the contents
                // of the password box, minus the * characters, onto the existing password string
                loginPassword += password.getText().replaceAll("\\*", "");
                // Set the text back to all * chars, previous password characters will never be shown.
                password.setText(new String((new char[loginPassword.length()])).replace("\0", "*"));
            } else if (password.getText().length() < loginPassword.length()) {
                // If the length of the contents of the password box becomes less than the
                // length of the current password, we simply remove the last letter off of
                // the current password string
                loginPassword = loginPassword.substring(0, loginPassword.length() -1);
            }
        } else {
            // If the text box is empty, we simply set the password string to nothing.
            loginPassword = "";
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        // You can add extra stuff here
    }

}