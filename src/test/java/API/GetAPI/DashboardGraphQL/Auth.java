package API.GetAPI.DashboardGraphQL;

import Support.Constants;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

//import static Support.Constants.DashBoardAccount;

public class Auth {
	public static String getToken() {
		String apiUrl = Constants.dashboardQL;
		String username = Constants.email;
		String password = Constants.passAccount;
		String mutation = """
				mutation($email: String!, $password: String!) {
				    login(email: $email, password: $password) {
				        token

				    }
				}
				""";
		JSONObject requestPayload = new JSONObject();
		requestPayload.put("query", mutation); // Mutation bạn vừa tạo

// Tạo biến với giá trị từ JSON bạn cung cấp
		JSONObject variables = new JSONObject();
		variables.put("email", username);
		variables.put("password", password);

		String filePath = "src/test/java/API/Data/Dashboard/Auth.json";

		requestPayload.put("query", mutation);
		requestPayload.put("variables", variables);

		String response = null;
		try {
			response = sendGraphQLRequest(apiUrl, requestPayload);
//			System.out.println("GraphQL Response: " + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject(response);
		JSONObject getToken = jsonObject.getJSONObject("data").getJSONObject("login");
		String tokenValue = getToken.getString("token");

//		System.out.println("test: " + tokenValue);
		try {
			// Tạo một đối tượng FileWriter để ghi dữ liệu vào tệp tin
			FileWriter writer = new FileWriter(filePath);

			// Ghi dữ liệu vào tệp tin
			writer.write(jsonObject.toString());

			// Đóng tệp tin sau khi ghi xong
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tokenValue;

	}

	public static String sendGraphQLRequest(String apiUrl, JSONObject requestPayload) throws Exception {
		// Tạo HTTP Connection
		URL url = new URL(apiUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");

		// Gửi JSON payload qua OutputStream
		connection.setDoOutput(true);
		try (OutputStream os = connection.getOutputStream()) {
			byte[] input = requestPayload.toString().getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		// Đọc phản hồi từ InputStream
		StringBuilder response = new StringBuilder();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(connection.getInputStream(), "utf-8"))) {
			String line;
			while ((line = br.readLine()) != null) {
				response.append(line.trim());
			}
		}

		// Trả về phản hồi dưới dạng chuỗi
		return response.toString();
	}
}
