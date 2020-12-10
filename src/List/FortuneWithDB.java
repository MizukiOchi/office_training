package List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Bean.OmikujiBean;

public class FortuneWithDB {
	public static void main(String[] args) throws IOException, SQLException {
		FortuneWithDB fortunewWithDB = new FortuneWithDB();
		fortunewWithDB.fortune();
	}

	@SuppressWarnings("null")
	public void fortune() throws IOException, SQLException {
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
		 * ①
		 * select文の条件付きでresultsテーブルにデータがあるかどうかを確認しに行く
		 */

//		/**
//		 * ③過去の結果と比較する(DBを使用する) １、結果ファイルを１行ずつ読む。 ２、一致→同じ結果を返す→終了
//		 * ３、不一致→シャッフルをして結果を出す
//		 */
//		 //③−１resultsテーブルを１行ずつ読み込む
//		 File getFile = new File("fortuneget.csv");
//		 try {
//		 FileReader dateFileReader = new FileReader(getFile);
//		 BufferedReader dateBufferedReader = new
//		 BufferedReader(dateFileReader);
//		 String getdate;
//		 while ((getdate = dateBufferedReader.readLine()) != null) {
//		 //カンマで分割した内容を配列に格納する
//		 String[] content = getdate.split(",");
//		 if (content.length != 6) {
//		 System.out.println("結果ファイルが異常です！");
//		 return;
//		 }
//		 // 過去結果の誕生日と日付を取り出す。
//		 //配列の中身の0,1番目の文字列を表示する。
//		 String csvbirthday = content[0];
//		 String csvtoday = content[1];
//
//		// ③−２、もし①誕生日と②本日の日付が一致する結果があれば同じ結果を表示して終了する
//		 if (csvbirthday.equals(birthday) && csvtoday.equals(df.format(date)))
//		 {
//		 //同じ結果を出す
//		 String csvunsei = content[2];
//		 String csvnegaigoto = content[3];
//		 String csvakinaii = content[4];
//		 String csvgakumon = content[5];
//		 StringBuilder sb = new StringBuilder();
//		 sb.append("今日の運勢は");
//		 sb.append(csvunsei);
//		 sb.append("です");
//		 sb.append("\n 願い事：");
//		 sb.append(csvnegaigoto);
//		 sb.append("\n 商い：");
//		 sb.append(csvakinaii);
//		 sb.append("\n 学問：");
//		 sb.append(csvgakumon);
//		 System.out.println(sb.toString());
//		 return;
//		 }
//		 }
//		 } catch (IOException e) {
//		 e.printStackTrace();
//		 }
		// ③−３、不一致の場合
		/**
		 * ④おみくじを作成する １、OmikujiTableToReaderクラスで書き込んだメソッドを使えるようにする
		 * ２、omikujiテーブルからおみくじの中身をとる
		 */
//		// ④−１、OmikujiTableToReaderクラスで書き込んだメソッドを使えるようにする
//		OmikujiTableToReader omikujitabletoreader = new OmikujiTableToReader();

		// ④−２、omikujiテーブルからおみくじの中身をとる
		// OmikujiTableToReaderクラスで作成したomikujiテーブルを１行ずつ読み込む
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		// // Omikujiクラスのオブジェクトを１つずつListに追加する
		 List<OmikujiBean> fortune = new ArrayList<>();


		// OmikujiTableToReaderクラスで作成したomikujiテーブルを１行ずつ読み込む
		// -----------------
		// ②ー１、JDBCを使用してDBへ接続
		// -----------------
		// ( "jdbc:postgresql://[場所(Domain)]:[ポート番号]/[DB名]",ログインロール,パスワード);
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/omikuji", "m_ochi", "mizusugatr09");
		statement = connection.createStatement();
//		System.out.println("connection"+statement);
		// DBにきちんと接続できとるかを確認
		// -----------------
		// SQLの発行
		// -----------------
		// ユーザー情報のテーブル
		// DBから値を取得
		resultSet = statement.executeQuery("SELECT f.fortune_id, f.fortune_name, f.changer, f.update_date, f.author, f.create_date, o.omikuji_id, o.fortune_id, o.wish, o.business, o.study, o.changer, o.update_date, o.author, o.create_date FROM fortune f LEFT OUTER JOIN omikuji o ON f.fortune_id = o.fortune_id;");
//		System.out.println(resultSet);
//		// OmikujiTableToReaderクラスで作成したomikujiテーブルを１行ずつ読み込む
//		 System.out.println("DB接続確認");
		// // Omikuji型の変数を作成する
				 OmikujiBean omikuji;
		 while (resultSet.next()) {
			 omikuji = new OmikujiBean();
			 System.out.println("while文 ："+ resultSet);
//			 fortune.add(resultSet);
//			 omikuji = resultSet.next();
//		 int omikuji_id = resultSet.getInt("omikuji_id");
////		 omikuji = omikuji_id;
//		 System.out.println("omikuji_id : " + omikuji_id);
//		 int fortune_id = resultSet.getInt("fortune_id");
//		 System.out.println("fortune_id : " + fortune_id);
//		 String wish = resultSet.getString("wish");
//		 System.out.println("wish : " + wish);
//		 String business = resultSet.getString("business");
//		 System.out.println("business : " + business);
//		 String study = resultSet.getString("study");
//		 System.out.println("study : " + study);
//		 String changer = resultSet.getString("changer");
//		 System.out.println("changer : " + changer);
//		 Timestamp update_date = resultSet.getTimestamp("update_date");
//		 System.out.println("update_date : " + update_date);
//		 String author = resultSet.getString("author");
//		 System.out.println("authoru : " + author);
//		 Timestamp create_date = resultSet.getTimestamp("create_date");
//		 System.out.println("create_date" + create_date);

//		 fortune.add(omikuji);
		 }
	}
	}
