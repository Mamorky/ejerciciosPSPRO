package dam.alma;

import com.sun.swing.internal.plaf.synth.resources.synth_zh_CN;

public class Almacen {
	int piezas = 8000;
	final int MAXPIEZAS = 20000;
	static boolean error = false;
	
    static final Object mutex1 = new Object();
    
    public synchronized void AtenderLlegadas(int numPiezas) {
    		if(numPiezas < MAXPIEZAS)
    			piezas += numPiezas;
    		else
    			error = true;
    }
    
    public synchronized void SalidasPiezas(int numPiezas) {
		if(numPiezas > piezas)
			piezas += numPiezas;
		else
			error = true;
}
}
