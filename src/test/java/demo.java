import Initialization.Init;
import helpers.ExcelHelper;
import logs.LogUtils;

//import static Support.Initialization.Init.driver;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class demo extends Init {
	public static void main(String[] args) {
		// Tạo JSON chính

		int ID = 1;
		String ORDER_TYPE = "editing";
		String ACADEMIC_LEVEL = "Undergrad. (yrs 3-4)";
		String TYPE_OF_DOCUMENT = "Analysis Paper"; // Your pape;
		String DISCIPLINE = "Agriculture"; // Your subjec;
		String PAPER_FORMAT = "APA";
		String TITLE = "Lorem Ipsum";
		String INSTRUCTIONS = "Lorem Ipsum";
		String FILE_UPLOAD = "";
		int SOURCES = 3;
		String DEADLINE = "48 hours";
		int PAGES = 4;
		String SPACING = "Single";
		int SLIDES = 3;
		String WRITER_CATEGORY = "ADVANCED (+25%)";
		String ABSTRACT = "Yes";
		String PREVIOUS_WRITER = "No";
		String PAYMENT_METHOD = "Stripe";
		String DISCOUNT = "paper15";

		JSONObject json = new JSONObject();

		// Input giá trị vào JSON
		json.put("step", 8);

		// orderType object
		JSONObject orderType = new JSONObject();
		orderType.put("title", ORDER_TYPE);
		orderType.put("description", "Have a professional academic writer complete your paper according to your specific requirements and deadline.");
		orderType.put("id", ORDER_TYPE);
		json.put("orderType", orderType);

		json.put("balance", 0);
		json.put("isWallet", false);
		json.put("stepPassed", 8);

		// discipline object
		JSONObject discipline = new JSONObject();
		discipline.put("id", 62);
		discipline.put("title", "Agriculture");
		discipline.put("isActive", false);
		discipline.put("isFavorite", true);
		discipline.put("description", "");
		discipline.put("isDefault", false);
		discipline.put("isIB", false);
		json.put("discipline", discipline);

		// documentType object
		JSONObject documentType = new JSONObject();
		documentType.put("id", 105);
		documentType.put("title", "Admission Essay");
		documentType.put("isActive", false);
		documentType.put("isFavorite", true);
		documentType.put("description", "");
		documentType.put("isDefault", false);
		documentType.put("isIB", 1);
		json.put("documentType", documentType);

		// academicLevel object
		JSONObject academicLevel = new JSONObject();
		academicLevel.put("id", 3);
		academicLevel.put("title", "Undergrad. (yrs 3-4)");
		academicLevel.put("isActive", true);
		academicLevel.put("isFavorite", false);
		academicLevel.put("description", "Designed for advanced undergraduate students, typically in the final years of a Bachelor's degree.");
		academicLevel.put("isDefault", 1);
		academicLevel.put("isIB", false);
		json.put("academicLevel", academicLevel);

		json.put("instructions", "<p style=\"margin-left: 0px; text-align: left\">S</p>");
		json.put("files", new JSONArray()); // files là một mảng rỗng
		json.put("sources", 0);

		// citationStyle object
		JSONObject citationStyle = new JSONObject();
		citationStyle.put("id", 1);
		citationStyle.put("title", "APA");
		citationStyle.put("isActive", true);
		citationStyle.put("isFavorite", false);
		citationStyle.put("description", "Telemedicine offers huge benefits in improving access to and enhancing the efficiency of healthcare, especially for people residing in rural areas (Monaghesh & Hajzadeh, 2020).");
		citationStyle.put("isDefault", 1);
		citationStyle.put("isIB", false);
		json.put("citationStyle", citationStyle);

		json.put("skipSources", 0);
		json.put("urgency", 48);
		json.put("pages", 2);
		json.put("slideNumbers", 0);
		json.put("spacing", 2);
		json.put("abstractPage", false);
		json.put("prevWriter", JSONObject.NULL); // prevWriter là null

		// In JSON ra màn hình
		System.out.println(json.toString(4)); // Format đẹp với indent = 4
	}
}
