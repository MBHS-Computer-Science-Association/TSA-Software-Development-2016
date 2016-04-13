package org.ecclesia.demo.graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Runs on release of either mouse button
	 * Adds a new point to the list
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		GraphicsFrame.addDrawable(new Point(x,y));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto
	}

}
