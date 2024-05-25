import java.awt.*;

class Zid extends Kvadrat {
	Zid(Kanalizacija kanlizacija) {
		super(kanlizacija, new Color(170, 170, 170), true);
	}

	@Override
	void naslikajKomponentu(Graphics g) {
		return;
	}
}
