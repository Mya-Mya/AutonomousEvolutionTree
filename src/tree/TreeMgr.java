package tree;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import client.Childtask;

public class TreeMgr extends Childtask {
	public static final int TREES_PER_GENERAT=60;
	
	private ArrayList<Tree> MyTrees=new ArrayList<Tree>();
	
	private int makingCnt=0;
	private int maxLighted=0;
	private int nowGeneration=1;
	
	private Tree Girl=null;
	private Tree Boy=null;
	

	public TreeMgr(int WindowW, int WindowH) {
		super(WindowW, WindowH);
	}

	@Override
	public void update() {
		//生成途中
		if(makingCnt<=TREES_PER_GENERAT) {	
			Tree buf=new Tree(WindowW, WindowH, Girl, Boy);
			buf.createMyBody(WindowW/2, WindowH, 7, 400, 3.1415/2);
			MyTrees.add(buf);
			makingCnt++;
		}else {
		//評価
			Girl=MyTrees.get(getMaxLightedTreeIndex());
			MyTrees.remove(Girl);
			Boy=MyTrees.get(getMaxLightedTreeIndex());
			MyTrees.clear();
			maxLighted=Girl.getRecievedLights();
			nowGeneration++;
			makingCnt=0;
		}
	}
	
	public int getMaxLightedTreeIndex() {
		int max=0;
		for(Tree t:MyTrees)
			max=t.getRecievedLights()>max?MyTrees.indexOf(t):max;
		return max;
	}

	@Override
	public void draw(Graphics g) {
		if(MyTrees.size()>0)MyTrees.get(MyTrees.size()-1).draw(g);
		g.setColor(Color.black);
		g.drawString(nowGeneration+"世代目 "+makingCnt+"個体目\n 前回の世代の最大受光量:"+maxLighted,0,100);
	}

}
