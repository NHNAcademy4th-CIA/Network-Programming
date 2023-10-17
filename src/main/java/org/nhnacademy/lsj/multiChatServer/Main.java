package org.nhnacademy.lsj.multiChatServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 다중 챗팅 서버를 구성해 보자.
 * 다중 챗팅 서버에는 둘 이상의 클리이언트 접속 가능하다.
 * 클리이언트에서 보낸 메시지는 모든 클라이언트들에게 전달된다.
 */
public class Main {


    //public static List<PrintWriter> printWriterList = new ArrayList<>();


    public static void main(String[] args) {

        ServerSocket server;

        int number = 1;

        try {
            server = new ServerSocket(32007);


            while (true) {

                Socket connection = server.accept();

                ClientHandler clientHandler = new ClientHandler(connection, number);

                number++;
                clientHandler.start();

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

}
