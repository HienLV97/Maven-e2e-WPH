package API.GetAPI.DashboardGraphQL;

import Support.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Price {
	public static void main(String[] args) {
		String apiUrl = Constants.Dashboard;
		String token = Constants.DashboardToken;
		String query = "{" +
				"prices(pid: 1) {" +
				"id " +
				"urgency { " +
				"id " +
				"title " +
				"is_default " +
				"}" +
				"level { " +
				"id " +
				"title " +
				"is_default " +
				"} " +
				"provider { " +
				"domain " +
				"}" +
				"price " +
				"}" +
				"}";
		String response = fetchGraphQL(apiUrl, token, query);
		System.out.println(response);


		JSONObject jsonObject = new JSONObject(response);
		String filePath = "src/test/java/API/Data/YetiCMS/Prices.json";
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
/*

		try (FileReader fileReader = new FileReader(filePath)) {
			JSONTokener jsonTokener = new JSONTokener(fileReader);
			JSONObject jsonObject1 = new JSONObject(jsonTokener);
			JSONObject articlesData = jsonObject1.getJSONObject("data").getJSONObject("articles");
			JSONArray dataArray = articlesData.getJSONArray("data");

			String desiredUrl = "do-my-accounting-assignment"; // URL mà bạn  muốn tìm kiếm

			for (int i = 0; i < dataArray.length(); i++) {
				JSONObject item = dataArray.getJSONObject(i);
				String url = item.getString("url");

				if (url.equals(desiredUrl)) {
					// Tìm thấy URL mà bạn muốn
					System.out.println("Tìm thấy URL: " + url);
					break; // Nếu bạn muốn dừng khi tìm thấy
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	 */
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
