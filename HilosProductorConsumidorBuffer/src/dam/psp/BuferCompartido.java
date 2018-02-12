package dam.psp;

public class BuferCompartido implements Bufer {

	private int bufer = -1;
	private int contadorOcupado = 0;
	
	@Override
	public synchronized int leer() {
		while(contadorOcupado == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		contadorOcupado = 0;
		notify();
		return bufer;
	}

	@Override
	public synchronized void escribir(int valor) {
		while (contadorOcupado == 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bufer = valor;
		contadorOcupado = 1;
		notify();
	}

	@Override
	public void mostrarEstado(String cadena) {
		StringBuffer linea = new StringBuffer(cadena);
		linea.setLength(80);
		linea.append(bufer+ " " + contadorOcupado);
		System.out.println(linea);
		System.out.println();
	}

}
