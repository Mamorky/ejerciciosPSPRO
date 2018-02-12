package dam.psp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class HiloInterrum implements Runnable{
	private Thread miHilo;
	private volatile boolean vivo;
	
	HiloInterrum() {
		this.miHilo = new Thread(this,"Nuevo Hilo");
		this.vivo = true;
		this.miHilo.start();
	}
	
	public boolean vive() {
		return this.vivo;
	}
	
	public void interrumpir() {
		if(this.miHilo != null)
			this.miHilo.interrupt();
	}
	
	public void detener() {
		this.vivo = false;
	}
	
	public void esperar() throws InterruptedException{
		this.miHilo.join();	
	}
	
	@Override
	public void run() {
		System.out.println("Corriendo el cuerpo del hilo ...");
		while(this.vivo) {
			System.out.println("El hilo pasa a dormir un rato ...");
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				System.out.println("El hilo a sido interrumpido de la siesta ...");
			}
		}
		System.out.println("Hilo hijo finalizado ...");
	}
	
	
}

public class HiloInterrumpible {

	public static void main(String[] args) {
		HiloInterrum hilo = new HiloInterrum();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String tecla;
		
		System.out.println("Iniciando el hilo principal ...");
		while (hilo.vive()) {
			System.out.println("Hilo vivo, [i] inetrrumpir,[k] matar ...");
			try {
				tecla = br.readLine();
				if(tecla.equals("i"))
					hilo.interrumpir();
				if(tecla.equals("k"))
					hilo.detener();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		try {
			hilo.esperar();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("El hilo principal finaliza ...");
	}

}
