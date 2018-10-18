

/**
 * 
 * @author Kieron Ho 20500057
 *
 */
public class MancalaImp implements MancalaAgent {
	private static int extraTurnUtility = 6;
	private final String name = "Johnny 5";
	
	public MancalaImp() {
	}

	/**
	 * 
	 */
	public int move(int[] board) {
		return getMaxValueIndex(utility(board));
	}

	
	/**
	 * 
	 */
	public String name() {
		return name;
	}

	/**
	 * 
	 */
	public void reset() {

	}

	/**
	 * Returns the utility values for the choices available
	 * @param board is the mancala board
	 * @return minimum value choices, or null if trimmed
	 */
	public static Integer[] utility(int[] board) {
		Integer[] minimaxUtilities = new Integer[6];
		Integer pruningMinimax = null;
		int localMinimum = Integer.MAX_VALUE;
		Integer firstUtility = null;
		Integer secondUtility = null;

		for(int i = 0 ; i < 6 ; i++) {//for each max move
			localMinimum = Integer.MAX_VALUE;//reset local minimum each time
			firstUtility = turnUtility(board, i);
			if(firstUtility == null) {
				minimaxUtilities[i] = null;
				continue;
			}
			int[] nextBoard = turnSimulator(board, i);//change the board
		for(int j = 0 ; j < 6 ; j++) {//for each min move
			secondUtility = turnUtility(nextBoard, j + 7);
			if(secondUtility == null) {
				continue;
			}
			if(pruningMinimax != null) {
				if(secondUtility < pruningMinimax) {
					minimaxUtilities[i] = null;//added so if pruned will be unavailable
					break;
				}
			}
			if(firstUtility + secondUtility < localMinimum) {//check if localMinimum needs to change
				localMinimum = firstUtility + secondUtility;
				minimaxUtilities[i] = localMinimum;
			}
		}
		if(pruningMinimax == null) {//if pruning Minimax has not been set, set it now
			pruningMinimax = localMinimum;
		}else if(pruningMinimax < localMinimum) {//if a local minimum is more than the pruning value, change to a new pruning value
			pruningMinimax = localMinimum;
		}
		}
		

		return minimaxUtilities;
	}
	
	
	/**
	 * Simulates a turn that moves a particular houses' seeds
	 * @param startBoard
	 * @param movingHouse
	 * @return a board that has been altered by a move
	 */
	public static int[] turnSimulator(int[] startBoard, int movingHouse) {
		int[] endBoard = startBoard.clone();
		int startPoint = movingHouse;
		int endPoint = (startPoint + startBoard[startPoint])%14;
		int numberOfMoves = startBoard[movingHouse];
		endBoard[movingHouse] = 0;
		for(int i = 0 ; i < numberOfMoves ; i++) {
			int stepHouse = (movingHouse + i + 1)%14;
			endBoard[stepHouse]++;
			if((stepHouse != 6 || stepHouse != 13) && ((startPoint < 6 && endPoint < 6) ||(startPoint > 6 && endPoint > 6))) {//Make sure you are allowed to take the stash
			if(i == numberOfMoves - 1 && startBoard[stepHouse] == 0 && startBoard[12 - stepHouse] != 0) {
				endBoard[stepHouse] = 0;
				endBoard[12 - stepHouse] = 0;
				if(stepHouse < 6) {
					endBoard[6] += 1 + startBoard[12 - stepHouse];
				}
				else endBoard[13] += 1 + startBoard[12 - stepHouse];
			}
			}
		}
		return endBoard;
	}
	
	/**
	 * 
	 * @param startBoard
	 * @param movingHouse is the house that has been selected to move
	 * @return the utility of this action
	 */
	public static Integer turnUtility(int[] startBoard, int movingHouse) {
		if(startBoard[movingHouse] == 0) {
			return null;
		}
		int numberOfMoves = startBoard[movingHouse];
		int[] steps = new int[numberOfMoves];
		for(int i = 0 ; i < numberOfMoves ; i++) {
			steps[i] = (movingHouse + i + 1)%14;
		}
		return utilityCounter(steps, startBoard);
	}
	
	
	/**
	 * Sums the utility of each step of a move
	 * @param changedHouses is an array that lists all houses that are affected by a move
	 * @param board is the board before changes
	 * @return The total utility from this move. Is negative if performed by the opposition agent
	 */
	public static int utilityCounter(int[] changedHouses, int[] board) {
		int utility = 0;
		int startingHouse = (changedHouses[0] + 13)%14;
		boolean highTurn = startingHouse >= 7 && startingHouse <= 12;
		int storeIndex;
		int endHouse = changedHouses[changedHouses.length - 1];
		if(highTurn) {
			storeIndex = 13;
		} else storeIndex = 6;
		
		
		//check for utilities to add
		if(endHouse == storeIndex) {//does it end on the store?
			utility += 1 + extraTurnUtility;
		} else if(contains(changedHouses, storeIndex)) {//or does it just pass it
			utility++;
		}
		if(endHouse != storeIndex) {
		if(board[endHouse] == 0 && board[12 - endHouse] != 0) {//snag the opposing seeds
			utility += 1 + board[12 - endHouse];
		}}
		
		//Add utility if there is a high amount of seeds bunched up
		if(board[startingHouse] > 4) {
		utility += 2;
		}
		if(highTurn) {
			return -1 * utility;
		}
		
		return utility;
	}
	
	
	/**
	 * Checks if a list contains a particular value
	 * @param list to check for the value
	 * @param value is the value to search for
	 * @return true if list contains the value
	 */
	public static boolean contains(int[] list, int value) {
		boolean doesContain = false;
		for(int i = 0 ; i < list.length ; i++) {
			if(list[i] == value) doesContain = true;
		}
		return doesContain;
	}
	
	
	/**
	 * Finds the index of the maximum value in a list
	 * @param values is an array of values to evaluate
	 * @return the index of the maximum value found
	 */
	public static int getMaxValueIndex(Integer[] values) {
		int maximumValue = Integer.MIN_VALUE;
		int maxValueIndex = 0;
		for(int i = 0 ; i < values.length ; i++) {
			if(values[i] != null) {
			if(values[i] > maximumValue) {
				maximumValue = values[i];
				maxValueIndex = i;
			}
			}
		}
		
		return maxValueIndex;
	}
	
	
	/**
	 * Finds the minimum value in a list
	 * @param values is an array of values to evaluate
	 * @return the minimum value found
	 */
	public static Integer getMinValue(Integer[] values) {
		int minimumValue = Integer.MAX_VALUE;
		for(int i = 0 ; i < values.length ; i++ ) {
			if(values[i] != null) {
			if(values[i] < minimumValue) {
				minimumValue = values[i];
			}
			}
		}
		if(minimumValue == Integer.MAX_VALUE) {
			return null;
		}
		return minimumValue;
	}
	
	
}
