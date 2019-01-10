/**
 *  TcpClientApp.java
 *  A simple TCP client that sends input to server and expects capitalized text from it.
 *  @author: Gabriel Brolo, 15105. Universidad del Valle de Guatemala. Redes.
 *  1/9/2019
 */
import java.io.*;
import java.net.Socket;

public class TcpClientApp {        

    public static void main(String args[]) throws IOException {
        String clientInput;
        String serverResponse;

        System.out.println("Enter some text here: \n");

        // User input
        BufferedReader input = new BufferedReader(
            new InputStreamReader(System.in)
        );

        Socket socket = new Socket("localhost", 8000);

        // Data Stream that will contain output from server through socket
        DataOutputStream output = new DataOutputStream(
            socket.getOutputStream()
        );

        // Get the actual server response
        BufferedReader serverInput = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );
        
        clientInput = input.readLine();
        output.writeBytes(clientInput + '\n');
        serverResponse = serverInput.readLine();

        System.out.println("Your converted text is: " + serverResponse);
        socket.close();
    }

}