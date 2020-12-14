
public class TrafficControllerSimple implements TrafficController {

	private TrafficRegistrar registrar;
	private boolean BridgeEmptyBool = true;
	
	public TrafficControllerSimple(TrafficRegistrar registrar) {
		this.registrar = registrar;
	}
	
	
	
	@Override
	public synchronized void enterLeft(Vehicle v) {
		while(!BridgeEmptyBool) {		//other Car on bridge
			try{ wait(); }			//wait till notified
			catch(InterruptedException e) { System.out.println(e.getMessage()); }		
		}
		BridgeEmptyBool = false;		//this.car on bridge
		registrar.registerLeft(v);
	}
	
	@Override
	public synchronized void leaveRight(Vehicle v) {
		BridgeEmptyBool = true;			//car left bridge - now empty
		registrar.deregisterRight(v);
		notifyAll();				//waiting cars can enter bridge again
	}

	
	
	@Override
	public synchronized void enterRight(Vehicle v) {
		while(!BridgeEmptyBool) {
			try{ wait(); }
			catch(InterruptedException e) { System.out.println(e.getMessage()); }
		}
		BridgeEmptyBool = false;
		registrar.registerRight(v);
	}

	@Override
	public synchronized void leaveLeft(Vehicle v) {
		BridgeEmptyBool = true;
		registrar.deregisterLeft(v);
		notifyAll();
	}
	
}
