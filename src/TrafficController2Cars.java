
public class TrafficController2Cars implements TrafficController{

	private TrafficRegistrar registrar;
	private boolean BridgeLeft1Car = false;
	private boolean BridgeLeft2Car = false;
	private boolean BridgeRight1Car = false;
	private boolean BridgeRight2Car = false;
	
	public TrafficController2Cars(TrafficRegistrar registrar) {
		this.registrar = registrar;
	}
	
	
	
	@Override
	public synchronized void enterLeft(Vehicle v) {
		while(BridgeRight1Car || BridgeLeft2Car) {
			try{ wait(); }
			catch(InterruptedException e) { System.out.println(e.getMessage()); }
		}
		if(BridgeLeft1Car) BridgeLeft2Car = true;
		else { BridgeLeft1Car = true; }
		
		registrar.registerRight(v);
	}
	
	@Override
	public synchronized void leaveRight(Vehicle v) {
		if(BridgeLeft2Car)  BridgeLeft2Car = false;
		else { BridgeLeft1Car = false; }
		registrar.deregisterLeft(v);
		notifyAll();
	}

	
	
	@Override
	public synchronized void enterRight(Vehicle v) {
		while(BridgeLeft1Car || BridgeRight2Car) {
			try{ wait(); }
			catch(InterruptedException e) { System.out.println(e.getMessage()); }
		}
		if(BridgeRight1Car) BridgeRight2Car = true;
		else { BridgeRight1Car = true; }
		
		registrar.registerRight(v);
	}

	@Override
	public synchronized void leaveLeft(Vehicle v) {
		if(BridgeRight2Car)  BridgeRight2Car = false;
		else { BridgeRight1Car = false; }
		registrar.deregisterLeft(v);
		notifyAll();
	}
	
}
