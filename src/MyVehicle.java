
public class MyVehicle implements Vehicle {
	
	private static int countCars = 0;
	private int id;
	
	public MyVehicle() {
		this.id = countCars++;
	}

	@Override
	public int getId() {
		return this.id;
	}
}
