package Locks;

import BASE.*;

public class PrincipalLocks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Buffer b = new BufferLocks();
		Productor p = new Productor(b);
		Consumidor c = new Consumidor(b);
		p.start();c.start();
	}

}
