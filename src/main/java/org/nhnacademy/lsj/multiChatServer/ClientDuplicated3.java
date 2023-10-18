package org.nhnacademy.lsj.multiChatServer;

import java.io.IOException;
import java.net.Socket;

class ClientDuplicated3 extends Thread {

    private String host;
    private int port;

    private Socket socket;


    ClientDuplicated3(String host, int port) {
        this.host = host;
        this.port = port;
        socket = null;
    }


    @Override
    public void run() {

        try {
            socket = new Socket(host, port);

            System.out.println("서버 연결");

            MultiChatSend multiChatSend = new MultiChatSend(socket);
            MultiChatReceive multiChatReceive = new MultiChatReceive(socket);

            multiChatReceive.start();
            multiChatSend.start();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }


    public static void main(String[] args) {

        ClientDuplicated3 clientDuplicated3 = new ClientDuplicated3("localhost", 32007);

        clientDuplicated3.start();

    }

}