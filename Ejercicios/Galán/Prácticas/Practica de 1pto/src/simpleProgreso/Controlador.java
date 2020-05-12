package simpleProgreso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Controlador implements ActionListener, PropertyChangeListener{

	private Panel panel;

	public Controlador(Panel panel){
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("SI")){
			panel.mensaje("Sí pulsado");

		} else if (e.getActionCommand().equals("NO")){
			panel.mensaje("No pulsado");

		} else if (e.getActionCommand().equals("PRIMOS")){
			int n = panel.numero();

			try{
				panel.limpiarArea();
				panel.progreso(0);
				Worker w = new Worker(n, panel);
				w.addPropertyChangeListener(this);
				w.execute();
				
			}catch(NumberFormatException ie){
				panel.mensaje("Error: no es un numero.");
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evento) {
		if(evento.getPropertyName().equals("progress")){
			panel.progreso((Integer)evento.getNewValue());
		}
	}
}
