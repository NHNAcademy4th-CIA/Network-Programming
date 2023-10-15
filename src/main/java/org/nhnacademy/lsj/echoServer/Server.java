package org.nhnacademy.lsj.echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) {

        ServerSocket serverSocket;

        Socket connection;

        try {

            serverSocket = new ServerSocket(32007);

            while (true) {
                connection = serverSocket.accept();
                sendData(connection);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void sendData(Socket connection) {


        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
             BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

            System.out.println("서버 연결 완료");
            System.out.println("연결된 원격 호스트의 정보 : " + connection.getRemoteSocketAddress().toString());

            String str = "";

            while (!(str = br.readLine()).equals("Close")) {
                pw.println("Server가 받은 Data Echo : " + str); // Client로 받은 Data 보내기
                pw.flush();
            }


            pw.println("Socket Close");
            pw.flush();
            connection.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
