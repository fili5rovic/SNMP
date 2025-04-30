package variant7;

import java.io.IOException;
import java.util.ArrayList;

import org.snmp4j.CommunityTarget;
import org.snmp4j.smi.VariableBinding;

import router.Router;
import util.Monitor;
import util.OIDUtil;

public class SNMPMonitor extends Monitor {

	private SNMPFrame frame;
	
	public SNMPMonitor(Router[] routers) {
		super(routers);
		
		this.frame = new SNMPFrame();
		this.frame.setVisible(true);
	}

	@Override
	protected void mainUpdatedTask() throws IOException {
		ArrayList<Object[][]> data = new ArrayList<>();
		
		for(Router r : routers) {
			data.add(fetchRouterData(r));
		}
		
		for(int i = 0; i < routers.length ; i++) {
			frame.setData(routers[i], data.get(i));
		}
	}
	
	private Object[][] fetchRouterData(Router r) throws IOException {
		CommunityTarget target = Router.createTarget(r.getIP());
		
		ArrayList<VariableBinding> inPkts = getData(target, OIDUtil.OID_snmpInPkts);
		ArrayList<VariableBinding> outPkts = getData(target, OIDUtil.OID_snmpOutPkts);
		ArrayList<VariableBinding> outSetReqs = getData(target, OIDUtil.OID_snmpOutSetRequests);
		ArrayList<VariableBinding> outGetResponses = getData(target, OIDUtil.OID_snmpOutGetResponses);
		ArrayList<VariableBinding> traps = getData(target, OIDUtil.OID_snmpOutTraps);
		ArrayList<VariableBinding> outBadCommunity = getData(target, OIDUtil.OID_snmpBadCommunityNames);
		
		int colCount = SNMPFrame.getColumnNum();
		Object[][] data = new Object[1][colCount];
		
		data[0] = new Object[colCount];
		data[0][0] = inPkts.get(0).getVariable().toString();
		data[0][1] = outPkts.get(0).getVariable().toString();
		data[0][2] = outSetReqs.get(0).getVariable().toString();
		data[0][3] = outGetResponses.get(0).getVariable().toString();
		data[0][4] = traps.get(0).getVariable().toString();
		data[0][5] = outBadCommunity.get(0).getVariable().toString();
		
		
		return data;
	}

}
