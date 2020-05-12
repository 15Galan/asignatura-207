package sensores;


public class Mediciones {

	private int n=0;//numero de mediciones almacenadas
	private boolean[] nuevaMed={false,false,false};
	
	public synchronized void nuevaMedicion(int id) throws InterruptedException{
		n++;
		if(n==3){
			notifyAll();// despertar al worker
		}
		System.out.println("Sensor "+id+" deja dato: "+n);
		
	while(!nuevaMed[id])//espero hasta que me lo digan
		wait();
	nuevaMed[id]=false;//para la siguente iteracion
	}
	
	public synchronized void leerMediciones() throws InterruptedException{
		while(n!=3)
			wait();
		System.out.println("Tabajador lee datos: "+n);
		n=0;//extrae los datos
	}
	
	public void finTarea() throws InterruptedException{			
		for(int i=0;i<nuevaMed.length;i++){
			nuevaMed[i]=true;
		notifyAll();
		System.out.println("Trabajador fianliza tarea");
		}
		
		
	}
}
