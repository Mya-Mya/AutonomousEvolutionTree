package tree;

import java.awt.Graphics;
import java.util.ArrayList;

import client.Childtask;
import client.MyFrame;
import phenotypeidea.idea1;

public class Tree extends Childtask implements BodyPartsRegisterer{

	private ArrayList<BodyPart>BodyParts=new ArrayList<BodyPart>();
	private GeneAndPhenotype BodyMaker=new idea1();

	public Tree(Tree Girl,Tree Boy) {
		BodyMaker.inherit(Girl, Boy);
	}

	public void makeBody() {
		BodyMaker.createBody(this);
	}
	
	//遺伝子と発現クラスが体の部品を登録する時に使う
	@Override
	public void registerBodyPart(BodyPart bodyPart) {
		BodyParts.add(bodyPart);
	}
	
	//葉がどのくらいの光を受けているか
	public int getRecievedLights() {
		int lights=0;
		boolean lightedFlag;

		for(int x=0;x<MyFrame.WindowW;x++) {
			lightedFlag=false;
			for(BodyPart b:BodyParts) {
				if(b.isYourLeafCrossing(x))lightedFlag=true;
			}
			if(lightedFlag)lights++;
		}
		return lights;
	}

	//自身の遺伝子
	public double[] getGene() {
		return BodyMaker.Gene;
	}


	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		for(BodyPart b:BodyParts)b.draw(g);
	}

	

}
