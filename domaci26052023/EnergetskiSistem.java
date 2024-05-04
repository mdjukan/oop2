import java.awt.*;
import java.awt.event.*;

class EnergetskiSistem extends Frame {
	Proizvodjac generisiProizvodjaca() {
		switch (proizvodjac.getSelectedItem()) {
			case "Hidroelektrana":
				return new Hidroelektrana(plac, baterija);
			case "Solarni panel":
				return new SolarniPanel(plac, baterija);
			case "Vetrenjaca":
				return new Vetrenjaca(plac, baterija);
		}

		return null;
	}

	Plac plac = new Plac(5, 5);
	Baterija baterija = new Baterija(100);

	void reset() {
		plac.reset();
		baterija.reset();
	}

 Button dodajProizvodjaca = new Button("Dodaj proizvodjaca");
	Choice proizvodjac = new Choice();
	{
		proizvodjac.addItem("Hidroelektrana");
		proizvodjac.addItem("Solarni panel");
		proizvodjac.addItem("Vetrenjaca");
	}

	TextField dt = new TextField();
	TextField energija = new TextField();
	Button dodajPotrosaca = new Button("Dodaj potrosaca");

	void dodajElemente() {
		Panel panel1 = new Panel();
		panel1.setLayout(new GridLayout(1,2));
		panel1.add(proizvodjac);
		panel1.add(dodajProizvodjaca);

		Panel panel2 = new Panel();
		panel2.setLayout(new GridLayout(1, 5));
		panel2.add(new Label("Vreme:"));
		panel2.add(dt);
		panel2.add(new Label("Energija:"));
		panel2.add(energija);
		panel2.add(dodajPotrosaca);

		Panel panel = new Panel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(panel1);
		panel.add(panel2);

		add(panel, "North");

		add(plac, "Center");
		add(baterija, "South");
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		dodajProizvodjaca.addActionListener((e)->{
			Proizvodjac proizvodjac = generisiProizvodjaca();

			if (proizvodjac instanceof Hidroelektrana && !(plac.izabrana instanceof VodenaPovrs)) {
				plac.setBackground(Color.RED);
				try {
					Thread.sleep(500);
				} catch (InterruptedException exp) {}
				plac.setBackground(Color.WHITE);
			} else {
				plac.zaustaviIzabranu();
				plac.parcele.set(plac.indeksIzabrane(), proizvodjac);
				plac.postaviIzabranu(proizvodjac);
				proizvodjac.pokreni();
				plac.azurirajPrikaz();
			}
		});

		dodajPotrosaca.addActionListener((e)->{
			try {
			int energijaVred = Integer.parseInt(energija.getText());
			int dtVred = Integer.parseInt(dt.getText());
			plac.zaustaviIzabranu();
			Potrosac potrosac = new Potrosac(plac, baterija, energijaVred, dtVred);
			potrosac.pokreni();
			plac.parcele.set(plac.indeksIzabrane(), potrosac);
			plac.postaviIzabranu(potrosac);
			plac.azurirajPrikaz();
			} catch (NumberFormatException exp) {}
		});
	}

	void dodajMeni() {
		MenuBar bar = new MenuBar();
		Menu meni = new Menu("Akcija");
		MenuItem item1 = new MenuItem("Zatvori", new MenuShortcut('Q'));
		meni.add(item1);
		MenuItem item2 = new MenuItem("Reset", new MenuShortcut('R'));
		meni.add(item2);
		bar.add(meni);
		setMenuBar(bar);
		item1.addActionListener((e)->{dispose();});
		item2.addActionListener((e)->{reset();});
	}

	EnergetskiSistem() {
		super("Energetski sistem");
		setSize(500, 500);
		setVisible(true);
		//setResizable(false);

		dodajElemente();
		dodajOsluskivace();
		dodajMeni();
	}

	public static void main(String[] args) {
		new EnergetskiSistem();
	}
}
