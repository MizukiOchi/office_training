package List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OmikujiTableToReader {
	// Fortune telling.csvファイルをomikjiテーブルに読み込むだけのクラス

	public static void main(String[] args) throws SQLException, IOException {
		/**
		 * ①Fortune telling.csvファイルを読み込む
		 * １、CSVファイルを１行ずつ読み込む
		 * ２、CSVから読み込んだデータをコンソールへ出力する
		 */
		//----------------------------------------
		// １、CSVファイルを１行ずつ読み込む
		//----------------------------------------
		File file = new File("Fortune telling.csv");
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String data = "";
			// CSVファイルの２行目から読み込む(不要な一行目をループ前に読み込んでいる。)
			bufferedReader.readLine();
			//------------------------------------------------------------------------------------------------
			//２、CSVから読み込んだデータをコンソールへ出力する（※読み込まれているかを確認するため）
			//------------------------------------------------------------------------------------------------
			int x = 0;
			//contentsというString型の箱をwhile文の外で作成しておく（※箱は１つで良いため）
			String[] contents;

			//読み込んだ値がnullじゃない限り読み込む処理をする
			while ((data = bufferedReader.readLine()) != null) {
				// 値を分解する
				contents = data.split(",");
				//omikuji_idと紐づけるために上から順に番号をつける
				System.out.println("\nomikuji_id : " + x++);
				//---------------------------------------------------------------------------------------
				//３、Fortune telling.csvから読み込んだ値をコンソールへ出力する(※確認するため)
				//---------------------------------------------------------------------------------------
				// fortune_idにつける番号をswitch文を使用して割り当てる。
				switch (contents[0]) {
				case "大吉":
					System.out.println(1);
					break;
				case "中吉":
					System.out.println("fortune_id : " + 2);
					break;
				case "小吉":
					System.out.println("fortune_id : " + 3);
					break;
				case "末吉":
					System.out.println("fortune_id : " + 4);
					break;
				case "吉":
					System.out.println("fortune_id : " + 5);
					break;
				case "凶":
					System.out.println("fortune_id : " + 6);
					break;
				}

				System.out.println("運勢 : " + contents[0]);
				System.out.println("願い事 : " + contents[1]);
				System.out.println("商い : " + contents[2]);
				System.out.println("学問 : " + contents[3]);
			}

			// 最後にファイルを閉じてリソースを開放する
			bufferedReader.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}

		/**
		 * ②JDBCを使用して読み込んだデータをomikujiテーブルに投入する （＊あとで別のクラスを作ること）
		 *１、JDBCをしようしてDBへ接続
		 *２、CSVファイルを読み込んでDBへデータを入力する
		 */
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// -----------------
			// １、JDBCを使用してDBへ接続
			// -----------------
			//( "jdbc:postgresql://[場所(Domain)]:[ポート番号]/[DB名]",ログインロール,パスワード);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/omikuji", "m_ochi",
					"mizusugatr09");
			statement = connection.createStatement();
			//			//DBにきちんと接続できとるかを確認
			//			//-----------------
			//			// SQLの発行
			//			//-----------------
			//			//ユーザー情報のテーブル
			//			//DBから値を取得
			//			resultSet = statement.executeQuery("SELECT * FROM fortune");
			//			System.out.println("DB接続確認");
			//			while (resultSet.next()) {
			//				int fortune_id = resultSet.getInt("fortune_id");
			//				System.out.println(fortune_id);
			//				String fortune_name = resultSet.getString("fortune_name");
			//				System.out.println(fortune_name);
			//			}

			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String data = "";
			// CSVファイルの２行目から読み込む(不要な一行目をループ前に読み込んでいる。)
			bufferedReader.readLine();
			//------------------------------------------------------------------------------------------------
			//２、CSVから読み込んだデータをコンソールへ出力する（※読み込まれているかを確認するため）
			//------------------------------------------------------------------------------------------------
			int x = 0;

			//contentsというString型の箱をwhile文の外で作成しておく（※箱は１つで良いため）
			String[] contents;
			int unsei = 0;
			String wish = "";
			String business = "";
			String study = "";
			String ochi = "越智";

			while ((data = bufferedReader.readLine()) != null) {
				// 値を分解する
				contents = data.split(",");
				//omikuji_idと紐づけるために上から順に番号をつける
				unsei = JudgeUnseiCode(contents[0]);
				wish = contents[1];
				business = contents[2];
				study = contents[3];
				// SQLの実行
				Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/omikuji", "m_ochi",
						"mizusugatr09");
				String sql = "INSERT INTO omikuji(omikuji_id,fortune_id, wish, business, study, changer, update_date, author, create_date) values (?, ?, ?, ?, ?, ?, current_timestamp, ?, current_timestamp);";
				PreparedStatement ps = conn.prepareStatement(sql);
				System.out.println(ps);
				ps.setInt(1, x++);
				ps.setInt(2, unsei);
				ps.setString(3, wish);
				ps.setString(4, business);
				ps.setString(5, study);
				ps.setString(6, ochi);
				ps.setString(7, ochi);
				System.out.println(ps);
				ps.executeUpdate();
				conn.commit();
				System.out.println(sql);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	//	メソッド
	private static int JudgeUnseiCode(String s) {
		int unsei = 0;
		// fortune_idにつける番号をswitch文を使用して割り当てる。
		switch (s) {
		case "大吉":
			unsei = 1;
			break;
		case "中吉":
			unsei = 2;
			break;
		case "小吉":
			unsei = 3;
			break;
		case "末吉":
			unsei = 4;
			break;
		case "吉":
			unsei = 5;
			break;
		case "凶":
			unsei = 6;
			break;
		}
		return unsei;
	}
	//		 //-----------------
	//		 // 値の取得
	//		 //-----------------
	//		 // フィールド一覧を取得
	//		 List<String> fields = new ArrayList<String>();
	//		 ResultSetMetaData rsmd = resultSet.getMetaData();
	//		 for (int i = 1; i <= rsmd.getColumnCount(); i++) {
	//		 fields.add(rsmd.getColumnName(i));
	//		 }

	////		 結果の出力
	//		 int rowCount = 0;
	//		 while (resultSet.next()) {
	//		 rowCount++;
	//
	//
	//		System.out.println("---------------------------------------------------");
	//		// System.out.println("--- Rows:" + rowCount);
	//		//
	//		System.out.println("---------------------------------------------------");
	//		//
	//		// //値は、「resultSet.getString(<フィールド名>)」で取得する。
	//		// for (String field : fields) {
	//		// System.out.println(field + ":" + resultSet.getString(field));
	//		// }
	//		// }
	//
	//		// } finally {
	//		// // 接続を切断する
	//		// if (resultSet != null) {
	//		// resultSet.close();
	//		// }
	//		// if (statement != null) {
	//		// statement.close();
	//		// }
	//		// if (connection != null) {
	//		// connection.close();
	//		// }
	//		// }

}
