package org.nhnacademy.lsj.echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class Client extends Thread {

    private String host;
    private int port;

    private Socket socket;


    Client(String host, int port) {
        this.host = host;
        this.port = port;
        socket = null;
    }


    @Override
    public void run() {

        try {
            socket = new Socket(host, port);

            System.out.println("서버 연결");


            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));


            String line;

            while (!(line = reader.readLine()).equals("Close")) {
                pw.println(line);
                pw.flush();

                String reponse = bf.readLine();

                System.out.println("수신 " + reponse);

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("프로그램 종료");
        }


    }
}