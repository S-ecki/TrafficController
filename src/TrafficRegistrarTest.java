
public class TrafficRegistrarTest implements TrafficRegistrar{

	@Override
	public void registerLeft(Vehicle v) {
		System.out.println("\nLeft! - T" + Thread.currentThread().getId() + " registered left, car with ID: " + v.getId());
	}

	@Override
	public void registerRight(Vehicle v) {
		System.out.println("\nRight! - T" + Thread.currentThread().getId() + " registered right, car with ID: " + v.getId());
	}

	@Override
	public void deregisterLeft(Vehicle v) {
		System.out.println("\n     T" + Thread.currentThread().getId() + " deregistered left, car with ID: " + v.getId());
	}

	@Override
	public void deregisterRight(Vehicle v) {
		System.out.println("\n     T" + Thread.currentThread().getId() + " deregistered right, car with ID: " + v.getId());
	}
	
}
