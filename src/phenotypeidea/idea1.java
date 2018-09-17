package phenotypeidea;

import tree.BodyPartsRegisterer;
import tree.GeneAndPhenotype;

public class idea1 extends GeneAndPhenotype {

	public idea1() {
		this.NUM_GENE_ELEMENTS=4;
	}

	@Override
	public void inherit(double[] Girl,double[] Boy) {
		super.inherit(Girl, Boy);
		//TODO 遺伝子生成
	}
	@Override
	public void createBody(BodyPartsRegisterer register) {
		//TODO 体作る
	}
}
