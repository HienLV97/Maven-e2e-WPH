package API.GetAPI.DashboardGraphQL;

import helpers.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Discounts {
	public static int GetDiscount(String discountCode) {
		String apiUrl = Constants.DASHBOARD_QL;
		String token = Auth.getToken();
		String query = "{" +
				"discounts(pid: 1,first: 100, page: 1) { " +
				"data { " +
				"id "+
				"name "+
				"type "+
				"value "+
				"expired_at "+
				"}"+
				"}" +
				"}";
		String response = fetchGraphQL(apiUrl, token, query);
		JSONObject jsonObject = new JSONObject(response);
		String filePath = "src/test/java/API/Data/Dashboard/Discounts.json";
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
		JSONArray DiscountArray = jsonObject.getJSONObject("data").getJSONObject("discounts").getJSONArray("data");
		int discount = 0;
		for (int i = 0; i < DiscountArray.length(); i++) {
			JSONObject priceObject = DiscountArray.getJSONObject(i);
			String nameCode = priceObject.getString("name");

			// Kiểm tra nếu cả hai tiêu đề đều khớp
			if (nameCode.equals(discountCode)) {
				// Nếu khớp, lấy giá cả và in ra
				discount = priceObject.getInt("value");

				break; // Dừng vòng lặp sau khi tìm thấy kết quả đầu tiên phù hợp
			}
		}
		return discount;
	}

	public static String fetchGraphQL(String apiUrl, String bearerToken, String graphqlQuery) {
		try {
			// Create the connection object and set the required HTTP method and headers
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
			connection.setRequestProperty("Postman-Token", "<calculated when request is sent>");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Length", "<calculated when request is sent>");
			connection.setRequestProperty("Host", "<calculated when request is sent>");
			connection.setRequestProperty("User-Agent", "PostmanRuntime/7.37.3");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			connection.setRequestProperty("Connection", "keep-alive");

			connection.setDoOutput(true);
			JSONObject variables = new JSONObject();

			int pid = 1;
			variables.put("pid", pid);
			// Create the JSON request payload
			String jsonInputString = "{\"query\":\"" + graphqlQuery.replace("\"", "\\\"") + "\"}";
			// Write the JSON payload to the connection output stream
			try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
				outputStream.writeBytes(jsonInputString);
				outputStream.flush();
			}

			// Read the response from the input stream
			StringBuilder response = new StringBuilder();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				try (BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()))) {
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
				}
			} else {
				// If connection is not OK, read the error stream
				try (BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getErrorStream()))) {
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
				}
			}

			// Disconnect the connection
			connection.disconnect();

			// Return the response
			return response.toString();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
