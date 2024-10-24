package API.GetAPI.NextProxy.Citation;

import helpers.Constants;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

//
public class Citation {
	static String filePath = "src/test/java/API/Data/Dashboard/Citation.json";
	static String URLCitation = Constants.PROXY_DEV_URL + "/styles";

	public static String getCitation(int value) {
		try {
			// Tạo đối tượng kết nối và thiết lập phương thức HTTP và headers yêu cầu
			URL url = new URL(URLCitation);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Basic a2Ftb3JhOmlhbWFmcmllbmQ=");
			connection.setRequestProperty("User-Agent", "PostmanRuntime/7.37.0");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			connection.setRequestProperty("Connection", "keep-alive");

			StringBuilder response = new StringBuilder();

			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
			}

			// Ghi phản hồi vào file
			try (FileWriter writer = new FileWriter(filePath)) {
				writer.write(response.toString());
			}

			// Phân tích cú pháp phản hồi JSON
			JSONObject jsonObject = new JSONObject(response.toString());
			JSONArray pricesArray = jsonObject.getJSONArray("data");
			JSONObject data = pricesArray.getJSONObject(value).getJSONObject("data");

			return (String) data.get("title");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}