package org.nhnacademy.lsj.multiChatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends Thread {


    private static List<PrintWriter> printWriterList = new ArrayList<>();

    private Socket connection;

    private int id;

    ClientHandler(Socket connection, int id) {
        this.connection = connection;
        this.id = id;

        try {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
            printWriterList.add(pw);
            pw.println("당신의 ID는 " + id + " 입니다.");
            pw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }


    }

    @Override
    public void run() {


        try (BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        ) {
            String line;

            boolean flag = true;

            while ((line = bf.readLine()) != null) {

                System.out.println(line);

                if (!flag) {

                    if (line.startsWith("@")) {
                        sendMessageByTarget(line);
                    } else {
                        sendMessage(line);
                    }
                    flag = true;

                } else {
                    flag = false;


                }

            }

            System.out.println("클라이언트 연결 종료");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }


    private void sendMessageByTarget(String message) {

        int targetIndex = 0;

        int index = message.indexOf(" ");

        String targetMessage = message;

        if (index != -1) {

            String number = targetMessage.substring(1, index);
            try {

                targetIndex = Integer.parseInt(number); // target2 :문자 형식이 아니면 전부 치우기  , 또 숫자로된 유저 id를 불러야 함
                targetMessage = targetMessage.substring(index + 1);

                if (targetIndex >= 0 && targetIndex < printWriterList.size()) {
                    printWriterList.get(id).println(targetMessage);
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("id : " + id + " 사용자 잘못된 명령어 양식 제출");

                printWriterList.get(id).println("잘못된 양식입니다 -> [@상대방ID번호 메시지] 양식으로 입력해 주세요");
                printWriterList.get(id).flush();
                return;
            }

        }

        sendMessage(message);
    }

    private void sendMessage(String message) {

        for (int i = 0; i < printWriterList.size(); i++) {
            if (id == i) {
                continue;
            }

            printWriterList.get(id).println(message);
            printWriterList.get(id).flush();

        }
    }


}