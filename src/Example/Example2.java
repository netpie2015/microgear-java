package Example;

import Microgear.Microgear;

public class Example2 {
	final static String appID = "AppID";
	final static String Key = "Key";
	final static String Secret = "Secret";

	public static void main(String[] args) throws InterruptedException {
		Microgear microgear = new Microgear();
		microgear.connect(appID, Key, Secret);
		microgear.Subscribe("mygear2");
		
		for (;;) {
			microgear.Publish("mygear2", "Hello world");
			Thread.sleep(2000);
		}
	}

}
