import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        final String HOST = "127.0.0.1";
        final int PORT = 2222;
        Scanner un = new Scanner(System.in);
        System.out.print("Please enter username: ");
        String username = un.nextLine();
        
        try (
            Socket socket = new Socket(HOST, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner in = new Scanner(socket.getInputStream());
            Scanner s = new Scanner(System.in);
        ) {
            while (true) {
                System.out.print("Input: ");
                String input = s.nextLine();
                out.println(username + ": " + input);           
                System.out.println("server: " + in.nextLine());
                if (input.equalsIgnoreCase("exit")) break;
            }
        }
    }
}