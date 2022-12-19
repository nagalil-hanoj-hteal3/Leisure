import java.awt.*; // for creating user interface (mouse and button clicks)
import java.awt.event.*; //listener, adaptor features

import javax.swing.*; // for setting the features to be displayed on the GUI (mouse and button clicks)
import java.io.*; // input and output interface 
import java.util.*; // use for scanner --> record and update score count

/*
 * Links used
 * https://www.tutorialspoint.com/when-can-we-use-the-pack-method-in-java#:~:text=The%20pack()%20method%20is,()%20or%20setBounds()%20methods.
 * https://www.rapidtables.com/web/color/RGB_Color.html
 */

public class TicTacToe extends JPanel implements ActionListener{ //set the tic tac toe game to be a JPanel element
    
    //variables to be used for the tic tac toe game (logic)
    boolean player; // used to swap turns with other player (X or O)
    boolean game_status = false; // track the game status to off
    int winner = -1; // default value to track wins
    int player_X = 0; // track player X win counter (player 1)
    int player_O = 0; // track player O win counter (player 2)
    int board[][] = new int[3][3]; // generate this into a 3x3 grid

    //variables for GUI optimization (grid of the tic tac toe)
    int lineWidth = 7; // set the thickness of the lines to be displayed
    int lineLength = 270; // set the length for how long the lines should be displayed on the GUI
    int x = 15, y = 100; // initalize the x and y for the width/length
    int offset = 95; // set the offset with the other cells
    int a = 0; 
    int b = 5;
    int selX = 0, selY = 0; // store location of mouse click

    //Colors used will be scaled in RGB
    Color white = new Color(255,255,255); //as the color of grid
    Color red = new Color(153,0,0); // for the X 
    Color green = new Color(0,204,102); // for the O
    Color gray = new Color(64,64,64);// for the rest

    //Components
    JButton button; //play again feature

