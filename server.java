package server;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
public static void main(String[] args) throws IOException {
        final int PORT = 2222;
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(PORT);
        
        System.out.println("$ ./ chat server 2222");
        System.out.println("opening server on port 2222");
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread t = new Thread() {
                public void run() {
                    try (
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        Scanner in = new Scanner(clientSocket.getInputStream());
                    ) {
                        while (in.hasNextLine()) {
                            String input = in.nextLine();
                            if (input.equalsIgnoreCase("exit")) {
                                break;
                            }
                            System.out.println(input);
                        }
                    } catch (IOException e) { }
                }
            };
            t.start();
        }
    }   
}