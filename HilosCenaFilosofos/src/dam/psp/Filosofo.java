package dam.psp;

import com.sun.swing.internal.plaf.synth.resources.synth;

public class Filosofo implements Runnable {
	private int pIzquierdo;
	private int pDerecho;
 	private int numero;
	private int veces;
	private Cena cena;
	Thread hilo;
	
	public Filosofo(Cena cena,int numFil,int veces) {
		this.cena = cena;
		this.numero = numFil;
		this.veces = veces;
		
		pIzquierdo = cena.getPalilloI(numero);
		pDerecho = cena.getPalilloD(numero);
		
		hilo = new Thread(this);
		hilo.start();
	}
	
	public void pensar() {
		System.out.println("Filosofo "+numero+" pensando");
		try {
			Thread.sleep((long) (Math.random()*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Filosofo "+numero+" tiene hambre");
	};
	
	public void cogerPalillo() {
		// Si el asiento de un filosofo es impar coge el izquierdo y luego el derecho
		// Si es el derecho de los contrarios
		System.out.println("Filosofo "+numero+" tratando de coger los palillos");
		if(!esPar()) {
			cena.getPalillo(this.pIzquierdo).coger();
			cena.getPalillo(this.pDerecho).coger();
		} 
		else {
			cena.getPalillo(this.pDerecho).coger();
			cena.getPalillo(this.pIzquierdo).coger();			
		}
		System.out.println("Filosofo "+numero+" coge los palillos");
	};
	
	public void soltarPalillo() {
		System.out.println("Filosofo "+numero+" suelta los palillos");
		cena.getPalillo(this.pIzquierdo).soltar();
		cena.getPalillo(this.pDerecho).soltar();
	};
	
	public void comer() {
		System.out.println("Filosofo "+numero+" esta comiendo");
		try {
			System.out.println("El hilo "+Thread.currentThread()+" esta comiendo");
			Thread.sleep((long) (Math.random()*2000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	public boolean esPar() {
		if(this.numero%2==0)
			return true;
		else
			return false;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < veces; i++) {
			pensar();
			cogerPalillo();
			comer();
			soltarPalillo();
		}
	}
}
