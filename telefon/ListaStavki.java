import java.awt.*;
import java.util.ArrayList;

class ListaStavki extends Panel {
	ArrayList<Stavka> stavke = new ArrayList<>();

	void dodajStavku(Stavka stavka) {
		stavke.add(stavka);
		removeAll();
		setLayout(new GridLayout(stavke.size(),1));
		for (int i=0; i<stavke.size(); ++i) {
			add(stavke.get(i));
		}

		validate();
		repaint();
	}
}
