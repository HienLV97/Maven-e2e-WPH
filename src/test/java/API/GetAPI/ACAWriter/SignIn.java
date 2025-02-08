package API.GetAPI.ACAWriter;


//import helpers.Constants;
//import Support.Writer.Routers;
//import helpers.Constants;
import helpers.Constants;
import logs.LogUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SignIn {
//	static String filePath = "src/test/java/API/Data/Writer/tokenClient.json";
	static String URLSignIn = "https://writersagency.dev/gateway" + "/auth/login";

	public static void main(String[] args) {
		String token = getToken(Constants.WRITER_EMAIL, Constants.COMMON_PASSWORD);
		LogUtils.info(token);
	}

	public static String getToken(String email, String password) {
		try {
			// Create the connection object and set the required HTTP method and headers
			URL url = new URL(URLSignIn);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("User-Agent", "PostmanRuntime/7.39.0");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setDoOutput(true);

			String rawText = "{" +
					"\"email\": \"" + email + "\"," +
					"\"password\": \"" + password + "\"" +
					"}";
			StringBuilder response = new StringBuilder();
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = rawText.getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new IOException("HTTP error code: " + responseCode);
			}

			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
			}

			JSONParser parser = new JSONParser();
			try {
				JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
				return (String) jsonObject.get("token");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

//	private static String handleData() {
//		String token = null;
//		try {
//			// Đọc tệp JSON
//			JSONParser parser = new JSONParser();
//			JSONObject jsonData = (JSONObject) parser.parse(new FileReader(filePath));
//
//			token = (String) jsonData.get("token");
//		} catch (IOException | ParseException e) {
//			e.printStackTrace();
//		}
//		return token;
//	}
}
