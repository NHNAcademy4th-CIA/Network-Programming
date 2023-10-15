package org.nhnacademy.clientsoket;

import java.io.IOException;
import java.net.Socket;

import java.io.IOException;
import java.net.Socket;

public class Client3 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 7000;
        Socket socket = null;

        try {
            socket = new Socket(host, port);

            System.out.println("Local address : " + socket.getLocalAddress().getHostAddress());
            System.out.println("Local port : " + socket.getLocalPort());
            System.out.println("Remote address : " + socket.getInetAddress().getHostAddress());
            System.out.println("Remote port : " + socket.getPort());
        } catch(IOException ignore) {
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch(IOException ignore) {
                }
                socket = null;
            }
        }
    }
}