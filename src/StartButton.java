import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends JButton implements ActionListener {
    SlidePuzzleBoard board;
    PuzzleFrame frame;
    boolean NoneStart = true;

    public StartButton(SlidePuzzleBoard b, PuzzleFrame f) {
        super("게임 시작!");
        board = b;
        frame = f;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (NoneStart) {
            board.createPuzzleBoard();
            frame.update();
            setText("다시 시작!");
            NoneStart = false;
        } else {
            board.createPuzzleBoard();
            frame.update();
        }
    }
}
