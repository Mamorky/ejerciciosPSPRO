package dam.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Emisor {

	public static final int PuertoEmisor = 5555;
	public static final int PuertoReceptor = 4444;
	//Ale Ip
	//public static final String IPReceptor = "192.168.3.63";
	
	//Eliseo Ip
	public static final String IPReceptor = "192.168.3.57";
	
	public Emisor() {
		DatagramSocket emisor;
		DatagramPacket datagramPacket;
		InetAddress IPLocal;
		InetAddress IPRemota;
		
		//Construimos el contenido del mensaje a enviar
		byte[] contenido = new byte[144];
		DatoUDP datoUDP = new DatoUDP("Este es mi mensaje",87);
		
		try {
			IPLocal = InetAddress.getByName("0.0.0.0");
			IPRemota = InetAddress.getByName(IPReceptor);
			
			emisor = new DatagramSocket(PuertoEmisor,IPLocal); //Por donde sale el mensaje
			contenido = datoUDP.ToByteArray();
			datagramPacket = new DatagramPacket(contenido,contenido.length,IPRemota,PuertoReceptor);
			
			emisor.send(datagramPacket);
			System.out.println("El mensaje a sido enviado");
		} catch (UnknownHostException | SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Emisor();
		
	}

}
