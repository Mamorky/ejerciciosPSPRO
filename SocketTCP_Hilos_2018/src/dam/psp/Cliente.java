package dam.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.xml.stream.util.EventReaderDelegate;

public class Cliente {
	
	static final String HOST = "localhost";
	static final int PUERTO = 5000;
	Scanner entrada;
	
	private String LeerMensaje() {
		System.out.println("Introduce el mensaje para enviar");
		String mensaje = entrada.nextLine();
		return mensaje;
	}
	
	public Cliente() {
		try {
			Socket skCli = new Socket(HOST, PUERTO);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(skCli.getInputStream(),"utf8"));
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in,"utf8"));
			PrintWriter pWriter = new PrintWriter(new OutputStreamWriter(skCli.getOutputStream(),"utf8"),true);
			System.out.println(bReader.readLine());
			
			//enviar mensaje pedido por consola al servidor
			System.out.println("Mensaje para enviar?");
			String mensaje = teclado.readLine();
			System.out.println("Enviando al servidor el mensaje: "+mensaje);
			pWriter.println(mensaje);
			
			skCli.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Cliente();
		
		/*for (int i = 0; i < 800000; i++) {
			new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < args.length; i++) {
						new Cliente();
					}
				}
			}).start();
		}*/
	}
}
