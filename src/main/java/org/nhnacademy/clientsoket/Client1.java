import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client1 extends Thread {
    String host;
    int port;
    Socket socket;

    public Client1(String host, int port) {
        this.host = host;
        this.port = port;
        this.socket = null;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(host,port);
            System.out.println("서버에 연결 되었습니다");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //Client1 client = new Client1("localhost", 1234);
        Client1 client = new Client1("localhost", 7000);
        client.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while((line = reader.readLine()) != null) {
            if (line.equals("quit")) {
                client.interrupt();
                break;
            }
        }
    }
}