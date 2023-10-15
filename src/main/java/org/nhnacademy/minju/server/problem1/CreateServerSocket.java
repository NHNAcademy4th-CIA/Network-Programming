package org.nhnacademy.minju.server.problem1;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 * ServerSocket 클래스의 인스턴스를 이용해 소켓을 생성
 * 클라이언트에서 접속 가능하도록 대기모드(LISTEN)인지 확인
 */
public class CreateServerSocket {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("포트 번호를 입력하세요.");
        int port = scanner.nextInt();

        try (ServerSocket socket = new ServerSocket(port)) {
            System.out.println("사용 가능");
        } catch (IOException e) {
            System.out.println("지정된 포트[ " + port + " ]가 이미 사용중입니다.");
        }
    }
}
