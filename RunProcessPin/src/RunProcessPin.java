import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class RunProcessPin {

	public static void main(String[] args) {
		if(args.length <= 0) {
			System.err.println("Se necesita como argumento un proceso a ejecutar");
			System.exit(-1);
		}
		
		ProcessBuilder pb = new ProcessBuilder(args);
		pb.redirectErrorStream(true);
		
		try {
			// El proceso a empezado
			Process proceso = pb.start();
			MostrarSalidaProceso(proceso);
			if(proceso != null) {
				proceso.destroy();
				System.err.println("El proceso a sido destruido");
			}
			System.exit(-1);
		}
		catch(IOException e) {
			System.err.println("Error de E/S");
		}
	}
	
	private static void MostrarSalidaProceso(Process proceso) {
		try {
			if(proceso.waitFor(2000, TimeUnit.MILLISECONDS)) {
				return;
			}
			int contLinea = 0;
			
		    InputStreamReader lector = new InputStreamReader(proceso.getInputStream(),"utf-8");
		    BufferedReader br = new BufferedReader(lector);
		    
		    String linea;
		    while (contLinea < 5 && (linea = br.readLine()) != null) {	
				System.out.println(linea);
				contLinea++;
			}
		}
		catch(IOException | InterruptedException e){
			e.printStackTrace();
		}
	}

}
