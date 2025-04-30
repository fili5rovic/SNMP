package variant2;

import router.Router;

public class Main {

	public static void main(String[] args) {
		PacketMonitor monitor = new PacketMonitor(Router.getRouters());
		monitor.startMonitoring();
	}
}
