package API.GetAPI.NextProxy.Auth;

import API.GetAPI.NextProxy.SignIn.SignIn;
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
	static String URLAuth = Constants.proxyDevURL + "/auth";
/*
	public static void main(String[] args) {
		String token = SignIn.getToken(Constants.emailAccount, Constants.passAccount);
		System.out.println("token tryen` vao`: " + token);

		String balance = getauth(token, "balance");
		String name = getauth(token, "name");
		String gender = getauth(token, "gender");
		String test = getauth(token, "test");
		System.out.println("balance :" + balance);
		System.out.println("name :" + name);
		System.out.println("gender :" + gender);
		System.out.println("test :" + test);
	}

 */
	public static String getauth(String token, String value) {
		try {
			// Create the connection object and set the required HTTP method and headers
			URL url = new URL(URLAuth);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Basic a2Ftb3JhOmlhbWFmcmllbmQ=");
			connection.setRequestProperty("Cookie", "token=" + token);
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
			try {
				// Đọc tệp JSON
				JSONParser parser = new JSONParser();
				JSONObject jsonData = (JSONObject) parser.parse(response.toString());
				JSONObject data = (JSONObject) jsonData.get("user");
				return (String) data.get(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}
}