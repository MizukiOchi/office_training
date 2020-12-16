package List;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Fortune2 {
	public static void main(String[] args) throws IOException {
		Fortune2 fortune2 = new Fortune2();
		fortune2.fortune();
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
			 * 入力チェックをする。 １、入力された日付が８桁以外の場合は、エラーメッセージを出力 ２、正しい年月日かどうか入力チェックする。
			 */
			try {
				System.out.println("誕生月を入力して下さい。(例：20200709)");
				birthday = reader.readLine();
				// １、入力されたのが８桁かどうか
				// ８桁以外が入力された場合→"例にの通り、８桁を入力してください。"
				if (birthday.length() != 8) {
					// 次の処理に行かずに次のループに入る。（初めから）
					System.out.println("例の通り8桁を入力してください。");
					continue;
				}
				// ２、正しい年月日かどうかをチェック
				// 正しい年月日でない場合 →"正しい日付を入力してください。"→再入力を求める
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				// 日付/時刻解析を厳密に（＝存在しない日付を指定された場合、Exception を発生させること。）行うかどうかを設定する。
				format.setLenient(false);
				try {
					format.parse(birthday);
					// ループを抜ける
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
		 * ③過去の結果と比較する（CSVファイルを使用する） １、結果ファイルを１行ずつ読む。 ２、一致→同じ結果を返す→終了
		 * ３、不一致→シャッフルをして結果を出す
		 */
		// ③−１、結果ファイルを１行ずつ読む
		File getFile = new File("fortuneget.csv");
		try {
			FileReader dateFileReader = new FileReader(getFile);
			BufferedReader dateBufferedReader = new BufferedReader(dateFileReader);
			String getdate;
			while ((getdate = dateBufferedReader.readLine()) != null) {
				// カンマで分割した内容を配列に格納する
				String[] content = getdate.split(",");
				if (content.length != 6) {
					System.out.println("結果ファイルが異常です！");
					return;
				}
				// 過去結果の誕生日と日付を取り出す。
				// 配列の中身の0,1番目の文字列を表示する。
				String csvbirthday = content[0];
				String csvtoday = content[1];
				// ③−２、もし①誕生日と②本日の日付が一致する結果があれば同じ結果を表示
				if (csvbirthday.equals(birthday) && csvtoday.equals(df.format(date))) {
					// 同じ結果を出す
					String csvunsei = content[2];
					String csvnegaigoto = content[3];
					String csvakinaii = content[4];
					String csvgakumon = content[5];
					StringBuilder sb = new StringBuilder();
					sb.append("今日の運勢は");
					sb.append(csvunsei);
					sb.append("です");
					sb.append("\n 願い事：");
					sb.append(csvnegaigoto);
					sb.append("\n 商い：");
					sb.append(csvakinaii);
					sb.append("\n 学問：");
					sb.append(csvgakumon);
					System.out.println(sb.toString());
					return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ③−３、不一致の場合
		/**
		 * ④おみくじを作成する １、おみくじの定義を１行ずつ読む ２、おみくじのインスタンスを作ってリストに入れる
		 */
		// ④−１、おみくじの定義を１行ずつ読む
		// Omikujiクラスのオブジェクトを１つずつListに追加する
		List<Omikuji> fortune = new ArrayList<>();
		// Omikuji型の変数を作成する
		Omikuji omikuji = null;
		// CSVファイルを１行ずつ読み込む
		File file = new File("Fortune telling.csv");
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String data = "";
			// CSVファイルの２行目から読み込む(不要な一行目をループ前に読み込んでいる。)
			bufferedReader.readLine();
			// ④−２、おみくじのインスタンスを作ってリストに入れる
			while ((data = bufferedReader.readLine()) != null) {
				// 値を分解する
				String[] contents = data.split(",");
				// 「０」番目でCSVファイルのどの値を読み込むかを判定
				switch (contents[0]) {
				case "大吉":
					// Daikichiクラスをインスタンス化（おみくじ型のDaikichiオブジェクトを生成）
					omikuji = new Daikichi();
					break;
				case "中吉":
					// Chuukichiクラスをインスタンス化
					omikuji = new Chuukichi();
					break;
				case "小吉":
					// Syoukichiクラスをインスタンス化
					omikuji = new Syoukichi();
					break;
				case "末吉":
					// Suekichiクラスをインスタンス化
					omikuji = new Suekichi();
					break;
				case "吉":
					// Kichiクラスをインスタンス化
					omikuji = new Kichi();
					break;
				case "凶":
					// Kyouクラスをインスタンス化
					omikuji = new Kyou();
					break;
				default:
				}

				// 追加した情報を以下でセットしている
				omikuji.setUnsei();
				omikuji.setUnsei();
				omikuji.setNegaigoto(contents[1]);
				omikuji.setAkinai(contents[2]);
				omikuji.setGakumon(contents[3]);

				// fortuneにswich文で回して読み込んだFortune telling.csvファイルを１行ずつ追加している。
				fortune.add(omikuji);

			}
			// 最後にファイルを閉じてリソースを開放する
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/**
		 * ⑤おみくじを１枚引く
		 */
		// Listに入れた子クラスノオブフェクトをシャッフルする
		// 1,shuffleで箱をシャッフルする方法
		Collections.shuffle(fortune);
		StringBuilder sb = new StringBuilder();

		// //エンコードを調べる
		// System.out.println(System.getProperty("file.encoding"));

		// インターファイスで定義したメソッドを出力
		System.out.println(fortune.get(0).disp());
		/**
		 * ⑥引いたおみくじの結果を結果ファイルに書き込む
		 */
		// ※結果が出た時点で出力結果を格納している
		// ファイルに書き出す
		try {
			// 出力先を作成する
			FileWriter fw = new FileWriter("fortuneget.csv", true);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			// 内容を指定する pw.print("");←この中にfortuneget.csvへ書き込みしたい情報を格納する。
			pw.print(birthday);
			pw.print(",");
			pw.print(df.format(date));
			pw.print(",");
			pw.print(fortune.get(0).getUnsei());
			pw.print(",");
			pw.print(fortune.get(0).getNegaigoto());
			pw.print(",");
			pw.print(fortune.get(0).getAkinai());
			pw.print(",");
			pw.print(fortune.get(0).getGakumon());
			pw.print("\n");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			// 例外時処理
			ex.printStackTrace();
		}
	}
}