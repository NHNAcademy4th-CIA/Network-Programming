package org.nhnacademy.minju.server.problem1.exercise2;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * netstat -latn | grep '*.*'
 * 다른 프로그램에서 사용중인 포트로 서버 소켓을 생성한다.
 */
public class CreateServerSocket {

    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket socket = new ServerSocket(port)) {
            System.out.println(port + "는 사용 가능한 포트입니다.");
        } catch (IOException e) {
            System.out.println("지정된 포트[ " + port + " ]가 이미 사용중입니다.");
        }
    }
}
