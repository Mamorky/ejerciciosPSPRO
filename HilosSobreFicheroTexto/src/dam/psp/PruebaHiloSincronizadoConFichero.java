package dam.psp;

public class PruebaHiloSincronizadoConFichero {

	public static void main(String[] args) {
		ControladorFichero cFichero = new ControladorFichero("poema.txt");

		String parrafo1 = "¡Ser o no ser, esa es la cuestion! ¿Que debe más dignamente optar ...";
		String parrafo2 = "En un lugar de la mancha, de cuyo nombre no quiero acordarme ...";
		
		Escritor cervantes = new Escritor(cFichero); // ojo con el objeto compartido
		Escritor shakespeare = new Escritor(cFichero);
		
		shakespeare.fraseAdd(parrafo1);
		cervantes.fraseAdd(parrafo2);
		
		shakespeare.start();
		cervantes.start();
		
		
		try {
			shakespeare.join();
			cervantes.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		finally {
			cFichero.close();
		}
		
		System.out.println("Los datos han sido escritos correctamente?");
	}

}
