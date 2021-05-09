package ex1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;

public class Servidor {

    MulticastSocket socket;
    InetAddress multicastIP;
    int port;
    boolean continueRunning = true;
    Velocitat simulator;

    public Servidor(int portValue, String strIp) throws IOException {
            socket = new MulticastSocket(portValue);
            multicastIP = InetAddress.getByName(strIp);
            port = portValue;
            simulator = new Velocitat(100);
    }

    public void runServer() throws IOException{
    DatagramPacket packet;
    byte [] sendingData;

    while(continueRunning){
    sendingData = ByteBuffer.allocate(4).putInt(simulator.simuladorVelocitat()).array();
    packet = new DatagramPacket(sendingData, sendingData.length,multicastIP, port);
    socket.send(packet);
    System.out.println(sendingData);

    try {
    Thread.sleep(500);
    } catch (InterruptedException ex) {
    ex.getMessage();
    }


    }
    socket.close();
    }

    public static void main(String[] args) throws IOException {
    Servidor servidor = new Servidor(5555, "224.0.3.1");
    servidor.runServer();
    System.out.println("Stop");

    }
}

