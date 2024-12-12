import javax.swing.*;
import java.awt.*;

public class PuzzleFrame extends JFrame {
    private SlidePuzzleBoard board;
    private PuzzleButton[][] button_board;
    private int size;
    public PuzzleFrame(SlidePuzzleBoard b, int s) {
        size = s;
        board = b;
        button_board = new PuzzleButton[size][size];
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(size, size));
        for (int row = 0; row < button_board.length; row++)
            for (int col = 0; col < button_board.length; col++) {
                button_board[row][col] = new PuzzleButton(board, this);
                boardPanel.add(button_board[row][col]);
            }
        update();
        setTitle("Slide Puzzle"); setSize(100 + 50 * size, 100 + 50 * size); setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        StartButton startButton = new StartButton(board, this);
        cp.add(startButton, BorderLayout.NORTH);
        cp.add(boardPanel, BorderLayout.CENTER);

    }
    public void update() {
        PuzzlePiece pp;
        for (int row = 0; row < button_board.length; row++)
            for (int col = 0; col < button_board.length; col++) {
                pp = board.getPuzzlePiece(row, col);
                if (pp != null)
                    button_board[row][col].setText(Integer.toString(pp.faceValue()));
                else button_board[row][col].setText("");
            }
        if (board.gameOver()) finish();
    }

    /** finish - 퍼즐 게임 종료를 표시함 */
    public void finish() {
        button_board[size - 1][size - 1].setText("Done");
    }
}
