package dam.psp;

import java.util.Random;


public class Arbitro {
	private int numJug;
	private int turno;
	private int objetivo;
	private boolean acertado;
	public static final int MAX = 1000;
	private Random random = new Random();
	
	public Arbitro(int numJug) {
		this.numJug = numJug;
		this.turno = random.nextInt(this.numJug-1);
		this.objetivo = random.nextInt(MAX)+1;
		this.acertado = false;
		System.out.println("Numero a acertar "+objetivo);
	}
	
	public int esTurnoDe() {
		return turno;		
	}
	
	public synchronized boolean seAcabo() {
		return acertado;
	}
	
	public synchronized void jugar(int dorsal,int numProbar) {
		while(dorsal != turno && !acertado) {
			try {
				System.out.println("[PRE] Jugador " + dorsal +" es turno de "+ turno );
				wait();
				System.out.println("[POST] Jugador " + dorsal +" es turno de "+ turno );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(!acertado) {
			System.out.println("El jugador " + turno + " prueba con "+ numProbar);
			if(numProbar == objetivo) {
				acertado = true;
				System.out.println("El jugador "+ turno +" wins");
			}
			else {
				turno = (turno+1)%numJug;
			}
		}
		notifyAll();
	}
}
