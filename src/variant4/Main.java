package variant4;

import router.Router;

public class Main {

	public static void main(String[] args) {
		NBGPMonitor monitor = new NBGPMonitor(Router.getR1());
		monitor.startMonitoring();
	}
}
