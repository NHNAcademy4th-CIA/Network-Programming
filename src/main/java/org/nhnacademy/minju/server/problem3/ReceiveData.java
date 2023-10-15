package org.nhnacademy.minju.server.problem3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 서버 소켓은 accept()를 통해 대기
 * 클라이언트가 접속하면 accept() 함수를 통해 반환된 소켓을 이용해 통신
 * 클라이언트의 연결이 끊어지거나 끊나면 해당 소켓을 닫고
 * 서버 소켓은 다시 accept()를 통해 대기
 */
public class ReceiveData {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        int port = 1234;

        try {
            serverSocket = new ServerSocket(port);

            System.out.println("서버 소켓이 생성되었습니다.");
            while (!Thread.interrupted()) {
                System.out.println("클라이언트 연결을 기다립니다.");
                Socket socket = serverSocket.accept();
                try {
                    System.out.println("클라이언트가 연결되었습니다.");

                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line); // receive data
                    }
                } catch (IOException ignore) {
                    System.out.println("socket error : " + ignore.getMessage());
                } finally {
                    // 통신 소켓을 닫는다
                    socket.close();
                }
            }
        } catch (IOException e) {
            System.out.println("지정된 포트[ " + port + " ]가 이미 사용중입니다.");
        } finally {
            // 서버 소켓을 닫는다
            try {
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}