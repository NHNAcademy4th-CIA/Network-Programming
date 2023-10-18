package org.nhnacademy.lsj.echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class EhcoServer extends Thread {

    private ServerSocket socket;

    private int port;

    EhcoServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {

            socket = new ServerSocket(port);

            Socket connection = socket.accept();
            System.out.println("클라이언트 연결");


            try (BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 PrintWriter pw = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()))
            ) {

                String line;

                while ((line = bf.readLine()) != null) {

                    System.out.println(line);
                    pw.println(line);
                    pw.flush();

                }

                System.out.println("클라이언트 연결 종료");

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("서버 종료");
        }
    }
}
