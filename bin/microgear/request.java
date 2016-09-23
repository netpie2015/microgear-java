package microgear;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

import Decoder.BASE64Encoder;

public class request {

	final static String version = "1.0";
	final static String Hmac = "HMAC-SHA1";
	final static String Method = "POST";
	final static String Request_url = "http://ga.netpie.io:8080/api/rtoken";
	public JSONObject token_token_secret = new JSONObject();
	//private EventListener eventListener = new EventListener();

	public String OAuth(String Key, String Secret, String authorize_callback) throws Exception {
		String Header = Sinature(Key, Secret, authorize_callback);
		URL Url;
		try {
			Url = new URL(Request_url);
			URLConnection connect = Url.openConnection();
			((HttpURLConnection) connect).setRequestMethod(Method);
			connect.setDoOutput(true);
			connect.setConnectTimeout(5000);
			connect.setReadTimeout(5000);
			connect.setRequestProperty("Authorization", Header);
			OutputStreamWriter writer = new OutputStreamWriter(connect.getOutputStream());
			writer.write(Header);
			writer.flush();
			InputStream is = connect.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				token_token_secret.put("", response);
			}
			rd.close();
			Microgear.setStatus("0");
			return token_token_secret.toString();
		} catch (Exception e) {
			//eventListener.mError.onError("Please check Appid,Key,Secret. ");
			//Microgear.ErrorListener.OnErrorArrived("Please check Appid,Key,Secret. ");
			System.exit(0);
		}
		return null;
	}

	public static String Sinature(String Key, String Secret, String authorize_callback) {
		String key = Key;
		String secret;
		try {
			secret = URLEncoder.encode(Secret, "UTF-8");
			String callback = URLEncoder.encode(authorize_callback, "UTF-8");
			String timestamp = Integer.toString((int) Math.floor((new Date()).getTime() / 1000));

			String[] headers_key = new String[] { "oauth_callback", "oauth_consumer_key", "oauth_nonce",
					"oauth_signature_method", "oauth_timestamp", "oauth_version", "oauth_signature" };
			String[] headers_value = new String[] { callback, key, _getNonce(), Hmac, timestamp, version, "" };

			String base = "";
			for (int i = 0; i < headers_value.length - 1; i++) {
				base += headers_key[i] + "=" + headers_value[i] + "&";
			}
			base = base.substring(0, base.length() - 1);

			String url = URLEncoder.encode(Request_url, "UTF-8");
			String parameters = URLEncoder.encode(base, "UTF-8");
			String signatureBase = Method.toUpperCase() + "&" + url + "&" + parameters;
			String tokenSecret = "";
			String hkey = secret + "&" + tokenSecret;

			SecretKeySpec keySpec = new SecretKeySpec(hkey.getBytes(), "HmacSHA1");
			Mac mac;
			mac = Mac.getInstance("HmacSHA1");
			mac.init(keySpec);
			byte[] result = mac.doFinal(signatureBase.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			String Hash = encoder.encode(result).toString();
			headers_value[0] = authorize_callback;
			headers_value[6] = Hash;
			String authHeader = "OAuth ";
			for (int i = 0; i < headers_value.length; i++) {
				authHeader += URLEncoder.encode(headers_key[i], "UTF-8") + "=\""
						+ URLEncoder.encode(headers_value[i], "UTF-8") + "\"" + ",";
			}
			authHeader = authHeader.substring(0, authHeader.length() - 1);
			return authHeader;
		} catch (Exception e) {
		}
		return null;

	}

	private static char[] NONCE_CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9' };

	private static String _getNonce() {

		StringBuilder sb = new StringBuilder();
		new Random();
		for (int i = 0; i < 32; i++) {
			char nonce = NONCE_CHARS[(int) Math.floor(Math.random() * NONCE_CHARS.length)];
			sb.append(nonce);
		}
		String output = sb.toString();

		return output;
	}

}
