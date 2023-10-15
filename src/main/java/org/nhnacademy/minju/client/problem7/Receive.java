package org.nhnacademy.minju.client.problem7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Receive extends Thread{

    private Socket socket;

    public Receive(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line;
            while (!Thread.currentThread().isInterrupted() && (line = reader.readLine())!=null) {
                System.out.println("응답 : " + line);
            }

        } catch (IOException e) {
            System.out.println("error2 : " + e.getMessage());
        }

    }
}
