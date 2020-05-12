package simpleProgreso;

import javax.swing.*;

public class Principal {

	public static void crearGUI(JFrame ventana){
		Panel panel = new Panel();
		Controlador ctr = new Controlador(panel);
		panel.controlador(ctr);
		ventana.setContentPane(panel);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.pack();
		ventana.setVisible(true);
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame ventana = new JFrame("GUI simple");
				crearGUI(ventana);		
			}
			
		});
		
	}
}
