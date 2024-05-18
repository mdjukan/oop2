import java.util.ArrayList;
import java.awt.*;

class SkupFigura {
	ArrayList<Figura> figure = new ArrayList<>();

	private Figura tekuca;

	Figura tekuca() {
		return tekuca;
	}

	boolean sadrzi(Figura f) {
		for (Figura figura : figure) {
			if (f==figura) return true;
		}

		return false;
	}

	boolean preklapa(Figura f) {
		for (Figura figura : figure) {
			if (figura.preklapa(f)) {
				return true;
			}
		}

		return false;
	}

	void dodajFiguru(Figura f) throws Greska {
		if (sadrzi(f)) throw new Greska("Figura je vec u skupu!");
		if (preklapa(f)) throw new Greska("Preklapa se sa figurom skupa!");
		figure.add(f);
	}

	void prvaTekuca() {
		if (brojFigura()==0) {
			tekuca = null;
		} else {
			tekuca = figure.get(0);
		}
	}

	boolean postojiSledeca() {
		return figure.indexOf(tekuca)<figure.size()-1;
	}

	void sledecaTekuca() throws Greska {
		if (!postojiSledeca()) throw new Greska("Ne postoji sledeca!");
		tekuca = figure.get(figure.indexOf(tekuca)+1);
	}

	void isprazniSlup() {
		figure.clear();
	}

	void izbaci(Figura f) {
		if (sadrzi(f) && brojFigura()==1) {
			figure.remove(f);
			tekuca = null;
		}

		if (f==tekuca) {
			try {
				sledecaTekuca();
			} catch (Greska g) {
				prvaTekuca(); //zatvaranje moguceg problema L -> F
			}
		}

		if (sadrzi(f)) {
			figure.remove(f);
		}
	}

	int brojFigura() {
		return figure.size();
	}

	Figura najbliza(Figura f) {

		Figura minFigura = null;

		for (Figura figura : figure) {
			if (figura!=f && minFigura==null) {
				minFigura = figura;
			} else if (minFigura!=null && figura!=f && minFigura.rastojanje(f) > figura.rastojanje(f)) {
				minFigura = figura;
			}
		}

		return minFigura;
	}

	void naslikaj(Graphics g) {
		for (Figura f : figure) {
			f.naslikaj(g);
		}
	}
}
