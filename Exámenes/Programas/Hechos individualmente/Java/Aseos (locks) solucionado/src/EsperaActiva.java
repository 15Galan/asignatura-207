public class EsperaActiva {
	
	/*
	 * Realiza la suma de N numeros aleatorios entre 0 y 1 
	 * para simular una espera activa utilizando la cpu intensivamente
	 * @parameter ciclos Numero de veces que suman aleatorios multiplicado por 100
	 * @parameter visible true para mostrar mensajes por consola, false en caso contrario
	 */
	
	public static void sleep(int ciclos, boolean visible) throws InterruptedException{
		double suma = 0;
		long inicio = System.currentTimeMillis();
		for (int i = 0; i < ciclos*100; i++) {
			if(Thread.interrupted()){
				System.out.println(Thread.currentThread().getName()+ " ha sido interrumpido esperando.");
				throw new InterruptedException("Esperando");
			}
			suma += Math.random();
		}
		if(visible){
			String name = Thread.currentThread().getName();
			String formatSum = String.format("%9.2f",	suma);
			System.out.println(name + " ha sumado " + formatSum + " en milisegundos: " + (System.currentTimeMillis()-inicio) );
		}
	}
}
