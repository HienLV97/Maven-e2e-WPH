package SampleTest.Parameter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParam {
	@Test(groups = "1")
	@Parameters({"val1", "val2", "val3"})
	public void Sum(int v1, int v2, String v3) {
		int finalSum = v1 + v2;
		System.out.println("Kết quả là: " + finalSum);
		System.out.println("Kết quả là: " + v3);
	}
	@Test(groups = "2")
	@Parameters({"email","password"})
	public void other(String v1, String v2) {
		System.out.println("Email: "+v1);
		System.out.println("Password: "+v2);

	}
}