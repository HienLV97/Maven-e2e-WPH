package API.GetAPI.NextProxy;

import Support.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Auth {
	static String filePath = "src/test/java/API/Data/Dashboard/authClient.json";
	static String URLAuth = Constants.ProxyDevURL + "/auth";

	//	public static String getToken() {
	public static void main(String[] args) {

//		String token =  SignIn.getToken(Constants.emailBalance, Constants.passAccount);
//		System.out.println(token);
		auth("67b552966bb227fbd3ae5ea079156570");
		System.out.println();
//		return handleData();
	}

	private static void auth(String token) {
		try {
			// Create the connection object and set the required HTTP method and headers
			URL url = new URL(URLAuth);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Basic a2Ftb3JhOmlhbWFmcmllbmQ=");
			connection.setRequestProperty("Cookie", "token=80e2d1f5d3815e5984ac6f8605de769b");
			connection.setRequestProperty("Postman-Token", "<calculated when request is sent>");
			connection.setRequestProperty("Host", "<calculated when request is sent>");
			connection.setRequestProperty("User-Agent", "PostmanRuntime/7.37.0");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("token", token);
			connection.setDoOutput(true);

			StringBuilder response = new StringBuilder();


			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
			}
			System.out.println(response);
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String handleData() {
		String token = null;
		try {
			// Đọc tệp JSON
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject) parser.parse(new FileReader(filePath));
			JSONObject data = (JSONObject) jsonData.get("data");
			String title = (String) data.get("title");

			token = (String) jsonData.get("token");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return token;
	}
}
