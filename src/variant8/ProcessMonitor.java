package variant8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.snmp4j.CommunityTarget;
import org.snmp4j.smi.VariableBinding;

import router.Router;
import util.Monitor;
import util.OIDUtil;

public class ProcessMonitor extends Monitor {
	
	private ProcessFrame frame;

	public ProcessMonitor(Router[] routers) {
		super(routers);
		frame = new ProcessFrame();
		
		frame.getIntervalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = frame.getInputFromField();
                try {
                    int seconds = Integer.parseInt(input);
                    System.out.println("Polling interval set to: " + seconds + " seconds");
                    ProcessMonitor.this.changeTimer(seconds, true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
	}

	@Override
	protected void mainUpdatedTask() throws IOException {
		for(Router r : routers) {
			updateGraphs(r);
		}
	}
	
	private void updateGraphs(Router r) throws IOException {
		CommunityTarget target = Router.createTarget(r.getIP());
		
		ArrayList<VariableBinding> used = getData(target, OIDUtil.OID_ciscoMemoryPoolUsed);
		ArrayList<VariableBinding> free = getData(target, OIDUtil.OID_ciscoMemoryPoolFree);
		
		ArrayList<VariableBinding> load5sec = getData(target, OIDUtil.OID_cpmCPULoad5sec);
		ArrayList<VariableBinding> load1min = getData(target, OIDUtil.OID_cpmCPULoad1min);
		ArrayList<VariableBinding> load5min = getData(target, OIDUtil.OID_cpmCPULoad5min);
		
		System.out.println("-SNAPSHOT-");
		for(int i = 0; i < 2; i++) {
			String type = i == 0 ? "PROCESSOR":"IO";
			frame.addData(used.get(i).getVariable().toInt(), r.getName() + "_" + type + "_USAGE");
			frame.addData(free.get(i).getVariable().toInt(), r.getName() + "_" + type + "_FREE");
		}
		frame.addData(load5sec.get(0).getVariable().toInt(), r.getName() + "_5SEC");
		frame.addData(load1min.get(0).getVariable().toInt(), r.getName() + "_1MIN");
		frame.addData(load5min.get(0).getVariable().toInt(), r.getName() + "_5MIN");
		System.out.println();
		
		frame.repaint();
	}
	
	

}
