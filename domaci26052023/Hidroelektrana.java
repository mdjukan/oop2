import java.awt.*;

class Hidroelektrana extends Proizvodjac {
	Hidroelektrana(Plac plac, Baterija baterija) {
		super('H', Color.BLUE, plac, 1500, baterija);
	}

	@Override
	boolean uspesnoProizveo() {
		return plac.susedneVodenePovrsi(this)!=0;
	}

	@Override
	int proizvedenaEnergija() {
		return plac.susedneVodenePovrsi(this);
	}
}
