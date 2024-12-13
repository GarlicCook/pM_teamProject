import javax.swing.*;
import java.awt.*;

public class PuzzleFrame extends JFrame {

    private SlidePuzzleBoard board;
    private PuzzleButton[][] button_board;

    public PuzzleFrame(SlidePuzzleBoard b) {
        board = b;
        button_board = new PuzzleButton[board.getSize()][board.getSize()];
        Container cp = getContentPane();
        cp.setLayout(null);

        JPanel p1 = new JPanel(new FlowLayout());
        p1.setBounds(0, 0, board.getSize() * 90 + 20, 30);
        StartButton start_button = new StartButton(board, this);
        start_button.setBackground(Color.GRAY);
        p1.add(start_button);
        cp.add(p1);

        final int buttonSize = 80;
        final int blank = 10;

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                PuzzlePiece pp = board.getPuzzlePiece(row, col);
                int x = 10 + col * (buttonSize + blank);
                int y = 40 + row * (buttonSize + blank);
                PuzzleButton button = new PuzzleButton(board, this);
                button.setBackground(Color.WHITE);
                button.setBounds(x, y, buttonSize, buttonSize);
                if (pp != null) {
                    button.setText(Integer.toString(pp.faceValue()));
                } else {
                    button.setText("");
                }
                cp.add(button);
                button_board[row][col] = button;
            }
        }

        setTitle("Slide Puzzle");
        setSize(board.getSize() * 90 + 20, board.getSize() * 90 + 70);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void update() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                PuzzlePiece pp = board.getPuzzlePiece(row, col);
                if (pp != null) {
                    button_board[row][col].setText(Integer.toString(pp.faceValue()));
                } else {
                    button_board[row][col].setText("");
                }
            }
        }
        if (!board.gameOn() && (board.gameCount() != 1)) {
            button_board[board.getSize() - 1][board.getSize() - 1].setText("Done");
        }
    }

    public void movingButton() {

    }
}
