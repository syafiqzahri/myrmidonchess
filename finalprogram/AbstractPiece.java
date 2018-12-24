import java.util.*;
import java.*;
import java.io.*;

public abstract class AbstractPiece implements Cloneable{
	
	protected String ap_colour;
	protected String pieceName = "";
	
	//Constructor for AbstractPiece, will not be instantiated since this is an abstract class
	AbstractPiece(String color, String pieceName){
		ap_colour = color;
		this.pieceName = pieceName;
	}
	
	/*
	@author: Nur Farisha Shahirah bt Abdul Rahman
	*/
	// Prototype design pattern, abstract method for clone
	public abstract AbstractPiece clone(String ap_color);
	
	//get the colour of the piece
	public String getColour(){
		return ap_colour;
	}
	
	//get the piece name
	public String getPieceName(){
		return pieceName;
	}
	
	/*
	@author Bobby Alistor anak Jotem
	*/
	//get the file name of the icon 
	public String getIconPath() {
		return pieceName + "-" + ap_colour + ".png";
	}
	
	/*
	@author Muhammad Syafiq Bin Zahri
	*/
	//method to transform the pieces after 6 turns of each player
	public void transform(ArrayList<Tile> tiles) {
		for (int i = 0; i < tiles.size(); i++) {
			if (tiles.get(i).getTileName().contains("plus"))
				tiles.get(i).setPiece(new Triangle(tiles.get(i).getPiece().getColour(), "triangle"));
			else if (tiles.get(i).getTileName().contains("triangle"))
				tiles.get(i).setPiece(new Chevron(tiles.get(i).getPiece().getColour(), "chevron"));
			else if (tiles.get(i).getTileName().contains("chevron"))
				tiles.get(i).setPiece(new Plus(tiles.get(i).getPiece().getColour(), "plus"));
		}
	}

	/*
	@author: Muhammad Fahmi Bin Abu Bakar
	*/
	//abstract method to check validation the valid movement of the pieces
	public abstract boolean canMove(int x1, int y1, int x2, int y2, ArrayList<Tile> tiles, int index, int checkTile);
	
}