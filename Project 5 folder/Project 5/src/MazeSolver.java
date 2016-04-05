import java.awt.Point;
import java.io.*;
import java.util.*;

/**
 * The program solves the maze with either DFS or BFS.
 * @author jiawen zhong
 * CECS 274
 * Project 5
 *
 */
public class MazeSolver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int menuInput = 0;
		int score = 0;
		boolean done = false;
		while(!done) {
			menu();
			menuInput = CheckInput.checkInt(1, 2);
			switch(menuInput){
			case 1: 
				System.out.println("Please enter a number between 1 - 4.");
				int mazeLvl = CheckInput.checkInt(1, 4);
				if (mazeLvl == 1) {
					//send text file Maze-Level0.txt
					reader ("Maze-Level0.txt");
				} else if (mazeLvl == 2) {
					//send text file Maze-Level1.txt
					reader ("Maze-Level1.txt");
				} else if (mazeLvl == 3) {
					//send text file Maze-Level2.txt
					reader ("Maze-Level2.txt");
				} else {
					//send text file Maze-Level3.txt
					reader ("Maze-Level3.txt");
				}
				break;

			case 2: //Quit the program
				System.out.println("Game end");
				done = true;
				break;
			default:System.out.println("Please enter a number according to the menu.");
			break;
			}
		}
	}

	/**
	 * the menu
	 */
	public static void menu () {
		System.out.println("1. Choose a maze level");
		System.out.println("2. Quit");

	}

	/**
	 * Reads in the file and store it into a 2d array
	 * @param fileName the file that contain the maze
	 */
	public static void reader (String fileName) {
		try {
			//read in the height and width
			Scanner read = new Scanner (new File (fileName));
			String dimension = read.nextLine();
			String separate [] = dimension.split(" ");
			char [][] maze = new char [Integer.parseInt(separate[1])] [Integer.parseInt(separate[0])];
			//System.out.println(Integer.parseInt(separate[1]) + " " + Integer.parseInt(separate[0]));
			int row = 0;
			do {
				//read in the maze from each line
				String mazeLine = read.nextLine();
				//put each of the character in the line into the 2d array
				for (int col = 0; col < mazeLine.length(); col ++) {
					maze [col][row] = mazeLine.charAt(col);
					System.out.print(maze [col][row]);
				}
				System.out.println("k");
				row ++;

			} while (read.hasNext());
			read.close();
			System.out.println("Would you like to solve the puzzle with DFS or BFS?");
			System.out.println("(Enter 1 for DFS, 2 for BFS)");
			int method = CheckInput.checkInt(1, 2);
			if (method == 1) {
				solverDFS (maze, Integer.parseInt(separate[1]), Integer.parseInt(separate[0]) );
			} else {
				solverBFS (maze);
			}
		} catch (FileNotFoundException n) {
			System.out.println("File not found.");
		}		
	}

	/**
	 * Solves the puzzle with DFS (stack)
	 * @param maze the 2d array that contains the maze
	 */
	public static void solverDFS (char [][] maze, int mazeCol, int mazeRow) {
		for (int col = 0; col < mazeCol; col ++) {
			for (int row = 0; row < mazeRow; row ++) {
				System.out.print(maze[col][row] + " ");
			}
			System.out.println("");
		}
		LinkedStack stack = new LinkedStack();
		boolean foundStart = false;
		char wall = '*';//wall symbol
		char path = '.';//put a path to the maze
		//find the starting position of the maze
		for (int row = 0; row < mazeRow && !foundStart; row ++) {
			for (int col = 0; col < mazeCol && !foundStart; col ++) {
				if (maze [col][row] == 's') {
					Point pt = new Point (col, row);
					stack.push(pt);
					//print out the start point
					System.out.println(pt);
					foundStart = true;
				}
			}
		}//end of search for 's'

		//Maze solving algorithm
		boolean endMaze = false;
		int count = 3;
		//while (!stack.isEmpty() && !endMaze) {
		while (count < 100 && !endMaze){
			//remove the top point from the stack
			Point removePt = new Point (0, 0);
			removePt = stack.pop();
			int x = (int) removePt.getX();//col
			int y = (int) removePt.getY();//row
			
			System.out.println("removed point: " + removePt);
			
			//check if it is the finish point
			if (maze [x][y] == 'f') {
				//print out the maze
				for (int col = 0; col < mazeCol; col ++) {
					for (int row = 0; row < mazeRow; row ++) {
						System.out.print(maze [col][row]);
					}
				System.out.println("");
				endMaze = true;
				}
			} else {
				//Mark the spot in the maze as evaluated
				maze [x][y] = path;
				
				//System.out.println("Path point: " + x + " " + y + " " + maze [y][x] );
				
				int leftC = x - 1;
				int rightC = x + 1;
				int upR = y - 1;
				int downR = y + 1;

				//check if they are within the range
				if (leftC >= 0){ 
					if (maze [leftC][y] != wall && maze [leftC][y] != path) {
						//stack.push(new Point (x, left));
						
						stack.push(new Point (leftC, y));

						System.out.println("left " + leftC + " " + y);
					} 
				}
				if (rightC < mazeCol) {
					//maze [right][x] = replacement;
					if (maze [rightC][y] != wall && maze [rightC][y] != path) {
						//stack.push(new Point (x, right));
						
						stack.push(new Point (rightC, y));

						System.out.println("right " + rightC + " " + y);

					} 		
				}
				if (upR >=0) {
					//maze [y][up] = replacement;
					if (maze [x][upR] != wall && maze [x][upR] != path) {
						//stack.push(new Point (up, y));
						
						stack.push(new Point (x, upR));

						System.out.println("up " + x + " " + upR);

					} 
				}
				if (downR < mazeCol) {
					//maze [y][down] = replacement;
					if (maze [x][downR] != wall && maze [x][downR] != path) {
						//stack.push(new Point (down, y));
						
						stack.push(new Point (x, downR));

						System.out.println("down " + x + " " + downR);
					} 
				}
				System.out.println("done " + stack.size());

			}
			count ++;
			
		}
	}

	/**
	 * solves the puzzle with BFS (queue)
	 * @param maze the 2d array that contains the maze
	 */
	public static void solverBFS (char [][] maze) {
		LinkedQueue queue = new LinkedQueue();
	}
}
