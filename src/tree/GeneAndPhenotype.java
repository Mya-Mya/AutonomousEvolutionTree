package tree;

public abstract class GeneAndPhenotype {
	//予め遺伝子数は決めておくこと
	protected int NUM_GENE_ELEMENTS;
	protected double[] Gene;
	public GeneAndPhenotype() {	}
	
	//遺伝させ自身の遺伝子を作る
	public void inherit(double[] Girl,double []Boy) {
		Gene=new double[NUM_GENE_ELEMENTS];
	}
	//自身の体を作る
	public void createBody(BodyPartsRegisterer registerer) {}
	
	//遺伝子を返す
	public double[] getGene() {
		return this.Gene;
	}
}
