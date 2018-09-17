package tree;

public abstract class GeneAndPhenotype {
	//予め遺伝子数は決めておくこと
	protected int NUM_GENE_ELEMENTS;
	protected double[] Gene;
	public GeneAndPhenotype() {	}
	
	//遺伝させ自身の遺伝子を作る
	public void inherit(Tree Girl,Tree Boy) {
		Gene=new double[NUM_GENE_ELEMENTS];
	}
	//発現させる
	public void createBody(BodyPartsRegisterer registerer) {}
	
	//遺伝子を返す
	public double[] getGene() {
		return this.Gene;
	}
}
