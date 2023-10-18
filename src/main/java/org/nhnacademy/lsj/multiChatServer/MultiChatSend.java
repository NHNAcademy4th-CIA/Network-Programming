package org.nhnacademy.lsj.multiChatServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client의 영역으로 , 두 기능이 분리된 상태임 , 1 . 입력을 하는 부분  , 2 서버로부터 수신을 받는 부분.
 * 여기는 입력을 하는 부분을 담당함.
 */
public class MultiChatSend extends Thread {


    private Socket socket;


    MultiChatSend(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {

            String message;


            while (!(message = reader.readLine()).equals("Close")) {

                printWriter.println(socket.getInetAddress() + " " + socket.getLocalPort());

                printWriter.println(message);
                printWriter.flush();

            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}
