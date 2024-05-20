import java.util.ArrayList;
import java.util.HashSet;
import java.awt.*;

class Mreza extends Panel {
	static int RAZMAK = 3;

	private ArrayList<Polje> polja = new ArrayList<>();
	private ArrayList<Polje> izabranaPolja = new ArrayList<>();
	private HashSet<Integer> indeksiIzabranih = new HashSet<>();
	private Igra igra;

	int sirina, visina;
	Mreza(int sirina, int visina, Igra igra) {
		this.sirina = sirina;
		this.visina = visina;
		this.igra = igra;

		for (int i=0; i<sirina*visina; ++i) {
			polja.add(new Polje(i, this));
		}

		setBackground(Color.BLACK);
		setLayout(new GridLayout(visina, sirina, RAZMAK, RAZMAK));
		for (int i=0; i<sirina * visina; ++i) {
			add(polja.get(i));
		}
	}

	static final int SIRINA = 5;
	static final int VISINA = 4;
	Mreza(Igra igra) {
		this(SIRINA, VISINA, igra);
	}

	void promenaStatusa(Polje polje) {
		if (polje.status()==Status.IZABRANO) {
			izabranaPolja.add(polje);
			indeksiIzabranih.add(polja.indexOf(polje));
		} else if (polje.status()==Status.SLOBODNO) {
			izabranaPolja.remove(polje);
			indeksiIzabranih.remove(polja.indexOf(polje));
		}

		igra.promenaStatusa();
	}

	ArrayList<Polje> polja() {
		return polja;
	}

	int brojIzabranih() {
		return indeksiIzabranih.size();
	}

	int brojPolja() {
		return visina * sirina;
	}

	boolean jeIzabran(int broj) {
		return indeksiIzabranih.contains(broj);
	}
}
