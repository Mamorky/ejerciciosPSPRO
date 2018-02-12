package dam.psp;

import com.sun.glass.ui.TouchInputSupport;

class HiloHolaMundo implements Runnable{

	Thread hilo;
	
	public HiloHolaMundo() {
		hilo = new Thread(this,"Nuevo hilo creado");
		System.out.println("Creando hilo nuevo .... "+ hilo);
		hilo.start();
	}
	
	public void espera() {
		try {
			hilo.join();
		}
		catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	@Override
	public void run() {
		System.out.println("Hilo nuevo ha empezado a ejecutarse ...");
		
		try {
			// Es la clase thread quien debe de decir el sleep
			Thread.sleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Hilo hijo finalizado ...");
		
	}
	
}

public class HolaMundo {

	public static void main(String[] args) {
		System.out.println("Hola desde el hilo principal (main)");	
		
		HiloHolaMundo hijo = new HiloHolaMundo();
		hijo.espera();
		
		System.out.println("Hilo principal (main) finalizando ...");
	}

}
