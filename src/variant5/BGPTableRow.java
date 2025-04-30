package variant5;

public class BGPTableRow {
	private String oid;
	private String address;
	private String value;

	public BGPTableRow(String oid, String address, String value) {
		this.oid = oid;
		this.address = address;
		this.value = value;
	}
	
	public String getOID() {
		return oid;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return oid + " | " + address + " | " + value;
	}
}
