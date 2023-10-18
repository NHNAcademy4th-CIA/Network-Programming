package org.nhnacademy.lsj.echoServer;

import java.io.IOException;


/**
 * 켓을 에코 서버에 연결하고, 데이터 송수신 확인하기.
 * 에코 서버는 클라이언트가 서버에 연결하여 메시지를 보내면, 서버에서는 그 메시지를 그대로 돌려 준다.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        EhcoServer server = new EhcoServer(32007);

        server.start();
    }
}
