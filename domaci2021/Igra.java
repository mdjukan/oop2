import java.awt.*;
import java.awt.event.*;

class Igra extends Frame {
	static final int INF = -1;

	private Mreza mreza = new Mreza(this);

	void promenaStatusa() {
		if (mreza.brojIzabranih()==0) {
			kvota = INF;
		} else {
			kvota = (double)mreza.brojPolja() / (double)mreza.brojIzabranih();
		}

		azurirajPrikaz();
	}

	Label balansLabela = new Label("0");
	TextField ulogInput = new TextField("100");
	Label kvotaLabela = new Label("");
	Label dobitakLabela = new Label("");
	Button igraj = new Button("Igraj");
	Label izvucenBroj = new Label("");

	private double balans = 0;
	private double ulog = 100;
	private double kvota = INF;

	void azurirajPrikaz() {
		if (kvota==INF) {
			kvotaLabela.setText("INF");
		} else {
			kvotaLabela.setText(""+kvota);
		}

		if (kvota==INF) {
			dobitakLabela.setText("INF");
		} else {
			double dobitak = ulog * kvota;
			dobitakLabela.setText(""+dobitak);
		}

		balansLabela.setText(""+balans);
	}

	Igra() {
		super("Igra");
		setVisible(true);
		setSize(500,500);

		dodajElemente();
		dodajOsluskivace();
	}

	void dodajElemente() {
		add(mreza, "Center");

		Panel panel = new Panel();
		panel.setLayout(new GridLayout(4,2));

		panel.add(new Label("Balans:"));
		panel.add(balansLabela);

		panel.add(new Label("Ulog:"));
		panel.add(ulogInput);

		panel.add(new Label("Kvota:"));
		panel.add(kvotaLabela);

		panel.add(new Label("Dobitak:"));
		panel.add(dobitakLabela);

		Panel panel1 = new Panel();
		panel1.setLayout(new GridLayout(2,1));
		panel1.add(panel);
		panel1.add(igraj);

		add(panel1, "East");

		add(izvucenBroj, "South");

		azurirajPrikaz();
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		ulogInput.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent e) {
				try {
					ulog = Double.parseDouble(ulogInput.getText());
					azurirajPrikaz();
				} catch (NumberFormatException exp) {}
			}
		});

		igraj.addActionListener((e)->{
			int broj = Generator.slucajanBroj(0, mreza.brojPolja()-1);
			izvucenBroj.setText(""+broj);

			if (mreza.jeIzabran(broj)) {
				balans += ulog * kvota;
				izvucenBroj.setBackground(Color.GREEN);
			} else {
				balans -= ulog;
				izvucenBroj.setBackground(Color.RED);
			}

			azurirajPrikaz();
		});
	}

	public static void main(String[] args) {
		new Igra();
 }
}
