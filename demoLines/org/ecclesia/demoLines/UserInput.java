package org.ecclesia.demoLines;
import org.ecclesia.demoLines.GraphicsFrame;
import org.ecclesia.demoLines.UserPoint;
import org.ecclesia.neural.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserInput implements MouseListener {

	
	/**
	 * Runs on release of either mouse button
	 * Adds a new point to the list
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		GraphicsFrame.addDrawable(new UserPoint(x,y));
		LinePredictor.initPoint(x, y);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
