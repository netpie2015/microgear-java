package examples;

import io.netpie.microgear.Microgear;
import io.netpie.microgear.MicrogearEventListener;

public class Basic {
	final static String appID = "appID";
	final static String Key = "Key";
	final static String Secret = "Secret";
	static Microgear microgear;
	static callback callback;


	public static void main(String[] args) throws InterruptedException {
		microgear = new Microgear();
		callback = new callback();
		microgear.setCallback(callback);
		microgear.connect(appID, Key, Secret);
		microgear.Subscribe("/Topic");
		int count = 1;
		for(;;){
			microgear.Publish("/Topic", String.valueOf(count)+".  Test message");
			count++;
			Thread.sleep(2000);
		}
	}

	static class callback implements MicrogearEventListener{

		public void onConnect() {
			// TODO Auto-generated method stub
			System.out.println("Microgear is Connected.");
			//this.microgear.Publish("/chat", "Hello world#pppppp#qqqqq");
		}

		public void onMessage(String topic, String message) {
			// TODO Auto-generated method stub
			System.out.println(topic+" "+message);
		}

		public void onPresent(String token) {
			// TODO Auto-generated method stub
			System.out.println("Present "+token);
		}

		public void onAbsent(String token) {
			// TODO Auto-generated method stub
			System.out.println("Absent "+token);
		}

		public void onDisconnect() {
			// TODO Auto-generated method stub
			System.out.println("Microgear is Disconnect.");
		}

		public void onError(String error) {
			// TODO Auto-generated method stub
			System.out.println("Error "+error);
		}
	}
}
