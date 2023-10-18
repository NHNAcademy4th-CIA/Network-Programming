package org.nhnacademy.clientsoket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client2 extends Thread {
    public static void main(String[] args) throws IOException {
        //Client1 client = new Client1("localhost", 1234);
        int count = 0;
        for(int i=0;i<=65534;i++) {
            try {
               Socket socket = new Socket("localhost", i);
                count++;
                socket.close();
            }catch (IOException e)
            {
                System.out.println(e.toString());
            }
        }
        System.out.println("현재 열려있는 포트수 : "+count);
    }
}