import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

public class Main {
	public static ArrayList<Karteikarte> Stufe1 = new ArrayList<Karteikarte>();
	static ArrayList<Karteikarte> Stufe2 = new ArrayList<Karteikarte>();
	static ArrayList<Karteikarte> Stufe3 = new ArrayList<Karteikarte>();
	static ArrayList<Karteikarte> FertigGelernt = new ArrayList<Karteikarte>();
	static ArrayList<Karteikarte> bereitsGelernt = new ArrayList<Karteikarte>();
	static Connection con;
	static Statement statement;
	static Boolean geladen = false;
	BufferedReader in;

	public static void main(String[] args) throws Exception {
		Main main = new Main();
	}

	public Main() throws Exception {
		KarteiLaden();
		while (!geladen) {
			synchronized (this) {
				this.wait(100);
			}
		}
		GUI gui = new GUI(this);
		gui.open();
		System.out.println("ende Main");
		KarteiAusgeben();
		DatenbankAktualisieren();
	}

	public Main(Karteikarte kartei) throws Exception {
		KarteiHinzuf�gen(kartei);
	}

	public void KarteiHinzuf�gen() throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Frage der neuen Kartei:");
		String Frage = in.readLine();
		System.out.println("Antwort 1:");
		String Antwort1 = in.readLine();
		System.out.println("Antwort 2:");
		String Antwort2 = in.readLine();
		System.out.println("Antwort 3:");
		String Antwort3 = in.readLine();
		System.out.println("Antwort 4:");
		String Antwort4 = in.readLine();
		int l�s = Integer.MAX_VALUE;
		while (l�s == Integer.MAX_VALUE) {
			System.out.println("Nummer der richtigen L�sung:");
			String L�sung = in.readLine();
			if (L�sung.equals("1") || L�sung.equals("2") || L�sung.equals("3") || L�sung.equals("4")) {
				l�s = Integer.valueOf(L�sung);
				break;
			}
			System.err.println("Eine Zahl zwischen 1 und 4 bitte!");
		}
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:9999", "root", "mysql");
		statement = con.createStatement();
		statement.executeUpdate(
				"INSERT INTO `testdatenbank`.`stufe1` (`Antwort_Eins`, `Antwort_Zwei`, `Antwort_Drei`, `Antwort_Vier`, `Frage`, `L�sung`) VALUES ('"
						+ Antwort1 + "','" + Antwort2 + "','" + Antwort3 + "','" + Antwort4 + "','" + Frage + "'," + l�s
						+ ")");

	}

	public void KarteiHinzuf�gen(Karteikarte kartei) throws Exception {		
		Stufe1.add(kartei);
		System.out.println("Kartei mit Frage: ''" + kartei.getFrage() + "'' wurde hinzugef�gt!");
	}

	private void KarteiAusgeben() {

		System.out.println("\n\nStufe1:");
		for (int i = 0; i < Stufe1.size(); i++) {
			System.out.println(Stufe1.get(i).toString());
		}
		System.out.println("_____________________________________________________");
		System.out.println("_____________________________________________________");

		System.out.println("Stufe2:");
		for (int i = 0; i < Stufe2.size(); i++) {
			System.out.println(Stufe2.get(i).toString());
		}
		System.out.println("_____________________________________________________");
		System.out.println("_____________________________________________________");

		System.out.println("Stufe3:");
		for (int i = 0; i < Stufe3.size(); i++) {
			System.out.println(Stufe3.get(i).toString());
		}
		System.out.println("_____________________________________________________");
		System.out.println("_____________________________________________________");

		System.out.println("fertig gelernt");
		for (int i = 0; i < FertigGelernt.size(); i++) {
			System.out.println(FertigGelernt.get(i).toString());
		}

	}

	/**
	 * L�d aus der Datenbank die Karteikarten in das Lernprogramm
	 */
	private static void KarteiLaden() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:9999", "root", "mysql");
		statement = con.createStatement();

		ResultSet rs = statement.executeQuery("SELECT * FROM testdatenbank.stufe1");
		while (rs.next()) {
			Stufe1.add(createKarteiFromDatabase(rs));
		}
		rs = statement.executeQuery("SELECT * FROM testdatenbank.stufe2");
		while (rs.next()) {
			Stufe2.add(createKarteiFromDatabase(rs));
		}

		rs = statement.executeQuery("SELECT * FROM testdatenbank.stufe3");
		while (rs.next()) {
			Stufe3.add(createKarteiFromDatabase(rs));
		}

		rs = statement.executeQuery("SELECT * FROM testdatenbank.fertiggelernt");
		while (rs.next()) {
			FertigGelernt.add(createKarteiFromDatabase(rs));
		}

		geladen = true;

	}

	private static Karteikarte createKarteiFromDatabase(ResultSet rs) throws Exception {
		String Antwort1 = rs.getString(1);
		String Antwort2 = rs.getString(2);
		String Antwort3 = rs.getString(3);
		String Antwort4 = rs.getString(4);
		String Frage = rs.getString(5);
		int L�sung = rs.getInt(6);
		return new Karteikarte(Antwort1, Antwort2, Antwort3, Antwort4, Frage, L�sung);
	}

	private void DatenbankAktualisieren() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:9999", "root", "mysql");
		statement = con.createStatement();

		statement.executeUpdate("DELETE FROM `testdatenbank`.`stufe1`");
		statement.executeUpdate("DELETE FROM `testdatenbank`.`stufe2`");
		statement.executeUpdate("DELETE FROM `testdatenbank`.`stufe3`");
		statement.executeUpdate("DELETE FROM `testdatenbank`.`fertiggelernt`");

		for (int i = 0; i < Main.Stufe1.size(); i++) {
			statement.executeUpdate(
					"INSERT INTO `testdatenbank`.`stufe1` (`Antwort_Eins`, `Antwort_Zwei`, `Antwort_Drei`, `Antwort_Vier`, `Frage`, `L�sung`) VALUES ('"
							+ Main.Stufe1.get(i).getAntwort1() + "','" + Main.Stufe1.get(i).getAntwort2() + "','"
							+ Main.Stufe1.get(i).getAntwort3() + "','" + Main.Stufe1.get(i).getAntwort4() + "','"
							+ Main.Stufe1.get(i).getFrage() + "'," + Main.Stufe1.get(i).getL�sung() + ")");
		}
		for (int i = 0; i < Main.Stufe2.size(); i++) {
			statement.executeUpdate(
					"INSERT INTO `testdatenbank`.`stufe2` (`Antwort_Eins`, `Antwort_Zwei`, `Antwort_Drei`, `Antwort_Vier`, `Frage`, `L�sung`) VALUES ('"
							+ Main.Stufe2.get(i).getAntwort1() + "','" + Main.Stufe2.get(i).getAntwort2() + "','"
							+ Main.Stufe2.get(i).getAntwort3() + "','" + Main.Stufe2.get(i).getAntwort4() + "','"
							+ Main.Stufe2.get(i).getFrage() + "'," + Main.Stufe2.get(i).getL�sung() + ")");
		}
		for (int i = 0; i < Main.Stufe3.size(); i++) {
			statement.executeUpdate(
					"INSERT INTO `testdatenbank`.`stufe3` (`Antwort_1`, `Antwort_2`, `Antwort_3`, `Antwort_4`, `Frage`, `L�sung`) VALUES ('"
							+ Main.Stufe3.get(i).getAntwort1() + "','" + Main.Stufe3.get(i).getAntwort2() + "','"
							+ Main.Stufe3.get(i).getAntwort3() + "','" + Main.Stufe3.get(i).getAntwort4() + "','"
							+ Main.Stufe3.get(i).getFrage() + "'," + Main.Stufe3.get(i).getL�sung() + ")");
		}
		for (int i = 0; i < Main.FertigGelernt.size(); i++) {
			statement.executeUpdate(
					"INSERT INTO `testdatenbank`.`fertiggelernt` (`Antwort_1`, `Antwort_2`, `Antwort_3`, `Antwort_4`, `Frage`, `L�sung`) VALUES ('"
							+ Main.FertigGelernt.get(i).getAntwort1() + "','" + Main.FertigGelernt.get(i).getAntwort2()
							+ "','" + Main.FertigGelernt.get(i).getAntwort3() + "','"
							+ Main.FertigGelernt.get(i).getAntwort4() + "','" + Main.FertigGelernt.get(i).getFrage()
							+ "'," + Main.FertigGelernt.get(i).getL�sung() + ")");
		}

	}

	public void promoteKartei(Karteikarte kartei) {
		for (int i = 0; i < Main.Stufe1.size(); i++) {
			if (kartei.equals(Main.Stufe1.get(i))) {
				Main.Stufe2.add(kartei);
				Main.Stufe1.remove(i);
				return;
			}
		}

		for (int i = 0; i < Main.Stufe2.size(); i++) {
			if (kartei.equals(Main.Stufe2.get(i))) {
				Main.Stufe3.add(kartei);
				Main.Stufe2.remove(i);
				return;
			}
		}

		for (int i = 0; i < Main.Stufe3.size(); i++) {
			if (kartei.equals(Main.Stufe3.get(i))) {
				Main.FertigGelernt.add(kartei);
				Main.Stufe3.remove(i);
				return;
			}
		}
	}

	public void demoteKartei(Karteikarte kartei) {
		for (int i = 0; i < Main.Stufe2.size(); i++) {
			if (kartei.equals(Main.Stufe2.get(i))) {
				Main.Stufe1.add(kartei);
				Main.Stufe2.remove(i);
				return;
			}
		}
		for (int i = 0; i < Main.Stufe3.size(); i++) {
			if (kartei.equals(Main.Stufe3.get(i))) {
				Main.Stufe2.add(kartei);
				Main.Stufe3.remove(i);
				return;
			}
		}
	}

	public void abfrage() {
		int i = 0;

		outer: for (; i < Main.Stufe1.size(); i++) {
			for (int j = 0; j < Main.bereitsGelernt.size(); j++) {
				if (Main.Stufe1.get(i).equals(Main.bereitsGelernt.get(j))) {
					i++;
					if (i <= Main.Stufe1.size()) {
						continue;
					} else
						break outer;
				}
			}
			Main.bereitsGelernt.add(Main.Stufe1.get(i));
			GuiAbfrage gui = new GuiAbfrage(Main.Stufe1.get(i), this);
			gui.open();
			i--;
			
		}

		i = 0;

		outer: for (; i < Main.Stufe2.size(); i++) {
			for (int j = 0; j < Main.bereitsGelernt.size(); j++) {
				if (Main.Stufe2.get(i).equals(Main.bereitsGelernt.get(j))) {
					i++;
					if (i < Main.Stufe2.size()) {
						continue;
					} else
						break outer;

				}
			}
			Main.bereitsGelernt.add(Main.Stufe2.get(i));
			GuiAbfrage gui = new GuiAbfrage(Main.Stufe2.get(i), this);
			gui.open();
			i--;
			

		}

		i = 0;

		outer: for (; i < Main.Stufe3.size(); i++) {
			for (int j = 0; j < Main.bereitsGelernt.size(); j++) {
				if (Main.Stufe3.get(i).equals(Main.bereitsGelernt.get(j))) {
					i++;
					if (i < Main.Stufe3.size()) {
						continue;
					} else
						break outer;
				}
			}
			Main.bereitsGelernt.add(Main.Stufe3.get(i));
			GuiAbfrage gui = new GuiAbfrage(Main.Stufe3.get(i), this);
			gui.open();
			i--;
		
		}

	}

}

