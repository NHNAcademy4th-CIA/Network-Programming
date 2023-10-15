package org.nhnacademy.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = serverSocket.accept();
            socket.getOutputStream().flush();
            socket.close();
        } catch (IOException e) {
            System.out.println("지정된 포트[ " + port + " ]가 이미 사용중입니다.");
        }
        System.out.println("정상 사용가능한 포트");
    }
}