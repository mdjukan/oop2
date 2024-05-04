import java.awt.*;

class SolarniPanel extends Proizvodjac {
	SolarniPanel(Plac plac, Baterija baterija) {
		super('#', Color.BLACK, plac, 2500, baterija);
	}

	@Override
	boolean uspesnoProizveo() {
		return true;
	}

	@Override
	int proizvedenaEnergija() {
		return 2;
	}
}