    //constructor
    public TicTacToe(){
        Dimension size = new Dimension(420, 300); //set the GUI JPanel to be 1200 x 900
        /*
         * used this to let the pixels be resized
         * while the game is active
         */
        setPreferredSize(size); // set the size to be the size declared above
        setMinimumSize(size); // set the minimum size to be the size declared
        setMaximumSize(size); // set the maximum size to be the size declared

        addMouseListener(new XOListener()); //for computer mouse to read where the player will click

        button = new JButton("Play Again"); //Play Again button
        button.addActionListener(this); // to hear the button clicked
        
        button.setBounds(315, 210, 100, 30);
		add(button);
		reset();
        //button.setVisible(false); // keep hidden when game is active
    }
    /*
     * To generate a new game
     */
    public void reset(){
        player = true;
        winner = -1;
        game_status = false;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = 0; // set all grids to 0
            }

        }
        getButton().setVisible(false); // keep it hidden when the game is active
    }
    /*
     * For graphic
     * optimization
     */
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        drawBoard(page);
        drawUI(page);
        drawGame(page);
    }

    public void drawBoard(Graphics page) {
        setBackground(white);
        page.setColor(gray);
        /*
         * where x and y are the starting positions
         * 5 and 30 are line radius
         * lineLength and lineWidth will determine 
         * the lines to be outputted on the GUI
         */
        //for horizontal lines
        page.fillRoundRect(x, y, lineLength, lineWidth, 5, 30);
        page.fillRoundRect(x, y + offset, lineLength, lineWidth, 5, 30);
        //for vertical lines
        page.fillRoundRect(y, x, lineWidth, lineLength, 30, 5);
        page.fillRoundRect(y + offset, x, lineWidth, lineLength, 30, 5);
    }

    public void drawUI(Graphics page) {
        page.setColor(gray);
        page.fillRect(300, 0, 150, 300);
        Font f = new Font("Times New Roman", Font.PLAIN,20);
        page.setFont(f);

        //Win counter
        page.setColor(white);
        page.drawString("Win Count", 310, 30);
        page.drawString(": " + player_O, 362, 70);
        page.drawString(": " + player_X, 362, 105);

        //Draw score for X --> must be used to insert as an image since it
        //will not have a function directly for an X shape
        ImageIcon x_Icon = new ImageIcon("x.png");
        Image x_Image = x_Icon.getImage();
        Image new_X_Image = x_Image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH); 
        ImageIcon new_X_Icon = new ImageIcon(new_X_Image);
        page.drawImage(new_X_Icon.getImage(), 329, 47, null);

        //Draw score for O --> with using the oval function
        page.setColor(green);
        page.fillOval(328, 80, 30, 30);
        page.setColor(gray); //fill in the oval on the score sheet
        page.fillOval(334, 85, 19, 19);

        //determine the turn or winner
        page.setColor(white);
        Font f1 = new Font("Times New Roman", Font.ITALIC, 18);    
        page.setFont(f1);

        if(game_status){
            if(winner == 1){//show winner if it is X
                page.drawString("The winner is", 310, 150);
                page.drawImage(x_Image, 335, 160, null);
            }
            else if(winner == 2){//show winner if it is O
                page.drawString("The winner is", 310, 150);
				page.setColor(green);
				page.fillOval(332, 160, 50, 50);
				page.setColor(gray);
				page.fillOval(342, 170, 30, 30);
            }
            else if(winner == 3){//tie
                page.drawString("It's a tie!", 330, 178);
            }
        }
        else{
            Font f2 = new Font("Times New Roman", Font.ITALIC, 20);
            page.setFont(f2);
            page.drawString("It's a tie", 350, 160);
            if(player){
                page.drawString("X 's Turn", 325, 180);
            }
            else{
                page.drawString("O 's Turn", 325, 180);
            }
        }
    }

    public void drawGame(Graphics page) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 0) {

				} else if (board[i][j] == 1) {
					ImageIcon xIcon = new ImageIcon("x.png");
					Image xImg = xIcon.getImage();
					page.drawImage(xImg, 30 + offset * i, 30 + offset * j, null);
				} else if (board[i][j] == 2) {
					page.setColor(green);
					page.fillOval(30 + offset * i, 30 + offset * j, 50, 50);
					page.setColor(white);
					page.fillOval(40 + offset * i, 40 + offset * j, 30, 30);
				}
			}
		}
		repaint();
    }

    public void checkWinner() {
		if (game_status == true) {
			System.out.print("gameDone");
			return;
		}
		// vertical
		int temp = -1;
		if ((board[0][0] == board[0][1])
				&& (board[0][1] == board[0][2])
				&& (board[0][0] != 0)) {
			temp = board[0][0];
		} else if ((board[1][0] == board[1][1])
				&& (board[1][1] == board[1][2])
				&& (board[1][0] != 0)) {
			temp = board[1][1];
		} else if ((board[2][0] == board[2][1])
				&& (board[2][1] == board[2][2])
				&& (board[2][0] != 0)) {
			temp = board[2][1];

			// horizontal
		} else if ((board[0][0] == board[1][0])
				&& (board[1][0] == board[2][0])
				&& (board[0][0] != 0)) {
			temp = board[0][0];
		} else if ((board[0][1] == board[1][1])
				&& (board[1][1] == board[2][1])
				&& (board[0][1] != 0)) {
			temp = board[0][1];
		} else if ((board[0][2] == board[1][2])
				&& (board[1][2] == board[2][2])
				&& (board[0][2] != 0)) {
			temp = board[0][2];

			// diagonal
		} else if ((board[0][0] == board[1][1])
				&& (board[1][1] == board[2][2])
				&& (board[0][0] != 0)) {
			temp = board[0][0];
		} else if ((board[0][2] == board[1][1])
				&& (board[1][1] == board[2][0])
				&& (board[0][2] != 0)) {
			temp = board[0][2];
		} else {

			// CHECK FOR A TIE
			boolean notDone = false;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == 0) {
						notDone = true;
						break;
					}
				}
			}
			if (notDone == false) {
				temp = 3;
			}
		}
		if (temp > 0) {
			winner = temp;
			if (winner == 1) {
				player_X++;
				System.out.println("winner is X");
			} else if (winner == 2) {
				player_O++;
				System.out.println("winner is O");
			} else if (winner == 3) {
				System.out.println("It's a tie");
			}
			game_status = true;
			getButton().setVisible(true);
		}
	}
        /*
     * get function to access the button
     * within the application
     */
    public JButton getButton(){
        return button;
    }
    /*
     * set function to access the button
     * to be found within the application
     * for player 1 or player "X"
     */
    public void setPlayerX_Wins(int X) {player_X = X;}
    /*
     * set function to access the button
     * to be found within the application
     * for player 2 or player "O"
     */
    public void setPlayerO_Wins(int O) {player_O = O;}

    public static void main(String[] args) {
        //create a title in the GUI
        JFrame title = new JFrame("Tic Tac Toe");
        title.getContentPane();

        //create a TicTacToe object and insert it to the frame
        TicTacToe t_panel = new TicTacToe();
        title.add(t_panel);

        title.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				try {
					File file = new File("score.txt");
					Scanner sc = new Scanner(file);
					t_panel.setPlayerX_Wins(Integer.parseInt(sc.nextLine()));
					t_panel.setPlayerO_Wins(Integer.parseInt(sc.nextLine()));
					sc.close();
				} catch (IOException io) {
					// file doesnt exist
					File file = new File("score.txt");
				}
			}

			public void windowClosing(WindowEvent e) {
				try {
					PrintWriter pw = new PrintWriter("score.txt");
					pw.write("");
					pw.write(t_panel.player_X + "\n");
					pw.write(t_panel.player_O + "\n");
					pw.close();
				} catch (FileNotFoundException e1) { }
			}
		});

        //set default settings to include in the JFrame
        title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set up the frame
        title.setResizable(false); //to make sure it stays in the same size
        title.pack(); // to make sure the size is where it should be (size)
        title.setVisible(true); //to display GUI
    }

    private class XOListener implements MouseListener {

		public void mouseClicked(MouseEvent event) {
			selX = -1;
			selY = -1;
			if (game_status == false) {
				a = event.getX();
				b = event.getY();
				int selX = 0, selY = 0;
				if (a > 12 && a < 99) {
					selX = 0;
				} else if (a > 103 && a < 195) {
					selX = 1;
				} else if (a > 200 && a < 287) {
					selX = 2;
				} else {
					selX = -1;
				}

				if (b > 12 && b < 99) {
					selY = 0;
				} else if (b > 103 && b < 195) {
					selY = 1;
				} else if (b > 200 && b < 287) {
					selY = 2;
				} else {
					selY = -1;
				}
				if (selX != -1 && selY != -1) {

					if (board[selX][selY] == 0) {
						if (player) {
							board[selX][selY] = 1;
							player = false;
						} else {
							board[selX][selY] = 2;
							player = true;
						}
						checkWinner();
						System.out.println(" CLICK= x:" + a + ",y: " + b + "; selX,selY: " + selX + "," + selY);

					}
				} else {
					System.out.println("invalid click");
				}
			}
		}

		public void mouseReleased(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
	}
    public void actionPerformed(ActionEvent e) { reset(); }
}
