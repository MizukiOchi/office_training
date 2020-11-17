package List;

import java.util.Properties;

public class TestFortune {
	public static void main(String[] args) {
		Properties properties = new Properties();
		//		String strpass = "/Fortune_telling.properties";
		String DISP_STR = properties.getProperty("disp_str");
		System.out.println(DISP_STR);
	}
}