import java.awt.*;
import java.awt.event.*;

class Tastatura extends Panel {
	void postaviTekstDugmadi() {
		if (rezim == Rezim.SLOVA) { //0 9 11
			dugmad[0].setLabel(""); //.get(0) --- > index out out of bounds
			dugmad[1].setLabel("ABC"); //[1,8]
			dugmad[2].setLabel("DEF");
			dugmad[3].setLabel("GHI");
			dugmad[4].setLabel("JKL");
			dugmad[5].setLabel("MNO");
			dugmad[6].setLabel("PQRS");
			dugmad[7].setLabel("TUV");
			dugmad[8].setLabel("WXYZ");
			dugmad[9].setLabel("");
			dugmad[10].setLabel("_"); // ---> space a ne _
			dugmad[11].setLabel("");
		} else if (rezim == Rezim.BROJEVI) {
			dugmad[0].setLabel("1");
			dugmad[1].setLabel("2");
			dugmad[2].setLabel("3");
			dugmad[3].setLabel("4");
			dugmad[4].setLabel("5");
			dugmad[5].setLabel("6");
			dugmad[6].setLabel("7");
			dugmad[7].setLabel("8");
			dugmad[8].setLabel("9");
			dugmad[9].setLabel("*");
			dugmad[10].setLabel("0");
			dugmad[11].setLabel("+");
		}
	}

	Label broj = new Label();;
	Label ime = new Label();
	Button promenaRezima = new Button("Promena rezima");
	{
		promenaRezima.setBackground(Color.LIGHT_GRAY);
	}
	Rezim rezim = Rezim.SLOVA;

 Button[] dugmad = new Button[12];
	{
		for (int i=0; i<12; ++i) {
			dugmad[i] = new Button();
			dugmad[i].setName(""+i);
			dugmad[i].setBackground(Color.LIGHT_GRAY);
		}

		postaviTekstDugmadi();
	}

	int poslednjeKliknuto = -1;
	long trenutakKlikanja = System.currentTimeMillis();
	int dodatoSlovo = -1;

	Tastatura() {
		promenaRezima.addActionListener((e)->{
			if (rezim==Rezim.SLOVA) {
				rezim = Rezim.BROJEVI;
			} else if(rezim==Rezim.BROJEVI) {
				rezim = Rezim.SLOVA;
			}

			postaviTekstDugmadi();
		});

		for (int i=0; i<12; ++i) {
			dugmad[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int idx = Integer.parseInt(((Button)e.getSource()).getName());

					long dt = System.currentTimeMillis() - trenutakKlikanja;
					if (rezim==Rezim.SLOVA) {
						if (idx==0 || idx==9 || idx==11) {
							return;
						}

						if (idx==10) {
							ime.setText(ime.getText()+" ");
							return;
						}

						if (poslednjeKliknuto==idx && dt<1000) {
							int sledeceSlovo;
							if (dodatoSlovo+1==dugmad[idx].getLabel().length()) {
								sledeceSlovo = 0;
							} else {
								sledeceSlovo = dodatoSlovo+1;
							}

							ime.setText(ime.getText().substring(0,ime.getText().length()-1)+dugmad[idx].getLabel().charAt(sledeceSlovo));
							dodatoSlovo = sledeceSlovo;
						} else {
							dodatoSlovo = 0;
							ime.setText(ime.getText()+dugmad[idx].getLabel().charAt(0));
						}

						poslednjeKliknuto = idx;
						trenutakKlikanja = System.currentTimeMillis();
					} else if (rezim==Rezim.BROJEVI) {
						broj.setText(broj.getText()+dugmad[idx].getLabel());
					}
				}
			});
		}

		Panel panelDugmad = new Panel();
		panelDugmad.setLayout(new GridLayout(4,3));
		for (int i=0; i<12; ++i) {
			panelDugmad.add(dugmad[i]);
		}

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		c.gridheight = 1;
		ime.setPreferredSize(new Dimension(100, 20));
		add(ime,c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 10;
		c.gridheight = 1;
		broj.setPreferredSize(new Dimension(100, 20));
		add(broj,c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 10;
		c.gridheight = 5;
		panelDugmad.setPreferredSize(new Dimension(100, 100));
		add(panelDugmad,c);

		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 10;
		c.gridheight = 1;
		promenaRezima.setPreferredSize(new Dimension(100, 20));
		add(promenaRezima,c);
	}

	Kontakt formirajKontakt() throws Exception {
		if (broj.getText().length()<8) {
			throw new Exception("Uneti broj nije validan!");
		}

		return new Kontakt(ime.getText(), new Broj(broj.getText()));
	}
}
