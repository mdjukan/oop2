import java.awt.*;
import java.awt.event.*;

class Aplikacija extends Frame {
	String[] opcije = {"Levo-Desno", "Gore-Dole", "Dole-Gore", "Levo-Dole", "Levo-Gore", "Gore-Desno", "Dole-Desno"};
	int sirina = 5;
	int visina = 5;

	Kanalizacija kanalizacija = new Kanalizacija(visina,sirina);
	Button dodaj = new Button("Dodaj");

	CheckboxGroup grupa = new CheckboxGroup();
	Checkbox[] dugmad = new Checkbox[opcije.length];
	{
		for (int i=0; i<opcije.length; ++i) {
			dugmad[i] = new Checkbox(opcije[i], grupa, i==0);
		}
	}

	Aplikacija() {
		super("Cevi");
		setVisible(true);
		setSize(490, 430);

		dodajElemente();
		dodajOsluskivace();

		doLayout();
		validate();
	}

	void dodajElemente() {
		add(kanalizacija, "Center");

		Panel panel = new Panel();
		panel.setLayout(new GridLayout(8,1));
		for (int i=0; i<dugmad.length; ++i) {
			panel.add(dugmad[i]);
		}

		panel.add(dodaj);
		add(panel, "East");
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

	public static void main(String[] args) {
		new Aplikacija();
	}
}
