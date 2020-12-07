package List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.Omikuji;


public class OmikujiTableToReader {
	// Fortune telling.csvファイルをomikjiテーブルに読み込むだけのクラス

	public static void main(String[] args) throws SQLException {
		/**
		 * ①Fortune telling.csvファイルを読み込む
		 */
		// CSVファイルを１行ずつ読み込む
		File file = new File("Fortune telling.csv");
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// CSVファイルの２行目から読み込む(不要な一行目をループ前に読み込んでいる。)
			String data = "";
			bufferedReader.readLine();
			int x = 0;

			String[] contents;
			// omikuji entityをnewする
			Omikuji omikuji = new Omikuji();
			// setterメソッドを使用してデータベースの値をセットする

			// getterメソッドを使用して
			while ((data = bufferedReader.readLine()) != null) {
				// 値を分解する
				contents = data.split(",");
				System.out.println();
				// omikuji_idと紐づけるために上から順に番号をつける
				System.out.println("omikuji_id : " + x++);
				// Fortune telling.csv殻読み込んだ値をコンソールへ出力する
				// fortune_idにつける番号をswitch文を使用して割り当てる。
				switch (contents[0]) {
				case "大吉":
					System.out.println("fortune_id : " + 1);
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
		 * ②JDBCを使用して読み込んだデータをomikujiテーブルに投入する （＊別のクラスを作ること）
		 *
		 */
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// -----------------
			// 接続
			// -----------------
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/omikuji",
					// "jdbc:postgresql://[場所(Domain)]:[ポート番号]/[DB名]"
					"training", // ログインロール
					"training"); // パスワード
			statement = connection.createStatement();

			// //-----------------
			// // SQLの発行
			// //-----------------
			// //ユーザー情報のテーブル
			// resultSet = statement.executeQuery("SELECT * FROM omikuji");
			resultSet = statement.executeQuery("SELECT * FROM fortune");
			// System.out.println(resultSet);

			while (resultSet.next()) {
				int fortune_id = resultSet.getInt("fortune_id");
				System.out.println(fortune_id);
				String fortune_name = resultSet.getString("fortune_name");
				System.out.println(fortune_name);
			}
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
		}

		// //-----------------
		// // 値の取得
		// //-----------------
		// // フィールド一覧を取得
		// List<String> fields = new ArrayList<String>();
		// ResultSetMetaData rsmd = resultSet.getMetaData();
		// for (int i = 1; i <= rsmd.getColumnCount(); i++) {
		// fields.add(rsmd.getColumnName(i));
		// }

		// //結果の出力
		// int rowCount = 0;
		// while (resultSet.next()) {
		// rowCount++;
		//
		//
		System.out.println("---------------------------------------------------");
		// System.out.println("--- Rows:" + rowCount);
		//
		System.out.println("---------------------------------------------------");
		//
		// //値は、「resultSet.getString(<フィールド名>)」で取得する。
		// for (String field : fields) {
		// System.out.println(field + ":" + resultSet.getString(field));
		// }
		// }

		// } finally {
		// // 接続を切断する
		// if (resultSet != null) {
		// resultSet.close();
		// }
		// if (statement != null) {
		// statement.close();
		// }
		// if (connection != null) {
		// connection.close();
		// }
		// }

	}
}
