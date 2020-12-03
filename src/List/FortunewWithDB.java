package List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FortunewWithDB {
	public static void main(String[] args) throws IOException {
		FortunewWithDB fortunewWithDB = new FortunewWithDB();
		fortunewWithDB.fortune();
	}

	@SuppressWarnings("null")
	public void fortune() throws IOException {
		/**
		 * ①誕生日の入力を求める
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String birthday = null;
		while (true) {
			/**
			 * 入力チェックをする。
			 * １、入力された日付が８桁以外の場合は、エラーメッセージを出力
			 * ２、正しい年月日かどうか入力チェックする。
			 */
			try {
				System.out.println("誕生月を入力して下さい。(例：20200709)");
				birthday = reader.readLine();
				//１、入力されたのが８桁かどうか
				//８桁以外が入力された場合→"例にの通り、８桁を入力してください。"
				if (birthday.length() != 8) {
					//次の処理に行かずに次のループに入る。（初めから）
					System.out.println("例の通り8桁を入力してください。");
					continue;
				}
				//２、正しい年月日かどうかをチェック　
				//正しい年月日でない場合　→"正しい日付を入力してください。"→再入力を求める
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				// 日付/時刻解析を厳密に（＝存在しない日付を指定された場合、Exception を発生させること。）行うかどうかを設定する。
				format.setLenient(false);
				try {
					format.parse(birthday);
					//ループを抜ける
					break;
				} catch (Exception e) {
					System.out.println("正しい日付を入力してください。");
					continue;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		}

		/**
		 * ②本日を求める
		 */
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

		/**
		 * ③過去の結果と比較する(DBを使用する)
		 * 　１、結果ファイルを１行ずつ読む。
		 * 　２、一致→同じ結果を返す→終了
		 * 　３、不一致→シャッフルをして結果を出す
		 */
		//③−１resultsテーブルを１行ずつ読み込む

		//③−２、もし①誕生日と②本日の日付が一致する結果があれば同じ結果を表示して終了する

		//③−３、不一致の場合
		/**
		 * ④おみくじを作成する
		 * 　１、おみくじの定義を１行ずつ読む
		 * 　２、おみくじのインスタンスを作ってリストに入れる
		 */
		//④−１、おみくじの定義を１行ずつ読む
		//Omikujiクラスのオブジェクトを１つずつListに追加する
		List<Omikuji> fortune = new ArrayList<>();
		//Omikuji型の変数を作成する
		Omikuji omikuji = null;
		//OmikujiTableToReaderクラスで作成したomikujiテーブルを１行ずつ読み込む
//		File file = new File("Fortune telling.csv");
//		try {
//			FileReader fileReader = new FileReader(file);
//			BufferedReader bufferedReader = new BufferedReader(fileReader);
//	}









	}
}
