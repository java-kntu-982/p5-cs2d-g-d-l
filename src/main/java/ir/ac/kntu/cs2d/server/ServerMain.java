package ir.ac.kntu.cs2d.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

        public static void main(String[] arg) throws IOException {

            ServerSocket socket = null;

            int serverPort = 50128;
            System.out.println("Waiting for client!");
            while (true){
                try {
                    socket = new ServerSocket(serverPort);
                } catch (IOException e) {
                    //System.out.println("Connection error!");
                }
                Socket client = null;
                try {
                    client = socket.accept();

                } catch (IOException io) {

                    System.out.println("Accept error!");

                }
                System.out.println("Connected to new client successfully....");
            }

        }


}
