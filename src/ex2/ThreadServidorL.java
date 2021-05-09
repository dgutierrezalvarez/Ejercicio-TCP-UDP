package ex2;
import java.io.*;
import java.net.Socket;
import java.util.stream.Collectors;

public class ThreadServidorL implements Runnable {

    Socket clientSocket = null;
    Llista missatgeIn, missatgeOut;
    InputStream in;
    ObjectInputStream inStream;
    OutputStream out;
    ObjectOutputStream outStream;
    boolean terminar;

    public ThreadServidorL(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        terminar = false;
        in = clientSocket.getInputStream();
        inStream = new ObjectInputStream(in);
        out = clientSocket.getOutputStream();
        outStream = new ObjectOutputStream(out);
    }

    @Override
    public void run() {
        while (!terminar) {
            try {
                missatgeIn = (Llista) inStream.readObject();
                missatgeOut = generarResposta(missatgeIn);
                outStream.writeObject(missatgeOut);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                outStream.writeObject(missatgeOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private Llista generarResposta(Llista missatgeIn) {
        System.out.println(missatgeIn.getNom());
        Llista ret = null;

        if (missatgeIn != null && missatgeIn.getNumberList().size() > 0) {
            ret = new Llista(missatgeIn.getNom(), missatgeIn.getNumberList().stream().sorted().distinct().collect(Collectors.toList()));
            terminar = true;
        }
        return ret;
    }
}