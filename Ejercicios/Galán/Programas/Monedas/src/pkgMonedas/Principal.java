package pkgMonedas;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Principal {

	public static void main(String[] args) {
		final JFrame miVentana = new JFrame("Monedas");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				crearGUI(miVentana);	
			}
		});
	}

	private static void crearGUI(JFrame miVentana) {
		Panel panel = new Panel();
		Controlador ctr = new Controlador(panel);
		panel.controlador(ctr);
		
		// Propiedades de la la ventana principal (Frame)
		miVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miVentana.setContentPane(panel);
		miVentana.pack();
		miVentana.setLocation(300, 300);
		miVentana.setVisible(true);
	}
}
