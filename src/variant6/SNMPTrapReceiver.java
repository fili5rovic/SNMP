package variant6;

import org.snmp4j.*;
import org.snmp4j.smi.*;
import org.snmp4j.transport.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SNMPTrapReceiver implements CommandResponder {
    private Snmp snmp = null;
    private TransportMapping transport = null;
    
    private static final String BGP_ESTABLISHED = "1.3.6.1.2.1.15.7.1";
    private static final String BGP_BACKWARD_TRANSITION = "1.3.6.1.2.1.15.7.2";

    public void start() throws IOException {
        transport = new DefaultUdpTransportMapping(new UdpAddress("0.0.0.0/1620"));
        snmp = new Snmp(transport);
        snmp.addCommandResponder(this);
        transport.listen();
        System.out.println("Started listening for BGP traps...");
    }

    @Override
    public void processPdu(CommandResponderEvent event) {
    	System.out.println("Received trap");
        PDU pdu = event.getPDU();
        if (pdu != null) {
            String routerIP = event.getPeerAddress().toString();
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            
            for (VariableBinding vb : pdu.getVariableBindings()) {
                String oid = vb.getOid().toString();
                
                if (oid.startsWith(BGP_ESTABLISHED)) {
                    System.out.println("=== BGP ESTABLISHED NOTIFICATION ===");
                    System.out.println("Time: " + currentTime);
                    System.out.println("Router: " + routerIP);
                    System.out.println("BGP session established");
                }
                else if (oid.startsWith(BGP_BACKWARD_TRANSITION)) {
                    System.out.println("=== BGP BACKWARD TRANSITION NOTIFICATION ===");
                    System.out.println("Time: " + currentTime);
                    System.out.println("Router: " + routerIP);
                    System.out.println("BGP session state change");
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            SNMPTrapReceiver receiver = new SNMPTrapReceiver();
            receiver.start();
            
            while(true) {
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}