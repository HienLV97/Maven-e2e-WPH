
import helpers.Constants;
//import org.json.JSONArray;
//import org.json.JSONObject;
import logs.LogUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


public class SignIn {
	public static void main(String[] args) {
		String apiUrl = Constants.CORE_API;
		String token = Constants.CORE_API_TOKEN;
		String response = getAPI(apiUrl, token);
//		JSONObject jsonObject = new JSONObject(response);
		String filePath = "src/test/java/API/Data/Dashboard/OrderForm.json";
		String value1 = handleData(filePath).get(1);
	}

	public static String getAPI(String apiUrl, String bearerToken) {
		try {
			// Create the connection object and set the required HTTP method and headers
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer "+ bearerToken);
			connection.setRequestProperty("Postman-Token", "<calculated when request is sent>");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "<calculated when request is sent>");
			connection.setRequestProperty("Host", "<calculated when request is sent>");
			connection.setRequestProperty("User-Agent", "PostmanRuntime/7.35.0");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("authority", "writersperhour.host");
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("accept-language", "vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7");
//			connection.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
			connection.setRequestProperty("cookie", "cb-enabled=accepted; _omappvp=KWk2eldPX0szQ1NH6b4Rv8AudajNJ599qubwG6Rnm0LNdU2MssWRodrHpTF6yGCQa3Cdir6BkJtJEBOoFoVCJr7YoKWgS3Zj; _gid=GA1.2.1128844179.1685930757; SSIDWPH=eyJpdiI6IkNaT0twMHNBNzZtbm9oYUhiRi9TeVE9PSIsInZhbHVlIjoiSS9nWE56RHQrUDk5MS92emlaV0lKV1I0dXV0Z0dDSWtrVW5iUk5ReDd6WHFURWp6cjhHWjNYbmxoMC9Gdno3MTcvQ2d6TU5rSDRpVWFYK0hOQ2RjdVE9PSIsIm1hYyI6IjBhMjNiMzA4MTdjY2Q5NjU4YzRkOTlkMDM3MTVmMTRlN2NlZWE4ZGY3ZGYwOWFmOWE0MmViNzY0YmNjMjc4ZGIiLCJ0YWciOiIifQ%3D%3D; _gat_gtag_UA_234898087_1=1; _ga_RELGLE584D=GS1.1.1686121106.34.1.1686122324.60.0.0; _ga=GA1.1.475469588.1685348994; XSRF-TOKEN=eyJpdiI6Inh0ejVldGViZGFmak1DSFdDOVR2L0E9PSIsInZhbHVlIjoiY25YYkdRMm9OU2xJdXZjNEVVYzhzdzVNbElpbElrOUdGM2IrTk5CSFYvZXBNVUZwSVZub3JMb3lTbWZXWkVadTVXb3RWQlpHVmRQSEN5QnNxMlNIV1ZkdmxVYjZNOC9ScDFJQ25IZVZmdWZObk80azFTWW45Y1ZHY0ZEK0JraW8iLCJtYWMiOiJmZDM0OThkNWQ4ZWQ4MmZhNGIwZWUxYTZjM2QzMmZjNTNiNTJjNDIzYTk5YmY5YWYxODI0ZGRkZDZjYzM5Zjc3IiwidGFnIjoiIn0%3D; laravel_session=eyJpdiI6ImJVQnNuRm9ibGFFWXRtWVR4YkRqTUE9PSIsInZhbHVlIjoiSTNPTk9HakROR0ROYzN3OVJLT0FtdUhxbGxiN20xaWhybmdPNkZMLzdUeVpuZzZ5ZXF1cU9kVndXa2V2TTg0Z1JETTJYS1d5dlpKUExkbXhoSytPT0ozSjB4Vys3NGY0cU5ySFRrV3ZNSGNWWHhibm1QbzJOVzVLOTVqQ3piMDkiLCJtYWMiOiJlNTBmYmI0OTM1MWIxNmE4ZDU5MWVkYzc4Mzk0OTMzYTI0NDJmNGRhYjgyY2M0MmZmNjVmNmRhODAwMTlhYjkyIiwidGFnIjoiIn0%3D");
			connection.setRequestProperty("origin", "https://writersperhour.host");
			connection.setRequestProperty("referer", "https://writersperhour.host/order");
			connection.setRequestProperty("sec-ch-ua", "\"Google Chrome\";v=\"113\", \"Chromium\";v=\"113\", \"Not-A.Brand\";v=\"24\"");
			connection.setRequestProperty("sec-ch-ua-mobile", "?0");
			connection.setRequestProperty("sec-ch-ua-platform", "\"Linux\"");
			connection.setRequestProperty("sec-fetch-dest", "empty");
			connection.setRequestProperty("sec-fetch-mode", "cors");
			connection.setRequestProperty("sec-fetch-site", "same-origin");
			connection.setRequestProperty("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
			connection.setRequestProperty("x-requested-with", "XMLHttpRequest");
			connection.setDoOutput(true);


			String jsonData1 = "{\"method\":\"all\",\"namespace\":\"Urgency\"}";
			Map<String, String> formData = new HashMap<>();
			formData.put("method", "all");
			formData.put("namespace", "Urgency");

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
					}
				}
				LogUtils.info("Response: " + response.toString());
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

	public static List<String> handleData(String filePath) {
		List<String> titles = null;

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


			LogUtils.info("Successfully updated JSON file.");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return titles;
	}

}
