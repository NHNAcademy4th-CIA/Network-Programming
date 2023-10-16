package org.nhnacademy.lsj.echoChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {

    private ServerSocket serverSocket;

    public ServerThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {

        System.out.println("시작");

        try {
            while (true) {

                Socket connection = serverSocket.accept();
                System.out.println("연결된 원격 호스트 정보 : " + connection.getRemoteSocketAddress().toString());

                ClientThread clientThread= new ClientThread(connection);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }


}
