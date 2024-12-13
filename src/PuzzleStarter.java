import javax.swing.*;

public class PuzzleStarter {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "게임을 시작합니다.");
        while(true) {
            int size = Integer.parseInt(JOptionPane.showInputDialog("게임판의 크기를 입력해주세요.(2~9) "));
            if (size > 1 && size <= 9) {
                new PuzzleFrame(new SlidePuzzleBoard(size));
                break;
            } else {
                JOptionPane.showMessageDialog(null, "크기를 다시 입력해주세요.");}
        }
    }
}
