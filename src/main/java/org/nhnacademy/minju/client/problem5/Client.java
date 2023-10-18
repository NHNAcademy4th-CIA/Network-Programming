package org.nhnacademy.minju.client.problem5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 소켓을 이용해 서버에 접속하였다면, 해당 소켓을 이용해 데이터를 받아 보자.
 * 소켓에서는 데이터를 받기 위한 InputStream 제공
 * 받은 문자열은 표준 출력을 통해 출력
 * 서버에서 연결 후 데이터 전송을 위한 프로그램이 필요
 *  - 간단하게 텍스트를 보내거나 받을 수 있는 nc를 이용
 */
public class Client extends Thread {
    String host;
    int port;
    Socket socket;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        this.socket = null;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(host, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("서버에 연결 되었습니다.");

            String line;
            while ((line = reader.readLine()) != null) {
                // 입력 문자열 전송
                System.out.println(line);
            }

            System.out.println("서버와 연결이 끊어졌습니다.");
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("socket closed");
                }
                socket = null;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1", 8080);

        client.start();
        try {
            client.join();
        } catch (InterruptedException ignore) {
        }
    }
}