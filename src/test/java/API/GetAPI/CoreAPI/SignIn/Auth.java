package API.GetAPI.CoreAPI.SignIn;

//import helpers.Constants;
import helpers.Constants;
import logs.LogUtils;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Auth {
	static String filePath = "src/test/java/API/Data/Dashboard/Auth.json";

	public static String getDataUser(String email, String pass, String value) {
		getAuth(email, pass);
		LogUtils.info("balance: "+handleData(value));
		return handleData(value);
	}

	private static void getAuth(String email, String password) {
		try {
			// Create the connection object and set the required HTTP method and headers
			URL url = new URL(Constants.CORE_API);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + Constants.CORE_API_TOKEN);
			connection.setRequestProperty("Postman-Token", "<calculated when request is sent>");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "<calculated when request is sent>");
			connection.setRequestProperty("Host", "<calculated when request is sent>");
			connection.setRequestProperty("User-Agent", "PostmanRuntime/7.37.0");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setDoOutput(true);

			List<NameValuePair> urlParameters = new ArrayList<>();
			urlParameters.add(new BasicNameValuePair("method", "open"));
			urlParameters.add(new BasicNameValuePair("namespace", "Auth"));
			urlParameters.add(new BasicNameValuePair("email", email));
			urlParameters.add(new BasicNameValuePair("password", password));

			StringBuilder formStringBuilder = new StringBuilder();
			for (NameValuePair entry : urlParameters) {
				if (formStringBuilder.length() > 0) {
					formStringBuilder.append("&");
				}
				formStringBuilder.append(URLEncoder.encode(entry.getName(), "UTF-8"));
				formStringBuilder.append("=");
				formStringBuilder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			}
			String formDataString = formStringBuilder.toString();

			try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
				outputStream.writeBytes(formDataString);
				outputStream.flush();
			}

			StringBuilder response = new StringBuilder();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
				}
			} else {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
				}
			}

			connection.disconnect();

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

	private static String handleData(String value) {
		JSONObject dataObject = null;
		try {
			// Đọc tệp JSON
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject) parser.parse(new FileReader(filePath));

			// Trích xuất mảng "data" từ đối tượng "content"
			JSONObject content = (JSONObject) jsonData.get("content");
			dataObject = (JSONObject) content.get("data");

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return (String) dataObject.get(value);
	}
}
