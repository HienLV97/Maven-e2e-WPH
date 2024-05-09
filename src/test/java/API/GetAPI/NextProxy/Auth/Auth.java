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
import java.util.Base64;

public class Auth {
	static String filePath = "src/test/java/API/Data/Dashboard/authClient.json";
	static String URLAuth = Constants.proxyDevURL + "/auth";

	//	public static String getToken() {
	public static void main(String[] args) {
		String token = SignIn.getToken(Constants.emailBalance, Constants.passAccount);
//		System.out.println("a ");
		System.out.println("token tryen` vao`: "+token);
		auth(token);
//		System.out.println("token1: "+token);
//		System.out.println();
	}

	private static void auth(String token) {
		try {
			// Mã hóa thông tin đăng nhập
			String basicAuth = "Basic " + Base64.getEncoder().encodeToString((Constants.basicUsername + ":" + Constants.passAccount).getBytes());

			// Create the connection object and set the required HTTP method and headers
			URL url = new URL(URLAuth);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", basicAuth);
			System.out.println("Basic: "+basicAuth);
			connection.setRequestProperty("Cookie", "token=2f6d7917007558db98ef4cf557baffea");
			connection.setRequestProperty("Postman-Token", "<calculated when request is sent>");
			connection.setRequestProperty("Host", "<calculated when request is sent>");
			connection.setRequestProperty("User-Agent", "PostmanRuntime/7.37.0");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			connection.setRequestProperty("Connection", "keep-alive");
			System.out.println("token2: "+token);
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
	public class BasicAuth {
		public static String encodeBasicAuth(String username, String password) {
			String authString = username + ":" + password; // Nối tên người dùng và mật khẩu với dấu hai chấm
			byte[] encodedBytes = Base64.getEncoder().encode(authString.getBytes()); // Mã hóa base64
			return "Basic " + new String(encodedBytes); // Thêm "Basic " trước mã hóa base64
		}
	}
}
//	Basic a2Ftb3JhOmlhbWFmcmllbmQ=
//			token=c4e89d3b35394ef1ac3bf38557979862
