package pkgMonedas;

import java.util.List;
import java.util.Random;

import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Void, FlipFlap>{
	Panel panel;

	public Worker(Panel panel) {
		this.panel = panel;
	}

	@Override
	protected Void doInBackground() throws Exception {
		int caras = 0;
		int cruces = 0;
		Random r = new Random();
		
		System.out.println("Arranca worker");
		while(!isCancelled()){
			if(r.nextBoolean())
				caras++;
			else
				cruces++;
			publish(new FlipFlap(caras, cruces));
	
		}
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
}
