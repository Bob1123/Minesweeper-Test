import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.GenericArrayType;
import java.util.Random;

public class GameBoard extends GameObject {

	// ------------------------------------------------------------------------------------- Properties
	
	public static final int ROWS = 25;
	public static final int COLUMNS = 40;
	public static final int MINES = 100;
	
	private GameBox[][] board = new GameBox[ROWS][COLUMNS];
	
	// ------------------------------------------------------------------------------------- Constructors
	
	public GameBoard(int x, int y, int width, int height, GameBox[][] board) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setBoard(board);
	}
	
	public GameBoard(int x, int y, int width, int height) {
		this(x, y, width, height, randomBoard(x, y, width, height));
	}
	
	// ------------------------------------------------------------------------------------- Methods
	
	@Override
	public void draw(Graphics g) {
		for(GameBox[] row : getBoard()) {
			for(GameBox b : row) {
				b.draw(g);
			}
		}
	}
	
	public static GameBox[][] randomBoard(int x, int y, int width, int height) {
		GameBox[][] newBoard = new GameBox[ROWS][COLUMNS];
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				newBoard[row][col] = new GameBox(x+col*width/COLUMNS, y+row*height/ROWS, width/COLUMNS, height/ROWS, BoxType.ZERO);
			}
		}
		Random rng = new Random();
		for(int i = 0; i < MINES; i++) {
			int row = rng.nextInt(ROWS);
			int col = rng.nextInt(COLUMNS);
			if(!(newBoard[row][col].getType() == BoxType.MINE) && !(row>=ROWS/2-2 && row<=ROWS/2+2 && col>=COLUMNS/2-2 && col<=COLUMNS/2+2)) {
				newBoard[rng.nextInt(ROWS)][rng.nextInt(COLUMNS)].setType(BoxType.MINE);
			} else {
				i--;
			}
		}
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				if(!(newBoard[row][col].getType() == BoxType.MINE)) {
					newBoard[row][col].setType(BoxType.numToType(determineNumber(row, col, newBoard)));
				}
			}
		}
		return newBoard;
	}
	
	public static int determineNumber(int row, int col, GameBox[][] board) {
		int num = 0;
		for(int i = -1; i<=1; i++) {
			for(int j = -1; j<=1; j++) {
				if(!(i==0 && j==0)) {
					if(inBounds(row+i, col+j)){if(board[row+i][col+j].getType() == BoxType.MINE){num++;}}					
				}
			}
		}
		return num;
	}
	
	public static boolean inBounds(int row, int col) {
		return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
	}
	
	public Point getPosition(Point p) {
		Point answer = new Point();
		int cellRow = (p.y-getY() < 0) ? -1 : (p.y-getY())*ROWS/getHeight();
		int cellColumn = (p.x-getX() < 0) ? -1 : (p.x-getX())*COLUMNS/getWidth();
		answer.x = (cellRow < 0 || cellRow >= ROWS) ? -1 : cellRow;
		answer.y = (cellColumn < 0 || cellColumn >= COLUMNS) ? -1 : cellColumn;
		return answer;
	}
	
	public boolean zero(Point p) {
		boolean changed = false;
		int row = p.x;
		int col = p.y;
		boolean checked = board[row][col].getStatus() == StatusType.COVERED;
		System.out.println("UNCOVER");
		board[row][col].setStatus(StatusType.UNCOVERED);
		if(board[row][col].getType() == BoxType.ZERO && checked) {
			changed = true;
			for(int i = -1; i<=1; i++) {
				for(int j = -1; j<=1; j++) {
					if(inBounds(row+i, col+j)){zero(new Point(row+i, col+j));}
				}
			}
		}
		return changed;
	}
	
	public boolean one() {
		boolean changed = false;
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				if(board[row][col].getStatus() == StatusType.UNCOVERED) {
					int covered = 0;
					for(int i = -1; i<=1; i++) {
						for(int j = -1; j<=1; j++) {
							if(!(i==0 && j==0)) {
								if(inBounds(row+i, col+j)){if(!(board[row+i][col+j].getStatus() == StatusType.UNCOVERED)){covered++;}}					
							}
						}
					}
					if(board[row][col].getType() == BoxType.numToType(covered)) {
						for(int i = -1; i<=1; i++) {
							for(int j = -1; j<=1; j++) {
								if(!(i==0 && j==0)) {
									if(inBounds(row+i, col+j)){if(board[row+i][col+j].getStatus() == StatusType.COVERED){board[row+i][col+j].setStatus(StatusType.FLAGGED); changed = true;}}					
								}
							}
						}
					}
				}
			}
		}
		return changed;
	}
	
	public boolean two() {
		boolean changed = false;
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				if(board[row][col].getStatus() == StatusType.UNCOVERED) {
					int flagged = 0;
					for(int i = -1; i<=1; i++) {
						for(int j = -1; j<=1; j++) {
							if(!(i==0 && j==0)) {
								if(inBounds(row+i, col+j)){if(board[row+i][col+j].getStatus() == StatusType.FLAGGED){flagged++;}}					
							}
						}
					}
					if(board[row][col].getType() == BoxType.numToType(flagged)) {
						for(int i = -1; i<=1; i++) {
							for(int j = -1; j<=1; j++) {
								if(!(i==0 && j==0)) {
									if(inBounds(row+i, col+j)){if(board[row+i][col+j].getStatus() == StatusType.COVERED){board[row+i][col+j].setStatus(StatusType.UNCOVERED); changed = true;}}					
								}
							}
						}
					}
				}
			}
		}
		return changed;
	}
	
	public boolean three() {
		return true;
	}
	
	public int numFlagged() {
		int counter = 0;
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				if(board[row][col].getStatus() == StatusType.FLAGGED) {
					counter++;
				}
			}
		}
		return counter;
	}
	
	// ------------------------------------------------------------------------------------- Getters and Setters

	public GameBox[][] getBoard() {
		return board;
	}

	public void setBoard(GameBox[][] board) {
		this.board = board;
	}
	
}
