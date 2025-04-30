package variant8b;

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

public class ProcessMonitorB extends Monitor {
	
	private ProcessFrameB frame;

	public ProcessMonitorB(Router[] routers) {
		super(routers);
		frame = new ProcessFrameB(routers);
		
		frame.getIntervalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = frame.getInputFromField();
                try {
                    int seconds = Integer.parseInt(input);
                    System.out.println("Polling interval set to: " + seconds + " seconds");
                    ProcessMonitorB.this.changeTimer(seconds, true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
	}

	@Override
	protected void mainUpdatedTask() throws IOException {
		ArrayList<Object[][]> data = new ArrayList<>();
		
		for(Router r : routers) {
			data.add(fetchRouterData(r));
		}
		
		for(int i = 0; i < routers.length ; i++) {
			frame.setData(routers[i], data.get(i));
		}
	}
	
	private Object[][] fetchRouterData(Router r) throws IOException {
		CommunityTarget target = Router.createTarget(r.getIP());
		
		ArrayList<VariableBinding> names = getData(target, OIDUtil.OID_ciscoMemoryPoolName);
		ArrayList<VariableBinding> used = getData(target, OIDUtil.OID_ciscoMemoryPoolUsed);
		ArrayList<VariableBinding> free = getData(target, OIDUtil.OID_ciscoMemoryPoolFree);
		
		ArrayList<VariableBinding> load5sec = getData(target, OIDUtil.OID_cpmCPULoad5sec);
		ArrayList<VariableBinding> load1min = getData(target, OIDUtil.OID_cpmCPULoad1min);
		ArrayList<VariableBinding> load5min = getData(target, OIDUtil.OID_cpmCPULoad5min);
		
		System.out.println(r.getName());
		System.out.println(names);
		System.out.println(used);
		System.out.println(free);
		System.out.println(load5sec);
		System.out.println(load1min);
		System.out.println(load5min);
		System.out.println();
		
		int rowCount = names.size();
		
		int colCount = ProcessFrameB.getColumnNum();
//		System.out.println(r.getIP() + " " + rowCount + " " + colCount);
		Object[][] data = new Object[rowCount][colCount];
		
		for(int i = 0; i < rowCount ;i++) {
			data[i] = new Object[colCount];
			
			
			
			data[i][0] = names.get(i).getVariable().toString();
			data[i][1] = used.get(i).getVariable().toString();
			data[i][2] = free.get(i).getVariable().toString();
			data[i][3] = load5sec.get(0).getVariable().toString();
			data[i][4] = load1min.get(0).getVariable().toString();
			data[i][5] = load5min.get(0).getVariable().toString();
			
		}
		
		return data;
	}
	
	

}
