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

    private int number;

    ClientHandler(Socket connection, int number) {
        this.connection = connection;
        this.number = number;
    }

    @Override
    public void run() {


        try (BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()))
        ) {

            printWriterList.add(pw);

            String line;

            boolean flag = true;

            while ((line = bf.readLine()) != null) {

                System.out.println(line);

                if (!flag) {
                    sendEachOther(line);
                    flag = true;
                } else {
                    flag = false;
                }

            }

            System.out.println("클라이언트 연결 종료");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendEachOther(String message) {

        System.out.println(number + " ㅋㅋ");

        for (int i = 0; i < printWriterList.size(); i++) {

            if (number == i + 1) {
                continue;
            }
            printWriterList.get(i).println(message);
            printWriterList.get(i).flush();
        }


    }

}


