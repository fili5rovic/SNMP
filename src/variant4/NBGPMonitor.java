package variant4;

import java.io.IOException;
import java.util.ArrayList;

import org.snmp4j.CommunityTarget;
import org.snmp4j.smi.VariableBinding;

import router.RouteChangeListener;
import router.Router;
import util.Monitor;
import util.OIDUtil;

import variant5.BGPFrame;

public class NBGPMonitor extends Monitor implements RouteChangeListener {
	
	private BGPFrame frame;
	
	private static String[] cols = new String[] {"ID","State","Version",
			"IP","AS","Total in", "Total out","Keep Alive","Peer Time"};

	public NBGPMonitor(Router router) {
		super(new Router[] {router});
		
		frame = new BGPFrame("BGP Neighbour monitor", cols);
		frame.setRouteChangeListener(this);
	}

	@Override
	protected void mainUpdatedTask() throws IOException {
		Object[][] data = fetchRouterData(routers[0]);
		frame.setData(data);
	}
	
	private Object[][] fetchRouterData(Router r) throws IOException {
		CommunityTarget target = Router.createTarget(r.getIP());
		
		ArrayList<VariableBinding> identifiers = getData(target, OIDUtil.OID_BGP_PEER_IDENTIFIER);
		ArrayList<VariableBinding> states = getData(target, OIDUtil.OID_BGP_PEER_STATE);
		ArrayList<VariableBinding> versions = getData(target, OIDUtil.OID_BGP_PEER_VERSION);
		ArrayList<VariableBinding> ips = getData(target, OIDUtil.OID_BGP_PEER_IP);
		ArrayList<VariableBinding> as = getData(target, OIDUtil.OID_BGP_PEER_AS);
		ArrayList<VariableBinding> totalIn = getData(target, OIDUtil.OID_BGP_PEER_TOTAL_IN);
		ArrayList<VariableBinding> totalOut = getData(target, OIDUtil.OID_BGP_PEER_TOTAL_OUT);
		ArrayList<VariableBinding> keepAlive = getData(target, OIDUtil.OID_BGP_PEER_KEEP_ALIVE);
		ArrayList<VariableBinding> peerTime = getData(target, OIDUtil.OID_BGP_PEER_TIME);
		
		
		int rowCount = identifiers.size();
		int colCount = frame.getColumnNum();
		Object[][] data = new Object[rowCount][colCount];
		
		for(int i = 0; i < rowCount ;i++) {
			data[i] = new Object[colCount];
			
			data[i][0] = identifiers.get(i).getVariable().toString();
			data[i][1] = states.get(i).getVariable().toString();
			data[i][2] = versions.get(i).getVariable().toString();
			data[i][3] = ips.get(i).getVariable().toString();
			data[i][4] = as.get(i).getVariable().toString();
			data[i][5] = totalIn.get(i).getVariable().toString();
			data[i][6] = totalOut.get(i).getVariable().toString();
			data[i][7] = keepAlive.get(i).getVariable().toString();
			data[i][8] = peerTime.get(i).getVariable().toString();
		}
		
		return data;
	}

	@Override
	public void onChange(Router r) {
		routers[0] = r;
		timer.cancel();
		frame.clearTable();
		startMonitoring();
	}

}
