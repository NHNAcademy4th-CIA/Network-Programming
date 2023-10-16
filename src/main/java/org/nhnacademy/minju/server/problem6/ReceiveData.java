package org.nhnacademy.minju.server.problem6;

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
 * 메시지는 기본적으로 전체에게 전송된다.
 * @target 을 통해 특정한 클라이언트를 지정할 경우, 해당 클라이언트에게만 전달된다.
 */
public class ReceiveData extends Thread {

    private Socket socket;
    private String name;
    private BufferedWriter writer;
    private List<ReceiveData> serverList;

    public ReceiveData(Socket socket, List<ReceiveData> serverList) {
        this.socket = socket;
        this.serverList = serverList;
        serverList.add(this);
    }

    private void chatHandler(String line) {
        // list에 line을 뿌린다
        String targetName;
        if (line.startsWith("@")) {
            int splitIndex = line.indexOf(" ");
            try {
                targetName = line.substring(1, splitIndex);
                loopServer(line.substring(splitIndex + 1), targetName);
            } catch (StringIndexOutOfBoundsException e) {
                send("잘못된 입력입니다.");
            }
        } else {
            for (ReceiveData server : serverList) {
                server.send(line);
                System.out.println(server);
            }
        }
    }

    private void loopServer(String line, String targetName) {
        boolean nameExists = false;
        for (ReceiveData server : serverList) {
            if (server.name.equals(targetName)) {
                server.send(line);
                System.out.println(server);
                nameExists = true;
            }
        }
        if (!nameExists) {
            send("입력한 닉네임은 존재하지 않습니다.");
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

            chatHandler("닉네임을 입력해주세요.");
            String name = reader.readLine();
            this.name = name;

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