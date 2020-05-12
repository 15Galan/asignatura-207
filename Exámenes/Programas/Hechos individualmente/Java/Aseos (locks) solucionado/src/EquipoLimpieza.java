public class EquipoLimpieza extends Thread{
	
	private IAseo aseo;
	private boolean limpiando = false; // para controlar si nos interrumpen en el aespera entre sentrar y salir

	public EquipoLimpieza(IAseo aseo){
		this.aseo = aseo;
	}
	
	public void run(){
		Thread.currentThread().setName("Equipo-Limpieza");
		while (true){
			try {
				aseo.entraEquipoLimpieza();
				limpiando = true;
				EsperaActiva.sleep(3000, false);
				aseo.saleEquipoLimpieza();
				limpiando = false;
				EsperaActiva.sleep(1000, false);

			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " finalizando.");
				if(limpiando) aseo.saleEquipoLimpieza(); // si se comenta esta linea se quedaran algunos proceso bloqueados y no termina el prugramas
				return;
			}		
		}
	}
}
