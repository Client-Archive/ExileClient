package com.exileclient.client.controller.impl.socket;

import com.exileclient.client.controller.Controller;
import com.exileclient.client.network.websocket.SocketClient;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class SocketController extends Controller {

    public ArrayList<String> onClient = new ArrayList<>();
    public ArrayList<String> onClientRoles = new ArrayList<>();
    public ArrayList<String> checkedID = new ArrayList<>();

    public boolean hasSentServerReq;

    private void attemptConnection() {
        if (Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().theWorld != null) {
            if (!hasSentServerReq) {
                System.out.println(SocketClient.client.request("start_client", Minecraft.getMinecraft().thePlayer.getGameProfile().getId() + ":true"));
                hasSentServerReq = true;
            }
        }
    }

    public void sendServerRequest(String method, String payload) {
        hasSentServerReq = false;
        System.out.println(SocketClient.client.request(method, payload));
    }

    // Done to prevent too many socket calls, causing a crash.
    public String[] checkID(UUID id) {
        int i = 0;
        String[] result = {"false", "default"};
        if (checkedID.contains(id.toString())) return result;
        /*for(String userID : onClient) {
            System.out.println(i);
            //System.out.println(userID);
            //System.out.println(id.toString());
            if(userID == id.toString()) {
                System.out.println(id.toString());
                result[0] = "true";
                result[1] = onClientRoles.get(i);
                return result;
            } else {
                i++;
            }
        }/**/
        //if(onClient.contains(id.toString())) return true;
        if (!onClient.contains(id.toString())) {
            result = SocketClient.getUser(id);
            if (Boolean.parseBoolean(result[0])) {
                onClient.add(id.toString());
                onClientRoles.add(result[1]);
                //System.out.println(onClient.toString());
                System.out.println("Added " + id);
                return result;
            }
        } else {
            //result = SocketClient.getUser(id);
            result[0] = "true";
            if (id.toString().equalsIgnoreCase("642e94e9-35fa-4543-b805-11aad1092545")) {
                result[1] = "owner";
            } else if (id.toString().equalsIgnoreCase("8623cf2b-bd42-4eb2-9891-9ac9b62204c2")) {
                result[1] = "boahnenj";
            } else result[1] = "default";
            return result;
        }
        checkedID.add(id.toString());
        return result;
    }

    @Override
    public void onLoad() {

    }
}
