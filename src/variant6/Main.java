package variant6;

import router.Router;

public class Main {

	public static void main(String[] args) {
		TrapHandler th = new TrapHandler(Router.getRouters());
		th.start();
	}
}
