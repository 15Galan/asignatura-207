package pkgMonedasConBarra;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	private JLabel nCaras = new JLabel("Caras");
	private JTextField nCarasText = new JTextField(15);
	private JLabel nCruces = new JLabel("Cruces");
	private JTextField nCrucesText = new JTextField(15);
	private JLabel ratio = new JLabel("Ratio");
	private JTextField ratioText = new JTextField(15);
	private JButton fin = new JButton("fin");
	
	private JProgressBar barra = new JProgressBar(0, 100);
	private JLabel barraJL = new JLabel("En progreso");
	
	public Panel(){
		this.setLayout(new FlowLayout());
		add(nCaras);
		add(nCarasText);
		add(nCruces);
		add(nCrucesText);
		add(ratio);
		add(ratioText);
		add(fin);
		barra.setStringPainted(true);
		add(barraJL);
		add(barra);
	}

	public void controlador(Controlador ctr) {
		fin.addActionListener(ctr);
		fin.setActionCommand("FIN");
	}

	public void setNumCaras(String str) {
		nCarasText.setText(str);
	}

	public void setNumCruces(String str) {
		this.nCrucesText.setText(str);
	}

	public void setRatio(String str) {
		this.ratioText.setText(str);
	}
	
	public void setProgreso(int n){
		barra.setValue(n); // entre 0 y 100
	}

	public void setFin() {
		barraJL.setText("Finalizado ");
	}

}
