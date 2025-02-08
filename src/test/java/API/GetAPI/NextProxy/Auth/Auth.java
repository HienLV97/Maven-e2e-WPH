package API.GetAPI.NextProxy.Auth;

import helpers.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class Auth {
	static String filePath = "src/test/java/API/Data/Dashboard/authClient.json";
	static String URLAuth = Constants.PROXY_DEV_URL + "/auth";

	public static void main(String[] args) {
		getAuth("t1@g.c","123123");
	}
	public static String getAuth(String token, String value) {
		try {
			URL url = new URL(URLAuth);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", "Basic a2Ftb3JhOmlhbWFmcmllbmQ=");
			connection.setRequestProperty("Cookie", "token=" + token);
			connection.setRequestProperty("Postman-Token", "<calculated when request is sent>");
			connection.setRequestProperty("Host", "<calculated when request is sent>");
			connection.setRequestProperty("User-Agent", "PostmanRuntime/7.37.0");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("token", token);
			connection.setDoOutput(true);

			// Kiểm tra và giải nén phản hồi nếu cần thiết
			InputStream inputStream = connection.getInputStream();
			String encoding = connection.getContentEncoding();
			if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
				inputStream = new GZIPInputStream(inputStream);
			} else if (encoding != null && encoding.equalsIgnoreCase("deflate")) {
				inputStream = new InflaterInputStream(inputStream);
			}

			StringBuilder response = new StringBuilder();
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
			}

			// Ghi phản hồi vào tệp
			Files.writeString(Paths.get(filePath), response.toString());

			// Phân tích cú pháp phản hồi JSON
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject) parser.parse(response.toString());
			System.out.println(jsonData);
			JSONObject data = (JSONObject) jsonData.get("user");
			return (String) data.get(value);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return token;
	}
}