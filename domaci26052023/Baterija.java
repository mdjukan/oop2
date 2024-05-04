import java.awt.*;

class Baterija extends Label {
	int energija;
	int kapacitet;

	Baterija(int kapacitet) {
		super("", Label.CENTER);
		this.kapacitet = kapacitet;
		energija = kapacitet;
		this.setText(this.toString());
		this.setFont(new Font("Monospaced", Font.BOLD, 14));
		this.setBackground(Color.GREEN);
	}

	void reset() {
		energija = kapacitet;
		azurirajPrikaz();
	}

	void azurirajPrikaz() {
		this.setText(this.toString());
		if (jePuna()) {
			this.setBackground(Color.GREEN);
		} else {
			this.setBackground(Color.YELLOW);
		}
	}

	synchronized boolean uzmiEnergiju(int e) {
		if (energija>=e) {
			energija -= e;
			azurirajPrikaz();
			return true;
		}

		return false;
	}

	synchronized void dodajEnergiju(int e) {
		energija += e;
		if (energija>kapacitet) {
			energija = kapacitet;
		}

		azurirajPrikaz();
	}

	void isprazniBateriju() {
		energija = 0;
	}

	boolean jePuna() {
		return energija==kapacitet;
	}

	int procenat() {
		return (int)((double)energija * 100.0 / (double)kapacitet);
	}

	@Override
	public String toString() {
		return energija+"/"+kapacitet+"-"+procenat()+"%";
	}
}
