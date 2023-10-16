package org.nhnacademy.lsj.echoChatServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {


    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(32007);
            ServerThread serverThread = new ServerThread(serverSocket);
            serverThread.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }

}
