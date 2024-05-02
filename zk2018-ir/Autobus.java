import java.awt.*;
import java.util.ArrayList;

class Autobus extends TextArea implements Runnable {
	int maxPutnika;
	ArrayList<Putnik> putnici = new ArrayList<>();
	int brojPutnika = 0;
	Putanja putanja;
	double[] vremeCekanja;

	Thread nit = new Thread(this);
	Autobus(Putanja putanja, int maxPutnika, double[] vremeCekanja) {
		setEditable(false);
		this.putanja = putanja;
		this.maxPutnika = maxPutnika;
		this.vremeCekanja = vremeCekanja;
		stanje = Stanje.NaStanici;
		trenutniGrad = putanja.dohvatiGrad(0);
		nit.start();
	}
	

	volatile Stanje stanje;
	volatile Grad trenutniGrad;

	synchronized boolean primiPutnika(Putnik putnik) {
		System.out.println("primiPutnika call");
		if (stanje==Stanje.NaStanici &&
				putnik.pocetni().equals(trenutniGrad) &&
				putnici.size()<maxPutnika) {
			putnici.add(putnik);
			notify();
			return true;
		} else {
			return false;
		}
	}

	void ispustiPutnike() {
		ArrayList<Putnik> izlaz = new ArrayList<>();
		for (Putnik putnik : putnici) {
			if (putnik.krajnji().equals(trenutniGrad)) {
				izlaz.add(putnik);
			}
		}

		for (Putnik putnik : izlaz) {
			putnici.remove(putnik);
		}
	}

	void postaviBoju() {
		switch (stanje) {
			case Vozi:
				setBackground(Color.GREEN);
				break;
			case NaStanici:
				setBackground(Color.ORANGE);
				break;
			case Kraj:
				setBackground(Color.RED);
				break;
		}
	}

	void azurirajPrikaz(Stanje stanje) {
			this.stanje = stanje;
			setText(this.toString());
			postaviBoju();
	}

	@Override
	public void run() {
		setText(this.toString());
		postaviBoju();

		synchronized (this) {
			while (putnici.size()<maxPutnika) {
				setText(this.toString());
				try {
					wait();
				} catch (InterruptedException e) {}
			}
		}

		for (int i=1; i<putanja.brojGradova()-1; ++i) {
			azurirajPrikaz(Stanje.Vozi);
			try {
				Thread.sleep((int)putanja.putevi.get(i-1).trajanje());
			} catch (InterruptedException e) {}

			trenutniGrad = putanja.dohvatiGrad(i);
k		ispustiPutnike();
			azurirajPrikaz(Stanje.NaStanici);

			try {
				Thread.sleep((int)vremeCekanja[i]);
			} catch (InterruptedException e) {}
		}

		azurirajPrikaz(Stanje.Vozi);
		try {
			Thread.sleep((int)putanja.putevi.get(putanja.putevi.size()-1).trajanje());
		} catch (InterruptedException e) {}

		trenutniGrad = putanja.dohvatiGrad(putanja.brojGradova()-1);
		ispustiPutnike();
		azurirajPrikaz(Stanje.Kraj);
	}

	@Override
	public String toString() {
		String out = "";
		out += "Putanja:" + putanja;
		out += "Broj putnika:" + putnici.size() + "/" + maxPutnika + "\n";
		switch (stanje) {
			case Vozi:
				out += "Vozi:"+putanja.odPocetnogGrada(trenutniGrad);
			 break;
			case NaStanici:
				out += "Stoji:"+trenutniGrad.naziv();
				break;
			case Kraj:
				out += "Stigao:"+trenutniGrad.naziv();
				break;
		}

		return out;
	}
}
