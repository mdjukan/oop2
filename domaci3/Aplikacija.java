import java.awt.*;
import java.awt.event.*;

class Aplikacija extends Frame {
	int sirina = 5;
	int visina = 5;
	Kanalizacija kanalizacija = new Kanalizacija(visina,sirina);
	CheckboxGroup grupa = new CheckboxGroup();

	Aplikacija() {
		super("Aplikacija");
		setVisible(true);
		setSize(500, 500);

		dodajElemente();
		dodajOsluskivace();

		doLayout();
		validate();
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		dodaj.addActionListener((e)->{
			Kvadrat novi = Kvadrat.odNaziva(kanalizacija, grupa.getSelectedCheckbox().getLabel());
			kanalizacija.zameniOznacenog(novi);
		});
	}

	Button dodaj = new Button("Dodaj");
	void dodajElemente() {
		add(kanalizacija, "Center");

		Panel panel = new Panel();
		panel.setLayout(new GridLayout(8,1));

		String[] opcije = {"Levo-Desno", "Gore-Dole", "Dole-Gore", "Levo-Dole", "Levo-Gore", "Gore-Desno", "Dole-Desno"};

		Checkbox[] dugmad = new Checkbox[opcije.length];
		for (int i=0; i<opcije.length; ++i) {
			dugmad[i] = new Checkbox(opcije[i], grupa, i==0);
		}

		for (int i=0; i<dugmad.length; ++i) {
			panel.add(dugmad[i]);
		}

		panel.add(dodaj);
		add(panel, "East");
	}

	public static void main(String[] args) {
		new Aplikacija();
	}
}
