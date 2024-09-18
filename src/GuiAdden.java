import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.awt.Color;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;

public class GuiAdden {

	Display display;
	protected Shell shlKarteiHinzufgen;
	private Text txtBitteAlleFelder;
	private Text txtFrage;
	private Text txtAntwort;
	private Text txtAntwort_1;
	private Text txtAntwort_2;
	private Text txtAntwort_3;
	private Text txtBuchstabeDerLsung;
	private Text Frage;
	private Text Antwort_1;
	private Text Antwort_2;
	private Text Antwort_3;
	private Text Antwort_4;
	private Text Lösung;
	private Text ErrorMeldung;

	

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shlKarteiHinzufgen.open();
		shlKarteiHinzufgen.layout();
		while (!shlKarteiHinzufgen.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlKarteiHinzufgen = new Shell();
		shlKarteiHinzufgen.setMinimumSize(new Point(130, 40));
		shlKarteiHinzufgen.setSize(648, 327);
		shlKarteiHinzufgen.setText("Kartei Hinzuf\u00FCgen");

		txtBitteAlleFelder = new Text(shlKarteiHinzufgen, SWT.BORDER | SWT.MULTI);
		txtBitteAlleFelder.setText(
				"Bitte alle Felder ausf\u00FCllen. (Es muss tats\u00E4chlich die Nummer der richtigen L\u00F6sung angegeben werden!). \r\nMit der Taste \"Hinzuf\u00FCgen\" wird die Kartei gepr\u00FCft und der Datenbank angeh\u00E4ngt. \r\nUm das Hinzuf\u00FCgen zu beenden bitte die Taste \"Exit\" dr\u00FCcken.");
		txtBitteAlleFelder.setBounds(0, 10, 625, 67);

		txtFrage = new Text(shlKarteiHinzufgen, SWT.BORDER);
		txtFrage.setEditable(false);
		txtFrage.setText("Frage:");
		txtFrage.setBounds(10, 89, 80, 19);

		txtAntwort = new Text(shlKarteiHinzufgen, SWT.BORDER | SWT.READ_ONLY);
		txtAntwort.setEditable(false);
		txtAntwort.setText("Antwort 1:");
		txtAntwort.setBounds(10, 114, 80, 19);

		txtAntwort_1 = new Text(shlKarteiHinzufgen, SWT.BORDER | SWT.READ_ONLY);
		txtAntwort_1.setEditable(false);
		txtAntwort_1.setText("Antwort 2:");
		txtAntwort_1.setBounds(10, 139, 80, 19);

		txtAntwort_2 = new Text(shlKarteiHinzufgen, SWT.BORDER | SWT.READ_ONLY);
		txtAntwort_2.setEditable(false);
		txtAntwort_2.setText("Antwort 3:");
		txtAntwort_2.setBounds(10, 164, 80, 19);

		txtAntwort_3 = new Text(shlKarteiHinzufgen, SWT.BORDER | SWT.READ_ONLY);
		txtAntwort_3.setEditable(false);
		txtAntwort_3.setText("Antwort 4:");
		txtAntwort_3.setBounds(10, 189, 80, 19);

		txtBuchstabeDerLsung = new Text(shlKarteiHinzufgen, SWT.BORDER | SWT.READ_ONLY);
		txtBuchstabeDerLsung.setEditable(false);
		txtBuchstabeDerLsung.setText("Zahl der L\u00F6sung:");
		txtBuchstabeDerLsung.setBounds(10, 214, 112, 19);

		Frage = new Text(shlKarteiHinzufgen, SWT.BORDER);
		Frage.setBounds(107, 89, 518, 19);

		Antwort_1 = new Text(shlKarteiHinzufgen, SWT.BORDER);
		Antwort_1.setBounds(107, 114, 518, 19);

		Antwort_2 = new Text(shlKarteiHinzufgen, SWT.BORDER);
		Antwort_2.setBounds(107, 139, 518, 19);

		Antwort_3 = new Text(shlKarteiHinzufgen, SWT.BORDER);
		Antwort_3.setBounds(107, 164, 518, 19);

		Antwort_4 = new Text(shlKarteiHinzufgen, SWT.BORDER);
		Antwort_4.setBounds(107, 189, 518, 19);

		Lösung = new Text(shlKarteiHinzufgen, SWT.BORDER);
		Lösung.setBounds(134, 214, 41, 19);

		ErrorMeldung = new Text(shlKarteiHinzufgen, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		ErrorMeldung.setEnabled(false);
		ErrorMeldung.setEditable(false);
		ErrorMeldung.setBounds(10, 241, 182, 41);

		Button btnNewButton = new Button(shlKarteiHinzufgen, SWT.NONE);
		btnNewButton.setBounds(430, 233, 182, 49);
		btnNewButton.setText("Exit");
		btnNewButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				shlKarteiHinzufgen.dispose();
			}
		});

		Button btnHinzufgen = new Button(shlKarteiHinzufgen, SWT.NONE);
		btnHinzufgen.setText("Hinzuf\u00FCgen");
		btnHinzufgen.setBounds(226, 233, 182, 49);
		btnHinzufgen.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				while (true) {
					ErrorMeldung.setText("");
					String a1 = Antwort_1.getText();
					String a2 = Antwort_2.getText();
					String a3 = Antwort_3.getText();
					String a4 = Antwort_4.getText();
					String f = Frage.getText();
					String lös = Lösung.getText();
					if (a1.equals("") || a2.equals("") || a3.equals("") || a4.equals("") || f.equals("")
							|| lös.equals("")) {
						ErrorMeldung.setText("Bitte alle Felder ausfüllen!");
						break;
					} else if (!lös.equals("1") && !lös.equals("2") && !lös.equals("3") && !lös.equals("4")) {
						ErrorMeldung.setText("Bitte eine Zahl zwischen 1\r\n und 4 für die Lösung angeben!");
						break;
					}	else if(a1.length() >= 45 || a2.length()>= 45 || a3.length() >= 45 || a4.length() >= 45 || lös.length() >= 45) {
						ErrorMeldung.setText("Bitte die Felder kürzen!");
						break;
					}
					int lösi = Integer.valueOf(lös);
					Antwort_1.setText("");
					Antwort_2.setText("");
					Antwort_3.setText("");
					Antwort_4.setText("");
					Lösung.setText("");
					Frage.setText("");
					ErrorMeldung.setText("");

					Karteikarte neueKartei = new Karteikarte(a1, a2, a3, a4, f, lösi);
					try {
						Main main = new Main(neueKartei);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		});

	}
}
