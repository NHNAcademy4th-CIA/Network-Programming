package org.nhnacademy.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void main(String[] args) {
//        int port = 1234;

        int port = 7000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("포트 " + port + "는 사용 가능합니다.");
        } catch (IOException e) {
            System.out.println("포트 " + port + "는 이미 사용 중입니다.");
        }
    }
}