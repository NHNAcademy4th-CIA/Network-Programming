package org.nhnacademy.minju.client.problem2;

import java.io.IOException;
import java.net.Socket;

/**
 * port : 1~65535
 */
public class CheckPort {
    private static void availablePort(int port) {
        try (Socket socket = new Socket("localhost", port)) {
            System.out.println(port + "가 열려있습니다.");
        } catch (IOException e) {
            System.out.println(port + "는 닫혀있습니다.");
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 65535; i++) {
            availablePort(i);
        }
    }
}
