package Support.CMS;

public class Routers {
	static final String BasicUserName = "kamora";
	static final String BasicPassWord = "iamafriend";
	public static final String DomainDEV = "yeti-cms.dev";
	public static final String BaseURL = "https://"+DomainDEV;
	public static final String AuthURL = "https://"+BasicUserName+":"+BasicPassWord+"@"+DomainDEV;
	//    public static  String BaseURL = "https://writersperhour.com";
	public static String BaseURLAuthor = "https://kamora:iamafriend@writersperhour.dev";
//	public static final String BaseURL = "https://kamora:iamafriend@ibhelper.dev";

	//        public static final String BaseURL = "https://ibhelper.com";
	public static final String ARTICLES = BaseURL + "/yeti/main/articles/all";
	public static final String CUSTOMER_REVIEW = BaseURL + "/yeti/main/reviews/all";
	public static final String WRITER_REVIEW = BaseURL + "/yeti/main/writers/all";
	public static final String CONSTANTS = BaseURL + "/yeti/main/constants/all";
	public static final String HOME = BaseURL + "/";
	public static final String SAMPLES = BaseURL + "/yeti/main/samples/all";
	public static final String EDIT_ARTICLES = BaseURL + "/yeti/main/articles/edit";
	public static final String SIGN_IN = BaseURL + "/login";
}
