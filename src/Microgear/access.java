package Microgear;

import java.io.BufferedReader;
import java.io.IOException;
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

import org.json.JSONException;
import org.json.JSONObject;

import Decoder.BASE64Encoder;

public class access {

	final static String Method = "POST";
	final static String version = "1.0";
	final static String SignatureMethod = "HMAC-SHA1";
	static final String Access_url = "http://ga.netpie.io:8080/api/atoken";
	public JSONObject token_token_secret = new JSONObject();
	final static String Verifier = "JVM1a";

	public JSONObject OAuth(String Consumer_Key, String Consumer_Secret, String token, String Secret) throws Exception {
		String Header = Sinature(Consumer_Key, Consumer_Secret, token, Secret);

		URL Url;
		try {
			Url = new URL(Access_url);
			URLConnection connect = Url.openConnection();
			((HttpURLConnection) connect).setRequestMethod(Method);
			connect.setDoOutput(true);
			connect.setRequestProperty("Authorization", Header);
			connect.setReadTimeout(5000);
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
			return token_token_secret;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return token_token_secret;
	}

	private static String Sinature(String Consumer_Key, String Consumer_Secret, String token, String Secret) {
		try {
			Consumer_Secret = URLEncoder.encode(Consumer_Secret, "UTF-8");
			Secret = URLEncoder.encode(Secret, "UTF-8");
			String timestamp = Integer.toString((int) Math.floor((new Date()).getTime() / 1000));

			String[] headers_key = { "oauth_consumer_key", "oauth_nonce", "oauth_signature_method", "oauth_timestamp",
					"oauth_token", "oauth_verifier", "oauth_version", "oauth_signature" };
			String[] headers_value = { Consumer_Key, _getNonce(), SignatureMethod, timestamp, token, Verifier, version,
					"" };

			String base = "";
			for (int i = 0; i < headers_value.length - 1; i++) {
				base += headers_key[i] + "=" + headers_value[i] + "&";
			}
			base = base.substring(0, base.length() - 1);

			String url = URLEncoder.encode(Access_url, "UTF-8");
			String parameters = URLEncoder.encode(base, "UTF-8");
			String signature = Method.toUpperCase() + "&" + url + "&" + parameters;
			String key = Consumer_Secret + "&" + Secret;

			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
			Mac mac;
			mac = Mac.getInstance("HmacSHA1");
			mac.init(keySpec);

			byte[] result = mac.doFinal(signature.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			String hash = encoder.encode(result).toString();
			headers_value[headers_value.length - 1] = hash;

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
