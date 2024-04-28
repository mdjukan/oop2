import java.awt.*;

class Jednodelni extends Figura {
	Jednodelni(Pozicija p, Color boja) {
		super(p, boja);
	}

	@Override
	public void naslikaj(Graphics g, Tabla tabla) {
		g.setColor(boja());
		int w = tabla.getWidth() / tabla.nw();
		int h = tabla.getHeight() / tabla.nh();
		g.fillOval(pozicija().x() * w, pozicija().y() * h, w, h);
	}

	@Override
	boolean zauzima(Pozicija p) {
		return p.equals(this.pozicija());
	}

	@Override
	boolean mozeSePomeriti(Tabla tabla, Smer smer) {
		Pozicija sledeca = this.pozicija().pored(smer);
		try {
			if (tabla.zauzeta(sledeca)) {
				return false;
			} else {
				return true;
			}
		} catch (Greska g) {
			return false;
		}
	}
}
