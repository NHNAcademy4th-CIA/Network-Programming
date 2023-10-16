package org.nhnacademy.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server4 {
    public static class Server extends Thread{
        private ServerSocket serverSocket;

        public Server(int port)throws IOException{
            this.serverSocket = new ServerSocket(port);
        }
        @Override
        public void run(){
            try{

            }catch (IOException e)
            {

            }
        }
        public static ServerSocket getServerSocket(){

        }

    }
    public static void main(String[] args) {
        int port = 221;
        try {
            while(!Thread.interrupted()) {

                System.out.println("서버 소켓이 생성되어 클라이언트 접속을 기다린다");
                Socket socket = serverSocket.accept();

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        writer.write(line);
                        writer.newLine();
                        writer.flush();
                    }
                } catch (IOException ignore) {
                } finally {
                    socket.close();
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("지정된 포트[ " + port + " ]가 이미 사용중입니다.");
        }

    }
}