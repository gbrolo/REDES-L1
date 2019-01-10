/**
 *  UdpClientApp.java
 *  A simple UDP Client that sends input to server and expects capitalized text from it.
 *  @author: Gabriel Brolo, 15105. Universidad del Valle de Guatemala. Redes.
 *  1/9/2019
 */
import java.io.*;
import java.net.*;

public class UdpClientApp {    

    public static void main(String args[]) throws IOException {
        BufferedReader userInput = new BufferedReader(
            new InputStreamReader(System.in)
        );

        DatagramSocket socket = new DatagramSocket();

        InetAddress ipAddress = InetAddress.getByName("localhost");

        // byte arrays to send and receive data
        byte[] receivedData = new byte[1024];
        byte[] sentData = new byte[1024];

        String input = userInput.readLine();

        sentData = input.getBytes();

        DatagramPacket packetToSend = new DatagramPacket(sentData, sentData.length, ipAddress, 8000);
        socket.send(packetToSend);

        DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
        socket.receive(receivedPacket);

        // remove spaces and show text
        String serverResponse = new String(receivedPacket.getData());
        System.out.println("Your converted text is: " + serverResponse.substring(0, input.length()));

        socket.close();
    }

}