import java.awt.*;

class Clanak extends Figura {
	Clanak(Pozicija pozicija) {
		super(pozicija, Color.GREEN);
	}

	@Override
	boolean zauzima(Pozicija p) {
		return p.equals(pozicija());
	}

	@Override
	void naslikaj(Graphics g, Tabla tabla) {
		int w = tabla.getWidth() / tabla.nw();
		int h = tabla.getHeight() / tabla.nh();

		g.setColor(boja());
		g.fillOval(pozicija().x() * w, pozicija().y() * h, w, h);
	}

	@Override
	void pomeri(Smer smer, Tabla tabla) throws Greska {
		if (tabla.zauzeta(pozicija().susedna(smer)) || !tabla.uOkviru(pozicija().susedna(smer))) {
			System.out.println("Greksa generisana!");
			throw new Greska("Pokusano pomeranje na zauzetu poziciju");
		}

		pozicija().pomeri(smer);
	}
}
