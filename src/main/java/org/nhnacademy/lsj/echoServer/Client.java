package org.nhnacademy.lsj.echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 에코서버와 연결되는 클라이언트.
 */
class Client extends Thread {

    private String host;
    private int port;

    private Socket socket;


    Client(String host, int port) {
        this.host = host;
        this.port = port;
        socket = null;
    }


    /**
     * 입력이 일어남 , 입력한걸 서버로 보낸후 서버로부터 데이터를 받아옴.
     */
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
