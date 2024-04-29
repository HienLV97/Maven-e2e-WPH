import Support.Initialization.Init;
import Support.Routers;
import org.testng.annotations.Test;

//import static Support.Initialization.Init.driver;

public class demo extends Init {

	@Test
	public void t()  {
//		String filePathAcademicLevel = OrderForm.filePath(OrderForm.academicLevel);
//		List<String> academicLevel = OrderForm.handleData(OrderForm.academicLevel);
//		System.out.println(academicLevel);
		Authenticate();
		driver.get(Routers.SIGN_IN);
		String url = driver.getCurrentUrl();
		System.out.println(url);
	}
}
