import java.awt.*;
import java.awt.event.*;

class Tetris extends Frame {
	Tabla tabla = new Tabla(10, 30);
	void dodajElemente() {
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(tabla);
		add(panel, "Center");
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	Tetris() {
		super("Tetris");
		setSize(500, 500);
		setVisible(true);
		dodajElemente();
		dodajOsluskivace();
	}

	public static void main(String[] args) {
		new Tetris();
	}
}
