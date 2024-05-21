import java.awt.*;

class Telefon extends Panel {
	Broj broj;
	Color boja;

	Imenik imenik = new Imenik();
	Tastatura tastatura = new Tastatura();
	Button dodajKontakt = new Button("Dodaj Kontakt");
	Button iskljuciTelefon = new Button("Iskljuci Telefon");
	Label ispisBroja = new Label("", Label.CENTER);

	Telefon(Broj broj, Color boja) {
		this.broj = broj;
		this.boja = boja;
		ispisBroja.setText(broj.toString());

		setBackground(boja);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		c.gridheight = 5;
		tastatura.setPreferredSize(new Dimension(100, 150));
		add(tastatura, c);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 10;
		c.gridheight = 4;
		imenik.setPreferredSize(new Dimension(100, 150));
		add(imenik, c);

		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		dodajKontakt.setPreferredSize(new Dimension(50,10));
		add(dodajKontakt, c);
		dodajKontakt.setBackground(Color.LIGHT_GRAY);

		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		iskljuciTelefon.setPreferredSize(new Dimension(50,10));
		add(iskljuciTelefon, c);
		iskljuciTelefon.setBackground(Color.LIGHT_GRAY);

		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 10;
		c.gridheight = 1;
		ispisBroja.setPreferredSize(new Dimension(50,10));
		add(ispisBroja, c);

		dodajKontakt.addActionListener((e)->{
			try {
				Kontakt kontakt = tastatura.formirajKontakt();
				imenik.dodajKontakt(kontakt);
			} catch (Exception excp) {}
		});
	}
}
