package dam.psp;


class Bienvenida{
	
	boolean clase_comenzada;
	
	public Bienvenida() {
		this.clase_comenzada = false;
	}
	
	public synchronized void saludarProfesor() {
			// Por que no usar if. Puede ser por si el valor cambia mas de una vez y vuelvo a pasar ya no pregunto
			while (clase_comenzada == false) {	
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			System.out.println("Buenos dias profesor soy "+Thread.currentThread());
	}
	
	public synchronized void llegadaProfesor(String nombre) {
		System.out.println("Buenos días a todos. Soy el profesor "+nombre);
		this.clase_comenzada = true;
		notifyAll();
	}
}

class Alumno extends Thread{
	Bienvenida saludo;
	
	public Alumno(Bienvenida bienvenida) {
		this.saludo = bienvenida;
	}
	
	public void run() {
		System.out.println("Alumno llegó.");
		try {
			Thread.sleep(2000);
			saludo.saludarProfesor();
		} catch (InterruptedException e) {
			System.err.println("Thread alumno interrumpido");
			System.exit(-1);
		}
	}
}

class Profesor extends Thread{
	String nombre;
	Bienvenida saludo;
	
	public Profesor(String nombre,Bienvenida bienvenida) {
		this.nombre = nombre;
		this.saludo = bienvenida;
	}
	
	public void run() {
		System.out.println(nombre + " llegó ");
		try {
			Thread.sleep(1000);
			saludo.llegadaProfesor(nombre);
		} catch (InterruptedException e) {
			System.err.println("Thread profesor interrumpido!");
			System.exit(-1);
		}
	}
}

public class ComienzoClase {

	public static void main(String[] args) {
		
		//Objeto compartido
		Bienvenida bienvenida = new Bienvenida();
		
		int n_alumnos = Integer.parseInt(args[0]);
		for (int i = 0; i < n_alumnos; i++) {
			new Alumno(bienvenida).start();
		}
		
		Profesor profesor = new Profesor("Osvaldo Ramirez", bienvenida);
		profesor.start();

	}

}
