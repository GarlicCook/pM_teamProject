import javax.swing.*;
import java.awt.*;

public class PuzzleFrame extends JFrame {
	
	private SlidePuzzleBoard board;
	private PuzzleButton[][] button_board;
	
	public PuzzleFrame(SlidePuzzleBoard b) {
		board = b;
		button_board = new PuzzleButton[board.getSize()][board.getSize()];
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel p1 = new JPanel((new FlowLayout()));
		JPanel p2 = new JPanel((new GridLayout(board.getSize(), board.getSize())));

		//startButton 생성
		p1.add(new StartButton(board, this));
		cp.add(p1, BorderLayout.NORTH);

		//puzzleButton 생성
		for (int row = 0; row < button_board.length; row++)
			for (int col = 0; col < button_board.length; col++) {
				button_board[row][col] = new PuzzleButton(board,this);
				p2.add(button_board[row][col]);
			}
		cp.add(p2, BorderLayout.CENTER);


		update();
		setTitle("Slide Puzzle");
		setSize(400,400);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void update() {
		PuzzlePiece pp;
		for (int row = 0; row < button_board.length; row++)
			for (int col = 0; col < button_board.length; col++) {
				pp = board.getPuzzlePiece(row, col);
				if (pp != null)
					button_board[row][col].setText(Integer.toString(pp.faceValue()));
				else
					button_board[row][col].setText("");

        }
		if(!board.gameOn() && (board.gameCount() != 1))
			button_board[board.getSize()-1][board.getSize()-1].setText("Done");
    }
}