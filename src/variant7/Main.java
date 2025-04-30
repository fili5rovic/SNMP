package variant7;

import router.Router;

public class Main {
	 public static void main(String[] args) {
		SNMPMonitor monitor = new SNMPMonitor(Router.getRouters());
		monitor.startMonitoring();
	 }
}
