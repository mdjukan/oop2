import java.awt.*;
import java.awt.event.*;

class Simulacija extends Frame {
	Scena scena = new Scena(this);

	Simulacija() {
		super("Simulacija");
		setVisible(true);
		setSize(500, 500);

		dodajElemente();
		dodajOsluskivace();

		doLayout();
		validate();

		scena.dodajUsisivac();
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		scena.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton()==MouseEvent.BUTTON1) {
					scena.dodajFiguru(new Kamencic(e.getX(), e.getY()));
				}
			}
		});

		scena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_SPACE:
						if (scena.radi()) {
							scena.pauziraj();
						} else {
							scena.pokreni();
						}
						break;
					case KeyEvent.VK_ESCAPE:
						scena.zaustavi();
						dispose();
						break;
				}
			}
		});
	}

	void dodajElemente() {
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(scena);
		add(panel, "Center");
	}

	public static void main(String[] args) {
		new Simulacija();
	}
}

//[1]
//Generator === je runnable === da svako dt postavi kamencic na random x,y
//Scena ima svoj generator
//
//[2]
//Igrac === plavi kvadrat === ima svoj pomeraj === reaguje na strelice
//Kao usisvac skuplja kamencice
//Ako ga sretene usisivac game over
//labela u kojoj pise broj kamencia koje igrac prikujue
//broj kamencica koje je usisivac prikupuio
//
//[3]
//input field x, y dugme dodaj kamencic na uneto x,y
