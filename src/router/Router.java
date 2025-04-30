package router;

import org.snmp4j.*;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;

public class Router {
	
	public static String COMMUNITY = "si2019";
	
	private String name;
	private String[] interfaces;
	private String ip;
	private CommunityTarget[] targets;
	
	private static Router R1 = new Router("R1","192.168.10.1", new String[]{
            "192.168.12.1",
            "192.168.13.1",
            "192.168.122.100",
            "192.168.10.1"
    });
	private static Router R2 = new Router("R2","192.168.20.1", new String[]{
            "192.168.12.2",
            "192.168.23.2",
            "192.168.20.1"
    });
	private static Router R3 = new Router("R3","192.168.30.1", new String[]{
            "192.168.23.3",
            "192.168.13.3",
            "192.168.30.1"
    });
	
	
	private Router(String name, String ip, String[] interfaces) {
		this.name = name;
		this.ip = ip;
		this.interfaces = interfaces;
		createTargets();
	}
	
	private void createTargets() {
		this.targets = new CommunityTarget[interfaces.length];
		
		for (int i = 0; i < interfaces.length; i++) {
			targets[i] = createTarget(interfaces[i]);
		}
		
	}
	
   
    public static CommunityTarget createTarget(String _ip) {
    	CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(COMMUNITY));
        target.setAddress(new UdpAddress(_ip + "/161"));
        
        target.setVersion(SnmpConstants.version2c);
        target.setTimeout(300);
        target.setRetries(1);
        
        return target;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getIP() {
    	return ip;
    }
    
	public String[] getInterfaces() {
		return this.interfaces;
	}
	
	public CommunityTarget[] getTargets() {
		return this.targets;
	}
	
	public static Router getR1() {
        return R1;
    }
	
	public static Router getR2() {
        return R2;
    }

    public static Router getR3() {
        return R3;
    }
    public static Router[] getRouters() {
    	return new Router[] {R1,R2,R3};
    }
	
}
