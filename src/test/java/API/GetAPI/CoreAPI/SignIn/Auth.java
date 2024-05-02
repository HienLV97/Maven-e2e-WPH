package API.GetAPI.CoreAPI.SignIn;

import Support.Constants;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;

public class Auth {
	public static void main(String[] args) {
		getBalance(Constants.emailAccount,Constants.passAccount);
	}
	public static void getBalance(String email, String password){
		try {
			// Create the connection object and set the required HTTP method and headers
			URL url = new URL(Constants.CoreAPI);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + Constants.CoreAPIToken);
			connection.setRequestProperty("Postman-Token", "<calculated when request is sent>");
			connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=<calculated when request is sent>");
			connection.setRequestProperty("Content-Length", "<calculated when request is sent>");
			connection.setRequestProperty("Host", "<calculated when request is sent>");
			connection.setRequestProperty("User-Agent", "PostmanRuntime/7.37.0");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setDoOutput(true);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.STRICT);

			builder.addTextBody("method","open");
			builder.addTextBody("namespace","Auth");
			builder.addTextBody("email",email);
			builder.addTextBody("password",password);
			connection.set

//			Map<String, String> formData = new HashMap<>();
//			formData.put("method", "open");
//			formData.put("namespace", "Auth");
//			formData.put("email",email);
//			formData.put("password",password);

			StringBuilder formStringBuilder = new StringBuilder();
			for (Map.Entry<String, String> entry : formData.entrySet()) {
				if (formStringBuilder.length() > 0) {
					formStringBuilder.append("&");
				}
				formStringBuilder.append(entry.getKey());
				formStringBuilder.append("=");
				formStringBuilder.append(entry.getValue());
			}
			String formDataString = formStringBuilder.toString();

			// Write the form data string to the request body
			try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
				byte[] formDataBytes = formDataString.getBytes(StandardCharsets.UTF_8);
				outputStream.write(formDataBytes);
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
						System.out.println("Response: " + response);
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
			String filePath = "src/test/java/API/Data/Dashboard/Auth.json";
			try {
				// Tạo một đối tượng FileWriter để ghi dữ liệu vào tệp tin
				FileWriter writer = new FileWriter(filePath);

				// Ghi dữ liệu vào tệp tin
				writer.write(response.toString());

				// Đóng tệp tin sau khi ghi xong
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static List<String> handleData(String namefile) {
		List<String> titles = null;
		String filePath = "src/test/java/API/Data/Dashboard/" + namefile + ".json";
		try {
			// Đọc tệp JSON
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject) parser.parse(new FileReader(filePath));

			// Trích xuất mảng "data" từ đối tượng "content"
			JSONObject content = (JSONObject) jsonData.get("content");
			JSONArray dataArray = (JSONArray) content.get("data");

			titles = new ArrayList<>();

			// Lặp qua mảng "data" và lấy giá trị của trường "title" từ mỗi đối tượng
			for (Object item : dataArray) {
				JSONObject dataItem = (JSONObject) item;
				JSONObject data = (JSONObject) dataItem.get("data");
				String title = (String) data.get("title");
				titles.add("\"" + title + "\"");
			}

			System.out.println("Successfully updated JSON file.");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return titles;
	}

}
