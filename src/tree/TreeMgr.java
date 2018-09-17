package tree;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import client.Childtask;
import client.MyFrame;

public class TreeMgr extends Childtask {
	public static final int TREES_PER_GENERAT=50;

	private ArrayList<Tree> MyTrees=new ArrayList<Tree>();

	private int makingCnt=0;
	private int maxLighted=0;
	private int nowGeneration=1;
	private int previewingCnt;

	private Tree Girl=null;
	private Tree Boy=null;

	private Font MyFont=null;
	private Scene now=Scene.Making;

	public TreeMgr() {
		MyFont=new Font("メイリオ", Font.BOLD, 30);
	}

	@Override
	public void update() {
		switch(now) {
		case Making:
			if(makingCnt>=TREES_PER_GENERAT)now=Scene.Calculating;
			
			Tree buf=new Tree(Girl, Boy);
			buf.makeBody();
			MyTrees.add(buf);
			makingCnt++;
			break;
		case Calculating:
			Girl=MyTrees.get(getMaxLightedTreeIndex());
			maxLighted=Girl.getRecievedLights();
			MyTrees.remove(Girl);
			Boy=MyTrees.get(getMaxLightedTreeIndex());
			MyTrees.clear();
			
			previewingCnt=30;
			makingCnt=0;
			nowGeneration++;
			now=Scene.Previewing;
			break;
		case Previewing:
			previewingCnt--;
			if(previewingCnt<=0)now=Scene.Making;
			break;
		}
	}

	public int getMaxLightedTreeIndex() {
		int max=0;
		int mostlighted=0;
		for(Tree t:MyTrees) {
			if(mostlighted<t.getRecievedLights()) {
				mostlighted=t.getRecievedLights();
				max=MyTrees.indexOf(t);
			}
		}
		return max;
	}

	@Override
	public void draw(Graphics g) {
		g.setFont(MyFont);
		switch(now) {
		case Making:
			if(MyTrees.size()>0)MyTrees.get(MyTrees.size()-1).draw(g);
			g.setColor(Color.black);
			g.drawString(nowGeneration+"世代目 "+makingCnt+"個体目\n 前回の世代の最大受光量:"+maxLighted,0,MyFrame.WindowH-50);
			break;
		case Calculating:
			g.setColor(Color.ORANGE);
			g.drawString("計算中・・・・・・・・・・・・・",0,50);
			break;
		case Previewing:
			Girl.draw(g);
			g.setColor(Color.RED);
			g.drawString(nowGeneration+"世代目 の最優秀個体最大受光量:"+maxLighted,0,MyFrame.WindowH-50);
		}
	}

}

enum Scene{
	Making,
	Calculating,
	Previewing
}