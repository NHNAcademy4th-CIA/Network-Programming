package org.nhnacademy.minju.client.problem3;

import java.io.IOException;
import java.net.Socket;

/**
 * 결과
 * Local Host: /192.168.64.1( 60434 )
 * Remote Host: /192.168.64.3:1234( 1234 )
 */
public class SocketInformation {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 3306;
        Socket socket = null;

        try {
            socket = new Socket(host, port);
            System.out.println("Local Host: " + socket.getLocalAddress() + "( " + socket.getLocalPort() + " )");
            System.out.println("Remote Host: " + socket.getRemoteSocketAddress() + "( " + socket.getPort() + " )");

            System.in.read();
        } catch (IOException ignore) {
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ignore) {
                }
                socket = null;
            }
        }

    }
}
