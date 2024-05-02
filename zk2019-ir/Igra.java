import java.awt.*;
import java.awt.event.*;

class Igra extends Frame {
	Tabla tabla = new Tabla(10, 10, 500);
	TextField x = new TextField("10");
	TextField y = new TextField("10");
	Choice brzina = new Choice();
	Button kreniStani = new Button("Pokreni!");
	{
		brzina.addItem("Lak");
		brzina.addItem("Srednji");
		brzina.addItem("Tezak");
	}

	int dtOdChoice() {
		if (brzina.getSelectedItem()=="Lak") return 500;
		if (brzina.getSelectedItem()=="Srednji") return 300;
		if (brzina.getSelectedItem()=="Tezak") return 100;
		return 0;
	}

	void dodajElemente() {
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(tabla);
		add(panel, "Center");

		panel = new Panel();
		panel.setLayout(new GridLayout(2,2));
		panel.add(brzina);
		panel.add(kreniStani);
		panel.add(tabla.duzina);

		Panel panel1 = new Panel();
		panel1.setLayout(new GridLayout(1, 3));
		panel1.add(new Label("x, y:"));
		panel1.add(x);
		panel1.add(y);
		panel.add(panel1);

		add(panel, "South");
	}

	void novaIgra() {
		try {
		tabla.postaviNw(Integer.parseInt(x.getText()));
		tabla.postaviNh(Integer.parseInt(y.getText()));
		tabla.postaviDt(dtOdChoice());
		tabla.reset();
		} catch (NumberFormatException e) {}
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		kreniStani.addActionListener((e)->{
			if (!tabla.pokrenuta) {
				tabla.pokreni();
				kreniStani.setLabel("Pauza!");
			} else {
				if (tabla.radi) {
					tabla.pauziraj();
					kreniStani.setLabel("Nastavi!");
				} else {
					tabla.nastavi();
					kreniStani.setLabel("Pauza!");
				}
			}
		});

		tabla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						tabla.zmija.postaviSmer(Smer.Levo);
						break;
					case KeyEvent.VK_RIGHT:
						tabla.zmija.postaviSmer(Smer.Desno);
						break;
					case KeyEvent.VK_UP:
						tabla.zmija.postaviSmer(Smer.Gore);
						break;
					case KeyEvent.VK_DOWN:
						tabla.zmija.postaviSmer(Smer.Dole);
						break;
				}
			}
		});
	}

	void dodajMeni() {
		MenuBar bar = new MenuBar();
		Menu meni = new Menu("Akcije");
		MenuItem item1 = new MenuItem("Nova Igra", new MenuShortcut('N'));
		item1.addActionListener((e)->{novaIgra();});
		MenuItem item2 = new MenuItem("Zatvori", new MenuShortcut('Z'));
		item2.addActionListener((e)->{dispose();});
		meni.add(item1);
		meni.add(item2);
		bar.add(meni);
		setMenuBar(bar);
	}

	Igra() {
		super("Zmija");
		setSize(500, 500);
		setVisible(true);
		//tabla.pokreni();

		dodajElemente();
		dodajOsluskivace();
		dodajMeni();
	}

	public static void main(String[] args) {
		new Igra();
	}
}
