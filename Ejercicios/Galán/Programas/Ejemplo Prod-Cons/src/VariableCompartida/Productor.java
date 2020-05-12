package VariableCompartida;

import VariableCompartida.Variable;

import java.util.Random;

public class Productor extends Thread {

	private static Random r = new Random();
	private int numIter;
	private Variable<Integer> var;

	public Productor(int numIter, Variable<Integer> var) {
		this.numIter = numIter;
		this.var = var;
	}

	public void run() {
		int nDato = 0;
		for (int i = 0; i < numIter; i++) {
			nDato = r.nextInt(100);
			System.out.println("VariableCompartida.Productor " + nDato);
			var.almacena(nDato);
		}
	}
}
