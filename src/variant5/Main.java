package variant5;
import org.snmp4j.*;

import router.Router;

public class Main {
	
	public static void main(String[] args) {
        BgpMonitor monitor = new BgpMonitor(Router.getR1());
        monitor.startMonitoring();
    }
	
}
