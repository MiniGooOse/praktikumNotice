package de.umr.ds.images;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TextClient {

    public static void main(String[] args) {


        try{
            ServerSocket serverSocket = new ServerSocket(32823);
            Socket socket = serverSocket.accept();
            socket.close();
        }catch (IOException ex){
            System.out.println(ex.toString());
        }

    }
}
