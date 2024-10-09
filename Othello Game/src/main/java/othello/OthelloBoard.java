package othello;

/**
 * Keep track of all the tokens on the board. This understands some
 * interesting things about an Othello board, what the board looks like at the
 * start of the game, what the players tokens look like ('X' and 'O'), whether
 * given coordinates are on the board, whether either of the players have a move
 * somewhere on the board, what happens when a player makes a move at a specific
 * location (the opposite players tokens are flipped).
 * Othello makes use of the OthelloBoard.
 *
 */
public class OthelloBoard {
	
	public static final char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B';
	private int dim = 8;
	private char[][] board;

	/**
	 * Initializes a new board for a game of Othello
	 *
	 * @param dim the dimension of the board
	 */
	public OthelloBoard(int dim) {
		this.dim = dim;
		board = new char[this.dim][this.dim];
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				this.board[row][col] = EMPTY;
			}
		}
		int mid = this.dim / 2;
		this.board[mid - 1][mid - 1] = this.board[mid][mid] = P1;
		this.board[mid][mid - 1] = this.board[mid - 1][mid] = P2;
	}

	/***
	 * Returns the dimension of the board
	 *
	 * @return the dimension of the board
	 */
	public int getDimension() {
		return this.dim;
	}

	/**
	 * Given a player, returns the other one
	 * 
	 * @param player either P1 or P2
	 * @return P2 or P1, the opposite of player
	 */
	public static char otherPlayer(char player) {
		if (player == EMPTY) return EMPTY;
		return (player == P1) ? P2: P1;
	}

	/**
	 * returns the value of the board at the specified row and col
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return P1,P2 or EMPTY, EMPTY is returned for an invalid (row,col)
	 */
	public char get(int row, int col) {
		if (validCoordinate(row, col)) {
			return this.board[row][col];
		}
        return EMPTY;
    }

	/**
	 * returns true or false based on if the row and col provided exists on the board
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return whether (row,col) is a position on the board. Example: (6,12) is not
	 *         a position on the board.
	 */
	private boolean validCoordinate(int row, int col) {
        return (row <= (this.dim-1) & col <= (this.dim-1)) && (row >= 0 & col >= 0);
    }

	/**
	 * Error handling method. Checks if method parameters are valid.
	 *
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return true is parameters are correct. false if parameters are not correct and
	 * cannot be worked with
	 */
	private boolean validInput(int row, int col, int drow, int dcol) {

		if (drow == 0 && dcol == 0) return false;
		if (!validCoordinate(row,col)) return false;

		return true;
	}

	/**
	 * Check if there is an alternation of P1 next to P2, starting at (row,col) in
	 * direction (drow,dcol). That is, starting at (row,col) and heading in
	 * direction (drow,dcol), you encounter a sequence of at least one P1 followed
	 * by a P2, or at least one P2 followed by a P1. The board is not modified by
	 * this method. Why is this method important? If
	 * alternation(row,col,drow,dcol)==P1, then placing P1 right before (row,col),
	 * assuming that square is EMPTY, is a valid move, resulting in a collection of
	 * P2 being flipped.
	 * 
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1, if there is an alternation P2 ...P2 P1, or P2 if there is an
	 *         alternation P1 ... P1 P2 in direction (dx,dy), EMPTY if there is no
	 *         alternation
	 */
	private char alternation(int row, int col, int drow, int dcol) {


		if (validInput(row,col,drow,dcol)) {
			if (this.board[row][col] == EMPTY) return EMPTY;

			char startPiece = this.board[row][col];
			char otherPiece = otherPlayer(startPiece);

			row += drow;
			col += dcol;

			while (validCoordinate(row, col)) {
				if (this.board[row][col] == otherPiece){ return otherPiece;}
				if (this.board[row][col] == EMPTY){ return EMPTY;}

				row += drow;
				col += dcol;
			}
		}

		return EMPTY;
	}

	/**
	 * flip all other player tokens to player, starting at (row,col) in direction
	 * (drow, dcol). Example: If (drow,dcol)=(0,1) and player==O then XXXO will
	 * result in a flip to OOOO
	 *
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow   the row direction, in {-1,0,1}
	 * @param dcol   the col direction, in {-1,0,1}
	 * @param player Either OthelloBoard.P1 or OthelloBoard.P2, the target token to
	 *               flip to.
	 */
	public void flip(int row, int col, int drow, int dcol, char player) {

		if (validInput(row,col,drow,dcol)) {
			char startPiece = this.board[row][col];
			if (player == startPiece){return;}
			if (!(startPiece == EMPTY)) return;

			int flipped = 0;
			int postFlipRow = row;
			int postFlipCol = col;

			row += drow;
			col += dcol;

			while (validCoordinate(row,col)){
				if (this.board[row][col] == EMPTY) {return;}

				if (this.board[row][col] == player){
					int toFlip = flipped;
					row -= drow;
					col -= dcol;

					while (toFlip >= 0) {
						this.board[row][col] = player;
						toFlip -= 1;
						row -= drow;
						col -= dcol;
					}
					postFlip(postFlipRow,postFlipCol,player);
					return;
				}
				flipped += 1;
				row += drow;
				col += dcol;
			}
			postFlip(row,col,player);
		}
	}

	/**
	 * Ensures that after a move is made all new discs that can be flipped
	 * due to a sideffect are also flipped
	 *
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param player Either OthelloBoard.P1 or OthelloBoard.P2, the target token to
	 * flip to.
	 *
	 */
	private void postFlip(int row, int col, char player) {

		if (!validCoordinate(row, col)) return;
		char startPiece = this.board[row][col];
		if ((player != startPiece) || (startPiece == EMPTY)) return;

		// loop over each possible direction
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {

				if (drow == 0 && dcol ==0) {continue;}

				int flipped = 0;
				int initialRow = row + drow;
				int initialCol = col + dcol;
				boolean flipPossible = false;

				while (validCoordinate(initialRow, initialCol)) {
					if (this.board[initialRow][initialCol] == EMPTY) {
						break;
					}

					if (this.board[initialRow][initialCol] == player) {
						if (flipped > 0) {
							flipPossible = true;
						}
						break;
					}
					flipped += 1;
					initialRow += drow;
					initialCol += dcol;
				}
				if (flipPossible) {
					// we found the piece we wanted so start at the one before
					int toFlip = flipped;
					initialRow -= drow;
					initialCol -= dcol;

					// loop over backwards to flip all encountered pieces
					while(toFlip > 0){
						this.board[initialRow][initialCol] = player;
						toFlip -= 1;
						initialRow -= drow;
						initialCol -= dcol;
					}
				}
			}
		}
	}


	/**
	 * Return which player has a move (row,col) in direction (drow,dcol).
	 * 
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1,P2,EMPTY
	 */
	public char hasMove(int row, int col, int drow, int dcol) {

		if (validInput(row,col,drow,dcol)){
			char startPiece = this.board[row][col];
			if (startPiece != EMPTY){return EMPTY;}

			if (!validCoordinate(row + drow ,col + dcol )){return EMPTY;}

			char opponent = this.board[row + drow][col + dcol];
			if(opponent == EMPTY){ return EMPTY;}

			char lookingFor = otherPlayer(opponent);

			row += drow;
			col += dcol;

			while (validCoordinate(row, col)) {
				if (this.board[row][col] == opponent) {
					row += drow;
					col += dcol;
				} else if (this.board[row][col] == EMPTY) {
					return EMPTY;
				} else if (this.board[row][col] == lookingFor) {
					return lookingFor;
				}
			}
		}
		return EMPTY;
	}

	/**
	 * 
	 * @return whether P1,P2 or BOTH have a move somewhere on the board, EMPTY if
	 *         neither do.
	 */
	public char hasMove() {
		// for loop over every possible row and col and return that way
		char moveAvailable = EMPTY;

		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				for (int drow = -1; drow <= 1; drow++) {
					for (int dcol = -1; dcol <= 1; dcol++) {
						char whosMove = hasMove(row,col,drow,dcol);

						if(whosMove == EMPTY){continue;}
						if(whosMove == P1 && moveAvailable == EMPTY){moveAvailable = P1;}
						if(whosMove == P2 && moveAvailable == EMPTY){moveAvailable = P2;}

						if(whosMove == P1 && moveAvailable == P2){return BOTH;}
						if(whosMove == P2 && moveAvailable == P1){return BOTH;}
					}
				}
			}
		}

		return moveAvailable;
	}

	/**
	 * Make a move for player at position (row,col) according to Othello rules,
	 * making appropriate modifications to the board. Nothing is changed if this is
	 * not a valid move.
	 * 
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param player P1 or P2
	 * @return true if player moved successfully at (row,col), false otherwise
	 */
	public boolean move(int row, int col, char player) {
		// HINT: Use some of the above helper methods to get this methods
		// job done!!
		boolean moved = false;
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				char validMove = hasMove(row,col,drow,dcol);

				if (validMove == player) {
					flip(row,col,drow,dcol,player);
					moved = true;
				}
			}
		}
		return moved;
	}

	/**
	 * returns the amount of tokens on the board for the provided player
	 *
	 * @param player P1 or P2
	 * @return the number of tokens on the board for player
	 */
	public int getCount(char player) {
		int count = 0;
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				if(this.board[row][col] == player){ count += 1;}
			}
		}
		return count;
	}

	/**
	 * Copies the provided board onto the existing one
	 *
	 * @param otherBoard the board needed to be copied
	 */
	public void othelloBoardCopy(OthelloBoard otherBoard) {
		this.dim = otherBoard.getDimension();
		this.board = new char[this.dim][this.dim];

		for (int row = 0; row < otherBoard.board.length; row++) {
			for (int col = 0; col < otherBoard.board[row].length; col++) {
				this.board[row][col] = otherBoard.board[row][col];
			}
		}

	}


	/**
	 * @return a string representation of this, just the play area, with no
	 *         additional information. DO NOT MODIFY THIS!!
	 */
	public String toString() {
		String s = "";
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			s += col + " ";
		}
		s += '\n';

		s += " +";
		for (int col = 0; col < this.dim; col++) {
			s += "-+";
		}
		s += '\n';

		for (int row = 0; row < this.dim; row++) {
			s += row + "|";
			for (int col = 0; col < this.dim; col++) {
				s += this.board[row][col] + "|";
			}
			s += row + "\n";

			s += " +";
			for (int col = 0; col < this.dim; col++) {
				s += "-+";
			}
			s += '\n';
		}
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			s += col + " ";
		}
		s += '\n';
		return s;
	}

	/**
	 * A quick test of OthelloBoard.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		OthelloBoard ob = new OthelloBoard(8);
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));
		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				ob.board[row][col] = P1;
			}
		}
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));

		// Should all be blank
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				if (row == 0 || col == 0) {
					ob.board[row][col] = P2;
				}
			}
		}
		System.out.println(ob.toString());

		// Should all be P2 (O) except drow=0,dcol=0
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		// Can't move to (4,4) since the square is not empty
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));

		ob.board[4][4] = EMPTY;
		ob.board[2][4] = EMPTY;

		System.out.println(ob.toString());

		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("hasMove at (4,4) in above direction =" + ob.hasMove(4, 4, drow, dcol));
			}
		}
		System.out.println("who has a move=" + ob.hasMove());
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));
		System.out.println(ob.toString());

	}
}
