package dam.psp;

public class Juego {

	public static void main(String[] args) {
		Arbitro arbitro = new Arbitro(7); 
		
		for (int i = 0; i < 7; i++) {
			Jugador jugador = new Jugador(i, arbitro);
			try {
				jugador.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jugador.start();			
		}
	}

}
