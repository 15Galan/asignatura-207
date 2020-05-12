package VariableCompartida;

import VariableCompartida.Variable;

public class Consumidor extends Thread {

	private int numIter;
	private Variable<Integer> var;

	public Consumidor(int numIter, Variable<Integer> var) {
		this.numIter = numIter;
		this.var = var;
	}

	public void run() {
		int nDato = 0;
		for (int i = 0; i < numIter; i++) {
			nDato = var.extrae();
			System.out.println("VariableCompartida.Consumidor " + nDato);
		}
	}
}
