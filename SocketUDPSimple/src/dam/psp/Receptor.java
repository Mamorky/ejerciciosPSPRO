package dam.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Receptor {
	
	public static final int Puerto = 4444;
	public static final String IPEmisor = "0.0.0.0";
	DatagramSocket socket = null;
	
	public Receptor() {
		try {	
			socket = new DatagramSocket(Puerto,InetAddress.getByName(IPEmisor));
			
			System.out.println("Emisor conectado al socket: "+ socket.getLocalAddress());
			
			while(true) {
				DatagramPacket dato = new DatagramPacket(new byte[144],144);
				//Se recibe un dato y se escribe en pantalla
				socket.receive(dato);
				System.out.println("Recibo un dato de: "+dato.getAddress().getHostName() + ": ");
				byte[] contenido = dato.getData();
				System.out.println(" de longitud: "+dato.getLength());
				//System.out.println(new String(contenido));
				System.out.println(DatoUDP.FromByteArray(contenido).cadenaTexto+"y su valor es: "+DatoUDP.FromByteArray(contenido).valor);
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		finally {
			socket.close();
		}
	}

	public static void main(String[] args) {
		new Receptor();
	}

}
