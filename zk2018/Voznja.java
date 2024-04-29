import java.awt.*;
import java.awt.event.*;

class Voznja extends Thread {
	private String naziv;
	private int cena;
	private int minVisina;
	private int minGodina;
	private int brojRedova;
	private int brojKolona;
	private int trajanje;
	private Posetilac[] mesta;
	private int prvoSlobodno;
	private boolean popunjena = false;
	private Panel prikaz = new Panel();

	Panel dohvatiPrikaz() {
		return prikaz;
	}


	void inicijalizujPanel() {
		prikaz.setLayout(new BorderLayout());
		prikaz.add(new Label(naziv), "North");

		Panel matrica = new Panel();
		matrica.setLayout(new GridLayout(brojRedova, brojKolona, 3, 3));
		for (int i=0; i<labele.length; ++i) {
			matrica.add(labele[i]);
		}

		prikaz.add(matrica, "Center");
	}

	private Label[] labele;

	Voznja(String naziv, int cena, int minVisina, int minGodina, int brojRedova, int brojKolona, int trajanje) {
		this.naziv = naziv;
		this.cena = cena;
		this.minVisina = minVisina;
		this.minGodina = minGodina;
		this.brojRedova = brojRedova;
		this.brojKolona = brojKolona;
		this.trajanje = trajanje;
		mesta = new Posetilac[brojRedova * brojKolona];
		prvoSlobodno = 0;

		labele = new Label[brojRedova * brojKolona];
		for (int i=0; i<labele.length; ++i) {
			labele[i] = new Label("_", Label.CENTER);
			labele[i].setBackground(Color.RED);
		}

		inicijalizujPanel();
	}

	boolean dodajPosetioca(Posetilac p) {
		if (prvoSlobodno<brojRedova*brojKolona) {
			labele[prvoSlobodno].setText(p.dohvatiId());
			labele[prvoSlobodno].setBackground(Color.GREEN);
			mesta[prvoSlobodno++] = p;
			popunjena = prvoSlobodno==brojRedova*brojKolona;
			return true;
		}

		return false;
	}

	volatile boolean utoku = false;
	@Override
	public void run() {
		try{ 
			while (!Thread.interrupted()) {
				if (popunjena) {
					utoku = true;
					Thread.sleep(trajanje);
					utoku = false;

					synchronized (this) {
						isprazniVoznju();
						notify();
					}
				}
			}
		} catch (InterruptedException e) {}
	}

	void isprazniVoznju() {
		prvoSlobodno = 0;
		popunjena = false;
		for (int i=0; i<labele.length; ++i) {
			labele[i].setText("_");
			labele[i].setBackground(Color.RED);
		}
	}

	boolean utoku() {
		return utoku;
	}

	String dohvatiNaziv() {
		return naziv;
	}

	int dohvatiCenu() {
		return cena;
	}

	int dohvatiMinVisinu() {
		return minVisina;
	}

	int dohvatiMinGodina() {
		return minGodina;
	}

	int dohvatiBrojRedova() {
		return brojRedova;
	}

	int dohvatiBrojKolona() {
		return brojKolona;
	}

	int dohvatiTrajanje() {
		return trajanje;
	}

	void trajnoZaustavi() {
		this.interrupt();
	}

	boolean mozeSeVoziti(Posetilac posetilac) {
		return posetilac.dohvatiGodine()>=minGodina && posetilac.dohvatiVisinu()>=minVisina;
	}
}
