package API.GetAPI.oldServicePages;

import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;

import Support.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;


public class GetAPIPage {
	public static void main(String[] args) {
		String apiUrl = Constants.CMS;
		String token = Constants.CMS_TOKEN;
		String query = "{" +
				"articles(page: 1, first: 1000) {" +
				"data {" +
				"id url meta_title meta_description  " +
				"anchor" +
				"}" +
				"}" +
				"}";
		String response = fetchGraphQL(apiUrl, token, query);
		JSONObject jsonObject = new JSONObject(response);
		String filePath = "src/test/java/API/Data/YetiCMS/Articles.json";
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
	}

	public static String fetchGraphQL(String apiUrl, String bearerToken, String graphqlQuery) {
		try {
			// Create the connection object and set the required HTTP method and headers
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
			connection.setDoOutput(true);

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
