package org.nhnacademy.lsj.echoServerVersion2;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 다중 연결 에코서버를 구성해 보자.
 * 서버 소켓은 포트를 통해 대기 상태에서 클라이언트가 연결되면 통신을 위한 소켓을 생성함으로써.
 * 기본 기능을 완료하고 연결되어 있는 클라이언트가 끊어진 후 accept() 함수를 통해 다시 할 수 있다.
 * <p>
 * 이 과정을 스레드를 통해 분리해 보도록 하자.
 * <p>
 * 서버 소켓은 대기 상태에서 클라이언트 연결이 이루어 지면 이를 처리하기 위한 스레드를 생성하여.
 * 생성된 소켓을 넘겨 주고 서버 소켓은 다시 새로운 연결을 기다리도록 한다.
 */
public class Main {


    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(32007);

        while (true) {

            Socket connection = socket.accept();

            MultiConnectionEchoServer multiConnectionEchoServer = new MultiConnectionEchoServer(connection);

            multiConnectionEchoServer.start();

        }


    }

}
