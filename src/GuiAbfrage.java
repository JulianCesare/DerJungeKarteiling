import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class GuiAbfrage {

	protected Shell shell;
	private Text feedback;
	Boolean ButtonGedrückt = false;
	Karteikarte aktKartei;
	Main main;
	private Text Frage;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	/**
	 * Open the window.
	 */

	public GuiAbfrage(Karteikarte aktKartei, Main main) {
		this.aktKartei = aktKartei;
		this.main = main;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		shell = new Shell();
		shell.setSize(450, 361);
		shell.setText("Abfrage");
		Button btnNewButton = new Button(shell, SWT.NONE);
		Button ro = new Button(shell, SWT.NONE);
		Button lo = new Button(shell, SWT.NONE);
		Button ru = new Button(shell, SWT.NONE);

		Frage = new Text(shell, SWT.BORDER | SWT.MULTI);
		Frage.setEditable(false);
		Frage.setBounds(23, 2, 393, 36);
		Frage.setText(aktKartei.getFrage());

		ru.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		Button lu = new Button(shell, SWT.NONE);
		lu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});

		lo.setText(aktKartei.getAntwort1());
		ro.setText(aktKartei.getAntwort2());
		lu.setText(aktKartei.getAntwort3());
		ru.setText(aktKartei.getAntwort4());

		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(325, 247, 102, 58);
		btnNewButton.setText("Next Please");

		lo.setBounds(26, 40, 175, 92);

		lo.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (aktKartei.getLösung() == 1) {
					feedback.setText("Richtige Antwort!");
					main.promoteKartei(aktKartei);

				} else {
					feedback.setText("Falsche Antwort! Richtig: " + aktKartei.getLösung());
					main.demoteKartei(aktKartei);
				}

				btnNewButton.setEnabled(true);
				ro.setEnabled(false);
				lo.setEnabled(false);
				lu.setEnabled(false);
				ru.setEnabled(false);
			}

		});

		ro.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (aktKartei.getLösung() == 2) {
					feedback.setText("Richtige Antwort!");
					main.promoteKartei(aktKartei);

				} else {
					feedback.setText("Falsche Antwort! Richtig: " + aktKartei.getLösung());
					main.demoteKartei(aktKartei);
				}

				btnNewButton.setEnabled(true);
				ro.setEnabled(false);
				lo.setEnabled(false);
				lu.setEnabled(false);
				ru.setEnabled(false);
			}

		});

		ro.setBounds(238, 40, 175, 92);

		ru.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (aktKartei.getLösung() == 3) {
					feedback.setText("Richtige Antwort!");
					main.promoteKartei(aktKartei);

				} else {
					feedback.setText("Falsche Antwort! Richtig: " + aktKartei.getLösung());
					main.demoteKartei(aktKartei);
				}
				btnNewButton.setEnabled(true);
				ro.setEnabled(false);
				lo.setEnabled(false);
				lu.setEnabled(false);
				ru.setEnabled(false);
			}

		});

		ru.setBounds(239, 149, 175, 92);

		lu.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (aktKartei.getLösung() == 3) {
					feedback.setText("Richtige Antwort!");
					main.promoteKartei(aktKartei);

				} else {
					feedback.setText("Falsche Antwort! Richtig: " + aktKartei.getLösung());
					main.demoteKartei(aktKartei);
				}

				btnNewButton.setEnabled(true);
				ro.setEnabled(false);
				lo.setEnabled(false);
				lu.setEnabled(false);
				ru.setEnabled(false);
			}
		});

		lu.setBounds(26, 148, 175, 92);

		feedback = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		feedback.setBounds(24, 247, 295, 58);

		btnNewButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				shell.dispose();
			}
		});

	}
}
