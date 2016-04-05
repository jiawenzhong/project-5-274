import java.util.Scanner;
/**
 * the class checks the input
 * @author jiawen
 *
 */
public class CheckInput {

	/**
	 * check if the input is a valid integer
	 * @return			the number that the user has entered
	 */
	public static int checkInt(){

		Scanner in = new Scanner(System.in);
		int validNum = 0;
		boolean valid = false;
		while(!valid) {
			if(in.hasNextInt()) {
				validNum = in.nextInt();
				valid = true;
			} else {
				System.out.println("Invalid Input");
				in.next();
			}
		}
		return validNum;
	}

	/**
	 * check if the input is within the valid range
	 * @param low			lower bound
	 * @param high			upper bound
	 * @return				the valid number
	 */
	public static int checkInt( int low, int high ) {
		Scanner in = new Scanner(System.in);
		int validNum = 0;
		boolean valid = false;
		while(!valid) {
			if(in.hasNextInt()){
				validNum = in.nextInt();
				if (validNum >= low && validNum <= high) {
					//validNum = in.nextInt();
					valid = true;
				}else {
					System.out.println("Please enter a number within the range.");
				}
			} else {
				System.out.println("Please enter a valid number.");
				in.next();
			}
		}
		return validNum;
	}

}
