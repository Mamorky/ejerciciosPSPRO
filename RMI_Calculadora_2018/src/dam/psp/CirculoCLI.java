package dam.psp;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CirculoCLI {

	public static String servidor = "192.168.3.63";
	public static int puerto1 = 8888;
	public static int puerto2 = 8889;
	
	public static void main(String[] args) throws RemoteException {
		ICirculo circulo = null;
		ICalculadora calculadora = null;
		
		System.out.println("Localizando en la red el objeto remoto");
		Registry registry = LocateRegistry.getRegistry(servidor,puerto1);
		Registry registry2 = LocateRegistry.getRegistry(servidor,puerto2);
		
		System.out.println("Obteniendo el falso objeto <stub> de remoto");
		try {
			circulo = (ICirculo)registry.lookup("Circulo");
			if(circulo != null)
			{
				circulo.set_radio(20);
				System.out.println("longitud de la circunferencia "+circulo.longitud());
				System.out.println("area del circunferencia "+circulo.area());
			}
			
			calculadora = (ICalculadora)registry2.lookup("Calculadora");
			if(calculadora != null)
			{
				System.out.println("Suma de 10,15"+calculadora.suma(10, 15));
				System.out.println("Resta de 10,15"+calculadora.resta(10, 15));
				System.out.println("Multiplicar de 10,15"+calculadora.producto(10, 15));
				System.out.println("División de 10,15"+calculadora.division(10, 15));
			}
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
