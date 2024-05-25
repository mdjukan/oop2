import java.awt.*;

class Cev extends Kvadrat {
	static final Color unutrasnjost = new Color(204,204,204);

	TipCevi tip;
	Cev(Kanalizacija kanalizacija, TipCevi tip) {
		super(kanalizacija, new Color(170, 170, 170), true);
		this.tip = tip;
	}

	void naslikajKomponentu(Graphics g) {
		switch (tip) {
			case	LEVO_DESNO:
			case	DESNO_LEVO:
				g.setColor(unutrasnjost);
				g.fillRect(0,h/4,h,h/2);
				g.setColor(Color.BLACK);
				g.drawLine(0,h/4,h,h/4);
				g.drawLine(0,3*h/4,h,3*h/4);
				g.drawLine(h/4,h/2,3*h/4,h/2);
				break;
			case	GORE_DOLE:
			case	DOLE_GORE:
				g.setColor(unutrasnjost);
				g.fillRect(h/4,0,h/2,h);
				g.setColor(Color.BLACK);
				g.drawLine(h/4,0,h/4, h);
				g.drawLine(3*h/4,0, 3*h/4,h);
				g.drawLine(h/2,h/4,h/2,3*h/4);
				break;
		}

		int x, y;
		switch (tip) {
			case	GORE_DOLE: {
				x = h/2; y =3*h/4;
				int[] xs = {x-4,x+4,x};
				int[] ys = {y, y, y+10};
				g.fillPolygon(xs, ys, 3); }
				break;
			case	DESNO_LEVO: {
				x = h/4; y = h/2;
				int[] xs = {x, x, x-10};
				int[] ys = {y-4, y+4, y};
				g.fillPolygon(xs, ys, 3); }
				break;
			case	LEVO_DESNO: {
				x = 3*h/4; y = h/2;
				int[] xs = {x, x, x+10};
				int[] ys = {y-4,y+4,y};
				g.fillPolygon(xs, ys, 3); }
				break;
			case	DOLE_GORE: {
				x = h/2; y = h/4;
				int[] xs = {x-4,x+4,x};
				int[] ys = {y, y, y-10};
				g.fillPolygon(xs, ys, 3); }
				break;
		}
	}
}
