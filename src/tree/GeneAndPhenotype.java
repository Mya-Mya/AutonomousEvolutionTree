package tree;

public abstract class GeneAndPhenotype {
	//予め遺伝子数は決めておくこと
	private int NUM_GENE_ELEMENTS=1;
	private double[] Gene;
	public GeneAndPhenotype() {
		Gene=new double[NUM_GENE_ELEMENTS];
	}
	
	//遺伝させ自身の遺伝子を作る
	public void inherit(double[] Girl,double []Boy) {}
	//自身の体を作る
	public void createBody(BodyPartsRegisterer registerer) {}
}
