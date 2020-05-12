package sensores;

public class Principal {
	public static void main (String []args){
		Mediciones m=new Mediciones();
		Sensor[] s=new Sensor[3];
		for(int i=0;i<s.length;i++){
			s[i]=new Sensor(i,m);
		}
		for(int i=0;i<s.length;i++){
			s[i].start();
		}
		Worker w =new Worker(m);
		
		w.start();
		}
}
