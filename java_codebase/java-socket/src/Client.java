import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 55533;
        String message = "From client: Hello world";

        long time = -System.nanoTime();
        sendMessage(host, port, message);
        time += System.nanoTime();

        System.out.println("Send finished in " + (time / 1000_000) + " ms");
    }

    private static void sendMessage(String host, int port, String message) {
        try {
            Socket socket = new Socket(host, port);
            OutputStream outputStream = socket.getOutputStream();
            socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
