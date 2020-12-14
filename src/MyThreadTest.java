
public class MyThreadTest{
	private static final int N = 20;
	

	public static void main(String[] args) {
		TrafficControllerSimple controller = new TrafficControllerSimple(new TrafficRegistrarTest());
		
		Runnable leftCars = () -> {
			for(int i = 0; i < N; ++i) { MyVehicle v = new MyVehicle(); controller.enterLeft(v); controller.leaveRight(v); } }; 
		Runnable rightCars = () -> {
			for(int i = 0; i < N; ++i) { MyVehicle v = new MyVehicle(); controller.enterRight(v); controller.leaveLeft(v); } }; 
			
		Thread leftCarThread = new Thread(leftCars);
		Thread rightCarThread = new Thread(rightCars);
		Thread leftCarThread2 = new Thread(leftCars);
		Thread rightCarThread2 = new Thread(rightCars);
		
		leftCarThread.start();
		rightCarThread.start();
		leftCarThread2.start();
		rightCarThread2.start();
				
	}
}