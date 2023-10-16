package org.nhnacademy.lsj.echoChatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientDuplicated {

    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("localhost", 32007); // 서버랑 연결할 소켓

        Scanner sc = new Scanner(System.in);

        String str = "";

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        while (!(str = sc.nextLine()).equals("Close")) { // 클라이언트의 입력 받음
            pw.println(str); // 서버로 보내기
            pw.flush(); // 서버로 클라이언트 입력 보내기
            System.out.println(bf.readLine());
        }


    }

}
