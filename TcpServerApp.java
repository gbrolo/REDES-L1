/**
 *  TcpServerApp.java
 *  A simple TCP server that capitalizes input from client.
 *  @author: Gabriel Brolo, 15105. Universidad del Valle de Guatemala. Redes.
 *  1/9/2019
 */
import java.io.*;
import java.net.*;

public class TcpServerApp {    

    public static void main(String args[]) throws IOException {
        String clientInput;
        String serverResponse;
        ServerSocket socket = new ServerSocket(8000);

        while(true) {
            // Starting connection socket
            Socket connSocket = socket.accept();

            // Get raw input from client
            BufferedReader input = new BufferedReader(
                new InputStreamReader(connSocket.getInputStream())
            );

            // Data Stream that will contain server output
            DataOutputStream output = new DataOutputStream(connSocket.getOutputStream());

            clientInput = input.readLine();
            System.out.println("Server read input: " + clientInput + "\n");

            serverResponse = clientInput.toUpperCase() + '\n';
            System.out.println("Server wrote output: " + serverResponse + "\n");

            output.writeBytes(serverResponse);
        }
    }

}