package org.nhnacademy.lsj.multiChatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Client의 영역으로 , 두 기능이 분리된 상태임 , 1 . 입력을 하는 부분  , 2 서버로부터 수신을 받는 부분.
 * 여기는 수신을 받는 부분을 담당함.
 */
public class MultiChatReceive extends Thread {


    private Socket socket;


    MultiChatReceive(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {

            String message = "";

            while (!(message = bf.readLine()).equals("Close")) {
                System.out.println("수신 : " + message);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}
