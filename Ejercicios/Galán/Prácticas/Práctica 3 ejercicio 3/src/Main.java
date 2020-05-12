public class Main {
	
	public static void main(String[]args){
		
		VariableCompartida v = new VariableCompartida();
		
		Thread T1 = new Thread(v){
			@Override
			
			public void run(){
				
				for(int i = 0; i < 100; i++){
					
					v.set(i);
					
					try{
						
						wait(100);
					
					}catch(Exception e){
						
						
					}
				}
			}
			
		};
		
		Thread T2 = new Thread(v){
			
			@Override
			
			public void run(){
				
				int i = 1;
				
				while(T1.isAlive()){
					
					System.out.println("["+i+"] "+v.get());
					
					i++;
				}
			}
		};
		
	    T2.start();
	    T1.start();
		
//		System.out.println(v.get());
	}
}
