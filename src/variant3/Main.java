package variant3;

import router.Router;

public class Main {

	public static void main(String[] args) {
		RootTableMonitor monitor = new RootTableMonitor(Router.getRouters());
		monitor.startMonitoring();
	}
}
