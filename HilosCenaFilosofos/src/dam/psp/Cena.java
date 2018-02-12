package dam.psp;

public class Cena {
	int comensales;
	Palillo[] palillos;
	
	public Cena(int comensales) {
		this.comensales = comensales;
		this.palillos = new Palillo[comensales];
		for (int i = 0; i < palillos.length; i++) {
			new Palillo(i);
		}
	}
	
	public Palillo getPalillo(int x) {
		return palillos[x];
	}
	public int getPalilloD(int filosofo) {
		return (filosofo+1)%comensales;
	}
	public int getPalilloI(int filosofo) {
		return filosofo;
	}
}
