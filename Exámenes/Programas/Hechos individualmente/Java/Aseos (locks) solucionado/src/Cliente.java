public class Cliente extends Thread{
	
	private int id;
	private IAseo aseo;

	public Cliente(int id, IAseo aseo){
		this.id = id;
		this.aseo = aseo;
	}
	
	public void run(){
		Thread.currentThread().setName("Cliente_"+id);		
		while (true){		
			try {
				EsperaActiva.sleep(3000, false);

				aseo.entroAseo(id);
				EsperaActiva.sleep(1000, false);
				aseo.salgoAseo(id);
				
				EsperaActiva.sleep(3000, false);

			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " finalizando.");
				if(aseo.getAseos().contains(id)){
					System.out.println(Thread.currentThread().getName() + " sale del aseo antes de terminar.");
					aseo.salgoAseo(id); // si se comenta esta linea alguno se puede quedar dentro
				}
				return;
			}
		}
	}

}
