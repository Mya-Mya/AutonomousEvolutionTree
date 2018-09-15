package tree;

import java.awt.Graphics;
import java.util.ArrayList;

import client.Childtask;

public class Tree extends Childtask{
	public final static double MUTATION_RATE=0.05;

	private double[] Gene=new double[NUM_GENE_ELEMENTS];

	private ArrayList<Leaf>MyLeaves=new ArrayList<Leaf>();
	private ArrayList<Branch>MyBranches=new ArrayList<Branch>();

	public Tree(int WindowW, int WindowH, Tree Girl,Tree Boy) {
		super(WindowW, WindowH);

		//自分の遺伝子を親から受け継ぐ+変異
		for(int i=0;i<Gene.length;i++) {
			if(Girl!=null && Boy!=null) {
				Gene[i]=Math.random()>0.5?Girl.getGene()[i]:Boy.getGene()[i];
				Gene[i]=Gene[i]+MUTATION_RATE*(Math.random()-0.5);}
			else {
				Gene[i]=5*(Math.random()-0.5);
			}
		}
	}

	public final static int NUM_GENE_ELEMENTS=5;
	private double ShorterBranchBase=0.5;//長い枝のどこら辺から短い枝を出すか
	private double NextLongerSizeRate=0.8;
	private double NextShorterSizeRate=0.4;
	/*枝と木を遺伝子に従って作る
	 * [0]:[1]=長い方の枝の長さ:短い方の枝の長さ
	 * [2]=長い方の枝と短い方の枝の角度
	 * [3]=長い方の枝の末端から生えるネストの角度
	 * [4]=短い方の枝の末端から生えるネストの角度
	 */
	public void createMyBody(int x,int y,int Times, double SumSize, double Angle) {
		//枝の各種設定
		double LongerSize=SumSize/(Math.abs(Gene[0])+Math.abs(Gene[1]))*Math.abs(Gene[0]);
		double LXend=x+LongerSize* (Math.random()>0.5?Math.cos(Angle):-Math.cos(Angle));//左右どちらに振れるかわからない
		double LYend=y-LongerSize* (Math.random()>0.5?Math.sin(Angle):-Math.sin(Angle));

		double ShorterSize=SumSize/(Math.abs(Gene[0])+Math.abs(Gene[1]))*Math.abs(Gene[1]);
		double SXstart=ShorterBranchBase*x+(1-ShorterBranchBase)*LXend;
		double SYstart=ShorterBranchBase*y+(1-ShorterBranchBase)*LYend;
		double SXend=SXstart+ShorterSize* (Math.random()>0.5?Math.cos(Angle+Gene[2]):-Math.cos(Angle+Gene[2]));
		double SYend=SYstart-ShorterSize* (Math.random()>0.5?Math.sin(Angle+Gene[2]):-Math.sin(Angle+Gene[2]));

		if(Times==0) {//葉を作る
			MyLeaves.add(new Leaf(WindowW, WindowH,
					(int)LXend, (int)LYend,
					(int)(LXend+(LXend-x)*3),
					(int)(LYend+(LYend-y)*3)
			));
			return;
		}
		Times--;

		//長い方の枝の端から
		MyBranches.add(new Branch(WindowW, WindowH, x, y, LXend, LYend));
		createMyBody((int)LXend, (int)LYend, Times, SumSize*NextLongerSizeRate, Angle+Gene[3]);
		//短い方の枝の端から
		MyBranches.add(new Branch(WindowW,WindowH,SXstart,SYstart,SXend,SYend));
		createMyBody((int)SXend, (int)SYend, Times, SumSize*NextShorterSizeRate, Angle+Gene[4]);
	}

	//葉がどのくらいの光を受けられるか = どのくらいx軸に開いているか
	public int getRecievedLights() {
		int lights=0;
		boolean lightedFlag;

		for(int x=0;x<WindowW;x++) {
			lightedFlag=false;
			for(Leaf l:MyLeaves) {
				if(l.isCrossing(x))lightedFlag=true;
			}
			if(lightedFlag)lights++;
		}
		return lights;
	}

	//自身の遺伝子
	public double[] getGene() {
		return Gene;
	}


	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		for(Branch b:MyBranches)b.draw(g);
		for(Leaf l:MyLeaves)l.draw(g);
	}

}
