//package net.minecraft;
//
//import com.exileclient.client.ExileClient;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.entity.EntityPlayerSP;
//import net.minecraft.client.settings.GameSettings;
//import net.minecraft.util.MovementInputFromOptions;
//
//import java.text.DecimalFormat;
//
//public class MinecraftMovementInputHelper extends MovementInputFromOptions {
//    public static boolean isToggleSprintAllowed;
//    public static boolean doubleTap;
//    public static boolean isSprinting = true;
//    public static boolean superSusBoolean = false;
//    public static boolean aSusBoolean = false;
//    private static long someTiming;
//    private static long timeSince;
//    private static boolean someToggleSneakBoolean;
//    private static boolean noxIsAFurry;
//    private static boolean isPlayerRiding;
//    private static boolean IIIlIIllllIIllllllIlIIIll;
//    private static boolean shouldBeSprinting;
//    public static String toggleSprintString = "";
//
//    public MinecraftMovementInputHelper(GameSettings gameSettings) {
//        super(gameSettings);
//    }
//
//    public static void lIIIIlIIllIIlIIlIIIlIIllI(Minecraft minecraft, MovementInputFromOptions movementInputFromOptions, EntityPlayerSP entityPlayerSP) {
//        movementInputFromOptions.moveStrafe = 0.0f;
//        movementInputFromOptions.moveForward = 0.0f;
//        GameSettings gameSettings = minecraft.gameSettings;
//        if (gameSettings.keyBindForward.isPressed()) {
//            movementInputFromOptions.moveForward += 1.0f;
//        }
//        if (gameSettings.keyBindBack.isPressed()) {
//            movementInputFromOptions.moveForward -= 1.0f;
//        }
//        if (gameSettings.keyBindLeft.isPressed()) {
//            movementInputFromOptions.moveStrafe += 1.0f;
//        }
//        if (gameSettings.keyBindRight.isPressed()) {
//            movementInputFromOptions.moveStrafe -= 1.0f;
//        }
//        if (entityPlayerSP.isRiding() && !IIIlIIllllIIllllllIlIIIll) {
//            IIIlIIllllIIllllllIlIIIll = true;
//            shouldBeSprinting = isSprinting;
//        } else if (IIIlIIllllIIllllllIlIIIll && !entityPlayerSP.isRiding()) {
//            IIIlIIllllIIllllllIlIIIll = false;
//            if (shouldBeSprinting && !isSprinting) {
//                isSprinting = true;
//                timeSince = System.currentTimeMillis();
//                noxIsAFurry = true;
//                superSusBoolean = false;
//            }
//        }
//        movementInputFromOptions.jump = gameSettings.keyBindJump.isPressed();
//        if (ModToggleSprint.toggleSneak && ExileClient.getInstance().getModuleManager().getToggleSprintMod().isEnabled()) {
//            if (gameSettings.keyBindSneak.isPressed() && !someToggleSneakBoolean) {
//                if (entityPlayerSP.isRiding() || entityPlayerSP.capabilities.isFlying) {
//                    movementInputFromOptions.sneak = true;
//                    isPlayerRiding = entityPlayerSP.isRiding();
//                } else {
//                    movementInputFromOptions.sneak = !movementInputFromOptions.sneak;
//                }
//                someTiming = System.currentTimeMillis();
//                someToggleSneakBoolean = true;
//            }
//            if (!gameSettings.keyBindSneak.isPressed() && someToggleSneakBoolean) {
//                if (entityPlayerSP.capabilities.isFlying || isPlayerRiding) {
//                    movementInputFromOptions.sneak = false;
//                } else if (System.currentTimeMillis() - someTiming > 300L) {
//                    movementInputFromOptions.sneak = false;
//                }
//                someToggleSneakBoolean = false;
//            }
//        } else {
//            movementInputFromOptions.sneak = gameSettings.keyBindSneak.isPressed();
//        }
//        if (movementInputFromOptions.sneak) {
//            movementInputFromOptions.moveStrafe = (float)((double)movementInputFromOptions.moveStrafe * ((double)1.7f * 0.17647058328542756));
//            movementInputFromOptions.moveForward = (float)((double)movementInputFromOptions.moveForward * (0.19999999999999998 * 1.5));
//        }
//        boolean bl = (float)entityPlayerSP.getFoodStats().getFoodLevel() > (float)6 || entityPlayerSP.capabilities.isFlying;
//        boolean bl2 = !movementInputFromOptions.sneak && !entityPlayerSP.capabilities.isFlying && bl;
//        isToggleSprintAllowed = !((Boolean) ModToggleSprint.toggleSprint);
//        doubleTap = ModToggleSprint.doubleTap;
//        if ((bl2 || isToggleSprintAllowed) && gameSettings.keyBindSprint.isPressed() && !noxIsAFurry && !entityPlayerSP.capabilities.isFlying && !isToggleSprintAllowed) {
//            isSprinting = !isSprinting;
//            timeSince = System.currentTimeMillis();
//            noxIsAFurry = true;
//            superSusBoolean = false;
//        }
//        if ((bl2 || isToggleSprintAllowed) && !gameSettings.keyBindSprint.isPressed() && noxIsAFurry) {
//            if (System.currentTimeMillis() - timeSince > 300L) {
//                superSusBoolean = true;
//            }
//            noxIsAFurry = false;
//        }
//        MinecraftMovementInputHelper.lIIIIlIIllIIlIIlIIIlIIllI(movementInputFromOptions, entityPlayerSP, gameSettings);
//    }
//
//    public void setSprintState(boolean bl, boolean bl2) {
//        isSprinting = bl;
//        aSusBoolean = bl2;
//    }
//
//    private static void lIIIIlIIllIIlIIlIIIlIIllI(MovementInputFromOptions movementInputFromOptions, EntityPlayerSP entityPlayerSP, GameSettings gameSettings) {
//        String string = "";
//        boolean flying = entityPlayerSP.capabilities.isFlying;
//        boolean riding = entityPlayerSP.isRiding();
//        boolean sneakHeld = gameSettings.keyBindSneak.isPressed();
//        boolean sprintHeld = gameSettings.keyBindSprint.isPressed();
//        if (flying) {
//            DecimalFormat decimalFormat = new DecimalFormat("#.00");
//            string = ModToggleSprint.flyBoost && sprintHeld && entityPlayerSP.capabilities.isCreativeMode ? string
//                    + ((String)ExileClient.getInstance().getModuleManager().getToggleSprintMod().flyBoostString.getValue())
//                    .replaceAll("%BOOST%", decimalFormat.format(ModToggleSprint.flyBoostAmount.getValue()))
//                    :
//                    string + ExileClient.getInstance().getModuleManager().getToggleSprintMod().flyString.getValue();
//        }
//        if (riding) {
//            string = string + ExileClient.getInstance().getModuleManager().getToggleSprintMod().ridingString.getValue();
//        }
//        if (movementInputFromOptions.sneak) {
//            string = flying ? ExileClient.getInstance().getModuleManager().getToggleSprintMod().decendString.getValue().toString() :
//                    (riding ? ExileClient.getInstance().getModuleManager().getToggleSprintMod().dismountString.getValue().toString() :
//                            (sneakHeld ? string + ExileClient.getInstance().getModuleManager().getToggleSprintMod().sneakHeldString.getValue() :
//                                    string + ExileClient.getInstance().getModuleManager().getToggleSprintMod().sneakToggledString.getValue()));
//        } else if (isSprinting && !flying && !riding) {
//            boolean bl5 = superSusBoolean || isToggleSprintAllowed || aSusBoolean;
//            string = sprintHeld ? string + ExileClient.getInstance().getModuleManager().getToggleSprintMod().sprintHeldString.getValue()
//                    :
//                    (bl5 ? string + ExileClient.getInstance().getModuleManager().getToggleSprintMod().sprintVanillaString.getValue()
//                            :
//                            string + ExileClient.getInstance().getModuleManager().getToggleSprintMod().sprintToggledString.getValue());
//        }
//        toggleSprintString = string;
//    }
//}
