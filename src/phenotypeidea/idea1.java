package phenotypeidea;

import java.awt.Point;

import client.MyFrame;
import tree.BodyPart;
import tree.BodyPartMode;
import tree.BodyPartsRegisterer;
import tree.GeneAndPhenotype;
import tree.Tree;

public class idea1 extends GeneAndPhenotype {

	public idea1() {
		this.NUM_GENE_ELEMENTS=3;
	}

	@Override
	public void inherit(Tree Girl,Tree Boy) {
		super.inherit(Girl, Boy);

		for(int i=0;i<this.Gene.length;i++) {
			if(Girl!=null && Boy!=null &&Math.random()>0.1) {
				this.Gene[i]=Math.random()>0.5?Girl.getGene()[i]:Boy.getGene()[i];
				this.Gene[i]+=Math.random()*0.05-0.05*2;
			} else this.Gene[i]=3*(Math.random());
		}
	}

	@Override
	public void createBody(BodyPartsRegisterer register) {
		this.myRegister=register;
		createBranches(new Point(MyFrame.WindowW/2,MyFrame.WindowH-30),true,50,3.141592/2,10);
	}

	BodyPartsRegisterer myRegister;
	private void createBranches(Point MeStartP,boolean sideDirection,double length,double MeAngle,int leftTime) {
		Point MeEndP=new Point(
				(int)(MeStartP.x+length*Math.cos(MeAngle)),
				(int)(MeStartP.y-length*Math.sin(MeAngle))
		);
		BodyPartMode MeMode=leftTime<=0?BodyPartMode.MODE_LEAF:BodyPartMode.MODE_BRANCH;
		myRegister.registerBodyPart(new BodyPart(MeStartP, MeEndP, MeMode));
		if(leftTime<=0) {
			return;}
		
		double SideStartR=1/(1+Math.abs(Gene[0]));
		Point SideStartP=new Point(
				(int)(MeStartP.x+length*SideStartR*Math.cos(MeAngle)),
				(int)(MeStartP.y-length*SideStartR*Math.sin(MeAngle))
		);
		double SideAngle=MeAngle+Gene[1]*(sideDirection?-1:1);//isSideLeftに応じて自分に対し右に行くか左に行くか
		
		//サイド
		createBranches(SideStartP, !sideDirection, 0.8*length, SideAngle, leftTime-1);
		//メイン
		createBranches(MeEndP, !sideDirection, 0.9*length, MeAngle+Gene[2]*(sideDirection?-1:1), leftTime-1);
	}
}
