package variant2;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import router.Router;
import util.Monitor;
import util.OIDUtil;

public class PacketMonitor extends Monitor {
	
	private PacketFrame frame;
	
	private int sumInTraffic = 0, sumInUniTraffic = 0, sumInNonUniTraffic = 0;
	private int sumOutTraffic = 0, sumOutUniTraffic = 0, sumOutNonUniTraffic = 0;
	
	
	public PacketMonitor(Router[] routers) {
		super(routers);
		this.frame = new PacketFrame();
		this.frame.setTitle("Packet Monitor");
	}


	@Override
	protected void mainUpdatedTask() throws IOException {
	    int prevSumInTraffic = sumInTraffic;
	    int prevSumInUniTraffic = sumInUniTraffic;
	    int prevSumInNonUniTraffic = sumInNonUniTraffic;
	    
	    int prevSumOutTraffic = sumOutTraffic;
	    int prevSumOutUniTraffic = sumOutUniTraffic;
	    int prevSumOutNonUniTraffic = sumOutNonUniTraffic;
	    
	    for (Router r : routers) {
	        CommunityTarget target = Router.createTarget(r.getIP());
	        
	        sumInTraffic += getSumFromOid(target, OIDUtil.OID_IF_IN_OCTETS);
	        sumInUniTraffic += getSumFromOid(target, OIDUtil.OID_IF_IN_UNICAST_OCTETS);
	        sumInNonUniTraffic += getSumFromOid(target, OIDUtil.OID_IF_IN_NON_UNICAST_OCTETS);
	        
	        sumOutTraffic += getSumFromOid(target, OIDUtil.OID_IF_OUT_OCTETS);
	        sumOutUniTraffic += getSumFromOid(target, OIDUtil.OID_IF_OUT_UNICAST_OCTETS);
	        sumOutNonUniTraffic += getSumFromOid(target, OIDUtil.OID_IF_OUT_NON_UNICAST_OCTETS);
	    }
	    
	    int protokSumInTraffic = 8 * (sumInTraffic - prevSumInTraffic) / TIMER_DURATION;  // Traffic rate in bps
	    int protokSumInUniTraffic = 8 * (sumInUniTraffic - prevSumInUniTraffic) / TIMER_DURATION;  // Unicast rate in bps
	    int protokSumInNonUniTraffic = 8 * (sumInNonUniTraffic - prevSumInNonUniTraffic) / TIMER_DURATION;  // Non-unicast rate in bps
	    
	    int protokSumOutTraffic = 8 * (sumOutTraffic - prevSumOutTraffic) / TIMER_DURATION;  // Traffic rate out in bps
	    int protokSumOutUniTraffic = 8 * (sumOutUniTraffic - prevSumOutUniTraffic) / TIMER_DURATION;  // Unicast rate out in bps
	    int protokSumOutNonUniTraffic = 8 * (sumOutNonUniTraffic - prevSumOutNonUniTraffic) / TIMER_DURATION;  // Non-unicast rate out in bps

	    System.out.println("- Snapshot -");
	    System.out.println("Protok Sum In Traffic (bps): " + protokSumInTraffic);
	    System.out.println("Protok Sum In Unicast Traffic (bps): " + protokSumInUniTraffic);
	    System.out.println("Protok Sum In Non-Unicast Traffic (bps): " + protokSumInNonUniTraffic);
	    
	    System.out.println("Protok Sum Out Traffic (bps): " + protokSumOutTraffic);
	    System.out.println("Protok Sum Out Unicast Traffic (bps): " + protokSumOutUniTraffic);
	    System.out.println("Protok Sum Out Non-Unicast Traffic (bps): " + protokSumOutNonUniTraffic);

	    
	    frame.addTotalTraffic(protokSumInTraffic, protokSumOutTraffic);
	    frame.addTotalUnicastTraffic(protokSumInUniTraffic, protokSumOutUniTraffic);
	    frame.addTotalNonUnicastTraffic(protokSumInNonUniTraffic, protokSumOutNonUniTraffic);
	}

	
	private int getSumFromOid(CommunityTarget target, OID oid) throws NumberFormatException, IOException {
		int sum = 0;
		for(VariableBinding vb : getData(target, oid)) {
			sum += Integer.parseInt(vb.getVariable().toString());
		}
		return sum;
	}

}
