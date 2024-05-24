import java.awt.*;

class Igrac extends Figura {
	private int w, h;
	Igrac(Scena scena, int x, int y, int w, int h) {
		super(scena, Color.BLUE, x, y, 'I');
		this.w = w;
		this.h = h;
	}

	void ispaliLopticu() {
		Loptica loptica = new Loptica(scena(), x(), (int)(y()-3.0/2*h), 10, 2*h);
		loptica.pokreni();
	}

	@Override
	void iscrtaj(Graphics g) {
		g.setColor(boja());
		g.fillRect(x()-w/2, y()-h/2, w, h);
	}

	TipSudara sudar(Loptica loptica) {
		if ((x()-w/2<loptica.x() && loptica.x()<x()+w/2) &&
				(Math.abs(y()-h/2-loptica.y())<loptica.r()/2 ||
					Math.abs(y()+h/2-loptica.y())<loptica.r()/2 )) {
			return TipSudara.HORIZONTALNI;
					}

		if ( (y()-h/2<loptica.y() && loptica.y()<y()+h/2) &&
				(Math.abs(x()-w/2-loptica.x())<loptica.r()/2 ||
					Math.abs(x()+w/2-loptica.x())<loptica.r()/2) ) {
			return TipSudara.VERTIKALNI;
					}

		return TipSudara.NEMA_SUDARA;
	}
}
