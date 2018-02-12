package dam.alma;

public class Envio extends Thread {
	int piezasEnviar;
	Almacen almacen;
	
	public Envio(Almacen almacen) {
		this.almacen = almacen;
	}
	
	@Override
	public void run() {
		while(!Almacen.error) {
			try { 
				sleep(2400) ; // simulación de un día
			} catch (InterruptedException e)  { 
				e.printStackTrace() ; 
				piezasEnviar = (int) Math.round((Math.random()*(1000-400))+400);
				
			}
		}
	}
}
