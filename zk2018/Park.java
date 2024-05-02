import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
class Park extends Frame {
	private TextField ime = new TextField();
	private TextField godine = new TextField();
	private TextField visina = new TextField();
	private Checkbox posebna = new Checkbox("Posebna");
	private Button dodaj = new Button("Dodaj");
	private Choice voznjaDropdown = new Choice();

	ArrayList<Voznja> voznje = new ArrayList<>();
	ArrayList<Radnik> radnici = new ArrayList<>();
	{
		voznje.add(new Voznja("Speedy", 100, 150, 10, 2, 2, 2000));
		voznje.add(new Voznja("Kamikaza", 200, 160, 12, 3, 2, 3000));
		radnici.add(new Radnik("Pera", voznje.get(0)));
		radnici.add(new Radnik("Mika", voznje.get(1)));

		voznje.get(0).start();
		voznje.get(1).start();
		radnici.get(0).start();
		radnici.get(1).start();
	}

	void dodajElemente() {
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(1, voznje.size()));
		System.out.println(voznje.size());
		for (Voznja voznja : voznje) {
			panel.add(voznja.dohvatiPrikaz());
		}
		add(panel, "Center");

		panel = new Panel();
		panel.setLayout(new GridLayout(1,9));
		panel.add(new Label("Ime"));
		panel.add(ime);
		panel.add(new Label("Godine:"));
		panel.add(godine);
		panel.add(new Label("Visina:"));
		panel.add(visina);
		panel.add(voznjaDropdown);
		panel.add(posebna);
		panel.add(dodaj);

		for (Voznja voznja : voznje) {
			voznjaDropdown.addItem(voznja.dohvatiNaziv());
		}

		add(panel, "South");
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		dodaj.addActionListener((e) -> {
			Radnik radnik = radnici.get(0);
			Voznja voznja = voznje.get(0);
			String selektovana = voznjaDropdown.getSelectedItem();
			for (int i=0; i<voznje.size(); ++i) {
				if (selektovana==voznje.get(i).dohvatiNaziv()) {
					voznja = voznje.get(i);
					radnik = radnici.get(i);
				}
			}

			try {
				Posetilac posetilac = new Posetilac(ime.getText(), Integer.parseInt(godine.getText()), Integer.parseInt(visina.getText()));
				Karta karta = new Karta(posetilac, voznja, posebna.getState());
				radnik.dodajKartu(karta);
   } catch (NumberFormatException exp) {
			}
		});
	}

	Park() {
		super("Park");
		setSize(600, 400);
		setVisible(true);
		dodajElemente();
		dodajOsluskivace();
	}

	static public void main(String[] args) {
		new Park();
	}
}
