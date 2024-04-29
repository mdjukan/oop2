import java.awt.*;
import java.awt.event.*;

class Tetris extends Frame {
	Tabla tabla = new Tabla(10, 30);

	Label poeni = new Label("Poeni: 0", Label.CENTER);
	Label figure = new Label("Figure: 0", Label.CENTER);

	TextField x = new TextField();
	TextField y = new TextField();
	Button kreniStani = new Button("Stani|Kreni");

	void novaIgra() {
		try {
			int nw = Integer.parseInt(x.getText());
			int nh = Integer.parseInt(y.getText());
			tabla.postaviNw(nw);
			tabla.postaviNh(nh);
			tabla.resetujPoene();
			tabla.ukloniFigure();
		} catch (NumberFormatException e) {
		}
	}

	void dodajElemente() {
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(tabla);
		add(panel, "Center");

		panel = new Panel();

		panel.setLayout(new GridLayout(1, 2));

		Panel panelL = new Panel();
		panelL.setLayout(new GridLayout(2,1));
		panelL.add(poeni);
		panelL.add(figure);

		panel.add(panelL);

		Panel panelR = new Panel();
		panelR.setLayout(new GridLayout(2, 1));
		panelR.add(kreniStani);

		Panel panelD = new Panel();
		panelD.setLayout(new GridLayout(1,3));
		panelD.add(new Label("x,y:"));
		panelD.add(x);
		panelD.add(y);
		panelR.add(panelD);

		panel.add(panelR);

		add(panel, "South");
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		kreniStani.addActionListener((e)->{
			if (tabla.radi) {
				tabla.pauziraj();
			} else {
				tabla.pokreni();
			}
		});

		tabla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						tabla.pomeriPokretnu(Smer.Levo);
						break;
					case KeyEvent.VK_RIGHT:
						tabla.pomeriPokretnu(Smer.Desno);
						break;
					case KeyEvent.VK_DOWN:
						tabla.pomeriPokretnu(Smer.Dole);
						break;
				}
			}
		});
	}

	void dodajMeni() {
		MenuBar bar = new MenuBar();
		Menu menu = new Menu("Akacij");
		setMenuBar(bar);
		bar.add(menu);

		MenuItem novaIgra = new MenuItem("Nova Igra", new MenuShortcut('N'));
		menu.add(novaIgra);
		novaIgra.addActionListener((e)->{
			novaIgra();
		});
	}

	Tetris() {
		super("Tetris");
		setSize(250, 600);
		setVisible(true);
		dodajElemente();
		dodajOsluskivace();
		dodajMeni();
		tabla.zabeleziLabele(poeni, figure);
	}

	public static void main(String[] args) {
		new Tetris();
	}
}
