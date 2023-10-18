package org.nhnacademy.lsj.multiChatServer;

import java.io.IOException;
import java.net.Socket;


class ClientDuplicated1 extends Thread {
    private String host;
    private int port;

    private Socket socket;


    ClientDuplicated1(String host, int port) {
        this.host = host;
        this.port = port;
        socket = null;
    }


    @Override
    public void run() {

        System.out.println("서버 연결");

        try {

            socket = new Socket(host, port);


            MultiChatSend multiChatSend = new MultiChatSend(socket);
            MultiChatReceive multiChatReceive = new MultiChatReceive(socket);

            multiChatReceive.start();
            multiChatSend.start();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }


    public static void main(String[] args) {

        ClientDuplicated1 clientDuplicated1 = new ClientDuplicated1("localhost", 32007);


        clientDuplicated1.start();

    }

}