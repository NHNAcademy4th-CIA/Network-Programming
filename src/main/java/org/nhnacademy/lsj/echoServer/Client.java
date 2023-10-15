package org.nhnacademy.lsj.echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

    @Override
    public void run() {
        try (Socket socket = new Socket("127.0.0.1", 32007);
             BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {


            String str = "";

            Scanner sc = new Scanner(System.in);


            do {
                str = sc.nextLine();
                pw.println(str);
                pw.flush();
                System.out.println(bf.readLine());
            } while (!str.equals("Close"));


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client();

        client.start();
        try {
            client.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


    }

}

