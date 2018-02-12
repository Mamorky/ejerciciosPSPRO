package dam.psp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServidorHilo extends Thread{
	
	Socket mySocketocket = null;
	int id = 0;
	String mensaje = "Bienvenido a mi canal";
	
	public ServidorHilo(Socket mySocket,int id) {
		this.mySocketocket = mySocket;
		this.id = id; 
	}
	
	@Override
	public void run() {
		BufferedReader bReader;
		PrintWriter pWriter = null;
		
		try {
			//bo = new BufferedOutputStream(socket.getOutputStream());
			pWriter = new PrintWriter(new OutputStreamWriter(mySocketocket.getOutputStream(),"utf8"),true);
			pWriter.println("Bienvenido a mi canal");
			
			//
			bReader = new BufferedReader(new InputStreamReader(mySocketocket.getInputStream(), "utf8"));
			System.out.println("Mensaje recibido desde el cliente: ");
			System.out.println(bReader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(pWriter != null) {
			pWriter.flush(); //Nunca deberia hacer falta esto
			pWriter.close();
		}
	}
}
