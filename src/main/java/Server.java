import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        int port = 8089;
        System.out.println("Server started");

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

                out.println("Write your name:");
                String name = in.readLine();
                out.println("Are you child? (y/n)");
                while (true) {
                    String answer = in.readLine();
                    if (answer.equals("y")) {
                        out.println("Welcome to the kids area, " + name + "! Let's play!");
                        continue;
                    } else if (answer.equals("n")) {
                        out.println("Welcome to the adult zone, " + name + "! Have a good rest, or a good working day!");
                        continue;
                    } else out.println("Repeat ");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
