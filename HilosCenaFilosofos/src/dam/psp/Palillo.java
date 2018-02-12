package dam.psp;

public class Palillo {
	int numero;
	boolean enUso;
	
	public Palillo(int numPalillo) {
		this.numero = numPalillo;
		enUso = false;
	}
	
	public synchronized void coger() {
		while(enUso) {
			System.out.println("Palillo "+numero+" en uso");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		enUso = true;
		System.out.println("El hilo "+Thread.currentThread()+" Cogio el palillo");
	}
	
	public synchronized void soltar() {
		System.out.println("El hilo "+Thread.currentThread()+ "ya ha comido");
		enUso = false;
		System.out.println("Palillo "+numero+" libre");
		notify();
	}
}
