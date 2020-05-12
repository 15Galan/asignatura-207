public class FactoriaAseos implements IFactoriaAseos {

	@Override
	public IAseo crearAseo(boolean justo) {
		IAseo nuevoAseo;
		if (justo){
			nuevoAseo = new Aseos_lock_justo();
		} else { // aseo injusto
			nuevoAseo = new Aseos_lock_injusto();
		}
		return nuevoAseo;
	}

}
