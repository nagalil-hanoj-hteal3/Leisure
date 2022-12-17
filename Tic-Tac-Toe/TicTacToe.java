import java.util.ArrayList; //for position optimization
import java.util.Arrays; //intialization of the positions found in tic tac toe
import java.util.List; //for verifying win in tic tac toe
import java.util.Random; //for computer
import java.util.Scanner;//for scanner optimization


public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>(); 
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);//obtain inputs to put in game board
        
        char[][] boardGame = {{' ','_','_','_','_','_',' ','_','_','_','_','_',' ','_','_','_','_','_',' '},
                              {'|', ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' },
                              {'|', ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' },
                              {'|', '_','_','_','_','_', '|' , '_','_','_','_','_', '|' , '_','_','_','_','_', '|' },
                              {'|', ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' },
                              {'|', ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' },
                              {'|', '_','_','_','_','_', '|' , '_','_','_','_','_', '|' , '_','_','_','_','_', '|' },
                              {'|', ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' },
                              {'|', ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' , ' ', ' ', ' ', ' ', ' ', '|' },
                              {'|', '_','_','_','_','_', '|' , '_','_','_','_','_', '|' , '_','_','_','_','_', '|' }};
       
        /*Game Board to play tic tac toe
         * Dimensions for each cell is 5 x 4 
         * Top layer and bottom layer both used as underscore
         * Use the rest for pipe and extra space to fill the board
         * Set as a double array to initalize the column and row of the game board
         */
        printBoardGame(boardGame);//needed to output the game board

        while(true) //when the game is still active
        {
            System.out.println("Choose a number 1-9 to enter onto the board");//output statement for player to insert their choice in tic tac toe
            int player_position = scan.nextInt();//set position as an int value
            while(playerPositions.contains(player_position) || cpuPositions.contains(playerPositions) || cpuPositions.contains(player_position)){ //to check that the string is being entered on the correct position
                System.out.println("Position taken! Please choose a different position"); //warn the user they cant enter in a taken position
                player_position = scan.nextInt();//for user to enter another position that is empty
                /* Prevent user from printing on top of CPU */
                while(playerPositions.contains(player_position))
                {
                    System.out.println("Position taken! Please select a different position:");
                    player_position = scan.nextInt();
                }
            }
        //System.out.println(player_position);//output the position of where it is placed

            updateBoard(boardGame, player_position, "Player 1"); // used for player turn
           
            String result = verifyWinner();//to check who won
            if(result.length() > 0) //check for the player
                {
                    printBoardGame(boardGame);//print the board once finished
                    System.out.println(result);
                    break;
                }
            
            //for CPU control
            Random r = new Random();
            int CPU_position = r.nextInt(9) + 1; //get the position from 1 to 9
            while(playerPositions.contains(CPU_position) || cpuPositions.contains(CPU_position)) //similar to the previous loop, but for CPU
            {
                CPU_position = r.nextInt(9) + 1; //force the cpu to pick another position to enter
            }

            updateBoard(boardGame, CPU_position, "CPU");// used for CPU turn

            printBoardGame(boardGame);//needed once more to see the newly added position that has been updated
            
            result = verifyWinner();//to check who won
                if(result.length() > 0) //check for the cpu
                {
                    printBoardGame(boardGame); //print the board once finished
                    System.out.println(result);
                    break;
                }
            
            //System.out.println(result);
            //scan.close();
        }
    }

    public static void printBoardGame(char[][] board)
    {
        for(char[] row : board) {//for every row that would be in the board game
            for(char column : row) {//loop through each row for every column to output
                System.out.print(column);//to print the columns that will be outputted on the board
                //do not do println(column) as it will make a new line
            }
            System.out.println();//print the rows that will be in the board game
        }
    }

    public static void updateBoard(char[][] boardGame, int position, String user)
    {
        char symbol = ' ';//set symbol to default value or no value

        if(user.equals("Player 1")) {
            symbol = 'X'; //assigning the player to use string X
            playerPositions.add(position);//insert the position for the player
            } //for the user to interact
        else if(user.equals("CPU")) {
            symbol = 'O'; //assigning the CPU to use string O
            cpuPositions.add(position);//insert the position for the cpu
            } //for the computer to interact

        switch(position) //test cases to determine where the X will be placed
        {
            /* For each case, it will be including a number to be used as the position
             * When that case is called, it will set X onto the game board based on the
             * double array where [2][3] reads the [row][column] or [x][y]
             */
            case 1://set X to the first cell of board (top left)
                boardGame[2][3] = symbol;
                break;
            case 2://set X to the second cell of board (top center)
                boardGame[2][9] = symbol;
                break;
            case 3://set X to the third cell of board (top right)
                boardGame[2][15] = symbol;
                break;
            case 4://set X tp the fourth cell of board (middle left)
                boardGame[5][3] = symbol;
                break;
            case 5://set X to the fifth cell of board (center)
                boardGame[5][9] = symbol;
                break;
            case 6://set X to the sixth cell of board (middle right)
                boardGame[5][15] = symbol;
                break;
            case 7://set X to the seventh cell of board (bottom left)
                boardGame[8][3] = symbol;
                break;
            case 8://set X to the eighth cell of board (bottom middle)
                boardGame[8][9] = symbol;
                break;
            case 9://set X to the ninth cell of board (bottom right)
                boardGame[8][15] = symbol;
                break;
        }
    }

    public static String verifyWinner()
    {
        /* Used to check the rows within the board when a user or computer
         * wins a game of tic tac toe
         * Ex: _____ _____ _____        _____ _____ _____        _____ _____ _____
         *    |     |     |     |      |     |     |     |      |     |     |     |
         *    |  X  |  X  |  X  |      |     |     |     |      |     |     |     |
         *    |_____|_____|_____|      |_____|_____|_____|      |_____|_____|_____|
         *    |     |     |     |      |     |     |     |      |     |     |     |
         *    |     |     |     |  OR  |  X  |  X  |  X  |  OR  |     |     |     |
         *    |_____|_____|_____|      |_____|_____|_____|      |_____|_____|_____|
         *    |     |     |     |      |     |     |     |      |     |     |     |
         *    |     |     |     |      |     |     |     |      |  X  |  X  |  X  |
         *    |_____|_____|_____|      |_____|_____|_____|      |_____|_____|_____|
         */
        List topRow = Arrays.asList(1, 2, 3); //top row that has X or O three times in a row
        List middleRow = Arrays.asList(4, 5, 6); //middle row that has X or O three times in a row
        List lowRow = Arrays.asList(7, 8, 9); //bottom row that has X or O three times in a row
        /* Used to check the columns within the board when a user or computer
         * wins a game of tic tac toe
         * Ex: _____ _____ _____        _____ _____ _____         _____ _____ _____
         *    |     |     |     |      |     |     |     |       |     |     |     |
         *    |  X  |     |     |      |     |  X  |     |       |     |     |  X  |
         *    |_____|_____|_____|      |_____|_____|_____|       |_____|_____|_____|     
         *    |     |     |     |      |     |     |     |       |     |     |     |
         *    |  X  |     |     |  OR  |     |  X  |     |  OR   |     |     |  X  |
         *    |_____|_____|_____|      |_____|_____|_____|       |_____|_____|_____|
         *    |     |     |     |      |     |     |     |       |     |     |     |
         *    |  X  |     |     |      |     |  X  |     |       |     |     |  X  |
         *    |_____|_____|_____|      |_____|_____|_____|       |_____|_____|_____|
         */
        List leftColumn = Arrays.asList(1, 4, 7); //left column that has X or O three times in a row
        List middleColumn = Arrays.asList(2, 5, 8); //middle column that has X or O three times in a row
        List rightColumn = Arrays.asList(3, 6, 9); //bottom column that has X or O three times in a row
        /*  Used to check the diagonals within the board when a user or computer
         *  wins a game of tic tac toe
         *  Ex: _____ _____ _____        _____ _____ _____        
         *     |     |     |     |      |     |     |     |       
         *     |  X  |     |     |      |     |     |  X  |       
         *     |_____|_____|_____|      |_____|_____|_____|            
         *     |     |     |     |      |     |     |     |       
         *     |     |  X  |     |  OR  |     |  X  |     |   
         *     |_____|_____|_____|      |_____|_____|_____|       
         *     |     |     |     |      |     |     |     |       
         *     |     |     |  X  |      |  X  |     |     |       
         *     |_____|_____|_____|      |_____|_____|_____|
         */
        List leftDiagonal = Arrays.asList(1, 5, 9); //left diagonal that has X or O three times in a row
        List rightDiagonal = Arrays.asList(7, 5, 3); //right diagonal that has X or O three times in a row

        List<List> win = new ArrayList<List>();
        /* Use the win variable to add the following combination of positions/lines
         * to insert into the array
         */
        win.add(topRow);
        win.add(middleRow);
        win.add(lowRow);
        win.add(leftColumn);
        win.add(middleColumn);
        win.add(rightColumn);
        win.add(leftDiagonal);
        win.add(rightDiagonal);

        /* Winning conditions
         * for CPU, Player, or tie breaker
         */
        for(List l : win){
            if(playerPositions.containsAll(l)) {return "You win against the CPU!";}
            else if(cpuPositions.containsAll(l)) {return "Oh noooo, the CPU has beaten you. Lets play again";}
            else if(playerPositions.size() + cpuPositions.size() == 9) {return "It's a tie!";}
        }

        return "";
    }
}
