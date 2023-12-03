import javax.swing.JFrame;
import java.awt.*;

public class Dim extends JFrame {
    static Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static JFrame F = new Dim();
    Screen ScreenObject = new Screen();


    public Dim() {
        add(ScreenObject);
        setUndecorated(true);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);
    }

    public static void main(String[] args) {
        //System.out.println("Hello world!");
    }
}