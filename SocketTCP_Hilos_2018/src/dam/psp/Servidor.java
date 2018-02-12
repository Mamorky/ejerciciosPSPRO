package dam.psp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	static final int PUERTO = 5000;
	
	public Servidor() {
ServerSocket skSRV;
		
		try {
			skSRV = new ServerSocket(PUERTO);
			System.out.println("Servidor escuchado en: "+skSRV.getLocalSocketAddress().toString());
			
			int nCli = 0;
			while (true) {
				Socket skAtention = skSRV.accept();
				nCli++;
				System.out.println("Atendiendo al nuevo cliente: "+nCli);
				
				//Creamos un hilo para atender a un nuevo cliente y así liberar el socket principal
				new ServidorHilo(skAtention,nCli).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		new Servidor();
	}
}
