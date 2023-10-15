package org.nhnacademy.minju.client.problem7;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket socket;
    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void start() {
        try {
            socket = new Socket(host, port);
            Thread send = new Send(socket);
            Thread receive = new Receive(socket);
            send.start();
            receive.start();
        } catch (IOException e) {
            System.out.println("error0 : " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 1234);
        client.start();
    }
}
