import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Board extends JFrame implements ActionListener {
	private Container c = getContentPane();
	private JPanel p = new JPanel(new GridLayout(6, 7));
	private JButton[][] b = new JButton[6][7];
	private JLabel l = new JLabel("MERAH", JLabel.CENTER);
	private ArrayList<Tile> tiles = new ArrayList<>();
	private boolean clicked = false;
	private boolean redTurn = true;
	private int xred1 = 0;
	private int yred1 = 0;
	private int xred2 = 0;
	private int yred2 = 0;
	private int xblue1 = 0;
	private int yblue1 = 0;
	private int xblue2 = 0;
	private int yblue2 = 0;
	private int current = 0;
	private int current2 = 0;
	private int move = 0;
	private static Board board;
	
	//constructor
	private Board() {
		super("Myrmidon Chess");
		c.setLayout(new BorderLayout());
		c.add(p, BorderLayout.CENTER);
		c.add(l, BorderLayout.SOUTH);
		l.setForeground(Color.white);
		l.setBackground(Color.red);
		l.setOpaque(true);
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				b[i][j] = new JButton();
				p.add(b[i][j]);
				if ((((i == 0) || (i == 2) || (i == 4)) && ((j == 0) || (j == 2) || (j == 4) || (j == 6)))
					|| (((i == 1) || (i == 3) || (i == 5)) && ((j == 1) || (j == 3) || (j == 5))))
					b[i][j].setBackground(Color.white);
				else
					b[i][j].setBackground(Color.black);
				b[i][j].addActionListener(this);
			}
		}
		
		JMenuBar mb = new JMenuBar();
		JMenu m = new JMenu("File");
		mb.add(m);
		JMenuItem mi1 = new JMenuItem("New");
		m.add(mi1);
		mi1.addActionListener(new ActionListener() {
			//performs new game by calling clearBoard
			public void actionPerformed(ActionEvent e) {
				clearBoard(tiles);
			}
		});
		JMenuItem mi2 = new JMenuItem("Save");
		m.add(mi2);
		mi2.addActionListener(new ActionListener() {
			//performs save function by writing strings to a textfile - Farisha
			public void actionPerformed(ActionEvent e) {
				try {
					PrintWriter save = new PrintWriter(new BufferedWriter(new FileWriter("savefile.txt")));
					for (int i = 0; i < tiles.size(); i++)
						save.println(tiles.get(i).getTileName());
					save.println(move+"");
					save.println(clicked+"");
					save.println(redTurn+"");
					save.close();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		JMenuItem mi3 = new JMenuItem("Load");
		m.add(mi3);
		mi3.addActionListener (new ActionListener() {
			//performs load function by reading every line in the textfile - Farisha, Bobby
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner load = new Scanner(new File("savefile.txt"));
					String tileName;
					int i = 0;
					while (load.hasNext() && (i < 42)) {
						tiles.get(i).removePiece();
						tiles.get(i).setTileName(tiles.get(i).getTileName().substring(0, 2));
						tileName = load.next();
						if (tileName.contains("blueplus"))
							tiles.get(i).setPiece(new Plus("blue", "plus"));
						else if (tileName.contains("bluetriangle"))
							tiles.get(i).setPiece(new Triangle("blue", "triangle"));
						else if (tileName.contains("bluechevron"))
							tiles.get(i).setPiece(new Chevron("blue", "chevron"));
						else if (tileName.contains("bluesun"))
							tiles.get(i).setPiece(new Sun("blue", "sun"));
						else if (tileName.contains("redplus"))
							tiles.get(i).setPiece(new Plus("red", "plus"));
						else if (tileName.contains("redtriangle"))
							tiles.get(i).setPiece(new Triangle("red", "triangle"));
						else if (tileName.contains("redchevron"))
							tiles.get(i).setPiece(new Chevron("red", "chevron"));
						else if (tileName.contains("redsun"))
							tiles.get(i).setPiece(new Sun("red", "sun"));
						i++;
					}
					move = Integer.parseInt(load.next());
					clicked = Boolean.parseBoolean(load.next());
					redTurn = Boolean.parseBoolean(load.next());
					if (redTurn) {
						l.setText("MERAH");
						l.setBackground(Color.red);
					}
					else {
						l.setText("BIRU");
						l.setBackground(Color.blue);
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		setJMenuBar(mb);
		
		setSize(700, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// singleton design patern - Syafiq 
	public static synchronized Board getInstance() {
		if (board == null)
			board = new Board();
		return board;
	}
	
	//add tile to arraylist
	public void addTile(Tile tile) {
		tiles.add(tile);
	}
	
	//get tile from arraylist
	public Tile getTile(int index) {
		return tiles.get(index);
	}
	
	//prototype design pattern - Farisha
	//initialize pieces to tiles  
	public void initialize() {
		getTile(0).setPiece(new Plus("blue", "plus"));
		getTile(1).setPiece(new Triangle("blue", "triangle"));
		getTile(2).setPiece(new Chevron("blue", "chevron"));
		getTile(3).setPiece(new Sun("blue", "sun"));
		getTile(4).setPiece(getTile(2).getPiece().clone("blue"));
		getTile(5).setPiece(getTile(1).getPiece().clone("blue"));
		getTile(6).setPiece(getTile(0).getPiece().clone("blue"));
		
		getTile(35).setPiece(getTile(0).getPiece().clone("red"));
		getTile(36).setPiece(getTile(1).getPiece().clone("red"));
		getTile(37).setPiece(getTile(2).getPiece().clone("red"));
		getTile(38).setPiece(getTile(3).getPiece().clone("red"));
		getTile(39).setPiece(getTile(2).getPiece().clone("red"));
		getTile(40).setPiece(getTile(1).getPiece().clone("red"));
		getTile(41).setPiece(getTile(0).getPiece().clone("red"));
	}
	
	//when game starts
	public void newGame() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++)
				addTile(new Tile(i+""+j, b[i][j]));
		}
		initialize();
	}
	
	//when players restart a new game - Fahmi 
	public void clearBoard(ArrayList<Tile> tiles) {
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).removePiece();
			tiles.get(i).setTileName(tiles.get(i).getTileName().substring(0, 2));
		}
		initialize();
		move = 0;
		clicked = false;
		redTurn = true;
		l.setText("MERAH");
		l.setBackground(Color.red);
	}
	
	//actionperforms on buttons - Syafiq, bobby, fahmi
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < tiles.size(); i++) {
			if ((e.getSource() == tiles.get(i).getButton()) && !(tiles.get(i).isEmpty(tiles, i)) && (tiles.get(i).getPiece().getColour().equals("red")) && !clicked && redTurn) {
				xred1 = Character.getNumericValue(tiles.get(i).getTileName().charAt(0));
				yred1 = Character.getNumericValue(tiles.get(i).getTileName().charAt(1));
				tiles.get(i).getButton().setBackground(Color.yellow);
				//System.out.println(tiles.get(i).getTileName());
				current = i;
				clicked = true;
			}
			else if ((e.getSource() == tiles.get(i).getButton()) && (!(tiles.get(i).isEmpty(tiles, i)) && (tiles.get(i).getPiece().getColour().equals("blue")) || ((tiles.get(i).isEmpty(tiles, i)))) && clicked && redTurn) {
				xred2 = Character.getNumericValue(tiles.get(i).getTileName().charAt(0));
				yred2 = Character.getNumericValue(tiles.get(i).getTileName().charAt(1));
				int checkTile = i - current;
				if (tiles.get(current).getPiece().canMove(xred1, yred1, xred2, yred2, tiles, i, checkTile)) {
					if (current % 2 == 0)
						tiles.get(current).getButton().setBackground(Color.white);
					else
						tiles.get(current).getButton().setBackground(Color.black);
					String oldPiece = "";
					if (tiles.get(i).getTileName().contains("sun"))
						oldPiece = tiles.get(i).getPiece().getPieceName();
					AbstractPiece newPiece = tiles.get(current).removePiece(xred1, yred1);
					tiles.get(i).setPiece(newPiece);
					System.out.println(tiles.get(i).getTileName());
					if (oldPiece.equals("sun")) {
						int choice = JOptionPane.showOptionDialog(this, "Merah menang!!! Do you want to start a new game?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (choice == 0) {
							clearBoard(tiles);
						}
						else
							System.exit(0);
					}
					else {
						move++;
						if (move == 6) {
							tiles.get(i).getPiece().transform(tiles);
							move = 0;
						}
						clicked = false;
						redTurn = false;
						l.setText("BIRU");
						l.setBackground(Color.blue);
					}
				}
			}
			else if ((e.getSource() == tiles.get(i).getButton()) && !(tiles.get(i).isEmpty(tiles, i)) && (tiles.get(i).getPiece().getColour().equals("blue")) && !clicked && !redTurn) {
				xblue1 = Character.getNumericValue(tiles.get(i).getTileName().charAt(0));
				yblue1 = Character.getNumericValue(tiles.get(i).getTileName().charAt(1));
				tiles.get(i).getButton().setBackground(Color.yellow);
				//System.out.println(tiles.get(i).getTileName());
				current2 = i;
				clicked = true;
			}
			else if ((e.getSource() == tiles.get(i).getButton()) && (!(tiles.get(i).isEmpty(tiles, i)) && (tiles.get(i).getPiece().getColour().equals("red")) || ((tiles.get(i).isEmpty(tiles, i)))) && clicked && !redTurn) {
				xblue2 = Character.getNumericValue(tiles.get(i).getTileName().charAt(0));
				yblue2 = Character.getNumericValue(tiles.get(i).getTileName().charAt(1));
				int checkTile = i - current2;
				if (tiles.get(current2).getPiece().canMove(xblue1, yblue1, xblue2, yblue2, tiles, i, checkTile)) {
					if (current2 % 2 == 0)
						tiles.get(current2).getButton().setBackground(Color.white);
					else
						tiles.get(current2).getButton().setBackground(Color.black);
					String oldPiece = "";
					if (tiles.get(i).getTileName().contains("sun"))
						oldPiece = tiles.get(i).getPiece().getPieceName();
					AbstractPiece newPiece = tiles.get(current2).removePiece(xblue1, yblue1);
					tiles.get(i).setPiece(newPiece);
					System.out.println(tiles.get(i).getTileName());
					if (oldPiece.equals("sun")) {
						int choice = JOptionPane.showOptionDialog(this, "Biru menang!!! Do you want to start a new game?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (choice == 0) {
							clearBoard(tiles);
						}
						else
							System.exit(0);
					}
					else {
						move++;
						if (move == 6) {
							tiles.get(i).getPiece().transform(tiles);
							move = 0;
						}
					}
					clicked = false;
					redTurn = true;
					l.setText("MERAH");
					l.setBackground(Color.red);
				}
			}
		}
	}
}