package org.nhnacademy.lsj.chatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket socket;


    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()))
        ) {

            // bf로 클라이언트 입력을 읽으면 ,  pw로 서버소켓한테 보내줘야 함

            String str = "";

            while (!(str = bf.readLine()).equals("Close")) { // 클라이언트의 입력 받음
                pw.println(str); // 서버로 보내기
                pw.flush(); // 서버로 클라이언트 입력 보내기
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
