import java.util.List;

public interface IAseo {

	List<Integer> getAseos();

	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera solo si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 * @throws InterruptedException 
	 * 
	 */
	void entroAseo(int id);

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * @throws InterruptedException 
	 * 
	 */
	void salgoAseo(int id);

	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 * @throws InterruptedException 
	 * 
	 */
	void entraEquipoLimpieza() throws InterruptedException;

	/**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
	 */
	void saleEquipoLimpieza();

}