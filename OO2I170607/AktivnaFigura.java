import java.awt.*;

abstract class AktivnaFigura extends Figura implements Runnable {
	private int dt;
	private int dx, dy;
	private Thread nit = new Thread(this);

	AktivnaFigura(int x, int y, Color boja, Scena scena, int dx, int dy, int dt) {
		super(x, y, boja, scena);
		this.dx = dx;
		this.dy = dy;
		this.dt = dt;
	}

	int dx() {
		return dx;
	}

	int dy() {
		return dy;
	}

	void postaviDx(int dx) {
		this.dx = dx;
	}

	void postaviDy(int dy) {
		this.dy = dy;
	}

	volatile boolean radi = true;

	abstract void postaviBrzinu();

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					if  (!radi) {
						wait();
					}
				}

				postaviBrzinu();
				Thread.sleep(dt);
				postaviX(x()+dx);
				postaviY(y()+dy);
				scena().repaint();
			}
		} catch (InterruptedException e) {}
	}

	void pokreni() {
		nit.start();
	}

	void pauziraj() {
		radi = false;
	}

	synchronized void nastavi() {
		radi = true;
		notify();
	}

	void zaustavi() {
		nit.interrupt();
	}
}
