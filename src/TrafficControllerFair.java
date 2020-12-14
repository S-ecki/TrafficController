import java.util.concurrent.locks.*;

public class TrafficControllerFair implements TrafficController {
	
	private TrafficRegistrar registrar;
	private boolean bridgeEmptyBool = true;
	Lock lock = new ReentrantLock(true);
	Condition bridgeEmptyCond = lock.newCondition();
	
	public TrafficControllerFair(TrafficRegistrar r) {
		this.registrar = r;
	}
	
	
	@Override
	public void enterLeft(Vehicle v) {
		try {
			
			lock.lock();	//only one thread can enter any locked method
			while (!bridgeEmptyBool) { bridgeEmptyCond.await(); }	//car on  bridge - wait
			bridgeEmptyBool = false;		//this car on bridge now
			registrar.registerLeft(v);
			
		} catch(InterruptedException e) { System.out.println(e.getMessage()); }
		//dont unlock - only thread (vehicle) with lock can invoke a method -> only this vehicle can invoke leave
	}
	
	@Override
	public void leaveRight(Vehicle v) { 
		try {
			
		//no need to lock - still locked from enter
			bridgeEmptyBool = true;		//car left - bridge empty again
			registrar.deregisterRight(v);
			bridgeEmptyCond.signalAll();	//waiting cars can enter
			
		} finally { lock.unlock(); }
	}
	
	
	@Override
	public void enterRight(Vehicle v) { 
		try {
			
			lock.lock();
			while (!bridgeEmptyBool) { bridgeEmptyCond.await(); }
			bridgeEmptyBool = false;
			registrar.registerRight(v);
			
		} catch(InterruptedException e) { System.out.println(e.getMessage()); }
	}
	
	@Override
	public void leaveLeft(Vehicle v) {  
		try {
			
			bridgeEmptyBool = true;
			registrar.deregisterLeft(v);
			bridgeEmptyCond.signalAll();
			
		} finally { lock.unlock(); }
	}
	
}
