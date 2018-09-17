package client;

import java.awt.Graphics;

//描画系のあるクラスは全てこれを継承すること
abstract public class Childtask {
	public Childtask() {}
	public void update() {}
	public void draw(Graphics g) {}
}
