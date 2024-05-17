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

	void dodajFiguru(Figura f) throws Greska {
		if (sadrzi(f)) throw new Greska("Figura je vec u skupu!");

		boolean preklapa = false;

		for (Figura figura : figure) {
			if (figura.preklapa(f)) {
				preklapa = true;
			}
		}

		if (preklapa) throw new Greska("Preklapa se sa figurom skupa!");

		figure.add(f);
	}

	/*
	void prvaTekuca() throws Greska {
		if (figure.size()==0) throw new Greska("Skup je prazan!"); //?
		tekuca = figure.get(0);
	}
	*/

	void prvaTekuca() {
		tekuca = figure.get(0);
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
		//sta ako sam izbacio tekucu?
		//ostaje ista vred. a nije vise nizu?
		if (sadrzi(f)) {
			figure.remove(f);
		}
	}

	int brojFigura() {
		return figure.size();
	}

	//najbliza figura skupu figuri f
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
