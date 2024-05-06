package API.GetAPI.NextProxy;

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
	static String URLSignIn = Constants.ProxyDevURL + "/signin";

//	public static void main(String[] args) {
//		String a = SignIn(Constants.emailBalance, Constants.passAccount);
//		System.out.println("token : " + a);
//	}
//	public static String getToken(String email, String pass) {
//		String a = SignIn(email, pass);
////		return handleData();
//	}

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
			System.out.println(response);

			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
			}


			try {
				// Tạo một đối tượng FileWriter để ghi dữ liệu vào tệp tin
				FileWriter writer = new FileWriter(filePath);

				// Ghi dữ liệu vào tệp tin
				writer.write(response.toString());

				// Đóng tệp tin sau khi ghi xong
				writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
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

		} catch (IOException e) {
			e.printStackTrace();
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
