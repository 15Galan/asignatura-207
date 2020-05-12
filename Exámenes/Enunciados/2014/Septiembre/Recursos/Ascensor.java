/**
 * Clase del ascensor que tiene capacidad para una persona
 */
public class Ascensor extends Thread{
	private int sig = 1; // para saber si el ascensor sube o baja
	private int planta = 0; //planta en la que se encuentra el ascensor
	private int numPlantas;
	public Ascensor(int C){
	// C es el numero de plantas del edificio
		numPlantas = C;

	}


	// Cliente id  espera el ascensor desde origen
	public synchronized void clienteEsperaAscensor(int origen,int id) throws InterruptedException{


	}

	//el cliente id sube al ascensor, actualiza el destino
	public synchronized void clienteSubeAscensor(int destino, int id) throws InterruptedException{


	}

	// El cliente espera hasta llegar a su planta y baja del ascensor
		public synchronized void clienteBajaAscensor(int destino, int id) throws InterruptedException{

	}


	//Ascensor llega a una planta y si lleva a un cliente con destino a
	//planta lo deja bajar
	public synchronized void ascensorLlegaPlantaBajar(int planta) throws InterruptedException{

	}

	// el Ascensor espera a que se baje cliente, si es el caso
	public synchronized void ascensorEsperaBajarCliente(int planta) throws InterruptedException{


		}


	//Ascensor deja entrar a un nuevo cliente si esta vacio
	public synchronized void ascensorLlegaPlantaSubir(int planta) throws InterruptedException{


		}

	//Ascensor espera subir a un cliente, si esta vacio
	public synchronized void ascensorEsperaSubirCliente() throws InterruptedException{
			}



	//s�lo sirve para que se vayan actualizando las plantas
	//el ascensor sube hasta que llega al último piso, y luego
	//baja hasta que llega a la planta 0, y asi sucesivamente
	private void siguientePiso(){
			Thread.sleep(10);
			if (planta==numPisos-1) sig = -1;
			if (planta == 0) sig = 1;
			planta = planta + sig;
			System.out.println("Ascensor est� en la planta "+planta);
	}





	public void run(){

		while (true){
			try {
				ascensorllegaPlantaBajar(planta);
				ascensorEsperaBajarCliente(planta);
				ascensorllegaPlantaSubir(planta);
				ascensorEsperaSubirCliente();
				siguientePiso();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}


}
