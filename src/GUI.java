import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;;

public class GUI {

	protected Shell shlDerJungeKarteiling;
	private Text Anfangstext;
	Main main;
	
	public GUI(Main main) {
		this.main = main;
	}


	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlDerJungeKarteiling.open();
		shlDerJungeKarteiling.layout();
		while (!shlDerJungeKarteiling.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDerJungeKarteiling = new Shell();
		shlDerJungeKarteiling.setSize(450, 300);
		shlDerJungeKarteiling.setText("Der Junge Karteiling");

		Button KarteAdden = new Button(shlDerJungeKarteiling, SWT.NONE);
		KarteAdden.setSelection(true);
		KarteAdden.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		KarteAdden.setBounds(23, 153, 148, 38);
		KarteAdden.setText("Karteikarte hinzufügen");
		KarteAdden.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				shlDerJungeKarteiling.setVisible(false);
				GuiAdden add = new GuiAdden();
				add.open();
				shlDerJungeKarteiling.setVisible(true);
				shlDerJungeKarteiling.forceActive();

			}
		});

		Anfangstext = new Text(shlDerJungeKarteiling, SWT.BORDER | SWT.MULTI);
		Anfangstext.setText(
				"Hallo in meinem Karteikartenprogramm! Du hast die Möglichkeit, deiner\r\nKarteikartensammlung weitere Karteikarten hinzuzufügen oder die Abfrage\r\nzu beginnen. Später wird auch noch die Möglichkeit kommen, verschiedene\r\nKarteisammlungen anzulegen.");
		Anfangstext.setBounds(0, 0, 437, 100);

		Button AbfrageStarten = new Button(shlDerJungeKarteiling, SWT.NONE);
		AbfrageStarten.setText("Abfrage starten");
		AbfrageStarten.setBounds(252, 153, 148, 38);
		AbfrageStarten.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				 shlDerJungeKarteiling.setVisible(false);
				 main.abfrage();
				 shlDerJungeKarteiling.setVisible(true);
				 shlDerJungeKarteiling.forceActive();
			}
		});

	}
}
