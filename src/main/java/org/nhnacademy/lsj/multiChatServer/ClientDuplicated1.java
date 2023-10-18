package org.nhnacademy.lsj.multiChatServer;

import java.io.IOException;
import java.net.Socket;


/**
 * 서버와 연결되는 클라이언트 . 서버로 데이터를 송신하는 역할과 , 데이터를 수신하는 역할이 분리되어 Multi Thread로 구동됨.
 */
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