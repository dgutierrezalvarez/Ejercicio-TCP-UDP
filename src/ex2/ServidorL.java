package ex2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorL {

    int port;
    Llista llista;

    public ServidorL(int port){
        this.port = port;
    }

    public void listen(){
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket= new ServerSocket(port);
            while (true){
                clientSocket = serverSocket.accept();
                ThreadServidorL threadServidorL = new ThreadServidorL(clientSocket);
                Thread client = new Thread(threadServidorL);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServidorL servidorL = new ServidorL(5555);
        servidorL.listen();
    }

}