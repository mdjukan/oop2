import java.awt.*;

class Igrac extends AktivnaFigura {
	static final int w = 10, h = 50;
	Igrac(int x, Scena scena) {
		super(x, Scena.h/2-h/2, Color.BLACK, scena, 0, 0, 100);
		System.out.println("x->" + x());
		System.out.println("y->" + y());
	}

	@Override
	void postaviBrzinu() {
		if (y()<0 || y()+h>Scena.h) {
			postaviDy(-dy());
		} else {
			if ((x()<scena().pak.x() && scena().pak.dx()<0) || (x()>scena().pak.x() && scena().pak.dx()>0) ) {
				if (y()+h/2<scena().pak.y()) {
					postaviDy(1);
				} else {
					postaviDy(-1);
				}
			}
		}
	}

	@Override
	void naslikaj(Graphics g) {
		g.setColor(boja());
		g.fillRect(x(), y(), w, h);
	}
}
