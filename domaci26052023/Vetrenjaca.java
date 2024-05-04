import java.awt.*;

class Vetrenjaca extends Proizvodjac {
	Vetrenjaca(Plac plac, Baterija baterija) {
		super('x', Color.LIGHT_GRAY, plac, 500, baterija);
	}

	int efikasnost = 0;

	void vetar() {
		efikasnost = 4;
	}

	@Override
	boolean uspesnoProizveo() {
		return efikasnost>0;
	}

	@Override
	int proizvedenaEnergija() {
		return efikasnost--;
	}
}
