package dam.psp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DatoUDP2 implements Serializable{
	
	/**
	 * Serial UID
	 * */
	private static final long serialVersionUID = 1L;
	
	public String cadenaTexto;	
	public DatoUDP2(String cadena) {
		this.cadenaTexto = cadena;
	}
	
	public byte[] toByteArray() {
		//Realiza la conversión usando un byteArray[] output stream y un object OutPutStream
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			ObjectOutputStream os = new ObjectOutputStream(byteArrayOutputStream);
			os.writeObject(this);
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteArrayOutputStream.toByteArray();
	}
	
	public static DatoUDP2 FromByteArray(byte[] datos) {
		//Realiza la cinversión usando un ByteArrayInputStream y un ObjectInputStream
		ByteArrayInputStream bais = new ByteArrayInputStream(datos);
		DatoUDP2 aux;
		try {
			ObjectInputStream is = new ObjectInputStream(bais);
			aux = (DatoUDP2) is.readObject();
			is.close();
			return aux;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
