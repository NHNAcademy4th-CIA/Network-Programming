package org.nhnacademy.minju.server.problem5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 다중 챗팅 서버에는 둘 이상의 클리이언트 접속 가능하다.
 * 클리이언트에서 보낸 메시지는 모든 클라이언트들에게 전달된다.
 */
public class ReceiveData extends Thread {

    private Socket socket;
    private BufferedWriter writer;
    private List<ReceiveData> serverList;

    public ReceiveData(Socket socket, List<ReceiveData> serverList) {
        this.socket = socket;
        this.serverList = serverList;
        serverList.add(this);
    }

    private void chatHandler(String line) {
        // list에 line을 뿌린다
        for (ReceiveData server : serverList) {
            server.send(line);
            System.out.println(server);
        }
    }

    private void send(String line) {
        try {
            writer.write(line);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("error handling chat : " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter newWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            this.writer = newWriter;
            System.out.println("클라이언트가 연결되었습니다.");

            String line; // data received
            while ((line = reader.readLine()) != null) {
                System.out.println("input : " + line);
                chatHandler(line);
            }
        } catch (IOException ignore) {
            System.out.println("socket error : " + ignore.getMessage());
        } finally {
            // 통신 소켓을 닫는다
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("in thread");
            }
        }
    }

    public static void main(String[] args) {
        List<ReceiveData> serverList = new ArrayList<>();
        ServerSocket serverSocket = null;
        int port = 1234;

        try {
            serverSocket = new ServerSocket(port);

            System.out.println("서버 소켓이 생성되었습니다.");
            while (!Thread.interrupted()) {
                System.out.println("클라이언트 연결을 기다립니다.");

                Socket socket = serverSocket.accept();
                ReceiveData server = new ReceiveData(socket, serverList);
                server.start();
            }
        } catch (IOException e) {
            System.out.println("지정된 포트[ " + port + " ]가 이미 사용중입니다.");
        } finally {
            // 서버 소켓을 닫는다
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("server socket error : " + e.getMessage());
                }
            }
        }
    }
}