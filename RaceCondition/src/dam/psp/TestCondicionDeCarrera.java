package dam.psp;

import java.util.concurrent.atomic.AtomicInteger;

class Contador{
	public final static AtomicInteger cuenta = new AtomicInteger(0);
	
}

class Sumador extends Thread{
	@Override
	public void run() {	
		for (int i = 0; i < 100000; i++) {
			//Contador.cuenta++;
			Contador.cuenta.incrementAndGet();
		}
	}
}

class Restador extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 50000; i++) {
			//Contador.cuenta--;
			Contador.cuenta.decrementAndGet();
		}
	}
}

public class TestCondicionDeCarrera {

	public static void main(String[] args) {
		Sumador sumador = new Sumador();
		Restador restador = new Restador();
		
		sumador.start();
		restador.start();
		
		try {
			sumador.join();
			restador.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Valor final del contador: "+ Contador.cuenta);
	}

}
