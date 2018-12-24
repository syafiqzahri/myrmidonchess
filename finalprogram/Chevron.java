import java.util.*;
import java.*;

/*
@author: Bobby Alistor anak Jotem
*/

public class Chevron extends AbstractPiece{
	
	//constructor for Chevron, will be instantiated in Board once	
	Chevron(String color, String pieceName){
		super(color,pieceName);
	}
	
	//returns true if Chevron move is valid (L-shape like Knight in normal chess)	
	public boolean canMove(int x1, int y1, int x2, int y2, ArrayList<Tile> tiles, int index, int checkTile){
		int validRow = Math.abs(x1 - x2);
		int validCol = Math.abs(y1 - y2);
		
		if ((validRow == 1) && (validCol == 2))
			return true;
		if ((validRow == 2) && (validCol == 1))
			return true;
		return false;
	}
	
	//uses Prototype design to clone Chevron piece
	public AbstractPiece clone(String ap_color){
		return new Chevron(ap_color, super.getPieceName());
	}
}