/**
 * 
 * @author Julian F�hrt die Abfrage �ber die Konsole durch
 */
class Abfrage implements Runnable {

	private BufferedReader in;

	@Override
	public void run() {

		int i = 0;

		outer: for (; i < Main.Stufe1.size(); i++) {
			for (int j = 0; j < Main.bereitsGelernt.size(); j++) {
				if (Main.Stufe1.get(i).equals(Main.bereitsGelernt.get(j))) {
					i++;
					if (i <= Main.Stufe1.size()) {
						continue;
					} else
						break outer;
				}
			}
			KarteiDrucken(Main.Stufe1.get(i));
			in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Bitte Nummer der Richtigen Antwort eingeben: ");
			try {
				String L�sung = in.readLine();
				if (L�sung.equals("1") || L�sung.equals("2") || L�sung.equals("3") || L�sung.equals("4")) {
					int l�s = Integer.valueOf(L�sung);
					if (l�s == Main.Stufe1.get(i).getL�sung()) {
						System.err.println("Richtige L�sung!");
						Main.Stufe2.add(Main.Stufe1.get(i));
						Main.bereitsGelernt.add(Main.Stufe1.get(i));
						Main.Stufe1.remove(i);
						i--;
					} else {
						System.err.println("Falsche L�sung!");

					}

				} else {
					System.err.println("Zahl zwischen 1 und 4 eingeben!");
					i--;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		i = 0;

		outer: for (; i < Main.Stufe2.size(); i++) {
			for (int j = 0; j < Main.bereitsGelernt.size(); j++) {
				if (Main.Stufe2.get(i).equals(Main.bereitsGelernt.get(j))) {
					i++;
					if (i < Main.Stufe2.size()) {
						continue;
					} else
						break outer;

				}
			}
			KarteiDrucken(Main.Stufe2.get(i));
			in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Bitte Nummer der Richtigen Antwort eingeben: ");
			try {
				String L�sung = in.readLine();
				if (L�sung.equals("1") || L�sung.equals("2") || L�sung.equals("3") || L�sung.equals("4")) {
					int l�s = Integer.valueOf(L�sung);
					if (l�s == Main.Stufe2.get(i).getL�sung()) {
						System.err.println("Richtige L�sung!");
						Main.Stufe3.add(Main.Stufe2.get(i));
						Main.bereitsGelernt.add(Main.Stufe2.get(i));
						Main.Stufe2.remove(i);
						i--;
					} else {
						System.err.println("Falsche L�sung!");
						Main.bereitsGelernt.add(Main.Stufe2.get(i));
						Main.Stufe1.add(Main.Stufe2.get(i));
						Main.Stufe2.remove(i);
					}

				} else {
					System.err.println("Zahl zwischen 1 und 4 eingeben!");
					i--;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		i = 0;

		outer: for (; i < Main.Stufe3.size(); i++) {
			for (int j = 0; j < Main.bereitsGelernt.size(); j++) {
				if (Main.Stufe3.get(i).equals(Main.bereitsGelernt.get(j))) {
					i++;
					if (i <= Main.Stufe3.size()) {
						continue;
					} else
						break outer;
				}
			}
			KarteiDrucken(Main.Stufe3.get(i));
			in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Bitte Nummer der Richtigen Antwort eingeben: ");
			try {
				String L�sung = in.readLine();
				if (L�sung.equals("1") || L�sung.equals("2") || L�sung.equals("3") || L�sung.equals("4")) {
					int l�s = Integer.valueOf(L�sung);
					if (l�s == Main.Stufe3.get(i).getL�sung()) {
						System.err.println("Richtige L�sung!");
						Main.FertigGelernt.add(Main.Stufe3.get(i));
						Main.bereitsGelernt.add(Main.Stufe3.get(i));
						Main.Stufe3.remove(i);
						i--;
					} else {
						System.err.println("Falsche L�sung!");
						Main.Stufe2.add(Main.Stufe3.get(i));
						Main.bereitsGelernt.add(Main.Stufe3.get(i));
						Main.Stufe3.remove(i);
					}

				} else {
					System.err.println("Zahl zwischen 1 und 4 eingeben!");
					i--;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void KarteiDrucken(Karteikarte karteikarte) {
		System.out.println(
				"__________________________________________________________\nFrage: " + karteikarte.getFrage());
		System.out.println("\n1: " + karteikarte.getAntwort1() + "\n2: " + karteikarte.getAntwort2() + "\n3: "
				+ karteikarte.getAntwort3() + "\n4: " + karteikarte.getAntwort4());
	}

}
