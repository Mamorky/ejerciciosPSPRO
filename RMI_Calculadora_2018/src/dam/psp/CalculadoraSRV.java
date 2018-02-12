package dam.psp;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraSRV implements ICalculadora{
	
	@Override
	public float suma(float a, float b) throws RemoteException {
		return a+b;
	}

	@Override
	public float resta(float a, float b) throws RemoteException {
		return a-b;
	}

	@Override
	public float producto(float a, float b) throws RemoteException {
		return a*b;
	}

	@Override
	public float division(float a, float b) throws RemoteException {
		if(b==0)
			throw new RemoteException("División por cero, atontado");
		else
			return a/b;
	}
	
	public CalculadoraSRV(Registry registry) {
		System.out.println("Creando objeto Calculadora y su Inscripción en el registro");
		try {
			registry.bind("Calculadora",(ICalculadora)UnicastRemoteObject.exportObject(this,0));
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws RemoteException {
		final int puerto = 8889;
		System.setProperty("java.rmi.server.hostname","192.168.2.16");
		System.setProperty("java.net.preferIPv4Stack","true");
		Registry registry = LocateRegistry.createRegistry(puerto);
		new CalculadoraSRV(registry);
	}

}
