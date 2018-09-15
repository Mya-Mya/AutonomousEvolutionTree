package client;

import java.awt.Graphics;

//描画系のあるクラスは全てこれを継承すること
abstract public class Childtask {
	public int WindowW;
	public int WindowH;
	public Childtask(int WindowW,int WindowH) {
		this.WindowW=WindowW;
		this.WindowH=WindowH;
	}
	public void update() {

	}
	public void draw(Graphics g) {

	}
}
