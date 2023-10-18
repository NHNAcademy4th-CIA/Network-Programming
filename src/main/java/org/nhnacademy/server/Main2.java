package org.nhnacademy.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

class Client2 extends Thread{
    private Socket socket;
    private BufferedReader reader;

    private static List<Client> clientList = null;
    public Client2(Socket socket,String name) {
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            if(clientList==null)
            {
                clientList = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        clientList.add(new Client(name ,this.socket));
    }
    static class Client{
        private Socket socket;
        private String name;

        public Client(String name, Socket socket){
            this.name=name;
            this.socket=socket;
        }

        public String getName() {
            return name;
        }

        public Socket getSocket() {
            return socket;
        }
    }

    private void chatHandler(String line) {
        String nickName = "";
        boolean check = false;
        if(line.charAt(0)=='@')
        {
            String newLine = line.substring(1);
            StringTokenizer stringTokenizer = new StringTokenizer(newLine);
            nickName=stringTokenizer.nextToken();
            StringBuilder stringBuilder = new StringBuilder();
            try {
                while (stringTokenizer.hasMoreTokens())
                {
                    stringBuilder.append(stringTokenizer.nextToken()+" ");
                }
                line =stringBuilder.substring(0,stringBuilder.length()-1);
            }catch (NoSuchElementException e)
            {
                System.out.println("어노테이션 대상이 없어 메시지로 전송합니다.");
            }
            check=true;
        }
        for(int i = 0;i<clientList.size();i++)
        {
            Client client = clientList.get(i);
            try {
                if(client.getSocket()==this.socket)
                {
                    continue;
                }
                if(check)
                {
                    if(!nickName.equals(client.getName()))
                    {
                        continue;
                    }
                }
                System.out.println(client);
                BufferedWriter clientWriter = new BufferedWriter(new OutputStreamWriter(client.getSocket().getOutputStream()));
                clientWriter.write(line);
                clientWriter.newLine();
                clientWriter.flush();
                if(line.equals("exit"))
                {
                    client.getSocket().close();
                    clientList.remove(client);
                    i--;
                }

            } catch (IOException e) {
                System.out.println("error handling chat : " + e.getMessage());

            }
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("클라이언트가 연결되었습니다.");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("input : " + line);
                chatHandler(line);
            }
            socket.close();
        } catch (IOException e) {
            System.out.println("socket error : " + e.getMessage());

        }

    }
}
public class Main2{
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        int port = 221;

        try {
            serverSocket = new ServerSocket(port);

            System.out.println("서버 소켓이 생성되었습니다.");
            while (!Thread.interrupted()) {
                System.out.println("클라이언트 연결을 기다립니다.");
                Socket socket = serverSocket.accept();
                String line;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                line = bufferedReader.readLine();
                System.out.println("접속한 닉네임 : "+line);
                Client2 client = new Client2(socket,line);
                client.start();
            }
        } catch (IOException e) {
            System.out.println(port + "사용중");
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("소켓 에러");
                }
            }
        }
    }
}

