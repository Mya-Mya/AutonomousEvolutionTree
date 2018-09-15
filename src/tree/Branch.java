package tree;

import java.awt.Color;
import java.awt.Graphics;

import client.Childtask;

public class Branch extends Childtask {
	double X1;
	double Y1;
	double Y2;
	double X2;
	private Color MyCol;

	public Branch(int WindowW, int WindowH ,double X1,double Y1,double X2,double Y2) {
		super(WindowW,WindowH);
		this.X1=X1;
		this.X2=X2;
		this.Y1=Y1;
		this.Y2=Y2;
		
		MyCol=new Color(109, 86, 59);
	}
	
	@Override
	public void update() {

	}
	@Override
	public void draw(Graphics g) {
		g.setColor(MyCol);
		g.drawLine((int)X1, (int)Y1, (int)X2, (int)Y2);
	}
}
