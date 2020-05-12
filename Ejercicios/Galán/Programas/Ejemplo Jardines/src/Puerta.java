public class Puerta extends Thread {
	private Contador visitantes;
	private int iter;

	public Puerta(Contador c, int iter) {
		visitantes = c;
		this.iter = iter;
	}

	public void run() {
		for (int i = 0; i < iter; i++) {
			visitantes.entra();
		}
	}
}
