package org.nhnacademy.lsj.multiChatServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

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
