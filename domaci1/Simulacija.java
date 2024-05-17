import java.awt.*;
import java.awt.event.*;

class Simulacija extends Frame {
	Scena scena = new Scena(this);

	Simulacija() {
		super("Simulacija");
		setVisible(true);
		setSize(500, 500);

		dodajElemente();
		dodajOsluskivace();
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	void dodajElemente() {
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(new Scena(this));
		add(panel, "Center");
	}

	public static void main(String[] args) {
		new Simulacija();
	}
}
