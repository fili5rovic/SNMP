package variant6;


import java.text.SimpleDateFormat;

import org.snmp4j.CommandResponder;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;

import router.Router;
import util.Monitor;

public class TrapHandler {

	private Router[] routers;
	private TrapFrame frame;
	
	public TrapHandler(Router[] r) {
		this.routers = r;
		this.frame = new TrapFrame();
	}
	
	public void start() {
		frame.setVisible(true);
	}
    

}
