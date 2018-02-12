package dam.alma;

public class Retirada extends Thread{
	int piezasSalen;
	Almacen almacen;
	
	public Retirada(Almacen almacen) {
		this.almacen = almacen;
	}
	
	@Override
	public void run() {
		while(!Almacen.error) {
			try { 
				sleep(2400) ; // simulación de un día
			} catch (InterruptedException e)  { 
				e.printStackTrace() ; 
				piezasSalen = (int) Math.round((Math.random()*(2500-2000))+2000);
				synchronized(Almacen.mutex1) {
					almacen.piezas -= piezasSalen;
				}
			}
		}
	}
}
