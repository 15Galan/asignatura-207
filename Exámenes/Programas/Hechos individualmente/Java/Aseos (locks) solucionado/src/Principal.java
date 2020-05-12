import java.util.concurrent.TimeUnit;

public class Principal {

	private static final int NUM_CLIENTES = 20;
	private static final long TIMEOUT = 5; // segundos que dura la simulaci�n
	private static final boolean LIMP_PREF = false; // true para justo (preferencia limpieza)
	private static final long ESPERA_INT = 5; // milisegundos entre interropciones a las hebras clientes

	public static void main(String[] args) throws InterruptedException {
		String name = Thread.currentThread().getName();
		IFactoriaAseos factoria = new FactoriaAseos();	

		IAseo aseo = factoria.crearAseo(LIMP_PREF); // true para aseo justo, false para injusto

		System.out.println(name + " Arranca con preferencia para limpieza: " + LIMP_PREF);
		System.out.println(name + " los aseos son " + aseo.getClass().getName()); // comprobamos que coge la clase deseada
	
		Cliente[] cliente = new Cliente[NUM_CLIENTES];
		for (int i = 0; i < cliente.length; i++){
			cliente[i] = new Cliente(i, aseo);
			cliente[i].start();
		}

		EquipoLimpieza equipo = new EquipoLimpieza(aseo);
		equipo.start();
		
		TimeUnit.SECONDS.sleep(TIMEOUT);

		System.out.println(name + " interrumpiendo hebras ----------> ---------> ----------> ==========>");
		equipo.interrupt();
		for (int i = 0; i < cliente.length; i++) {
			cliente[i].interrupt();
			TimeUnit.MILLISECONDS.sleep(ESPERA_INT);
		}
		System.out.println("Clientes dentro de aseos: "+ aseo.getAseos());
	}
}
