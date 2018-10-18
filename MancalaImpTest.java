import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class MancalaImpTest {

	@Test
	void getMaxValueIndexTest() {
		Integer[] listArray = {-1, -1, null, -2147483648, 0, 0};
		assertEquals(4, MancalaImp.getMaxValueIndex(listArray));
		Integer[] listArray2 = {null, 19, null, -70, 23, 55, null, 999};
		assertEquals(7, MancalaImp.getMaxValueIndex(listArray2));
	}
	
	@Test
	void getMinValueTest() {
		Integer[] listArray = {null, -500, 0, 99, null, -501};
		int min = MancalaImp.getMinValue(listArray);
		assertEquals(-501, min);
	}
	
	@Test
	void containsTest() {
		int[] list = {0, 5, 11, 19, -4, 1003};
		assertEquals(true, MancalaImp.contains(list, 19));
		assertEquals(false, MancalaImp.contains(list, 4));
	}
	
	@Test
	void utilityCounterTest() {
		int[] board = {3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 0};
		int[] changedHouses1 = {4, 5, 6};
		int[] changedHouses2 = {5, 6, 7};
		int[] changedHouses3 = {1, 2, 3};
		int[] changedHouses4 = {11, 12, 13};
		assertEquals(7, MancalaImp.utilityCounter(changedHouses1,  board));
		assertEquals(1, MancalaImp.utilityCounter(changedHouses2, board));
		assertEquals(0, MancalaImp.utilityCounter(changedHouses3, board));
		assertEquals(-7, MancalaImp.utilityCounter(changedHouses4, board));
	}
	
	@Test
	void turnUtilityTest() {
		int[] board = {3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 0};
		int[] board2 = {0, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 0};
		Integer startHouse1 = 3;
		Integer startHouse2 = 4;
		Integer startHouse3 = 0;
		Integer startHouse4 = 10;
		assertEquals(0, Integer.compare(7, MancalaImp.turnUtility(board, startHouse1)));
		assertEquals(0, Integer.compare(1, MancalaImp.turnUtility(board, startHouse2)));
		assertEquals(0, Integer.compare(0, MancalaImp.turnUtility(board, startHouse3)));
		assertEquals(0, Integer.compare(-7, MancalaImp.turnUtility(board, startHouse4)));
		assertEquals(null, MancalaImp.turnUtility(board2,  0));
	}
	
	
	@Test
	void turnSimulatorTest() {
		int[] boardStart1 = {3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 0};
		int boardMove1 = 0;
		int[] boardEnd1 = {0, 4, 4, 4, 3, 3, 0, 3, 3, 3, 3, 3, 3, 0};
		
		int[] boardStart2 = {1, 0, 5, 0, 2, 1, 14, 2, 5, 4, 1, 1, 3, 17};
		int boardMove2 = 0;
		int[] boardEnd2 = {0, 0, 5, 0, 2, 1, 16, 2, 5, 4, 1, 0, 3, 17};
		
		int[] boardStart3 = {1, 0, 5, 0, 2, 1, 14, 2, 5, 4, 1, 1, 3, 17};
		int boardMove3 = 8;
		int[] boardEnd3 = {1, 0, 5, 0, 2, 1, 14, 2, 0, 5, 2, 2, 4, 18};
		
		int[] boardStart4 = {1, 0, 5, 0, 2, 1, 14, 2, 5, 4, 1, 1, 3, 17};
		int boardMove4 = 12;
		int[] boardEnd4 = {2, 1, 5, 0, 2, 1, 14, 2, 5, 4, 1, 1, 0, 18};
		
		assertTrue(Arrays.equals(boardEnd1, MancalaImp.turnSimulator(boardStart1, boardMove1)));
		assertTrue(Arrays.equals(boardEnd2, MancalaImp.turnSimulator(boardStart2, boardMove2)));
		assertTrue(Arrays.equals(boardEnd3, MancalaImp.turnSimulator(boardStart3, boardMove3)));
		assertTrue(Arrays.equals(boardEnd4, MancalaImp.turnSimulator(boardStart4, boardMove4)));
	}
	
	
	

	/**@Test
	void utilityTest() {
		int[] board1 = {3, 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 3, 0};
		int utility1 = MancalaImp.turnUtility(board1, 0);
		int utility2 = MancalaImp.turnUtility(board1, 3);
		int utility3 = MancalaImp.turnUtility(board1, 10);
		assertEquals(0, utility1);
		assertEquals(7, utility2);
		assertEquals(-7, utility3);
		
		int[] board2 = {3, 3, 3, 0, 3, 3, 0, 3, 3, 3, 3, 0, 3, 0};
		int utility4 = MancalaImp.turnUtility(board2, 0);
		Integer utility5 = MancalaImp.turnUtility(board2, 11);
		assertEquals(4, utility4);
		assertEquals(null, utility5);
	}
	
	*/
	
	
	
	
}
