import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Palacinkarnica extends Frame implements Runnable {
	ArrayList<Porudzbina> porudzbine = new ArrayList<>();
	ArrayList<Sto> stolovi = new ArrayList<>();
	Thread nit = new Thread(this);

	boolean postojiSlobodan() {
		for (Sto sto : stolovi) {
			if (sto.stanje()==Stanje.Slobodan) {
				return true;
			}
		}

		return false;
	}

	int prihod = 0;
	synchronized void dodajPorudzbinu(Porudzbina p) {
		porudzbine.add(p);
	}

	synchronized void ukloniPorudzbinu(int id) {
		Porudzbina zaBrisanje = null;
		for (Porudzbina porudzbina : porudzbine) {
			if (id==porudzbina.id()) {
				zaBrisanje = porudzbina;
			}
		}

		porudzbine.remove(zaBrisanje);
		prihod += zaBrisanje.cena();
	}

	@Override
	public void run() {
		while (true) {
			if (postojiSlobodan()) {
				synchronized (this) {
					this.notify();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			} else {
				synchronized (this) {
					try {
					this.wait();
					} catch (InterruptedException e) {}
				}
			}
			azurirajPorudzbine();
			azurirajPrihod();
		}
	}

	void pokreni() {
		for (Sto sto : stolovi) {
			sto.pokreni();
		}

		if (!nit.isAlive()) {
			nit.start();
		}
	}

	void zaustavi() {
		for (Sto sto : stolovi) {
			sto.zaustavi();
		}

		nit.interrupt();
	}

	///////////////////////////GUI///////////////////
	int redovi = 3, kolone = 3;
	{
		for (int i=0; i<redovi*kolone; ++i) {
			stolovi.add(new Sto(this));
		}
	}

	TextField redoviTF = new TextField();
	TextField koloneTF = new TextField();
	Button pokreni = new Button("Pokreni");
	Button prekini = new Button("Prekini");

	Label prihodLab = new Label("Kasa:0", Label.CENTER);
	{
		prihodLab.setFont(new Font("Arial", Font.BOLD, 30));
	}

	List porudzbineOut = new List();

	synchronized void azurirajPorudzbine() {
		porudzbineOut.removeAll();
		for (Porudzbina p : porudzbine) {
			porudzbineOut.add(p.toString());
		}
	}

	void azurirajPrihod() {
		prihodLab.setText("Kasa:"+prihod);
	}

	Panel stoloviPanel = new Panel();
	void azurirajStolove() {
		for (Sto sto : stolovi) {
			if (sto.nit.isAlive()) {
				sto.zaustavi();
			}
		}

		porudzbine.clear();
		azurirajPorudzbine();

		stolovi.clear();
		stoloviPanel.removeAll();
		stoloviPanel.setLayout(new GridLayout(redovi, kolone));
		for (int i=0; i<redovi*kolone; ++i) {
			stolovi.add(new Sto(this));
		}

		for (Sto sto : stolovi) {
			stoloviPanel.add(sto);
		}

		add(stoloviPanel, "Center");
	}

	void dodajElemente() {
		azurirajStolove();

		Panel panel = new Panel();
		Panel panel1 = new Panel();
		panel1.setLayout(new GridLayout(2, 1));
		Label naslov = new Label("Spisak porudzbina");
		naslov.setBackground(Color.GRAY);
		panel1.add(naslov);
		panel1.add(porudzbineOut);

		Panel panel2 = new Panel();
		panel2.setBackground(Color.GRAY);
		panel2.setLayout(new GridLayout(1, 2, 5, 5));
		panel2.add(prihodLab);
		
		Panel panel3 = new Panel();
		panel3.setLayout(new GridLayout(2, 3, 5, 5));
		panel3.add(new Label("Broj redova:"));
		panel3.add(redoviTF);
		panel3.add(pokreni);
		panel3.add(new Label("Broj kolona:"));
		panel3.add(koloneTF);
		panel3.add(prekini);

		panel2.add(panel3);

		panel = new Panel();
		panel.setLayout(new GridLayout(2,1, 5, 5));
		panel.add(panel1);
		panel.add(panel2);
		add(panel, "South");
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		pokreni.addActionListener((e)->{
			pokreni();
		});

		prekini.addActionListener((e)->{
			zaustavi();
			prekini.setEnabled(false);
		});

		redoviTF.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent e) {
				try {
				redovi = Integer.parseInt(redoviTF.getText());
				azurirajStolove();
				} catch (NumberFormatException exp) {}
			}
		});

		koloneTF.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent e) {
				try {
				kolone = Integer.parseInt(koloneTF.getText());
				azurirajStolove();
				} catch (NumberFormatException exp) {}
			}
		});
	}

	Palacinkarnica() {
		super("Palacinkarnica");
		setSize(500, 600);
		setVisible(true);

		dodajElemente();
		dodajOsluskivace();
	}

	public static void main(String[] args) {
		new Palacinkarnica();
	}
}
