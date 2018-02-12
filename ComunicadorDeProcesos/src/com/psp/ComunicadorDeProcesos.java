package com.psp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class ComunicadorDeProcesos {

	public static void Main(String[] args) {
		
		ProcessBuilder procesoA = new ProcessBuilder("/bin/ls","-l","/home/usuario");
		ProcessBuilder procesoB = new ProcessBuilder("/usr/bin/ls","-l","/home/usuario");
		
		Process pA;
		Process pB;
		
		try {
			pA = procesoA.start();
			pB = procesoB.start();
			
			BufferedReader salidaA = new BufferedReader(new InputStreamReader(pA.getInputStream(),"utf-8"));
			BufferedWriter entradaB = new BufferedWriter(new OutputStreamWriter(pB.getOutputStream(),"utf-8"));
			
			//Bucle de lectura y escritura						
			String linea = "";
			
			while ((linea = salidaA.readLine()) != null) {
				entradaB.write(linea);
				entradaB.newLine();
			}
			
			salidaA.close();
			entradaB.close();
			
			BufferedReader salidaB = new BufferedReader(new InputStreamReader(pB.getInputStream(),"utf-8"));
			
			String resultadoB;
			
			while ((resultadoB = salidaB.readLine()) != null) {
				System.err.println(resultadoB);
			}
			
			salidaB.close();
			
			int finA = pA.waitFor();
			int finB = pB.waitFor();
			
			System.out.println("Procesos A y B finalizados con resultado: "+ finA+ " y " + finB);
		}
		catch(IOException | InterruptedException e) {
			System.out.println("Error de E/S o exceptión por interrupción");
		}
	}
}
