package tictactoe;

import java.util.Scanner;

/********************************
 * 
 * @author Lozovski Maxim
 *
 */

public class TicTacToe {

    public static void main(String[] args) {
    	int game_mode;
    	Scanner in = new Scanner(System.in);
    	
    	System.out.println("Please select a game mode (1 - PvP, 2 - PvE)");
        game_mode = in.nextInt();
        
        // PvP
        while ( game_mode == 1)
        {
        	int player_number = 2;
        	int player_turn;
        	System.out.println("Player #1 - X, Player #2 - O");
        	Board gameboard = new Board();
        	gameboard.printBoard();
        	while (gameboard.declareWinner() == 0)
        	{
        		if (player_number == 1)
        			player_number = 2;
        		else
        			player_number = 1;
        		System.out.printf("Player %d, your turn: ", player_number);
        		player_turn = in.nextInt();
        		while (!gameboard.canMove(player_turn))
        		{
        			System.out.println("Incorrect number! Try another!");
        			player_turn = in.nextInt();
        		}
        		gameboard.makeMove(player_turn, player_number);
        		gameboard.printBoard();     		
        	}
        	if (gameboard.declareWinner() == 3)
        		System.out.println("Draw!");
        	else
        		System.out.printf("The player %d has won!", player_number);
        	System.out.println();
        	System.out.println("Please select a game mode (1 - PvP, 2 - PvE)");
        	game_mode = in.nextInt();
        }
        
        // PvE
        while ( game_mode == 2)
        {
        	int player_number = 2;
        	int player_turn;
        	System.out.println("Player - X, PC - O");
        	Board gameboard = new Board();
        	gameboard.printBoard();
        	while (gameboard.declareWinner() == 0)
        	{
        		if (player_number == 1)
        			player_number = 2;
        		else
        			player_number = 1;
        		if (player_number == 1)
        		{
	        		System.out.println("Player, your turn: ");
	        		player_turn = in.nextInt();
	        		while (!gameboard.canMove(player_turn))
	        		{
	        			System.out.println("Incorrect number! Try another!");
	        			player_turn = in.nextInt();
	        		}
	        		gameboard.makeMove(player_turn, player_number);
        		}
        		else
        		{
        			System.out.println("PC, your turn: ");
        			gameboard.pcTurn();
        		}
        		gameboard.printBoard();
        	}
        	if (gameboard.declareWinner() == 3)
        		System.out.println("Draw!");
        	else if (player_number == 1)
        		System.out.println("The player has won!");
        	else if (player_number == 2)
        		System.out.println("The PC has won!");
        	System.out.println("Please select a game mode (1 - PvP, 2 - PvE)");
        	game_mode = in.nextInt();
        }
        
        in.close();
    }
}
