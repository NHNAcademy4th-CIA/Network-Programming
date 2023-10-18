package org.nhnacademy.lsj.multiChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 다중 챗팅 서버를 구성해 보자.
 * 다중 챗팅 서버에는 둘 이상의 클리이언트 접속 가능하다.
 * 클리이언트에서 보낸 메시지는 모든 클라이언트들에게 전달된다.
 */
public class Main {


    /**
     * 클라이언트의 id는 자동으로 주어짐 , 서버는 연결이 기다리다 연결이되면 connection을 통해 클라이언트와 통신함.
     *
     * @param args CLA.
     */
    public static void main(String[] args) {

        ServerSocket server;

        int clientId = 0;

        try {
            server = new ServerSocket(32007);

            while (true) {

                Socket connection = server.accept();

                ClientHandler clientHandler = new ClientHandler(connection, clientId);

                System.out.println(
                        "클라이언트 연결 : " + connection.getInetAddress() + " " + connection.getLocalPort() + " id : " +
                                clientId);

                clientId++;
                clientHandler.start();

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

}
