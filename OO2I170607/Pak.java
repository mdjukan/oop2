import java.awt.*;

class Pak extends AktivnaFigura {
	private int d;
	int d() {
		return d;
	}

	Pak(Scena scena) {
		super(scena.w/2, scena.h/2, Color.RED, scena, 20, 20, 10);
		d = 30;
	}

	@Override
	void postaviBrzinu() {
		//sudar sa vertikalim zidovima
		if (x()<0) {
			postaviDx(-dx());
			scena().dodajPoenDesnom(); //nakon osvojenog poene igra se resetuje
			scena().resetPolozaj();
		}

		if (x()+d>scena().w) {
			postaviDx(-dx());
			scena().dodajPoenLevom();
			scena().resetPolozaj();
		}

		//sudar sa horizontalnim zidovmia
		if (y()<0 || y()+d>scena().getHeight()) {
			postaviDy(-dy());
		}

		//sudar sa levim igracem
		if (scena().levi.x()+scena().levi.w==x() && scena().levi.y()<y() && y()<scena().levi.y()+scena().levi.h) {
			postaviDx(1);
		}
		
		if (scena().desni.x()==x()+d() && scena().desni.y()<y() && y()<scena().desni.y()+scena().desni.h) {
			postaviDx(-1);
		}
	}


	@Override
	void naslikaj(Graphics g) {
		g.setColor(boja());
		g.fillOval(x(), y(), d, d);
	}
}
