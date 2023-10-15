package org.nhnacademy.minju.server.problem2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 클라이언트로부터 받은 데이터를 다시 클라이언트로 전송한다
 */
public class ReceiveData {
    public static void main(String[] args) {
        int port = 1234;
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("서버 소켓이 생성되어 클라이언트 접속을 기다린다");
            Socket socket = serverSocket.accept();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    writer.write(line);
                    writer.newLine();
                    writer.flush();
                }
            } catch (IOException ignore) {
            } finally {
                socket.close();
            }

            serverSocket.close();
        } catch (IOException e) {
            System.out.println("지정된 포트[ " + port + " ]가 이미 사용중입니다.");
        }

    }
}
