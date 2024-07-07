package API.GetAPI.NextProxy.SignIn;

import Support.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SignIn {
	static String filePath = "src/test/java/API/Data/Dashboard/tokenClient.json";
	static String URLSignIn = Constants.PROXY_DEV_URL + "/signin";

	public static void main(String[] args) {
		String token = SignIn.getToken(Constants.ACCOUNT_BALANCE, Constants.COMMON_PASSWORD);
		System.out.println(token);
	}

	public static String getToken(String email, String password) {
		try {
			// Create the connection object and set the required HTTP method and headers
			URL url = new URL(URLSignIn);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Basic a2Ftb3JhOmlhbWFmcmllbmQ=");
			connection.setRequestProperty("Cookie", "token=80e2d1f5d3815e5984ac6f8605de769b");
			connection.setRequestProperty("Postman-Token", "<calculated when request is sent>");
			connection.setRequestProperty("Content-Type", "text/plain");
			connection.setRequestProperty("Content-Length", "<calculated when request is sent>");
			connection.setRequestProperty("Host", "<calculated when request is sent>");
			connection.setRequestProperty("User-Agent", "PostmanRuntime/7.37.0");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setDoOutput(true);

			String rawText = "{" +
					" \"email\": \"" + email + "\"," +
					"  \"password\": \"" + password + "\"," +
					" \"backurl\": \"/\"" +
					"}";
			StringBuilder response = new StringBuilder();
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = rawText.getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
			}


			JSONParser parser = new JSONParser();
			System.out.println("SignIn: " + response);
			try {
				JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
				String token = (String) jsonObject.get("token");
				return token;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return email;
	}

	private static String handleData() {
		String token = null;
		try {
			// Đọc tệp JSON
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject) parser.parse(new FileReader(filePath));

			token = (String) jsonData.get("token");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return token;
	}
}
