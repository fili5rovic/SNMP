package variant9;

import router.Router;

public class Main {

	public static void main(String[] args) {
		SessionMonitor monitor = new SessionMonitor(Router.getRouters());
		monitor.startMonitoring();
	}
}
