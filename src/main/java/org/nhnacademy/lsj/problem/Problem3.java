package org.nhnacademy.lsj.problem;

import java.io.IOException;
import java.net.Socket;

/**
 * 연습 3. 소켓 정보를 알아보자
 * Socket 클래스에서는 현재 연결되어 있는 호스트 정보 뿐만 아니라 자신의 정보도 포함되어 있다.
 *
 * 위 예제를 보면 상대의 정보(호스트 이름 또는 IP 주소와 포트 번호)는 인스턴스 생성 시점에 주어짐으로 알 수 있다. 하지만, 정작 자신이 실행되고 있는 로컬 호스트에 대해서는 아무런 정보를 알지 못한다.
 *
 * Socket 클래스에서는 이러한 정보를 얻을 수 있다.
 *
 * Socket 클래스에서 지원하는 기능을 이용해 로컬 호스트와 원격 호스트에 정보를 출력해 보자.
 */
public class Problem3 {


    public static void main(String[] args) {


        String host = "localhost";
        int port = 32007;

        Socket socket;

        try {
            socket = new Socket(host, 32007);

            System.out.println("Local Host : " + socket.getInetAddress());
            System.out.println("Remote Host : " + socket.getRemoteSocketAddress());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

}
