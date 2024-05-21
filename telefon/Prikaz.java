import java.awt.*;
import java.awt.event.*;

class Prikaz extends Frame {
	Telefon telefon1 = new Telefon(new Broj("+391 60 123"), Color.YELLOW);
	Telefon telefon2 = new Telefon(new Broj("+391 60 567"), Color.GREEN);

	Prikaz() {
		super("Prikaz");
		setVisible(true);
		setSize(800,500);

		dodajElemente();
		dodajOsluskivace();
	}

	void dodajElemente() {
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(telefon1);
		panel.add(telefon2);
		add(panel);
	}

	void dodajOsluskivace() {
	}

	public static void main(String args[]) {
		new Prikaz();
	}
}
