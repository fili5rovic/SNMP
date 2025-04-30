package variant8;

import router.Router;

public class Main {

	public static void main(String[] args) {
		ProcessMonitor monitor = new ProcessMonitor(Router.getRouters());
		monitor.startMonitoring();
	}
}
