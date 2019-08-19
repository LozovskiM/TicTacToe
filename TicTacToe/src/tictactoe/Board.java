package tictactoe;

public class Board {

    private int[][] gameboard; 
    public int pcMoveField;
    public int[] weights =new int[]{3,2,3,2,4,2,3,2,3};
    static int board_size = 3;
    
    public Board() {
        gameboard = new int[board_size][board_size];
    }
    
    private int[] determineCoordinates(int turn) {
    	int [] coordinates = new int[2];
    	coordinates[0] = (turn % board_size == 0) ? turn / board_size - 1 : turn / board_size;
    	coordinates[1] = (turn % board_size == 0) ? board_size - 1  : turn % board_size -1;
    	return coordinates;
    }
    
    public boolean makeMove(int turn, int n) {
    	int [] coordinates = determineCoordinates(turn);
        if (gameboard[coordinates[0]][coordinates[1]] == 0) {
            gameboard[coordinates[0]][coordinates[1]] = n;
            return true;
        }
        return false;
    }

    public void printBoard() {
    	System.out.println();
        for (int x = 0; x < board_size; x++) {
            for (int y = 0; y < board_size; y++) {
                if (gameboard[x][y] == 0) {
                    System.out.print(" _");
                } else if (gameboard[x][y] == 1) {
                    System.out.print(" X");
                } else {
                    System.out.print(" O");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int declareWinner() {
        int winner = 0;
        if (diagonalWinner() != 0)
        {
        	winner = diagonalWinner();
        }
        else if (rowWinner() != 0)
        	winner = rowWinner();
        else if (columnWinner() != 0 )
        	winner = columnWinner();
        for (int x = 0; x < board_size; x++)
        	for (int y = 0; y < board_size; y++)
        	{
        		if (gameboard[x][y] == 0)
        			 return winner;
        	}
        winner = 3;
        return winner;
    }
    
    private int diagonalWinner(){
    	for ( int x = 1; x < board_size; x++)
    	{
    		if (gameboard[x][x] != gameboard[0][0])
    			x = board_size;
    		if (x == board_size-1)
    		{
    			return gameboard[0][0];
    		}
    	}
    	for ( int x = 1; x < board_size; x++)
    	{
    		if (gameboard[x][board_size-x-1] != gameboard[0][board_size-1])
    			x = board_size;
    		if (x == board_size-1)
    		{
    			return gameboard[0][board_size-1];
    		}
    	}
    	return 0;
    }
    
    private int rowWinner(){
    	for (int x = 0; x < board_size; x++) {
            for (int y = 1; y < board_size; y++) {
                if (gameboard[x][y] != gameboard[x][0])
                	break;
                if (y == board_size-1)
                	return gameboard[x][0];
            }
        }
    	return 0;
    }
      
    private int columnWinner(){
    	for (int y = 0; y < board_size; y++) {
            for (int x = 1; x < board_size; x++) {
                if (gameboard[x][y] != gameboard[0][y])
                	break;
                if (x == board_size-1)
                	return gameboard[0][y];
            }
        }
    	return 0;
    }
    
    public boolean canMove(int turn) {
    	int [] coordinates = determineCoordinates(turn);
        return turn > 0 && turn <= board_size*board_size && gameboard[coordinates[0]][coordinates[1]] == 0;
    }
    
    public int pcMove(){
		int cell=0;
		int max=Integer.MIN_VALUE;
		
		for(int i=0;i<weights.length;i++)
			if(weights[i]>max)
			{
				max=weights[i];
		
				cell=i+1+1;
			
			}
		
		
		int firstRandom = 3*((int)(Math.random()*2)+1);
		firstRandom++;
		int [] coordinates1 = determineCoordinates(firstRandom);
		int secondRandom = 3+2*(int)(Math.pow(-1, (int)(Math.random()*2)));
		secondRandom++;
		int [] coordinates2 = determineCoordinates(secondRandom);
		
		if(gameboard[1][1] == 0) 
		{
			cell=5;
		}		
		else
		if ((gameboard[0][0] == 1 || gameboard[1][2] == 1) && gameboard[0][2] == 0) {
			cell=3;
		} else if ((gameboard[0][2] == 1 || gameboard[2][1] == 1) && gameboard[2][2] == 0) {
			cell=9;
		} else if ((gameboard[2][0] == 1 || gameboard[0][1] == 1) && gameboard[0][0] == 0) {
			cell=1;
		} else if ((gameboard[0][0] == 1 || gameboard[2][2] == 1) && gameboard[coordinates1[0]][coordinates1[1]] == 0) {
			cell=firstRandom ;
		}else if ((gameboard[2][1] == 1 || gameboard[0][2] == 1) && gameboard[coordinates2[0]][coordinates2[1]] == 0) {
			cell=secondRandom;
		}
		
		return cell;
	}
    
	public boolean pcTurn() {
		
//		 Blocking 1 for win on sides on the middle
//		// Blocking 1 for win on Middle
		if (gameboard[0][0] == 1 && gameboard[0][2] == 1 && gameboard[0][1] == 0) {
			gameboard[0][1] = 2;
			return true;
		} else

		if (gameboard[0][0] == 1 && gameboard[2][0] == 1 && gameboard[1][0] == 0) {
			gameboard[1][0] = 2;
			return true;
		} else

		if (gameboard[2][0] == 1 && gameboard[2][2] == 1 && gameboard[2][1] == 0) {
			gameboard[2][1] = 2;
			return true;
		} else

		if (gameboard[0][2] == 1 && gameboard[2][2] == 1 && gameboard[1][2] == 0) {
			gameboard[1][2] = 2;
			return true;
		} else

		// Blocking 1 for win on Middle
		if (gameboard[1][0] == 1 && gameboard[1][1] == 1 && gameboard[1][2] == 0) {
			gameboard[1][2] = 2;
			return true;
		} else

		if (gameboard[1][2] == 1 && gameboard[1][1] == 1 && gameboard[1][0] == 0) {
			gameboard[1][0] = 2;
			return true;
		} else

		if (gameboard[2][1] == 1 && gameboard[1][1] == 1 && gameboard[0][1] == 0) {
			gameboard[0][1] = 2;
			return true;
		} else

		if (gameboard[0][1] == 1 && gameboard[1][1] == 1 && gameboard[2][1] == 0) {
			gameboard[2][1] = 2;
			return true;
		} else

		// Blocking 1 WIn on Sides
		if (gameboard[0][0] == 1 && gameboard[0][1] == 1 && gameboard[0][2] == 0) {
			gameboard[0][2] = 2;
			return true;
		} else if (gameboard[0][0] == 1 && gameboard[1][0] == 1 && gameboard[2][0] == 0) {
			gameboard[2][0] = 2;
			return true;
		}

		else
		// Win for PC Sides
		if (gameboard[0][0] == 2 && gameboard[0][1] == 2 && gameboard[0][2] == 0) {
			gameboard[0][2] = 2;
			return true;
		} else if (gameboard[0][0] == 2 && gameboard[1][0] == 2) {
			gameboard[2][0] = 2;
			return true;
		}

		else

		if (gameboard[2][0] == 2 && gameboard[2][1] == 2 && gameboard[2][2] == 0) {
			gameboard[2][2] = 2;
			return true;
		}

		else

		if (gameboard[2][2] == 2 && gameboard[1][2] == 2 && gameboard[0][2] == 0) {
			gameboard[0][2] = 2;
			return true;
		}

		// Win for PC Middle
		else if (gameboard[1][0] == 2 && gameboard[1][1] == 2 && gameboard[1][2] == 0) {
			gameboard[1][2] = 2;
			return true;
		} else

		if (gameboard[1][2] == 2 && gameboard[1][1] == 2 && gameboard[1][0] == 0) {
			gameboard[1][0] = 2;
			return true;
		} else

		if (gameboard[2][1] == 2 && gameboard[1][1] == 2 && gameboard[0][1] == 0) {
			gameboard[0][1] = 2;
			return true;
		} else

		if (gameboard[0][1] == 2 && gameboard[1][1] == 2 && gameboard[2][1] == 0) {
			gameboard[2][1] = 2;
			return true;
		} else

		// Middle
		if (gameboard[1][1] == 0) {
			gameboard[1][1] = 2;
			return true;
		} else

		if (gameboard[2][0] == 1 && gameboard[2][1] == 1 && gameboard[2][2] == 0) {
			gameboard[2][2] = 2;
			return true;
		}

		else

		if (gameboard[2][2] == 1 && gameboard[1][2] == 1 && gameboard[0][2] == 0) {
			gameboard[0][2] = 2;
			return true;
		}

		else

		if (gameboard[1][1] == 0) {
			gameboard[1][1] = 2;
			return true;
		} else
		// Corners. Because why not?
		if (gameboard[0][0] == 0) {
			gameboard[0][0] = 2;
			return true;
		} else if (gameboard[0][2] == 0) {
			gameboard[0][2] = 2;
			return true;
		} else if (gameboard[2][0] == 0) {
			gameboard[2][0] = 2;
			return true;
		} else if (gameboard[2][2] == 0) {
			gameboard[2][2] = 2;
			return true;
		} else if

		// The Last empty fields
		(gameboard[0][0] == (0)) {
			gameboard[0][0] = 2;
			return true;
		} else if (gameboard[0][1] == (0)) {
			gameboard[0][1] = 2;
			return true;
		} else if (gameboard[0][2] == (0)) {
			gameboard[0][2] = 2;
			return true;
		} else if (gameboard[1][0] == (0)) {
			gameboard[1][0] = 2;
			return true;
		} else if (gameboard[1][1] == (0)) {
			gameboard[1][1] = 2;
			return true;
		} else if (gameboard[1][2] == (0)) {
			gameboard[1][2] = 2;
			return true;
		} else if (gameboard[2][0] == (0)) {
			gameboard[2][0] = 2;
			return true;
		} else if (gameboard[2][1] == (0)) {
			gameboard[2][1] = 2;
			return true;
		} else if (gameboard[2][2] == (0)) {
			gameboard[2][2] = 2;
			return true;
		}     
		
		
		pcMoveField = pcMove();
		int [] coordinates = determineCoordinates(pcMoveField);
        if (gameboard[coordinates[0]][coordinates[1]] == 0) {
            gameboard[coordinates[0]][coordinates[1]] = 2;
            return true;
        }

        return false;
	}
}
