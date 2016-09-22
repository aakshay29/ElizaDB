
public class Interrupt extends Thread{
	
	public void run(){
		for(int i = 0; i < 1000; i++){
			try {
				Interrupt.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("I want to know more!");	
		}
	}

}
