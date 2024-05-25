import java.awt.*;

abstract class AktivnaFigura extends Figura implements Runnable {
	double dx, dy;
	int dt;

	private Thread nit = new Thread(this);

	AktivnaFigura(Scena scena, Color boja, double x, double y, char vrsta, double dx, double dy, int dt) {
		super(scena, boja, x, y, vrsta);
		this.dx = dx;
		this.dy = dy;
		this.dt = dt;
	}

	void pomeri() {
		postaviX(x()+dx);
		postaviY(y()+dy);
	}

	void pokreni() {
		nit.start();
	}

	void zaustavi() {
		nit.interrupt();
	}
}
