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
			int row = 0;
			do {
				//read in the maze from each line
				String mazeLine = read.nextLine();
				//put each of the character in the line into the 2d array
				for (int col = 0; col < mazeLine.length(); col ++) {
					maze [col][row] = mazeLine.charAt(col);
					System.out.print(maze [col][row]);
				}
				System.out.println("");
				row ++;

			} while (read.hasNext());
			read.close();
			System.out.println("Would you like to solve the puzzle with DFS or BFS?");
			System.out.println("(Enter 1 for DFS, 2 for BFS)");
			int method = CheckInput.checkInt(1, 2);
			if (method == 1) {
				solverDFS (maze, Integer.parseInt(separate[1]), Integer.parseInt(separate[0]));
			} else {
				solverBFS (maze, Integer.parseInt(separate[1]), Integer.parseInt(separate[0]));
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
					//System.out.println(pt);
					foundStart = true;
				}
			}
		}//end of search for 's'

		//Maze solving algorithm
		boolean endMaze = false;
		while (!stack.isEmpty() && !endMaze) {
			//remove the top point from the stack
			Point removePt = new Point (0, 0);
			removePt = stack.pop();
			int x = (int) removePt.getX();//row
			int y = (int) removePt.getY();//col

			//System.out.println("removed point: " + removePt);

			//check if it is the finish point
			if (maze [y][x] == 'f') {
				//print out the maze
				for (int row = 0; row < mazeRow; row ++) {
					for (int col = 0; col < mazeCol; col ++) {
						System.out.print(maze [col][row]);
					}
					System.out.println("");
					endMaze = true;
				}
			} else {
				//Mark the spot in the maze as evaluated
				maze [y][x] = path;

				int leftC = y - 1;
				int rightC = y + 1;
				int upR = x - 1;
				int downR = x + 1;

				//mark the path whenever there is not a wall
				//check if they are within the range
				if (leftC >= 0){ 
					if (maze [leftC][x] != wall && maze [leftC][x] != path) {						
						stack.push(new Point (x, leftC));
						//System.out.println("left pt " + x + " " + leftC);
					} 
				}
				if (rightC < mazeCol) {
					if (maze [rightC][x] != wall && maze [rightC][x] != path) {
						stack.push(new Point (x, rightC));
						//System.out.println("right " + x + " " + rightC);
					} 		
				}
				if (upR >=0) {
					//maze [y][up] = replacement;
					if (maze [y][upR] != wall && maze [y][upR] != path) {
						stack.push(new Point (upR, y));
						//System.out.println("up " + upR + " " + y);
					} 
				}
				if (downR < mazeCol) {
					if (maze [y][downR] != wall && maze [y][downR] != path) {
						stack.push(new Point (downR, y));
						//System.out.println("down " + downR + " " + x);
					} 
				}
			}//end of else			
		}//end of big while loop
	}

	/**
	 * solves the puzzle with BFS (queue)
	 * @param maze the 2d array that contains the maze
	 */
	public static void solverBFS (char [][] maze, int mazeCol, int mazeRow) {
		LinkedQueue queue = new LinkedQueue();
		
		boolean foundStart = false;
		char wall = '*';//wall symbol
		char path = '.';//put a path to the maze
		
		//find the starting position of the maze
		for (int row = 0; row < mazeRow && !foundStart; row ++) {
			for (int col = 0; col < mazeCol && !foundStart; col ++) {
				if (maze [col][row] == 's') {
					Point pt = new Point (col, row);
					queue.add(pt);
					//print out the start point
					//System.out.println(pt);
					foundStart = true;
				}
			}
		}//end of search for 's'

		//Maze solving algorithm
		boolean endMaze = false;
		while (!queue.isEmpty() && !endMaze) {
			//remove the top point from the stack
			Point removePt = new Point (0, 0);
			removePt = queue.remove();
			int x = (int) removePt.getX();//row
			int y = (int) removePt.getY();//col

			//System.out.println("removed point: " + removePt);

			//check if it is the finish point
			if (maze [y][x] == 'f') {
				//print out the maze
				for (int row = 0; row < mazeRow; row ++) {
					for (int col = 0; col < mazeCol; col ++) {
						System.out.print(maze [col][row]);
					}
					System.out.println("");
					endMaze = true;
				}
			} else {
				//Mark the spot in the maze as evaluated
				maze [y][x] = path;

				int leftC = y - 1;
				int rightC = y + 1;
				int upR = x - 1;
				int downR = x + 1;

				//mark the path whenever there is not a wall
				//check if they are within the range
				if (leftC >= 0){ 
					if (maze [leftC][x] != wall && maze [leftC][x] != path) {						
						queue.add(new Point (x, leftC));
						//System.out.println("left pt " + x + " " + leftC);
					} 
				}
				if (rightC < mazeCol) {
					if (maze [rightC][x] != wall && maze [rightC][x] != path) {
						queue.add(new Point (x, rightC));
						//System.out.println("right " + x + " " + rightC);
					} 		
				}
				if (upR >=0) {
					//maze [y][up] = replacement;
					if (maze [y][upR] != wall && maze [y][upR] != path) {
						queue.add(new Point (upR, y));
						//System.out.println("up " + upR + " " + y);
					} 
				}
				if (downR < mazeCol) {
					if (maze [y][downR] != wall && maze [y][downR] != path) {
						queue.add(new Point (downR, y));
						//System.out.println("down " + downR + " " + x);
					} 
				}
			}//end of else		
		}//end of big while loop
	}
}
