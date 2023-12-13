package Support.ScreenSetup;

public class OnlyTest {
	public static String t1() {
		int time = 1;
		String greeting = "Good evening";
		if (time < 10) {
			greeting = "Good morning";
			return greeting;
		}
		if (time < 20) {
			greeting = "Good day";
			return greeting;
		}
		return greeting;
	}

	public static void main(String[] args) {
		System.out.println(t1());

	}
}
