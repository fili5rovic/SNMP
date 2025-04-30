package variant1;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import org.snmp4j.CommunityTarget;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import router.Router;
import util.Monitor;
import util.OIDUtil;
import variant1.tree.ColoredTreeNode;
import variant5.BGPTableRow;

public class InterfaceMonitor extends Monitor {
	
	private InterfaceFrame frame;
	
	private String[] iProperties = new String[] {"Opis","Tip","MTU",
			 "Brzina","Fizicka adresa",
			 "Administrativni status", 
			 "Operativni status"};

	public InterfaceMonitor(Router[] routers) {
		super(routers);
		this.frame = new InterfaceFrame("Interface Monitor");
		
		this.frame.setVisible(true);
	}

	@Override
	protected void mainUpdatedTask() throws IOException {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Network");
		for(Router r: routers) {
			displayRouterData(r, root);
		}
		frame.updateTree(root);
		
		System.out.println("Updated Tree");
	}
	
	private void displayRouterData(Router r, DefaultMutableTreeNode root) throws IOException {
		CommunityTarget target = Router.createTarget(r.getIP());
		
		ArrayList<VariableBinding> vbs = getData(target, OIDUtil.OID_ENTRY, OIDUtil.OID_LAST_CHANGE);
		String interfaceNumberStr = readSingleValue(target, new OID(".1.3.6.1.2.1.2"));
		
		if(interfaceNumberStr.equals("ERROR"))
			return;
		int interfaceNumber = Integer.parseInt(interfaceNumberStr);
		
		DefaultMutableTreeNode routerNode = new ColoredTreeNode(r.getName(),Color.ORANGE);
		DefaultMutableTreeNode interfaces[] = new DefaultMutableTreeNode[interfaceNumber];
		
		// create interface nodes
		for(int i = 0; i < interfaceNumber; i++) {
			String oidStr = vbs.get(i).getOid().toString();
			String interfaceName = OIDUtil.getLastNumberInOID(oidStr);
			interfaces[i] = new ColoredTreeNode(interfaceName, Color.GRAY);
			
			routerNode.add(interfaces[i]);
		}
		
		
		for(int i = interfaceNumber; i < vbs.size(); i++) {
			VariableBinding vb = vbs.get(i);
			
			int cnt = i - interfaceNumber;
			int propertyIndex = cnt /interfaceNumber;
			int interfaceIndex = cnt % interfaceNumber;
		
			String val = vb.getVariable().toString();
			
			String property = iProperties[propertyIndex];
			String nodeValue = property +": " + val;
			
			DefaultMutableTreeNode node;
			if(statusProperty(property)) {
				node = new ColoredTreeNode(property, val.equals("1")? Color.GREEN:Color.RED);
			} else {
				node = new ColoredTreeNode(nodeValue, Color.LIGHT_GRAY);
			}
			
			interfaces[interfaceIndex].add(node);
		}
		root.add(routerNode);
		
	}
	
	private boolean statusProperty(String property) {
		int size = iProperties.length;
		return property.equals(iProperties[size-1]) ||
				property.equals(iProperties[size-2]); 
		
	}
	
	

}
