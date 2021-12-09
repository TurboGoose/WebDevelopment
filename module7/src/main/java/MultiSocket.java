import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiSocket extends Thread {
    public static final int PORT = 5050;
    private final BufferedReader in;
    private final PrintWriter out;

    public MultiSocket() throws IOException {
        try (ServerSocket s = new ServerSocket(PORT)) {
            try (Socket socket = s.accept()) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                start();
            }
        }
    }

    @Override
    public void run() {
        try {
            String str = in.readLine();
            while (!"Bye.".equals(str)) {
                out.println(str);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
