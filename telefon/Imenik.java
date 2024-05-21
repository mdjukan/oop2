import java.awt.*;
import java.util.ArrayList;

class Imenik extends ListaStavki {
	void dodajKontakt(Kontakt kontakt) {
		dodajStavku(kontakt);
	}

	String ime(Broj broj) throws GNePostoji {
		for (int i=0; i<stavke.size(); ++i) {
			Kontakt kontakt = (Kontakt)stavke.get(i);
			if (kontakt.broj.jednak(broj)) {
				return kontakt.ime;
			}
		}

		throw new GNePostoji("Ne postoji kontakt sa trazenim brojem!");
	}

	Broj broj(String ime) throws GNePostoji {
		for (int i=0; i<stavke.size(); ++i) {
			Kontakt kontakt = (Kontakt)stavke.get(i);
			if (kontakt.ime==ime) {
				return kontakt.broj;
			}
		}

		throw new GNePostoji("Ne postoji kontakt sa trazenim imenom!");
	}
}
