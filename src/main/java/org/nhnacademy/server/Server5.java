package org.nhnacademy.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server5 extends Thread{
    private Socket socket;
    private BufferedWriter writer;
    private List<Server5> serverList;

    public Server5(Socket socket, List<Server5> serverList) {
        this.socket = socket;
        this.serverList = serverList;
        serverList.add(this);
    }

    private void chatHandler(String line) {
        for (Server5 server : serverList) {
            try {
                writer.write(line);
                writer.newLine();
                writer.flush(); if(line.equals("exit"))
                {
                    socket.close();
                }

            } catch (IOException e) {
                System.out.println("error handling chat : " + e.getMessage());
            }
            System.out.println(server);
        }
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter newWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            this.writer = newWriter;
            System.out.println("클라이언트가 연결되었습니다.");

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("input : " + line);
                chatHandler(line);
            }
        } catch (IOException e) {
            System.out.println("socket error : " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("in thread");
            }
        }
    }

    public static void main(String[] args) {
        List<Server5> serverList = new ArrayList<>();
        ServerSocket serverSocket = null;
        int port = 221;

        try {
            serverSocket = new ServerSocket(port);

            System.out.println("서버 소켓이 생성되었습니다.");
            while (!Thread.interrupted()) {
                System.out.println("클라이언트 연결을 기다립니다.");
                Socket socket = serverSocket.accept();
                Server5 server = new Server5(socket, serverList);
                server.start();
            }
        } catch (IOException e) {
            System.out.println("지정된 포트[ " + port + " ]가 이미 사용중입니다.");
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("소켓에 문제가 있습니다 : " + e.getMessage());
                }
            }
        }
    }
}

