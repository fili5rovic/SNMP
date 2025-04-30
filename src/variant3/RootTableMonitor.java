package variant3;

import java.io.IOException;
import java.util.ArrayList;

import org.snmp4j.CommunityTarget;
import org.snmp4j.smi.VariableBinding;

import router.Router;
import util.Monitor;
import util.OIDUtil;

public class RootTableMonitor extends Monitor {

	private RootTableFrame frame;
	
	public RootTableMonitor(Router[] routers) {
		super(routers);
		
		this.frame = new RootTableFrame();
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
		
		ArrayList<VariableBinding> routes = getData(target, OIDUtil.OID_IP_ROUTE_DEST);
		ArrayList<VariableBinding> masks = getData(target, OIDUtil.OID_IP_ROUTE_MASK);
		ArrayList<VariableBinding> nextHops = getData(target, OIDUtil.OID_IP_ROUTE_NEXTHOP);
		ArrayList<VariableBinding> protocols = getData(target, OIDUtil.OID_IP_ROUTE_PROTOCOL);
		
		int rowCount = routes.size();
		int colCount = RootTableFrame.getColumnNum();
		Object[][] data = new Object[rowCount][colCount];
		
		for(int i = 0; i < rowCount ;i++) {
			data[i] = new Object[colCount];
			
			data[i][0] = routes.get(i).getVariable().toString();
			data[i][1] = masks.get(i).getVariable().toString();
			data[i][2] = nextHops.get(i).getVariable().toString();
			data[i][3] = protocols.get(i).getVariable().toString();
		}
		
		return data;
	}

}
