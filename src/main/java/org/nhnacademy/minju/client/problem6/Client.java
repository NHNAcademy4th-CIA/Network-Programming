package org.nhnacademy.minju.client.problem6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            System.out.println("서버에 연결 되었습니다.");

            String line;
            // 표준 입력에서 문자열 받기
            while ((line = reader.readLine()) != null) {
                // 입력 문자열 전송
                pw.println(line);
                // 전송
                pw.flush();
                // 수신
                String input = inputReader.readLine();
                // 표준 출력으로 출력
                System.out.println("수신 : " + input);
            }

            System.out.println("서버와 연결이 끊어졌습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("error : " + e.getMessage());
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