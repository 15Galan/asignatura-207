package VariableCompartida;

public class Variable<T> {

	private T var;
	private boolean hayDato = false;

	public void almacena(T v) { // Setter()
		while (hayDato == true) {
			Thread.yield();
		}
		var = v;
		hayDato = true;
	}

	public T extrae() { // Getter()
		while(hayDato == false){
			Thread.yield();
		}
		hayDato = false;
		return var;
	}
}
