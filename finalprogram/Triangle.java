import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;

//Syafiq
public class Triangle extends AbstractPiece{
	
	Triangle(String color, String pieceName){
		super(color,pieceName);
	}
	
	//check validation for moving piece
	public boolean canMove(int x1, int y1, int x2, int y2, ArrayList<Tile> tiles, int index, int checkTile){
		int validRow = Math.abs(x1 - x2);
		int validCol = Math.abs(y1 - y2);
		
		if ((validRow == 2) && (validCol == 2)) {
			if (checkTile == -12) {
				if (tiles.get(index + 6).getPiece() != null)
					return false;
			}
			if (checkTile == -16) {
				if (tiles.get(index + 8).getPiece() != null)
					return false;
			}
			if (checkTile == 12) {
				if (tiles.get(index - 6).getPiece() != null)
					return false;
			}
			if (checkTile == 16) {
				if (tiles.get(index - 8).getPiece() != null)
					return false;
			}
			
			return true;
		}
		if ((validRow == 1) && (validCol == 1))
			return true;
		
		return false;
	}
	
	//prototype design pattern - Farisha
	public AbstractPiece clone(String ap_color){
		return new Triangle(ap_color, super.getPieceName());
	}
}