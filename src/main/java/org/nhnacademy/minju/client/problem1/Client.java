package org.nhnacademy.minju.client.problem1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client extends Thread{
    private String host;
    private int port;
    private Socket socket;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        this.socket = null;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(host, port);
            if (socket.isConnected()) {
                System.out.println("서버에 연결되었습니다.");
            }
            socket.close();
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException{
        Client client = new Client("localhost", 65514);
        client.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // 수신
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.equals("quit")) {
                client.interrupt();
                break;
            }
        }
    }
}
