package org.nhnacademy.lsj.problem;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 연습 6. 정상적으로 생성된 경우, 클라이언트의 접속이 가능한지 확인해 보자.
 * ServerSocket 클래스의 인스턴스를 이용해 소켓을 생성.
 * 클라이언트에서 접속 가능하도록 대기모드(LISTEN)인지 확인.
 */
public class Problem6 {

    public static void main(String[] args) {

        try {

            ServerSocket socket = new ServerSocket(32007);
            System.out.println("서버 소켓 생성 완료");
        } catch (IOException e) {
            System.out.println("지정된 포트는 이미 사용중입니다");
        }


    }

}

