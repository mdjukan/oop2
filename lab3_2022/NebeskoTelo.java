import java.awt.*;

abstract class NebeskoTelo extends Objekat {
	private int r;
	NebeskoTelo(int x, int y, Color boja, int r) {
		super(x, y, boja);
		this.r = r;
	}

	int r() {
		return r;
	}
}
