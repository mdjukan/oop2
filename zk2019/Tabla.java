import java.awt.*;
import java.util.ArrayList;

class Tabla extends Canvas {
	Tabla(int nw, int nh) {
		this.nw = nw;
		this.nh = nh;
	}

	private ArrayList<Figura> figure = new ArrayList<>();
	void dodajFiguru(Figura f) {
		figure.add(f);
	}

	{
		dodajFiguru(new Jednodelni(new Pozicija(3,3), Color.RED));
		dodajFiguru(new Dvodelni(new Pozicija(5, 5), Color.GREEN));
	}

	@Override 
	public void paint(Graphics g) {
		int W = this.getWidth();
		int H = this.getHeight();
		int w = W / nw;
		int h = H / nh;

		for (int i=0; i<=nw; ++i) {
			//(i*w,0) -> (i*w,H);
			g.drawLine(i*w, 0, i*w, H);
		}

		for (int i=0; i<=nh; ++i) {
			//(0,i*h) -> (W,i*h)
			g.drawLine(0, i*h, W, i*h);
		}

		for (Figura figura : figure) {
			figura.naslikaj(g, this);
		} 
	}

	boolean zauzeta(Pozicija p) throws Greska {
		//(0,0) (1,0) ...(nw-1,0)
		//(0,1) (1,1) ...
		//(0,2) ...
		//.
		//.              (nw-1,nh-1);

		if (p.x()<0 || p.x()>=nw || p.y()<0 || p.y()>=nh) {
			throw new Greska("Pozicija van table!");
		}

		boolean z = false;
		for (Figura f : figure) {
			z |= f.zauzima(p);
		}

		return z;
	}

	private int nw, nh;
	int nw() {
		return nw;
	}

	int nh() {
		return nh;
	}

	void postaviNw(int nw) {
		this.nw = nw;
	}

	void postaviNh(int nh) {
		this.nh = nh;
	}
}
