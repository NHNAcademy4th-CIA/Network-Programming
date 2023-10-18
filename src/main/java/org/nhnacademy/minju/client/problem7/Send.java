package org.nhnacademy.minju.client.problem7;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Send extends Thread {

    private Socket socket;

    public Send(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {
            while (!Thread.currentThread().isInterrupted()) {
                writer.println(scanner.nextLine());
                writer.flush();
            }

        } catch (IOException e) {
            System.out.println("error1 : " + e.getMessage());
        }
    }
}
