package org.nhnacademy.server;

import java.io.*;
import java.net.Socket;

public class Client7 {

    private static String name;

    public static class Send extends Thread {
        private BufferedWriter output;
        private Socket socket;

        public Send(Socket socket) {
            this.socket = socket;
            try {
                output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        @Override
        public void run() {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    output.write(line);
                    output.write("\n");
                    output.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class Read extends Thread {
        private BufferedReader reader;
        private Socket socket;

        public Read(Socket socket) {
            this.socket = socket;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        @Override
        public void run() {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 221);
        BufferedWriter nickName = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("닉네임을 입력해주세요. : ");
        nickName.write(bufferedReader.readLine());
        nickName.write("\n");
        nickName.flush();
        Send send = new Send(socket);
        send.start();
        Read read = new Read(socket);
        read.start();
    }
}
