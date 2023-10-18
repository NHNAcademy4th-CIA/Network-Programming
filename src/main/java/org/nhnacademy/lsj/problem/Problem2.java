package org.nhnacademy.lsj.problem;

import java.io.IOException;
import java.net.Socket;

/**
 * 연습 2. 서버에 연결 가능한 포트를 찾아 보자.
 * 앞에서 서버에 포트가 열려 있는 경우와 그렇지 않은 경우에 대해 Socket 클래스를 이용한 인스턴스 생성 결과를 확인하였다.
 *
 * 해당 포트가 열려 있는 경우는 Socket 클래스의 인스턴스가 생성되지만, 그렇지 않은 경우에는 exception 발생을 통해 처리하도록 한다.
 *
 * 헤커들이 타겟 서버에 들어오기 위해서는 어떤 포트가 열려져 있는지 알아야 한다.
 *
 * 간단하게 열려져 있는 포트를 검색하는 프로그램을 만들어 보자.
 */
public class Problem2 {


    public static void main(String[] args) {


        int count = 0;

        for (int i = 0; i <= 65535; i++) {

            try {
                Socket socket = new Socket("localhost", i);
                System.out.println(i + "번 port가 열려 있습니다");
                break;
            } catch (IOException e) { // 서버에 연결이 불가능한 포트 수
            }

        }


    }


}
