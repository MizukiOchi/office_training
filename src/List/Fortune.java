package List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fortune {

	public static void main(String[] args) {
		Fortune fortune = new Fortune();
		fortune.fortune();

		fortune.mFortune();

	}

	public void fortune() {
		System.out.println("Collectionsクラスのshuffleメソッドを使用して出力");
		//		Collections.shuffleを使って出力
		List<String> fortune = new ArrayList<>();


		fortune.add("大吉");
		fortune.add("中吉");
		fortune.add("小吉");
		fortune.add("末吉");
		fortune.add("吉");
		fortune.add("凶");
		//		System.out.println(fortune);

		Collections.shuffle(fortune);
		//		System.out.println(fortune);

		StringBuilder sb = new StringBuilder();
		sb.append("今日の運勢は");
		sb.append(fortune.get(0));
		sb.append("です");

		System.out.println(sb.toString());
		System.out.println("-------------------------------------------------------------------------");
	}


	public void mFortune() {
		//		Mathクラスのrandom()メソッドを使って出力
		List<String> mFortune = new ArrayList<>();
		System.out.println("Mathクラスのrandomメソッドを使用して出力");
		mFortune.add("大吉");
		mFortune.add("中吉");
		mFortune.add("小吉");
		mFortune.add("末吉");
		mFortune.add("吉");
		mFortune.add("凶");

		double dFortune = Math.random();
		//		System.out.println(dFortune);

		//		System.out.println((int)(Math.random() * 10));
		int idx = (int) (dFortune * 10);
		idx = idx % 5;

		StringBuilder sb = new StringBuilder();
		sb.append("今日の運勢は");
		sb.append(mFortune.get(idx));
		sb.append("です");

		System.out.println(sb.toString());

//		System.out.println("確認①" + fortune);
//		switch (idx) {
//		case 0:
//			System.out.println("今日の運勢は大吉です");
//			break;
//
//		case 1:
//			System.out.println("今日の運勢は中吉です");
//			break;
//
//		case 2:
//			System.out.println("今日の運勢は小吉です");
//			break;
//
//		case 3:
//			System.out.println("今日の運勢は末吉です");
//			break;
//
//		case 4:
//			System.out.println("今日の運勢は吉です");
//			break;
//
//		case 5:
//			System.out.println("今日の運勢は凶です");
//			break;
//		}
//				double dFortune = Math.random();
//				System.out.println(dFortune);
//
//				System.out.println((int)(Math.random() * 10));
//		System.out.println("確認③" );
//				StringBuilder sb = new StringBuilder();
//				sb.append("（math)今日の運勢は");
//				sb.append(fortune);
//				sb.append("です");
//
//				System.out.println("確認②" + sb.toString());
	}

}

//＊方法はshufulとmathクラスのrundomメソッドとrundomクラスを使う方法がある

//①System.out.println("今日の運勢は" + fortune.get(1) + "です");

//		for (int i = 0; i >= fortune.size();) {
//		    System.out.println("今日の運勢は" + i + "です");//繰り返し表示

//②StringBuilder sb = new StringBuilder();
//sb.append("今日の運勢は");
//sb.append(fortune.get(1));
//sb.append("です");
//
//String str = new String(sb); // "abcdefghi"

//③Collections.shuffle(sList);//要素をシャッフル
//
//for (String s : sList) {
//    System.out.println(s);//繰り返し表示
//}

//(int i=0; i<lList.size(); ++i)
//{
//   System.out.println(lList.get(i));
//}