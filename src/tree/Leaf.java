package tree;

import java.awt.Color;
import java.awt.Graphics;

import client.Childtask;

public class Leaf extends Childtask{
	private int LeftX;
	private int RightX;
	private int LeftY;
	private int RightY;
	private Color MyCol;

	public Leaf(int WindowW, int WindowH, int X1,int Y1,int X2,int Y2) {
		super(WindowW, WindowH);

		if(X1<X2) { 
			this.LeftX=X1;this.RightX=X2;
			this.LeftY=Y1;this.RightY=Y2;
		}else{
			this.LeftX=X2;this.RightX=X1;
			this.LeftY=Y2;this.RightY=Y1;
		}

		MyCol=new Color(80, 230, 100);
	}

	//その画面内のx座標上にあなたの葉はあるか
	public boolean isCrossing(int YourX) {
		//地面より下にあるなら無効
		if(LeftY>WindowH||RightY>WindowH)return false;
		return (LeftX<YourX&&YourX<RightX)?true:false;
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(MyCol);
		g.drawLine(RightX, RightY, LeftX, LeftY);
	}
}
