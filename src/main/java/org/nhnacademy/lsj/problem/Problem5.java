package org.nhnacademy.lsj.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 연습 5. 소켓을 이용해 데이터를 받아 보자.
 * 소켓을 이용해 서버에 접속하였다면, 해당 소켓을 이용해 데이터를 받아 보자.
 * <p>
 * 소켓에서는 데이터를 받기 위한 InputStream 제공.
 * 받은 문자열은 표준 출력을 통해 출력.
 * 서버에서 연결 후 데이터 전송을 위한 프로그램이 필요.
 * 간단하게 텍스트를 보내거나 받을 수 있는 nc를 이용.
 */
public class Problem5 {

    public static void main(String[] args) {

        ClientForReceiveData client = new ClientForReceiveData("localhost", 32007);

        client.start();


    }

}


class ClientForReceiveData extends Thread {

    private String host;
    private int port;
    private Socket socket;


    ClientForReceiveData(String host, int port) {
        this.host = host;
        this.port = port;
        this.socket = null;
    }


    @Override
    public void run() {

        try {
            socket = new Socket(host, port);

            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;

            System.out.println("서버 연결");

            while (!(line = bf.readLine()).equals("Close")) {
                System.out.println(line);
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("프로그램 종료");
        }


    }
}