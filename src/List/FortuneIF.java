package List;

import java.io.IOException;
import java.util.ResourceBundle;

interface FortuneIF {
	//		プロパティファイルから読み込み
	ResourceBundle rb = ResourceBundle.getBundle("Fortune_telling");
	String DISP_STR = rb.getString("disp_str");

	String disp() throws IOException;

}