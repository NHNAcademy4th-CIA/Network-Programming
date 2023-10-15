package org.nhnacademy.minju.client.problem4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 표준 입력으로 받은 문자열 전송
 * - quit를 입력 받으면 종료
 * 소켓에서는 데이터를 보내기 위한 OutputStream 제공
 * 서버에서는 받은 데이터를 출력하기 위한 응용 프로그램이 필요
 * - 간단하게 텍스트를 보내거나 받을 수 있는 nc를 이용
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            System.out.println("서버에 연결 되었습니다.");

            String line;
            while ((line = reader.readLine()) != null) {
                // 입력 문자열 전송
                if (line.equals("quit")) {
                    socket.close();
                }
                writer.println(line);
                writer.flush();
            }

            System.out.println("서버와 연결이 끊어졌습니다.");
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
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