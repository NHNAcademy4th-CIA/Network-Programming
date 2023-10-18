package org.nhnacademy.lsj.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * 연습 1. 소켓을 이용해 서버에 연결해 보자 . 서버는 nc를 이용.
 */
public class Problem1 {


    public static void main(String[] args) {

        ClientForServerConnection client = new ClientForServerConnection("localhost", 32007);

        client.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                if (line.equals("quit")) {
                    client.interrupt();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("시스템 종료");
        }


    }

}

class ClientForServerConnection extends Thread {

    private String host;
    private int port;

    private Socket socket;


    ClientForServerConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }


    @Override
    public void run() {

        try {
            socket = new Socket(host, port);
            System.out.println("서버에 연결 되었습니다.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

}
