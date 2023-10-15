package org.nhnacademy.minju.server.problem4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 서버 소켓은 포트를 통해 대기 상태에서 클라이언트가 연결되면 통신을 위한 소켓을 생성함으로써
 * 기본 기능을 완료하고 연결되어 있는 클라이언트가 끊어진 후 accept() 함수를 통해 다시 할 수 있다.
 * 이 과정을 스레드를 통해 분리해 보도록 하자.
 * 서버 소켓은 대기 상태에서 클라이언트 연결이 이루어 지면 이를 처리하기 위한 스레드를 생성하여
 * 생성된 소켓을 넘겨 주고 서버 소켓은 다시 새로운 연결을 기다리도록 한다.
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
                Thread thread = new Thread(() -> {
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
                        try {
                            socket.close();
                        } catch (IOException e) {
                            System.out.println("in thread");
                        }
                    }
                });
                thread.start();
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