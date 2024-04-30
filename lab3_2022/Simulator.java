import java.awt.*;
import java.awt.event.*;

class Simulator extends Frame {
	Button pokreni = new Button("Pokreni!");
	Button zaustavi = new Button("Zaustavi!");
	Button nastavi = new Button("Nastavi!");
	Label poeni = new Label("Poeni: 0");

	Svemir svemir = new Svemir(poeni);
	Generator generator = new Generator(svemir);

	void dodajElemente() {
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(svemir);
		add(panel, "Center");

		Panel panel1 = new Panel();
		panel1.setLayout(new GridLayout(1,3));
		panel1.add(pokreni);
		panel1.add(zaustavi);
		panel1.add(nastavi);

		panel = new Panel();
		panel.setLayout(new GridLayout(2,1));
		panel.add(poeni);
		panel.add(panel1);

		add(panel, "South");

		zaustavi.setEnabled(false);
		nastavi.setEnabled(false);
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		pokreni.addActionListener((e)->{
			svemir.pokreni();
			generator.pokreni();

			pokreni.setEnabled(false);
			zaustavi.setEnabled(true);
		});

		zaustavi.addActionListener((e)->{
			svemir.pauziraj();
			generator.pauziraj();

			zaustavi.setEnabled(false);
			nastavi.setEnabled(true);
		});

		nastavi.addActionListener((e)->{
			svemir.nastavi();
			generator.nastavi();

			nastavi.setEnabled(false);
			zaustavi.setEnabled(true);
		});
	}

	Simulator() {
		super("Simlator");
		setSize(200, 400);
		setVisible(true);

		dodajElemente();
		dodajOsluskivace();
	}

	public static void main(String[] args) {
		new Simulator();
	}
}
