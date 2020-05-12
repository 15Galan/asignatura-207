package pkgMonedasConBarra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Controlador implements ActionListener, PropertyChangeListener {
	private Worker w;
	private Panel panel;
	
	public Controlador(Panel panel) {
		this.panel = panel;
		this.w = new Worker(this.panel);
		w.controlador(this);
		w.execute();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("fin"))
			if(!w.isCancelled()){ 
				w.cancel(true);
			}

				
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("progress")){
			int progreso = (Integer)evt.getNewValue();
			panel.setProgreso(progreso);
			if (progreso >= 100){
				panel.setFin();
			}
		}
	}

}
