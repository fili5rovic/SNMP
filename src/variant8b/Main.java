package variant8b;

import router.Router;

public class Main {

	public static void main(String[] args) {
		ProcessMonitorB monitor = new ProcessMonitorB(Router.getRouters());
		monitor.startMonitoring();
	}
}
