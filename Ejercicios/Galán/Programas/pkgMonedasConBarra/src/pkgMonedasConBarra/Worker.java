package pkgMonedasConBarra;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Void, FlipFlap>{
	private static final long NUM_TIRADAS = 100_000_000;
	Panel panel;

	public Worker(Panel panel) {
		this.panel = panel;
	}

	@Override
	protected Void doInBackground() throws Exception {
		int caras = 0;
		int cruces = 0;
		Random r = new Random();
		int progreso = 10;
		this.setProgress(progreso);
		
		System.out.println("Arranca worker");
		for (int i = 0; i < NUM_TIRADAS && !isCancelled(); i++) {
			if(r.nextBoolean())
				caras++;
			else
				cruces++;
			// TimeUnit.MILLISECONDS.sleep(100);

			publish(new FlipFlap(caras, cruces));
			
			progreso = (int) (((double)i/(double)NUM_TIRADAS)*100);
			// System.out.println(progreso);
			this.setProgress(progreso);
		}
		this.setProgress(100);
		return null;
	}

	protected void process(List<FlipFlap> pares){
		FlipFlap ultimo = pares.get(pares.size()-1);
		String carasStr = String.format("%,d", ultimo.getCaras());
		String crucesStr = String.format("%,d", ultimo.getCruces());
		double ratio = ((double)(ultimo.getCaras())/ultimo.getCruces());
		String ratioStr = String.format("%8.6f", ratio);
		
		panel.setNumCaras(carasStr);
		panel.setNumCruces(crucesStr);
		panel.setRatio(ratioStr);
	}
	
	public void controlador(PropertyChangeListener ctr){
		this.addPropertyChangeListener(ctr);
	}
}
