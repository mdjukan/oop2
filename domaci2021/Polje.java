import java.awt.*;
import java.awt.event.*;

class Polje extends Canvas {
	private int broj;
	private Mreza mreza;
	private Status status = Status.SLOBODNO;

	private int visina = 75;
	private int sirina = 75;

	void azurirajVisinu() {
		visina = this.getHeight();
	}

	void azurirajSirinu() {
		sirina = this.getWidth();
	}

	Polje(int broj, Mreza mreza) {
		this.broj = broj;
		this.mreza = mreza;

		Polje polje = this;

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton()==MouseEvent.BUTTON1) {
					polje.promeniStatus(); //?
				}
			}
		});

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				polje.azurirajVisinu();
				polje.azurirajSirinu();
			}
		});
	}

	Status status() {
		return status;
	}

	void postaviStatus(Status status) {
		this.status = status;
	}

	void promeniStatus() {
		if (status==Status.SLOBODNO) {
			status = Status.IZABRANO;
		} else if (status==Status.IZABRANO) {
			status = Status.SLOBODNO;
		}

		repaint();
		mreza.promenaStatusa(this);
	}

	@Override
	public void paint(Graphics g) {
		setBackground(Color.ORANGE);

		Font font = new Font("Serif", Font.PLAIN, visina/3);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);

		String s = "" + broj;
		int w = metrics.stringWidth(s);
		int h = visina/3;

		int x = sirina/2-w/2;
		int y = visina/2+h/2;

		if (status==Status.SLOBODNO) {
			g.setColor(Color.BLACK);
			g.drawString(s, x, y);
		} else if (status==Status.IZABRANO) {
			g.setColor(Color.BLUE);
			g.fillOval(0, 0, sirina, visina);
			g.setColor(Color.WHITE);
			g.drawString(s, x, y);
		}
	}
}
