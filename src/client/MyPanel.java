package client;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import tree.TreeMgr;

public class MyPanel extends JPanel implements ActionListener{
	Childtask task1;
	public MyPanel() {
		task1=new TreeMgr(MyFrame.WindowW, MyFrame.WindowH);
		new javax.swing.Timer(5,this).start();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		task1.update();
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		task1.draw(g);
	}
}