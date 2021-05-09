package ex2;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientL extends Thread{
    String hostname;
    int port;
    boolean continueConnected;
    List<Integer> numberList;
    Llista ret = null;
    String nombre;
    int numero = 0;
    Scanner scanner = new Scanner(System.in);
    Socket socket;
    InputStream in;
    ObjectInputStream oiStream;
    OutputStream out;
    ObjectOutputStream ooStream;

    public ClientL(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        continueConnected = true;
    }

    public void run() {
        numberList = new ArrayList<>();
        System.out.println("Posa el teu nom:");
        nombre = scanner.nextLine();
        System.out.print("Hola " + nombre + "");

        do {
            System.out.println(", Posa un numero (posa 0 per terminar)");
            numero = scanner.nextInt();
            if (numero > 0) numberList.add(numero);
        } while (numero > 0);
        ret = new Llista(nombre, numberList);

            try {
                socket = new Socket(InetAddress.getByName(hostname), port);
                out = socket.getOutputStream();
                ooStream = new ObjectOutputStream(out);
                ooStream.writeObject(ret);

                in = socket.getInputStream();
                oiStream = new ObjectInputStream(in);
                ret = (Llista) oiStream.readObject();
                getRequest(ret);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        }

        private void getRequest(Llista serverData) {
            System.out.print(serverData.getNom() + " aqui tens la teva llista ordenada ");
            serverData.getNumberList().forEach(integer -> System.out.print(integer + ", "));

        }

        public static void main(String[] args) {
            ClientL clientL = new ClientL("localhost", 5555);
            clientL.run();
        }
    }

