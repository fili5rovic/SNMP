package variant9;

import java.io.IOException;
import java.util.ArrayList;

import org.snmp4j.CommunityTarget;
import org.snmp4j.smi.VariableBinding;

import router.Router;
import util.Monitor;
import util.OIDUtil;

public class SessionMonitor extends Monitor {
	
	private SessionFrame frame;

	public SessionMonitor(Router[] routers) {
		super(routers);
		TIMER_DURATION = 5000;
		this.frame = new SessionFrame();
		this.frame.setVisible(true);
	}

	@Override
	protected void mainUpdatedTask() throws IOException {
		Object[][] tcpData = fetchRouterDataTCP(routers[0]);
		Object[][] udpData = fetchRouterDataUDP(routers[0]);
		frame.setTCPData(tcpData);
		frame.setUDPData(udpData);
	}
	
	private Object[][] fetchRouterDataTCP(Router r) throws IOException {
		CommunityTarget target = Router.createTarget(r.getIP());
		
		ArrayList<VariableBinding> localAddrs = getData(target, OIDUtil.OID_tcpConnLocalAddress);
		ArrayList<VariableBinding> remoteAddrs = getData(target, OIDUtil.OID_tcpConnRemAddress);
		ArrayList<VariableBinding> localPorts = getData(target, OIDUtil.OID_tcpConnLocalPort);
		ArrayList<VariableBinding> remotePorts = getData(target, OIDUtil.OID_tcpConnRemPort);
		
		int rowCount = localAddrs.size();
		
		int colCount = SessionFrame.getTCPColumnNum();
		Object[][] data = new Object[rowCount][colCount];
		
		for(int i = 0; i < rowCount ;i++) {
			data[i] = new Object[colCount];
			
			data[i][0] = localAddrs.get(i).getVariable().toString();
			data[i][1] = remoteAddrs.get(i).getVariable().toString();
			data[i][2] = localPorts.get(i).getVariable().toString();
			data[i][3] = remotePorts.get(i).getVariable().toString();
			
		}
		
		return data;
	}
	
	private Object[][] fetchRouterDataUDP(Router r) throws IOException {
		CommunityTarget target = Router.createTarget(r.getIP());
		
		ArrayList<VariableBinding> localAddrs = getData(target, OIDUtil.OID_udpLocalAddress);
		ArrayList<VariableBinding> localPorts = getData(target, OIDUtil.OID_udpLocalPort);
		int rowCount = localAddrs.size();
		
		System.out.println(localPorts);
		
		int colCount = SessionFrame.getUDPColumnNum();
		Object[][] data = new Object[rowCount][colCount];
		
		for(int i = 0; i < rowCount ;i++) {
			data[i] = new Object[colCount];
			
			data[i][0] = localAddrs.get(i).getVariable().toString();
			data[i][1] = localPorts.get(i).getVariable().toString();
			
		}
		
		return data;
	}

}
