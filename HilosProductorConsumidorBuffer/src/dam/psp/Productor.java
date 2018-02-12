package dam.psp;


public class Productor extends Thread {

	private Bufer compartido;
	private int veces;			//Veces que tiene que iterar las producciones de enteros
	
	public Productor(Bufer bufer,int nveces) {
		compartido = bufer;
		veces = nveces;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < veces; i++) {
				compartido.escribir(i);			
		}
		System.out.println(getName()+" termino de producir datos");
	}
}
