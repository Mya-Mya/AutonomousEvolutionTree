package tree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import client.Childtask;
import client.MyFrame;

public class BodyPart extends Childtask {
	private BodyPartMode Mode;
	private Color Col;
	Point p1=new Point();
	Point p2=new Point();
	
	private BasicStroke MyStroke=new BasicStroke(4.0f);

	public BodyPart(Point p1,Point p2, BodyPartMode Mode) {
		if(p1.x<p2.x) {
			this.p1=p1;this.p2=p2;
		}else {
			this.p1=p2;this.p2=p1;
		}
		this.Mode=Mode;
		switch (Mode){
		case MODE_BRANCH:
			Col=new Color(109,86,59);
			break;
		case MODE_LEAF:
			Col=new Color(80, 230, 100);
			break;
		}
	}

	public boolean isLeaf() {
		return Mode==BodyPartMode.MODE_LEAF;
	}

	//MODE_LEAF限定
	public boolean isYourLeafCrossing(int x) {
		if(!isLeaf())return false;
		if(p1.y>MyFrame.WindowH||p2.y>MyFrame.WindowH)return false;
		if(p1.y<0||p2.y<0)return false;
		return (p1.x<x&&x<p2.x)?true:false;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(MyStroke);
		g2.setColor(Col);
		g2.drawLine(p1.x,p1.y,p2.x,p2.y);
	}
}
