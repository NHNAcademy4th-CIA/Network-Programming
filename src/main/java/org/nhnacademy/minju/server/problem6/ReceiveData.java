package org.nhnacademy.minju.server.problem6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 메시지는 기본적으로 전체에게 전송된다.
 *
 * @target 을 통해 특정한 클라이언트를 지정할 경우, 해당 클라이언트에게만 전달된다.
 */
public class ReceiveData extends Thread {

    private Socket socket;
    private String nickName;
    private BufferedWriter writer;
    private List<ReceiveData> serverList = new ArrayList<>();

    public ReceiveData(Socket socket) {
        this.socket = socket;
        serverList.add(this);
    }

    public String getNickName() {
        return nickName;
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
                if (this.nickName.equals(server.getNickName())) {
                    continue;
                }
                server.send(line);
                System.out.println(server.getNickName());
            }
        }
    }

    private void loopServer(String line, String targetName) {
        boolean nameExists = false;
        for (ReceiveData server : serverList) {
            if (server.getNickName().equals(targetName)) {
                server.send(line);
                System.out.println(server.getNickName());
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
            System.out.println("error handling chat : " + e.getMessage() + " in " + this.getNickName());
        }
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter newWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            this.writer = newWriter;
            System.out.println("클라이언트가 연결되었습니다.");

            send("닉네임을 입력해주세요.");
            this.nickName = reader.readLine();

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
                serverList.remove(this);
            } catch (IOException e) {
                System.out.println("in thread");
            }
        }
    }
}