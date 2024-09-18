
public class Karteikarte {
	private String Antwort1;
	private String Antwort2;
	private String Antwort3;
	private String Antwort4;
	private String Frage;
	private int Lösung;

	public Karteikarte(String antwort1, String antwort2, String antwort3, String antwort4, String frage, int lösung) {
		Antwort1 = antwort1;
		Antwort2 = antwort2;
		Antwort3 = antwort3;
		Antwort4 = antwort4;
		Frage = frage;
		Lösung = lösung;
	}

	public String getAntwort1() {
		return Antwort1;
	}

	public String getAntwort2() {
		return Antwort2;
	}

	public String getAntwort3() {
		return Antwort3;
	}

	public String getAntwort4() {
		return Antwort4;
	}

	public String getFrage() {
		return Frage;
	}

	public int getLösung() {
		return Lösung;
	}

	public String toString() {
		return "1: " + Antwort1 + "; 2: " + Antwort2 + "; 3: " + Antwort3 + "; 4: " + Antwort4 + "; Frage: " + Frage
				+ "; Lösung: " + String.valueOf(Lösung);
	}

	public Boolean equals(Karteikarte kartei) {
		if (this.getAntwort1().equals(kartei.getAntwort1()) && this.getAntwort2().equals(kartei.getAntwort2())
				&& this.getAntwort3().equals(kartei.getAntwort3()) && this.getAntwort4().equals(kartei.getAntwort4())
				&& this.getFrage().equals(kartei.getFrage()) && this.getLösung() == kartei.getLösung()) {
			return true;
		}
		return false;
	}

}
