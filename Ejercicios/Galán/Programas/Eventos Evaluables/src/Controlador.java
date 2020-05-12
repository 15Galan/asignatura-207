package simple;
import java.awt.event.*;
import java.util.*;
public class Controlador implements ActionListener{

	private Panel panel;
	public Controlador(Panel panel){
		this.panel = panel;
	}
	

	private boolean esPrimo(int p) {
		if (p == 1 || p == 2)
			return true;
		else {
			int div = 2;
			boolean esPrimo = true;
			while (esPrimo && div * div <= p) {
				if (p % div == 0)
					esPrimo = false;
				div++;
			}
			return esPrimo;
		}
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
				List<Integer> lista = new ArrayList<Integer>();
				int p = 0; int primo = 1;
				while (p<n){
					if (esPrimo(primo)){
						lista.add(primo);
						p++;
					}
					primo++;
				}
				panel.listaPrimos(lista);
				
			}catch(NumberFormatException ie){
				panel.mensaje("Error: no es un numero");
			}
		}
	}

}
