package dam.psp;

public class Jugador extends Thread {
	
	Arbitro arbitro;
	int dorsal;
	
	public Jugador(int dorsal,Arbitro arbitro) {
		this.arbitro = arbitro;
		this.dorsal = dorsal;
	}
	
	@Override
	public void run() {
		while (!arbitro.seAcabo()) {
			arbitro.jugar(dorsal, 1+(int)(Arbitro.MAX*(Math.random())));
		}
		System.out.println("El jugador "+ dorsal +" ha dejado de jugar");
	}
}
