package variant1;

import router.Router;

public class Main {
	
	public static void main(String[] args) {
		InterfaceMonitor interfaceMonitor = new InterfaceMonitor(Router.getRouters());
		interfaceMonitor.startMonitoring();
	}
}
