import java.awt.*;
import java.awt.event.*;

abstract class Kvadrat extends Canvas {
	static int h = 75;

	Color pozadina;
	boolean oznacen = false;
	boolean mozeSeOznaciti;
	Kanalizacija kanalizacija;

	Kvadrat(Kanalizacija kanalizacija, Color pozadina, boolean mozeSeOznaciti) {
		this.kanalizacija = kanalizacija;
		this.pozadina = pozadina;
		this.mozeSeOznaciti = mozeSeOznaciti;
		setSize(h,h);
		setBackground(pozadina);

		Kvadrat kvadrat = this;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton()==MouseEvent.BUTTON1) {
					try {
						kanalizacija.promenaStatusa(kvadrat);
					} catch (GOznaka g) {}
				}
			}
		});
	}

	void promeniStatus() {
		oznacen = !oznacen;
		repaint();
	}

	abstract void naslikajKomponentu(Graphics g);

	@Override
	public void paint(Graphics g) {
		naslikajKomponentu(g);

		if (oznacen) {
			g.setColor(Color.RED);
			g.drawRect(0,0, h-1, h-1);
		}
	}

	static Kvadrat odNaziva(Kanalizacija kanalizacija, String naziv) {
		TipCevi tip = TipCevi.odNaziva(naziv);
		if (tip==TipCevi.LEVO_DESNO ||
				  tip==TipCevi.DESNO_LEVO || 
						tip==TipCevi.GORE_DOLE |
						tip==TipCevi.DOLE_GORE) {
			return new Cev(kanalizacija, tip);
						}

		return new UgaonaCev(kanalizacija, tip);
	}
}
