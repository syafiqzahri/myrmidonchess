import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Tile {
	private String tileName;
	private JButton b;
	private AbstractPiece piece;
	
	//constructor for Tile
	// sets tileName & assign JButton to every new Tile
	public Tile(String tileName, JButton b) {
		this.tileName = tileName;
		this.b = b;
	}
	
	//set tile name in Tile class according to parameter given
	public void setTileName(String tileName) {
		this.tileName = tileName;
	}
	
	//get tile name from Tile class
	public String getTileName() {
		return tileName;
	}
	
	//to access button of specific tile
	public JButton getButton() {
		return b;
	}
	
	//set piece to each tile
	//set the icon for the JButton according to pieceName - Farisha
	public void setPiece(AbstractPiece piece) {
		this.piece = piece;
		b.setIcon(new ImageIcon(piece.getIconPath()));
		if (tileName.length() == 2) {
			tileName = tileName + piece.getColour() + piece.getPieceName();
		}	
		else {
			tileName = tileName.substring(0, 2);
			tileName = tileName + piece.getColour() + piece.getPieceName();
		}
	}
	
	//get piece from Tile class
	public AbstractPiece getPiece() {
		return piece;
	}
	
	
	//called when piece is captured
	//remove captured piece from destination tile, sets icon of Jbutton to null - Bobby
	public AbstractPiece removePiece(int x, int y) {
		AbstractPiece temp = piece;
		tileName = x + "" + y;
		b.setIcon(null);
		piece = null;
		return temp;
	}
	
	//called to clear board & when loading a file - Fahmi
	public void removePiece() {
		b.setIcon(null);
		piece = null;
	}
	
	//returns true if tile is empty (when length of tilename of 2) - Syafiq
	public boolean isEmpty(ArrayList<Tile> tiles, int index) {
		boolean empty = false;
		if (tiles.get(index).getTileName().length() == 2)
			empty = true;
		return empty;
	}
}