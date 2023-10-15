package org.nhnacademy.clientsoket;

import java.io.*;
import java.net.Socket;

public class Client5 extends Thread {
    String host;
    int port;
    Socket socket;

    public Client5(String host, int port) {
        this.host = host;
        this.port = port;
        this.socket = null;
    }

    @Override
    public void run() {
        try {
            System.out.println(host+port);
            socket= new Socket(host,port);
            System.out.println("서버에 연결 되었습니다.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            byte[] buffer = new byte[2048];
            while ((line = reader.readLine()) != null) {
                // 입력 문자열 전송
                if (line.equals("exit")) {
                    break;
                }

                System.out.println(line);
            }

            System.out.println("서버와 연결이 끊어졌습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        Client5 client = new Client5("localhost", 222);

        client.start();
        try {
            client.join();
        } catch (InterruptedException ignore) {
            System.out.println(ignore.toString());
        }
    }
}
