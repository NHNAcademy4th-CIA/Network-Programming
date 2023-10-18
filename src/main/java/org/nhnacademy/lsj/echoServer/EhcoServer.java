package org.nhnacademy.lsj.echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 클라이언트에게 받은 입력을 되돌려주는 에코서버 .
 */
class EhcoServer extends Thread {

    private ServerSocket socket;

    private int port;

    EhcoServer(int port) {
        this.port = port;
    }

    /**
     * bf.readLine()으로 클라이언트의 입력을 받아냄 , 그 입력을 다시 클라이언트에게 보냄으로써 에코서버 구현.
     */
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
