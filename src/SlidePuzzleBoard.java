import java.util.Random;

/** SlidePuzzleBoard models a slide puzzle. */
public class SlidePuzzleBoard { 
	private PuzzlePiece[][] board;
	private int size;

	// 빈칸의 좌표 
	private int empty_row;
	private int empty_col;
	// representation invariant: board[empty_row][empty_col] == null
	private boolean game_on = false;
	//게임 횟수
	private int count = 0;

	/** Constructor - SlidePuzzleBoard 초기 퍼즐 보드 설정 - 감소하는 순으로 나열 
	 * @param s 퍼즐 보드 크기 */
	public SlidePuzzleBoard(int s) {
			count += 1;
			size = s;
			// size x size 보드 만들기
			board = new PuzzlePiece[size][size];
			// 퍼즐 조각 1~(size^2 - 1)를 보드에 역순으로 끼우기
			int number = 1;
			for (int row = 0; row < size; row++)
				for (int col = 0; col < size; col++) {
					board[row][col] = new PuzzlePiece(number);
					number += 1;
				}
			board[size - 1][size - 1] = null;
			empty_row = size - 1;
			empty_col = size - 1;
    }
	//가능 판정
	public boolean possibleTest(){
		//I 판정을 위한 반복문 // I : 각 칸이 뒤에 있는 칸의 숫자들보다 큰 모든 경우의 수
		int I = 0;
		for (int row = 0; row < size; row++)
			for (int col = 0; col < size; col++){
				for(int i = row; i < size; i++) {
					int j;
					if (row == i)
						j = col + 1;
					else
						j = 0;
					for (; j < size; j++) {
						if(board[i][j] == null);
						else if (board[row][col].faceValue() > board[i][j].faceValue())
							I += 1;
					}
				}
			}

		//System.out.println(size +" " + I); //I가 짝수면 가능 I가 홀수면 불가능
		return (I % 2 == 0);
	}

	public void createPuzzleBoard(){
		do {
			count += 1;
			game_on = true;
			Random random = new Random();
			board = new PuzzlePiece[size][size];
			//테스트 코드
			//int[] number = {0, 1,2,3,4,5,6,7,8,9,10, 11, 12, 13, 14};
			// 퍼즐 조각 1~(size^2 - 1)를 보드에 역순으로 끼우기
			int[] number = new int[size * size - 1];
			//중복없는 랜덤 리스트 생성 알고리즘
			for (int i = 0; i < size * size - 1; i++) {
				int d = random.nextInt(i + 1);
				number[i] = number[d];
				number[d] = i;
			}
			int piece_num = 0;
			for (int row = 0; row < size; row++)
				for (int col = 0; col < size; col++) {
					if (row == size - 1 && col == size - 1)
						break;
					board[row][col] = new PuzzlePiece(1 + number[piece_num]);
					piece_num += 1;
				}
			board[size - 1][size - 1] = null;
			empty_row = size - 1;
			empty_col = size - 1;
		}while(!possibleTest());
	}

	/** contents 현재 퍼즐 조각을 리턴
    * @return 퍼즐 조각  */
	public PuzzlePiece getPuzzlePiece(int row, int col) { 
		return board[row][col];
	}
	
	/** 이동이 가능하면, 퍼즐 조각을 빈칸으로 이동 
	 * @param w 이동하기 원하는 퍼즐 조각
	 * @return 이동 성공하면 true를 리턴하고, 이동이 불가능하면 false를 리턴 */
	public boolean move(int w) { 
		int row, col; // w의 위치 
		// 빈칸에 주변에서 w의 위치를 찾음 
		if (found(w, empty_row - 1, empty_col)) {
			row = empty_row - 1;
			col = empty_col;
		}
		else if (found(w, empty_row + 1, empty_col)) {
			row = empty_row + 1;
			col = empty_col;
		}
		else if (found(w, empty_row, empty_col - 1)) {
			row = empty_row;
			col = empty_col - 1;
		}
		else if (found(w, empty_row, empty_col + 1)) {
			row = empty_row;
			col = empty_col + 1;
		}
		else
			return false;
		// w를 빈칸에 복사
		board[empty_row][empty_col] = board[row][col];
		// 빈칸 위치를 새로 설정하고, w를 제거
		empty_row = row;
		empty_col = col;
		board[empty_row][empty_col] = null;
		game_on = onGame();
		return true;
	}
	
	/** found board[row][col]에 퍼즐 조각 v가 있는지 확인  */
	private boolean found(int v, int row, int col) { 
		if (row >= 0 && row < size && col >= 0 && col < size)
			return board[row][col].faceValue() == v; 
	    else
	    	return false;
	}
	public int getSize() {
		return size;
	}
	//게임 끝났는지 검사
	public boolean onGame() {
		if (empty_row != size - 1 || empty_col != size - 1)
			return true;
		else{
			int number = 1;
			for(int row = 0; row < size; row++)
				for(int col = 0; col < size; col++)
					if( row != (size - 1) || col != (size - 1))
						if(board[row][col].faceValue() != number)
							return true;
						else
							number++;
			return false;
		}
	}
	public boolean gameOn() {;
		return game_on;
	}
	public int gameCount(){
		return count;
	}
}

	
