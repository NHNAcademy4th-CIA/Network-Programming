package org.nhnacademy.lsj.problem;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 연습 4. 소켓을 이용해 데이터를 전송해 보자.
 * 소켓을 이용해 서버에 접속하였다면, 해당 소켓을 이용해 데이터를 전송해 보자.
 * <p>
 * 표준 입력으로 받은 문자열 전송.
 * quit를 입력 받으면 종료.
 * 소켓에서는 데이터를 보내기 위한 OutputStream 제공.
 * 서버에서는 받은 데이터를 출력하기 위한 응용 프로그램이 필요.
 * 간단하게 텍스트를 보내거나 받을 수 있는 nc를 이용.
 */
public class Problem4 {


    public static void main(String[] args) {


        ClientForDataSend client = new ClientForDataSend("localhost", 32007);

        client.start();

    }

}


class ClientForDataSend extends Thread {

    private String host;
    private int port;
    private Socket socket;


    ClientForDataSend(String host, int port) {
        this.host = host;
        this.port = port;
        socket = null;
    }


    public void run() {


        try (Scanner sc = new Scanner(System.in)) {
            socket = new Socket(host, port);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.println("서버 연결");


            String line;



            while (!(line = sc.nextLine()).equals("Close")) {
                pw.println(line);
                pw.flush();
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("프로그램 종료");
        }

    }


}
