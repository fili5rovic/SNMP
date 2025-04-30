package variant5;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.*;

import router.RouteChangeListener;
import router.Router;
import util.Monitor;
import util.OIDUtil;

import java.io.IOException;
import java.util.ArrayList;

public class BgpMonitor extends Monitor implements RouteChangeListener {
    private BGPFrame frame;
    
    private String[] cols = {"Route", "Origin", "AS-Path", "Next Hop", 
    		"MED", "Local Preference","Atomic Aggregate",
    		"Aggregator AS", "Aggregator Address", "Best Route"};
    
    
    public BgpMonitor(Router router) {
    	super(new Router[] {router});
    	this.frame = new BGPFrame("BGP Table", cols);
    	this.frame.setRouteChangeListener(this);
    }
    
    @Override
	public void onChange(Router r) {
		routers[0] = r;
		timer.cancel();
		frame.clearTable();
		startMonitoring();
	}
    
    @Override
	protected void mainUpdatedTask() throws IOException {
    	updateTable();
	}

    private void updateTable() throws IOException {
        System.out.println("Sending request...");
        
        BGPTable table = getBGPData();
        
        ArrayList<String[]> rows = table.getTableData();
        
        frame.clearTable();
        
        for(String[] row : rows) {
        	frame.getModel().addRow(row);
        }

        frame.resize();
    }
    
    
    private BGPTable getBGPData() throws IOException {
    	BGPTable bgpTable = new BGPTable();
    	
    	CommunityTarget target = Router.createTarget(routers[0].getIP());
        
        for(OID startOID : OIDUtil.getBGPOIDS()) {
            for(BGPTableRow row : getOIDRows(target, startOID)) {
            	bgpTable.addRow(row);
            } 
        }
        return bgpTable;
    }
    
    //TODO too slow
    private ArrayList<BGPTableRow> getOIDRows(CommunityTarget target, OID startOID) throws IOException {
        ArrayList<BGPTableRow> rows = new ArrayList<>();
    	for(VariableBinding vb: getData(target, startOID, null)) {
    		String oidAddress = OIDUtil.getAddressFromOID(vb.getOid());
            String oidNoAddress = OIDUtil.getOidStringWithoutAddress(vb.getOid());
            
            rows.add(new BGPTableRow(oidNoAddress, oidAddress, vb.getVariable().toString()));
    	}
		return rows;
    }
}
