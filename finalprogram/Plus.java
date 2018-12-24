import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;

/*
@author- Muhammad Fahmi Bin Abu Bakar
*/
public class Plus extends AbstractPiece{
	
	//costructor
	Plus(String color, String pieceName){
		super(color,pieceName);
	}
	
	//check validation for moving piece
	public boolean canMove(int x1, int y1, int x2, int y2, ArrayList<Tile> tiles, int index, int checkTile){
		int validRow = Math.abs(x1 - x2);
		int validCol = Math.abs(y1 - y2);
		
		if ((validRow == 0) && (validCol == 2)) {
			if (checkTile == -2) {
				if (tiles.get(index + 1).getPiece() != null)
					return false;
			}
			if (checkTile == 2) {
				if (tiles.get(index - 1).getPiece() != null)
					return false;
			}
			
			return true;
		}
		if ((validRow == 2) && (validCol == 0)) {
			if (checkTile == -14) {
				if (tiles.get(index + 7).getPiece() != null)
					return false;
			}
			if (checkTile == 14) {
				if (tiles.get(index - 7).getPiece() != null)
					return false;
			}
			
			return true;
		}
		if (((validRow == 1) && (validCol == 0)) || ((validRow == 0) && (validCol == 1)))
			return true;
			
		return false;
	}
	
	//use Prototype design pattern to clone Plus piece - Farisha
	public AbstractPiece clone(String ap_color){
		return new Plus(ap_color, super.getPieceName());
	}
}