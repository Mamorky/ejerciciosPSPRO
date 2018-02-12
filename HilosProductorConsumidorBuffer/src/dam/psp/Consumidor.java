package dam.psp;

public class Consumidor extends Thread {
	private Bufer compartido;
	private int veces;			//Veces que tiene que iterar las consumiciones de enteros
	
	public Consumidor(Bufer bufer,int nveces) {
		compartido = bufer;
		veces = nveces;
	}
	
	@Override
	public void run() {
		int suma = 0;
		for (int i = 0; i < veces; i++) {
				suma += compartido.leer();		
		}
		System.out.println(getName()+" termino de leer un total de " + suma);
	}
}
