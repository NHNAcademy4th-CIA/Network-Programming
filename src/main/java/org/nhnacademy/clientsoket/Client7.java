package org.nhnacademy.clientsoket;

import java.io.*;
import java.net.Socket;

public class Client7 extends Thread {
    BufferedReader input;
    public Client7(BufferedReader input){
        this.input = input;
    }

    @Override
    public void run() {
        String line;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                line=input.readLine();
                // 입력 문자열 전송
                if (line.equals("exit")) {
                    break;
                }
                System.out.println(line);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws IOException {
            Socket socket = new Socket("localhost", 221);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;
            Client7 client = new Client7(reader);
            client.start();
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("exit")) {
                    break;
                }
                output.write(line);
                output.write("\n");
                output.flush();
            }
    }
}
