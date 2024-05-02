import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

class Prevoznik extends Frame {
	static final Grad[] gradovi = {
		new Grad("Beograd", 0, 0),
		new Grad("Novi Sad", -1, 3),
		new Grad("Nis", 1, -4),
		new Grad("Loznica", -2, -1) };

	static Grad gradOdNaziva(String naziv) {
		for (Grad grad : gradovi) {
			if (grad.naziv()==naziv) {
				return grad;
			}
		}

		return null;
	}

	 ArrayList<Autobus> autobusi = new ArrayList<>();
		void dodajAutobus(Autobus autobus) {
			autobusi.add(autobus);
		}

		{
			Putanja p1 = new Putanja("Loznica-Beograd");
			p1.dodajPut(new Put(100, 1000, gradovi[3], gradovi[2]));
			p1.dodajPut(new Put(111, 1233, gradovi[2], gradovi[0]));
			double[] vc1 = {5000, 5000, 5000};
			dodajAutobus(new Autobus(p1, 3, vc1));
		}

		Choice pocetak = new Choice();
		Choice kraj = new Choice();
		Button dodaj = new Button("Dodaj putnika");
		{
			for (Grad grad : gradovi) {
				pocetak.addItem(grad.naziv());
				kraj.addItem(grad.naziv());
			}
		}

	double zarada = 0;
	Label total = new Label(""+zarada, Label.CENTER);

	Prevoznik() {
		super("Prevoznik");
		setSize(500,500);
		setVisible(true);

		dodajElemente();
		dodajOsluskivace();
	}

	void dodajElemente() {
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(total);
		add(panel, "North");

		panel = new Panel();
		panel.setLayout(new GridLayout(1, autobusi.size()));
		for (Autobus autobus : autobusi) {
			panel.add(autobus);
		}
		add(panel, "Center");

		panel = new Panel();
		panel.setLayout(new GridLayout(1, 3));
		panel.add(pocetak);
		panel.add(kraj);
		panel.add(dodaj);
		add(panel, "South");
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		dodaj.addActionListener((e)->{
			Grad pocetni = gradOdNaziva(pocetak.getSelectedItem());
			Grad krajnji = gradOdNaziva(kraj.getSelectedItem());
			Putnik putnik = new Putnik(pocetni, krajnji);

			Autobus min = null;
			double cena = Double.MAX_VALUE;
			for (Autobus autobus : autobusi) {
				double trenutnaCena = autobus.putanja.cenaOdDo(pocetni, krajnji);
				if (cena > trenutnaCena) {
					min = autobus;
					cena = trenutnaCena;
				}
			}
			if (cena!=Double.MAX_VALUE) {
				min.primiPutnika(putnik);
			}
		});
	}

	public static void main(String[] args) {
		new Prevoznik();
	}
}
