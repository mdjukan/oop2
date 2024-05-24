import java.awt.*;

abstract class AktivnaFigura extends Figura implements Runnable {
	double dx, dy;
	int dt;

	private Thread nit = new Thread(this);

	AktivnaFigura(Scena scena, Color boja, int x, int y, char vrsta, double dx, double dy, int dt) {
		super(scena, boja, x, y, vrsta);
		this.dx = dx;
		this.dy = dy;
		this.dt = dt;
	}

	void pomeri() {
		postaviX(x() + (int)dx);
		postaviY(y() + (int)dy);
	}

	void pokreni() {
		nit.start();
	}

	void zaustavi() {
		nit.interrupt();
	}
}
