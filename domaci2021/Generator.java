class Generator {
	static int slucajanBroj(int donja, int gornja) {
			return (int)(donja + (1-Math.random()) * (gornja+1-donja));
	}
}
