package util;

import org.snmp4j.smi.OID;

public class OIDUtil {
	
	//BGP
	public static OID OID_ORIGIN = new OID(".1.3.6.1.2.1.15.6.1.4");
    private static OID OID_ASPATH = new OID(".1.3.6.1.2.1.15.6.1.5");
    private static OID OID_NEXTHOP = new OID(".1.3.6.1.2.1.15.6.1.6");
    private static OID OID_MED = new OID(".1.3.6.1.2.1.15.6.1.7");
    private static OID OID_LP = new OID(".1.3.6.1.2.1.15.6.1.8");
    private static OID OID_ATOMIC_AGGR = new OID(".1.3.6.1.2.1.15.6.1.9");
    private static OID OID_AGGR_AS = new OID(".1.3.6.1.2.1.15.6.1.10");
    private static OID OID_AGGR_ADDR = new OID(".1.3.6.1.2.1.15.6.1.11");
    private static OID OID_BEST = new OID(".1.3.6.1.2.1.15.6.1.13");
    
    // Interface monitoring
    public static OID OID_ENTRY = new OID(".1.3.6.1.2.1.2.2.1");
    public static OID OID_FINDEX = new OID(".1.3.6.1.2.1.2.2.1.1");
    public static OID OID_LAST_CHANGE = new OID(".1.3.6.1.2.1.2.2.1.9");
    
    // Packet monitoring
    public static OID OID_IF_IN_OCTETS = new OID(".1.3.6.1.2.1.2.2.1.10");
    public static OID OID_IF_IN_UNICAST_OCTETS = new OID(".1.3.6.1.2.1.2.2.1.11");
    public static OID OID_IF_IN_NON_UNICAST_OCTETS = new OID(".1.3.6.1.2.1.2.2.1.12");
    
    public static OID OID_IF_OUT_OCTETS = new OID(".1.3.6.1.2.1.2.2.1.16");
    public static OID OID_IF_OUT_UNICAST_OCTETS = new OID(".1.3.6.1.2.1.2.2.1.17");
    public static OID OID_IF_OUT_NON_UNICAST_OCTETS = new OID(".1.3.6.1.2.1.2.2.1.18");
    
    
    // Route table monitoring
    public static OID OID_IP_ROUTE_DEST = new OID(".1.3.6.1.2.1.4.21.1.1");
    public static OID OID_IP_ROUTE_MASK = new OID(".1.3.6.1.2.1.4.21.1.11");
    public static OID OID_IP_ROUTE_NEXTHOP = new OID(".1.3.6.1.2.1.4.21.1.7");
    public static OID OID_IP_ROUTE_PROTOCOL = new OID(".1.3.6.1.2.1.4.21.1.9");
    
    // BGP Neighbor monitoring
    public static OID OID_BGP_PEER_IDENTIFIER = new OID(".1.3.6.1.2.1.15.3.1.1");
    public static OID OID_BGP_PEER_STATE = new OID(".1.3.6.1.2.1.15.3.1.2");
    public static OID OID_BGP_PEER_VERSION = new OID(".1.3.6.1.2.1.15.3.1.4");
    public static OID OID_BGP_PEER_IP = new OID(".1.3.6.1.2.1.15.3.1.5");
    public static OID OID_BGP_PEER_AS = new OID(".1.3.6.1.2.1.15.3.1.9");
    public static OID OID_BGP_PEER_TOTAL_IN = new OID(".1.3.6.1.2.1.15.3.1.10");
    public static OID OID_BGP_PEER_TOTAL_OUT = new OID(".1.3.6.1.2.1.15.3.1.11");
    public static OID OID_BGP_PEER_KEEP_ALIVE = new OID(".1.3.6.1.2.1.15.3.1.19");
    public static OID OID_BGP_PEER_TIME = new OID(".1.3.6.1.2.1.15.3.1.16");
    
    // Process monitor
    public static OID OID_ciscoMemoryPoolName = new OID(".1.3.6.1.4.1.9.9.48.1.1.1.2");
    public static OID OID_ciscoMemoryPoolUsed = new OID(".1.3.6.1.4.1.9.9.48.1.1.1.5");
    public static OID OID_ciscoMemoryPoolFree = new OID(".1.3.6.1.4.1.9.9.48.1.1.1.6");
    
    public static OID OID_cpmCPULoad5sec = new OID(".1.3.6.1.4.1.9.9.109.1.1.1.1.6");
    public static OID OID_cpmCPULoad1min = new OID(".1.3.6.1.4.1.9.9.109.1.1.1.1.7");
    public static OID OID_cpmCPULoad5min = new OID(".1.3.6.1.4.1.9.9.109.1.1.1.1.8");
    
    // Session
    public static OID OID_tcpConnLocalAddress  = new OID(".1.3.6.1.2.1.6.13.1.2");
    public static OID OID_tcpConnRemAddress = new OID(".1.3.6.1.2.1.6.13.1.4");
    public static OID OID_tcpConnLocalPort = new OID(".1.3.6.1.2.1.6.13.1.3");
    public static OID OID_tcpConnRemPort = new OID(".1.3.6.1.2.1.6.13.1.5");
    
    public static OID OID_udpLocalPort = new OID(".1.3.6.1.2.1.7.5.1.2");
    public static OID OID_udpLocalAddress = new OID(".1.3.6.1.2.1.7.5.1.1");
    
    // SNMP
    public static OID OID_snmpInPkts = new OID(".1.3.6.1.2.1.11.1");
    public static OID OID_snmpOutPkts = new OID(".1.3.6.1.2.1.11.2");
    public static OID OID_snmpOutSetRequests = new OID(".1.3.6.1.2.1.11.17");
    public static OID OID_snmpOutGetResponses = new OID(".1.3.6.1.2.1.11.18");
    public static OID OID_snmpOutTraps = new OID(".1.3.6.1.2.1.11.19");
    public static OID OID_snmpBadCommunityNames = new OID(".1.3.6.1.2.1.11.4");
    
    
	public static String getAddressFromOID(OID oid) {
		String oidStr = oid.toDottedString();
		
		return oidStr.substring(getAddressStartIndex(oidStr)+1);
	}
	public static String getOidStringWithoutAddress(OID oid) {
		String oidStr = oid.toDottedString();
		return oidStr.substring(0, getAddressStartIndex(oidStr));
	}
	
	public static OID[] getBGPOIDS() {
		return new OID[] {
			OID_ORIGIN,
			OID_ASPATH,
			OID_NEXTHOP,
			OID_MED,
			OID_LP,
			OID_ATOMIC_AGGR,
			OID_AGGR_AS,
			OID_AGGR_ADDR,
			OID_BEST // always last here
		};
	}
	
	private static int getAddressStartIndex(String oidStr) {
		int index = -1;
		int dotCount = 0;
		for(int i = oidStr.length()-1; i >= 0; i--) {
			if(oidStr.charAt(i) == '.') {
				dotCount++;
			}
			if(dotCount == 9) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static String getLastNumberInOID(String oid) {
        if (oid == null || oid.isEmpty()) {
            throw new IllegalArgumentException("OID cannot be null or empty");
        }

        int lastIndex = oid.lastIndexOf('.');

        if (lastIndex != -1 && lastIndex < oid.length() - 1) {
            return oid.substring(lastIndex + 1);
        } else {
            throw new IllegalArgumentException("Invalid OID format: " + oid);
        }
    }
}
