package org.nhnacademy.lsj.multiChatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiChatReceive extends Thread {


    private Socket socket;


    MultiChatReceive(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {

            String message = "";


            while (!(message = bf.readLine()).equals("Close")) {
                System.out.println("수신 : " + message);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}
