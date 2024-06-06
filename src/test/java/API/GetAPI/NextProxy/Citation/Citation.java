package API.GetAPI.NextProxy.Citation;

import Support.Constants;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//
import org.json.JSONArray;
import org.json.JSONObject;

public class Citation {
	static String filePath = "src/test/java/API/Data/Dashboard/Citation.json";
	static String URLCitation = Constants.proxyDevURL + "/styles";

	//			public static String getToken() {
	public static void main(String[] args) {
		getCitation(1);
	}

	public static String getCitation(int value) {
		try {
			// Create the connection object and set the required HTTP method and headers
			URL url = new URL(URLCitation);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Basic a2Ftb3JhOmlhbWFmcmllbmQ=");
			connection.setRequestProperty("Postman-Token", "<calculated when request is sent>");
			connection.setRequestProperty("Host", "<calculated when request is sent>");
			connection.setRequestProperty("User-Agent", "PostmanRuntime/7.37.0");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			connection.setRequestProperty("Connection", "keep-alive");
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
			JSONObject jsonObject = new JSONObject(response);
			// Đọc tệp JSON
//				JSONParser parser = new JSONParser();
//				JSONObject jsonData = (JSONObject) parser.parse(response.toString());
			System.out.println("jsonObject: "+jsonObject);
			JSONArray dataArray = jsonObject.getJSONArray("data");


//				JSONArray dataArray =  jsonData.getJSONArray("data");

			JSONObject dataObject = dataArray.getJSONObject(value);
			System.out.println("data: " + dataObject);
			String title = dataObject.getString("title");
			return title;
		} catch (ProtocolException e) {
			throw new RuntimeException(e);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}