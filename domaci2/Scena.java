import java.util.ArrayList;
import java.awt.*;

class Scena extends Canvas {
	static final int dt = 20;
	private ArrayList<Figura> figure = new ArrayList<>();
	private Igrac igrac;

	Igrac igrac() {
		return igrac;
	}

	ArrayList<Figura> figure() { //? --> 
		return figure;
	}

	synchronized void dodajFiguru(Figura figura) {
		figure.add(figura);
	}

	synchronized Figura fiuraPoIndeksu(int idx) {
		return figure.get(idx);
	}

	synchronized void ukloniFiguru(Figura figura) {
		figure.remove(figura);
	}

	TipSudara sudarZid(Loptica loptica) {
		if (loptica.x()-loptica.r()/2<0 || loptica.x()+loptica.r()/2>getWidth()) {
			return TipSudara.VERTIKALNI;
		}

		if (loptica.y()-loptica.r()/2<0) {
			return TipSudara.HORIZONTALNI;
		}

		return TipSudara.NEMA_SUDARA;
	}

	boolean vanScene(Figura figura) {
		return figura.y()>getHeight();
	}

	synchronized void iscratajFigure(Graphics g) {
		for (Figura figura : figure) {
			figura.iscrtaj(g);
		}
	}

	@Override
	public void paint(Graphics g) {
		iscratajFigure(g);
	}

	synchronized void pokreni() {
		for (Figura figura : figure) {
			if (figura instanceof AktivnaFigura) {
				((AktivnaFigura)figura).pokreni();
			}
		}
	}

	synchronized void zaustavi() {
		for (Figura figura : figure) {
			if (figura instanceof AktivnaFigura) {
				((AktivnaFigura)figura).zaustavi();
			}
		}
	}

	void inicijalizuj() {
		int SIRINA = getWidth();
		int VISINA = getHeight();
		igrac = new Igrac(this, SIRINA/2, VISINA-20, 50, 10);

		int CIGLA_PO_SIRINI = 5;
		int CIGLA_PO_VISINI = 3;
		int CIGLA_RAZMAK = 3;
		int CIGLA_SIRINA = (SIRINA-(CIGLA_PO_SIRINI+1)*CIGLA_RAZMAK)/CIGLA_PO_SIRINI;
		int CIGLA_VISINA = CIGLA_SIRINA/5;

		for (int i=0; i<CIGLA_PO_VISINI; ++i) {
			int y = CIGLA_RAZMAK + CIGLA_VISINA/2 + (CIGLA_VISINA+CIGLA_RAZMAK)*i;
			for (int j=0; j<CIGLA_PO_SIRINI; ++j) {
				int x = CIGLA_RAZMAK + CIGLA_SIRINA/2 + (CIGLA_SIRINA+CIGLA_RAZMAK)*j;
				new Cigla(this, x, y, 10, CIGLA_SIRINA, CIGLA_VISINA);
			}
		}
	}
}
