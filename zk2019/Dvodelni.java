import java.awt.*;

class Dvodelni extends Figura {
	Dvodelni(Pozicija p, Color boja) {
		super(p, boja);
	}

	@Override
	public void naslikaj(Graphics g, Tabla tabla) {
		g.setColor(boja());
		int w = tabla.getWidth() / tabla.nw();
		int h = tabla.getHeight() / tabla.nh();
		g.fillRect(pozicija().x() * w, pozicija().y() * h, 2*w, h);
	}

	@Override
	boolean zauzima(Pozicija p) {
		return p.equals(this.pozicija()) || p.equals(this.pozicija().pored(Smer.Desno));
	}

	@Override
	boolean mozeSePomeriti(Tabla tabla, Smer smer) {
		try {
			Pozicija blok1 = pozicija();
			Pozicija blok2 = blok1.pored(Smer.Desno);
			Pozicija blok1Sledeca = blok1.pored(smer);
			Pozicija blok2Sledeca = blok2.pored(smer);
			switch (smer) {
				case Dole:
					return !tabla.zauzeta(blok1Sledeca) && !tabla.zauzeta(blok2Sledeca);
				case Levo:
					return !tabla.zauzeta(blok1Sledeca);
				case Desno:
					return !tabla.zauzeta(blok2Sledeca);
			}
			return false;
		} catch (Greska g) {
			return false;
		}
	}
}
