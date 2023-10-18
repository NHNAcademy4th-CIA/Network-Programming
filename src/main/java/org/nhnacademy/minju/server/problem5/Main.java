package org.nhnacademy.minju.server.problem5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        int port = 1234;

        try {
            serverSocket = new ServerSocket(port);

            System.out.println("서버 소켓이 생성되었습니다.");
            while (!Thread.interrupted()) {
                System.out.println("클라이언트 연결을 기다립니다.");

                Socket socket = serverSocket.accept();
                ReceiveData server = new ReceiveData(socket);
                server.start();
            }
        } catch (IOException e) {
            System.out.println("지정된 포트[ " + port + " ]가 이미 사용중입니다.");
        } finally {
            // 서버 소켓을 닫는다
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("server socket error : " + e.getMessage());
                }
            }
        }
    }

}
