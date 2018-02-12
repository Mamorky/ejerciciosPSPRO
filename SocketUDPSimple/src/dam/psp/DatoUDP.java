package dam.psp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DatoUDP implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;
	
	public String cadenaTexto;
	public int valor;
	
	public DatoUDP (String cadena,int valor) {
		this.cadenaTexto = cadena;
		this.valor = valor;
	}
	
	public byte[] ToByteArray() {
		// realiza la conversion usando un ByteArrayOutputStream y un ObjectOutputStream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream os = new ObjectOutputStream(baos);
			os.writeObject(this);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
	
	public static DatoUDP FromByteArray (byte[] datos) {
		// realiza la conversión usando un ByteArrayInputStream y un ObjectInputStream
		ByteArrayInputStream bais = new ByteArrayInputStream(datos);
		DatoUDP aux = null;
		try {
			ObjectInputStream is = new ObjectInputStream(bais);
			aux = (DatoUDP) is.readObject();
			is.close();
			return aux;
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
