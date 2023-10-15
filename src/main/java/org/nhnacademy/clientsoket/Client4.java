package org.nhnacademy.clientsoket;

import java.io.*;
import java.net.Socket;

public class Client4 extends Thread {
    String host;
    int port;
    Socket socket;

    public Client4(String host, int port) {
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            OutputStream output = socket.getOutputStream();
            while ((line = reader.readLine()) != null) {
                // 입력 문자열 전송
                if (line.equals("exit")) {
                    break;
                }

                output.write(line.getBytes());
                output.write("\n".getBytes());
                output.flush();
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
        Client4 client = new Client4("localhost", 222);

        client.start();
        try {
            client.join();
        } catch (InterruptedException ignore) {
            System.out.println(ignore.toString());
        }
    }
}
