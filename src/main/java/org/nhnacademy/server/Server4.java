package org.nhnacademy.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server4 {
    public static class Server extends Thread {
        private ServerSocket serverSocket;

        public Server(ServerSocket serverSocket){
            this.serverSocket=serverSocket;
         }

        @Override
        public void run() {
            while (!Thread.interrupted()) {

                System.out.println("서버 소켓이 생성되어 클라이언트 접속을 기다린다");
                try {
                    Socket socket = serverSocket.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        writer.write(line);
                        writer.newLine();
                        writer.flush();
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
            Server server[] = new Server[2];
            for (int i=0;i<2;i++) {
                server[i]=new Server(serverSocket);
                server[i].start();
            }
        } catch (IOException e) {
            System.out.println("지정된 포트[ " + port + " ]가 이미 사용중입니다.");
        }

    }

}