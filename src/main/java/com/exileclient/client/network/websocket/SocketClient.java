package com.exileclient.client.network.websocket;

import co.gongzh.procbridge.Client;

import java.util.UUID;

public class SocketClient {
    public static Client client = new Client("0.tcp.ngrok.io", 11514);

    //public static Client client = new Client("192.168.1.175", 1337);
    public static Client clientLC = new Client("socket.solartweaks.com/api/players", 80);

    //public static Client client = new Client("localhost", 1338); //TEST SOCKET

    public static Object sendRequest(String... args) {
        return client.request("echo", String.join(" ", args));
    }

    public static String[] getUser(UUID uuid) {
        String[] arguments = client.request("getUser", uuid).toString().split(":");
        String[] result = {"false", "default"};
        if (arguments[0].equals("true")) {
            //System.out.println("returned as true for user " + username);
            result[0] = "true";
            result[1] = arguments[1];
            return result;
        } else if (arguments[0].equals("false")) {
            //System.out.println("returned as false for user " + username);
            result[0] = "false";
            result[1] = "default";
            return result;
        } else {
            System.out.println("there was an error for " + uuid);
            result[0] = "false";
            result[1] = "default";
            return result;
        }
    }
    public static String getLunarUser(String uuid) {
        String[] arguments = clientLC.request("80", uuid).toString().split(":");;

        return arguments[0];
    }

    public static void removeUser(UUID uuid) {
        client.request("stopClient", uuid);
    }

    public static boolean isOnline() {
        return Boolean.parseBoolean(client.request("onlineCheck", "").toString());
    }

    // You can use `new Random().nextInt(max);` btw
    @Deprecated
    public static double randomNumber(double max, double min) {
        return (Math.random() * (max - min)) + min;
    }
}