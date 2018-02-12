package dam.psp;

public class PruebaBuferCompartido {

	public static void main(String[] args) {
		//Bufer bcomp = new BuferCompartido();
		Bufer bcomp = new BufferCompartidoCircular();
		
		Productor productor = new Productor(bcomp, 10);
		Consumidor consumidor1 = new Consumidor(bcomp, 6);
		Consumidor consumidor2 = new Consumidor(bcomp, 4);
		
		StringBuffer encabezados = new StringBuffer("Operación");
		encabezados.setLength(40);
		encabezados.append("Buffer    Contador Ocupado");
		System.out.println("encabezado");
		System.out.println();
		
		bcomp.mostrarEstado("Estado Inicial");
		
		productor.start();
		consumidor1.start();
		consumidor2.start();
		
		
		try {
			productor.join();
			consumidor1.join();
			consumidor2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hilo principal ha finalizado");
	}

}
