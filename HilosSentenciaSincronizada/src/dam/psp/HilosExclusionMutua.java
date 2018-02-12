package dam.psp;

class DatosCompartidos{
	public static int c1 = 0;
	public static int c2 = 0;
	
	private static final Object mutex1 = new Object();
	private static final Object mutex2 = new Object();
	
	public static void incrementarC1() {		
		//SC1
		synchronized (mutex1) {
			c1++;
		} 
		//Fin SC1
	}
	
	public static void incrementarC2() {
		//SC2
		synchronized (mutex2) {
			c2++;
		} 
		//Fin SC2
	}
}

class HilosMutex extends Thread{
	
	@Override
	public void run() {
		DatosCompartidos.incrementarC1();
		DatosCompartidos.incrementarC2();
	}
}

public class HilosExclusionMutua {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		
		HilosMutex hilos[];
		System.out.println("Creando "+N+" hilos");
		
		hilos = new HilosMutex[N];
		
		for (int i = 0; i < N; i++) {
			hilos[i] = new HilosMutex();
			hilos[i].start();
		}
		
		for (int i = 0; i < N; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		System.out.println("C1 = "+DatosCompartidos.c1);
		System.out.println("C2 = "+DatosCompartidos.c2);
	}
}
