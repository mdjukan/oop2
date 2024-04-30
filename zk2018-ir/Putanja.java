import java.util.ArrayList;

class Putanja {
	private String naziv;
	ArrayList<Put> putevi = new ArrayList<>();

	String naziv() {
		return naziv;
	}

	Putanja(String naziv) {
		this.naziv = naziv;
	}

	boolean dodajPut(Put put) {
		if (putevi.size()==0) {
			putevi.add(put);
			return true;
		} else {
			Grad poslednji = putevi.get(putevi.size()-1).krajnji;
			Grad pocetni = put.pocetni;
			if (poslednji.equals(pocetni)) {
				putevi.add(put);
				return true;
			} else {
				return false;
			}
		}
	}

	double duzina() {
		double d = 0;
		for (Put put : putevi) {
			d += put.duzina();
		}

		return d;
	}

	int brojGradova() {
		return putevi.size()+1;
	}

	int indeksGrada(Grad grad) {
		for (int i=0; i<putevi.size(); ++i) {
			if (putevi.get(i).pocetni.equals(grad)) {
				return i;
			}
		}

		if (putevi.get(putevi.size()-1).krajnji.equals(grad)) {
			return putevi.size();
		}

		return -1;
	}

	Grad dohvatiGrad(int idx) {
		if (idx<putevi.size()) {
			return putevi.get(idx).pocetni;
		} else {
			return putevi.get(putevi.size()-1).krajnji;
		}
	}

	Put odPocetnogGrada(Grad grad) {
		for (Put put : putevi) {
			if (put.pocetni.equals(grad)) {
				return put;
			}
		}
		return null;
	}

		double cenaOdDo(Grad pocetni, Grad krajnji) {
			int idxPocetni = indeksGrada(pocetni);
			int idxKrajnji = indeksGrada(krajnji);
			if (idxPocetni==-1 || idxKrajnji==-1 || idxPocetni > idxKrajnji) {
				return Double.MAX_VALUE;
			}

			double cena = 0;
			for (int i=idxPocetni; i<idxKrajnji; ++i) {
				cena += putevi.get(i).cena();
			}

			return cena;
		}


	@Override
	public String toString() {
		String out = "";
		out += "Putanja: " + naziv + "\n";
		out += "Duzina:" + duzina() + "\n";
		out += "Stanice:\n";
		for (int i=0; i<=putevi.size(); ++i) {
			out += dohvatiGrad(i).naziv() + "\n";
		}

		return out;
	}
}
