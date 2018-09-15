package client;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	static final public int WindowW=1000;
	static final public int WindowH=600;

	static public void main(String[] args) {
		new MyFrame();
	}
	public MyFrame() {
		setTitle("自律進化する木");
		setPreferredSize(new Dimension(WindowW, WindowH));

		MyPanel panel=new MyPanel();
		add(panel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setVisible(true);
	}
}
