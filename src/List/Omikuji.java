package List;

import java.io.IOException;

//抽象クラス
public abstract class Omikuji implements FortuneIF {

	protected String unsei;
	protected String negaigoto;
	protected String akinai;
	protected String gakumon;
	protected String birthday;

	public abstract void setUnsei();

	//	プロパティファイルを読み取って実装
	public String disp() throws IOException {
		StringBuilder sb = new StringBuilder();
		//		String.format(書式文字列,　値)
		sb.append(String.format(DISP_STR, unsei));
		sb.append("\n 願い事：");
		sb.append(getNegaigoto());
		sb.append("\n 商い：");
		sb.append(getAkinai());
		sb.append("\n 学問：");
		sb.append(getGakumon());
		return sb.toString();

	}

	//	上記のフィールドのgetter・setterの生成
	public String getUnsei() {
		return unsei;
	}

	//	上のメソッドにあるpropertiesオブジェクトがあるから下記の処理はいらないのか？
	public void setUnsei(String unsei) {
		this.unsei = unsei;
	}

	public String getNegaigoto() {
		return negaigoto;
	}

	public void setNegaigoto(String negaigoto) {
		this.negaigoto = negaigoto;
	}

	public String getAkinai() {
		return akinai;
	}

	public void setAkinai(String akinai) {
		this.akinai = akinai;
	}

	public String getGakumon() {
		return gakumon;
	}

	public void setGakumon(String gakumon) {
		this.gakumon = gakumon;
	}

}
