import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;

/*
@author: Nur Farisha Shahirah bt Abdul Rahman
*/

public class Sun extends AbstractPiece{
	
	//Constructor for Sun, will be instantiated in Board
	Sun(String color, String pieceName){
		super(color,pieceName);
	}
	
	//use Prototype design pattern to clone a Sun piece
	public AbstractPiece clone(String ap_color){
		return new Sun(ap_color, super.getPieceName());
	}
	
	//return true if Sun move is valid (can move only 1 step in any direction)
	public boolean canMove(int x1, int y1, int x2, int y2, ArrayList<Tile> tiles, int index, int checkTile){
		int validRow = Math.abs(x1 - x2);  //horizontal distance from source to destination 
		int validCol = Math.abs(y1 - y2);  //vertical distance from source to destination
		
		if ((validRow == 1) || (validCol == 1))
			return true;
		else
			return false;
	}
}