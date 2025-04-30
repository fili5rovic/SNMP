package util;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.*;
import org.snmp4j.transport.*;

import router.RouteChangeListener;
import router.Router;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Monitor {
	
    protected Snmp snmp;
    protected Router[] routers;
    
    protected int TIMER_DURATION = 10_000;
    protected Timer timer;
    
    public Monitor(Router[] routers) {
    	this.routers = routers;
    }
    
    private void setupSNMP() throws Exception {
        snmp = new Snmp(new DefaultUdpTransportMapping());
        snmp.listen();
    }

    public void startMonitoring() {
    	try {
            setupSNMP();
            
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                    	mainUpdatedTask();
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }, 0, TIMER_DURATION);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void changeTimer(int seconds, boolean startTimer) {
    	this.timer.cancel();
    	if(seconds < 0)
    		seconds *= -1;
    	this.TIMER_DURATION = seconds * 1000;
    	if(startTimer)
    		startMonitoring();
    	
    }
    
    protected abstract void mainUpdatedTask() throws IOException;
    
    
    protected ArrayList<VariableBinding> getData(CommunityTarget target, OID startOID) throws IOException {
    	return getData(target,startOID,null);
    }
    
    /**
     * Searches each OID. It stops if it can't find OID that starts with startOID. Can end sooner with endOID.
     * @param target 
     * @param startOID Start OID of every variableBinding returned
     * @param endOID
     * @return Returns an ArrayList of all VariableBindings found
     * @throws IOException
     */
    protected ArrayList<VariableBinding> getData(CommunityTarget target, OID startOID, OID endOID) throws IOException {
        OID currentOID = startOID;
        
        ArrayList<VariableBinding> rows = new ArrayList<>();
    	
        while (currentOID != null) {
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(currentOID));
            pdu.setType(PDU.GETNEXT);

            ResponseEvent response = snmp.send(pdu, target);

            if (response == null || response.getResponse() == null) {
                System.out.println("Error: No response from target");
                break;
            }

            PDU responsePDU = response.getResponse();
            VariableBinding vb = responsePDU.get(0);

            if (vb.getOid() == null || !vb.getOid().startsWith(startOID) || (endOID != null && vb.getOid().startsWith(endOID))) {
                break;
            }
            rows.add(vb);
            currentOID = vb.getOid();
        }
        return rows;
    }
    
    protected String readSingleValue(CommunityTarget target, OID oid) throws IOException {
    	PDU pdu = new PDU();
        pdu.add(new VariableBinding(oid));
        pdu.setType(PDU.GETNEXT);
        
        ResponseEvent response = snmp.send(pdu, target);
        
        if (response == null || response.getResponse() == null) {
            return "ERROR";
        }
        
        PDU responsePDU = response.getResponse();
        VariableBinding vb = responsePDU.get(0);
        
        if(vb == null || vb.getOid() == null)
        	return "ERROR";
        
        return vb.getVariable().toString();
    }
    
}
