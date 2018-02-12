package com.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunProcessConectados {

	public static void main(String[] args) {
		if(args.length <= 0) {
			System.err.println("Se necesita como argumento un proceso a ejecutar");
			System.exit(-1);
		}
		
		ProcessBuilder pb = new ProcessBuilder(args);
		pb.redirectErrorStream(true);
		
		try {
			// El proceso a empezado
			Process proceso = pb.start();
			MostrarSalidaProceso(proceso);
			System.exit(-1);
		}
		catch(IOException e) {
			System.err.println("Error de E/S");
		}
	}
	
	private static void MostrarSalidaProceso(Process proceso) {
		try {
			int retorno = proceso.waitFor();
			
			System.out.println("El proceso hijo a devuelto: "+retorno);
			
		    InputStreamReader lector = new InputStreamReader(proceso.getInputStream(),"utf-8");
		    BufferedReader br = new BufferedReader(lector);
		    
		    String linea;
		    while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
		}
		catch(InterruptedException | IOException e){
			e.printStackTrace();
		}
	}

}
