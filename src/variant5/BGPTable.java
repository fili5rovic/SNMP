package variant5;

import java.util.ArrayList;
import java.util.HashSet;

import org.snmp4j.smi.OID;

import util.OIDUtil;

public class BGPTable {
	
	private ArrayList<String> addressList = new ArrayList();
	private ArrayList<BGPTableRow> table = new ArrayList<>();
	
	public void addRow(BGPTableRow row) {
		table.add(row);
		if(!addressList.contains(row.getAddress()))
			addressList.add(row.getAddress());
	}
	
	public ArrayList<String[]> getTableData() {
		ArrayList<String[]> data = new ArrayList<String[]>();
		
		int columnSize = OIDUtil.getBGPOIDS().length;
		for(String address : addressList) {
			String[] dataRow = new String[columnSize+1];
			dataRow[0] = address;
			int count = 1;
			for(OID oid : OIDUtil.getBGPOIDS()) {
				String oidKey = oid.toDottedString();
				
				for(BGPTableRow row : table) {
					if(row.getOID().equals(oidKey) && row.getAddress().equals(address)) {
						if(oid.equals(OIDUtil.OID_ORIGIN)) {
							dataRow[count++] = getOriginValue(row.getValue());
						} else {
							dataRow[count++] = row.getValue();
						}
						break;
					}
				}
			}
			data.add(dataRow);
		}
		
		return data;
	}
	
	private String getOriginValue(String val) {
		switch(val) {
			case "1":
				return "IGP";
			case "2":
				return "EGP";
			default:
				return "INCOMPLETE";
		}
	}
}
