import java.awt.*;

class Muva extends Figura {
	Muva(Pozicija pozicija) {
		super(pozicija, Color.BLACK);
	}

	@Override
	void naslikaj(Graphics g, Tabla tabla) {
		g.setColor(boja());

		int w = tabla.getWidth() / tabla.nw();
		int h = tabla.getHeight() / tabla.nh();

		int X = pozicija().x() * w;
		int Y = pozicija().y() * h;

		//[1] vertikalna po pola
		g.drawLine(X+w/2, Y, X+w/2, Y+h);
		//[2] horiznotalan po pola
		g.drawLine(X, Y+h/2, X+w, Y+h/2);
		//[3] glavna dijagonal
		g.drawLine(X, Y, X+w, Y+h);
		//[4] sporedna dijagonal
		g.drawLine(X+w, Y, X, Y+h);

	}

	@Override
	boolean zauzima(Pozicija p) {
		return p.equals(this.pozicija());
	}

	@Override
 void pomeri(Smer smer, Tabla tabla) throws Greska {
		return;
	}
}
