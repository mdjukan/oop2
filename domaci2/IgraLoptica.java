import java.awt.*;
import java.awt.event.*;

class IgraLoptica extends Frame {
	Scena scena = new Scena();
	IgraLoptica(){
		super("IgraLoptica");
		setVisible(true);
		setSize(400, 600);

		dodajElemente();
		dodajOsluskivace();

		doLayout();
		validate();

		scena.inicijalizuj();
		scena.pokreni();
	}

	void dodajElemente() {
		add(scena, "Center");
	}

	void dodajOsluskivace() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		int POMERAJ_IGRAC = 10;
		scena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_LEFT) {
					scena.igrac().pomeri(-POMERAJ_IGRAC, 0);
				} else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
					scena.igrac().pomeri(POMERAJ_IGRAC, 0);
				}
			}
		});

		scena.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton()==MouseEvent.BUTTON1) {
					scena.igrac().ispaliLopticu();
				}
			}
		});
	}

	public static void main(String[] args) {
		new IgraLoptica();
	}
}
