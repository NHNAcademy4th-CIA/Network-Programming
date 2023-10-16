package org.nhnacademy.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server4 {
    public static class Server  extends Thread{
        private Socket socket;
        public Server(Socket socket){
            this.socket=socket;
        }
        @Override
        public void run() {
            while (!Thread.interrupted()) {

                System.out.println("서버 소켓이 생성되어 클라이언트 접속을 기다린다");
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        writer.write(line);
                        writer.newLine();
                        writer.flush();
                        if(line.equals("exit"))
                        {
                            socket.close();
                        }
                    }
                    socket.close();
                } catch (IOException ignore) {
                    System.out.println(ignore);
                }
            }
        }
    }

    public static void main(String[] args) {
        int port = 221;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
              Server server = new Server(socket);
              server.start();
            }
        } catch (IOException e) {
            System.out.println("지정된 포트[ " + port + " ]가 이미 사용중입니다.");
        }

    }

}