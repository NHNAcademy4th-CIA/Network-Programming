package org.nhnacademy.lsj.echoServerVersion2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class MultiConnectionEchoServer extends Thread {

    private Socket connection;

    MultiConnectionEchoServer(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {


        try {

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
        }
    }


}
