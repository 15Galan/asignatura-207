package pkgMonedas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
	private Worker w;
	
	public Controlador(Panel panel) {
		this.w = new Worker(panel);
		w.execute();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("fin"))
			w.cancel(true);
	}

}
