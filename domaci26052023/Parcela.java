import java.awt.*;
import java.awt.event.*;

class Parcela extends Label {
	char oznaka;
	Color boja;
	Plac plac;

	void umanjiFont() {
		this.setFont(new Font("Serif", Font.BOLD, 14));
	}

	void uvecajFont() {
		this.setFont(new Font("Serif", Font.BOLD, 20));
	}

	Parcela(char oznaka, Color boja, Plac plac) {
		super(""+oznaka, Label.CENTER);
		this.setFont(new Font("Serif", Font.BOLD, 14));
		this.setBackground(boja);
		this.oznaka = oznaka;
		this.boja = boja;
		this.plac = plac;

		Parcela parcela = this;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				plac.postaviIzabranu(parcela); 
			}
		});
	}
}
