public class Jardines {
	public static void main(String[] args) {
		Contador c = new Contador();
		Puerta p1 = new Puerta(c, 10);
		Puerta p2 = new Puerta(c, 10);
		p1.start();
		p2.start();
		try {
			p1.join();
			p2.join();
		} catch (InterruptedException e) {
			System.out.println("La hebra ha sido interrumpida");
		}
		System.out.println(c.valor());
	}
}