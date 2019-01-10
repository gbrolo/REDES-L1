/**
 *  UdpServerApp.java
 *  A simple UDP server that capitalizes input from client.
 *  @author: Gabriel Brolo, 15105. Universidad del Valle de Guatemala. Redes.
 *  1/9/2019
 */
import java.io.*;
import java.net.*;

public class UdpServerApp {    

    public static void main(String args[]) throws IOException {
        DatagramSocket socket = new DatagramSocket(8000);

        // byte arrays for received data and sent data
        byte[] receivedData = new byte[1024];
        byte[] sentData = new byte[1024];

        String serverResponse;
        String input;
        int usedPort;
        InetAddress ipAddress;

        while(true) {
            // create udp packet
            DatagramPacket receivedPacket = new DatagramPacket(
                receivedData, receivedData.length
            );

            socket.receive(receivedPacket);

            // get user input from packet
            input = new String(receivedPacket.getData());
            System.out.println("Server read input: " + input + "\n");

            ipAddress = receivedPacket.getAddress();

            usedPort = receivedPacket.getPort();
            System.out.println("Input is: " + input);
            serverResponse = input.toUpperCase();

            sentData = serverResponse.getBytes();

            DatagramPacket packetToSend = new DatagramPacket(
                sentData, sentData.length, ipAddress, usedPort
            );

            socket.send(packetToSend);
        }
    }

}