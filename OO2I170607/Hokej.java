import java.awt.*;
import java.awt.event.*;

class Hokej extends Frame {
	Button kreni = new Button("Kreni");
	Button stani = new Button("Stani");
	Button reset = new Button("Reset");

	Scena scena = new Scena();

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		kreni.addActionListener((e)->{
			if (!scena.pokrenuta) {
				scena.pokreni();
			} else {
				scena.nastavi();
			}
		});

		stani.addActionListener((e)->{
			scena.pauziraj();
		});

		reset.addActionListener((e)->{
			scena.reset();
		});
	}

	void dodajElemente() {
		Panel panel = new Panel();
		panel.add(scena.poeni);
		add(panel, "North");

		panel = new Panel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(scena);
		scena.setSize(Scena.w, Scena.h);
		add(panel, "Center");

		panel = new Panel();
		panel.setLayout(new GridLayout(1, 3));
		panel.add(kreni);
		panel.add(stani);
		panel.add(reset);
		add(panel, "South");
	}

	Hokej() {
		super("Hokej");
		setSize(500, 500);
		setVisible(true);

		dodajElemente();
		dodajOsluskivace();
	}

	public static void main(String[] args) {
		new Hokej();
	}
}
