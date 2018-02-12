package dam.psp;

import com.sun.glass.ui.TouchInputSupport;

class MyThread extends Thread{
	
	@Override
	public void run() {
		System.out.println("El hilo hijo se esta ejecutando");
		try {
			this.sleep(2000);
		} catch (Exception e) {
			
		}
		System.out.println("El hilo hijo a terminado");
		
	}
}

public class HilosImplementsClaseThread {

	public static void main(String[] args) {
		System.out.println("Ejecución Hilo padre");
	
		MyThread miHilo = new MyThread();
		miHilo.start();		
		
		try {
			miHilo.join();
		} catch (Exception e) {
			
		}
		
		System.out.println("El hilo principal ha terminado");
	}

}
