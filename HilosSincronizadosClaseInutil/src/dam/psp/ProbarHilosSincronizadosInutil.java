package dam.psp;

class Inutil{
	private int a = 0;
	private int b = 0;
	

	public synchronized void marcar_5() {
		a = 5;
		System.out.println("Marcando ... no me metas bulla");
		b = 5;
	}
	public synchronized boolean esVerdad() {
		return a == 0 || b == 5;
	}
}

class HiloA extends Thread{
	private Inutil i;
	
	public HiloA(Inutil in) {
		this.i = in;
	}
	
	@Override
	public void run() {
		i.marcar_5();
	}
}

class HiloB extends Thread{
	private Inutil i;
	
	public HiloB(Inutil in) {
		this.i = in;
	}
	
	@Override
	public void run() {
		if(i.esVerdad())
			System.out.println("Verdadero");
		else
			System.out.println("Más falso que judas");
	}
}

public class ProbarHilosSincronizadosInutil {

	public static void main(String[] args) {
		Inutil inutil = new Inutil();
		
		HiloA hA = new HiloA(inutil);
		HiloB hB = new HiloB(inutil);
		
		hA.start();
		hB.start();
				
		try {
			hA.join();
			hB.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
