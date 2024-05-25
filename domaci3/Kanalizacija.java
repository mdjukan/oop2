import java.awt.*;
import java.util.ArrayList;

class Kanalizacija extends Panel {
	Panel okvir = new Panel();
	Kvadrat oznaceni = null;

	void popuniOkvir() {
		okvir.removeAll();
		for (Kvadrat kvadrat : kvadrati) {
			okvir.add(kvadrat);
			kvadrat.repaint();
		}

		revalidate();
	}

	ArrayList<Kvadrat> kvadrati = new ArrayList<>();
	int visina, sirina;

	Kanalizacija(int visina, int sirina) {
		setLayout(new FlowLayout());
		okvir.setLayout(new GridLayout(visina, sirina));
		okvir.setSize(sirina*Kvadrat.h, visina*Kvadrat.h);

		this.visina = visina;
		this.sirina = sirina;
		for (int i=0; i<visina; ++i) {
			for (int j=0; j<sirina; ++j) {
				kvadrati.add(new Zid(this));
			}
		}

		add(okvir);
		popuniOkvir();
	}

	void promenaStatusa(Kvadrat kvadrat) throws GOznaka {
		if (!kvadrat.mozeSeOznaciti) {
			throw new GOznaka();
		}
		if (oznaceni!=null) {
			oznaceni.promeniStatus();
		}

		kvadrat.promeniStatus();
		oznaceni = kvadrat;
	}

	void zameniOznacenog(Kvadrat novi) {
		if (oznaceni==null) return;
		kvadrati.set(kvadrati.indexOf(oznaceni), novi);
		oznaceni = novi;
		System.out.println(novi==null);
		popuniOkvir();
	}
}
