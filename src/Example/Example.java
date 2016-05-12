package Example;

import Microgear.Microgear;
import Microgear.Microgear.*;

public class Example {
	final static String appID = "appID";
	final static String Key = "Key";
	final static String Secret = "Secret";

	public static void main(String[] args) throws InterruptedException {
		Microgear microgear = new Microgear();
		microgear.setConnectEvent(new OnConnect() {

			@Override
			public void OnConnectArrived(Boolean c) {
				if (c) {
					System.out.println(c);
					System.out.println("Microgear is Connected.");
				}

			}
		});

		microgear.setMessageEvent(new OnMessage() {

			@Override
			public void OnMessageArrived(String Topic, String Message) {
				System.out.println(Topic + "  " + Message);

			}
		});

		microgear.setOnPresentEvent(new OnPresent() {

			@Override
			public void OnPresentArrived(String a) {
				System.err.println("Present "+a);

			}
		});

		microgear.setOnAbsentEvent(new OnAbsent() {

			@Override
			public void OnAbsentArrived(String a) {
				System.out.println("Absent "+a);

			}
		});

		microgear.setDisconnectEvent(new OnDisconnect() {

			@Override
			public void OnDisconnectArrived(Boolean d) {
				System.out.println(d);

			}
		});
		microgear.setErrorEvent(new OnError() {

			@Override
			public void OnErrorArrived(String d) {
				System.out.println(d);
				
			}
		});
		
		microgear.connect(appID, Key, Secret);
		microgear.Subscribe("/Topictest");
		int count = 1;
		for(;;){
			microgear.Publish("/Topictest", String.valueOf(count)+".  Test message");
			count++;
			Thread.sleep(2000);
		}

	}

}
