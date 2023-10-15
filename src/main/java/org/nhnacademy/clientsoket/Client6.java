package org.nhnacademy.clientsoket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client6 extends Thread {
    String host;
    int port;
    Socket socket;

    public Client6(String host, int port) {
        this.host = host;
        this.port = port;
        this.socket = null;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(host,port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream output = socket.getOutputStream();
            String line;
            while ((line = reader.readLine()) != null) {
                // 입력 문자열 전송
                if (line.equals("exit")) {
                    break;
                }

                System.out.println(line);
                output.write(line.getBytes());
                output.write("\n".getBytes());
                output.flush();


                }
            } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            System.out.println("서버와 연결이 끊어졌습니다.");
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }

    }

    public static void main(String[] args) throws IOException {
        Client6 client = new Client6("localhost", 221);

        client.start();
        try {
            client.join();
        } catch (InterruptedException ignore) {
        }
    }
}
