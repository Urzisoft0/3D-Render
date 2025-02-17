import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen extends JPanel implements KeyListener {

    double SleepTime = 1000 / 30, LastRefresh = 0;
    static double[] ViewFrom = new double[] {10, 10, 10};
    static double[] ViewTo = new double[] {1, 1, 1.5};
    static int NumberOfPolygons = 0, NumberOf3DPolygons = 0;
    static PolygonObject[] DrawablePolygons = new PolygonObject[100];
    PolygonObject Poly1;
    int[] NewOrder;

    static TriDim[] DPolygons = new TriDim[100];

    public Screen() {
        DPolygons[NumberOf3DPolygons] = new TriDim(new double[]{0, 2, 2, 0}, new double[]{0, 0, 2, 2}
                , new double[]{0, 0, 0, 0}, Color.black);
        DPolygons[NumberOf3DPolygons] = new TriDim(new double[]{0, 2, 2, 0}, new double[]{0, 0, 2, 2}
                , new double[]{3, 3, 3, 3}, Color.black);
        DPolygons[NumberOf3DPolygons] = new TriDim(new double[]{0, 2, 2, 0}, new double[]{0, 0, 0, 0}
                , new double[]{0, 0, 3, 3}, Color.black);
        DPolygons[NumberOf3DPolygons] = new TriDim(new double[]{0, 2, 2, 0}, new double[]{2, 2, 2, 2}
                , new double[]{0, 0, 3, 3}, Color.black);
        DPolygons[NumberOf3DPolygons] = new TriDim(new double[]{0, 0, 0, 0}, new double[]{0, 2, 2, 0}
                , new double[]{0, 0, 3, 3}, Color.black);
        DPolygons[NumberOf3DPolygons] = new TriDim(new double[]{2, 2, 2, 2}, new double[]{0, 2, 2, 0}
                , new double[]{0, 0, 3, 3}, Color.black);
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, 2000, 1200);
        g.drawString(System.currentTimeMillis() + "", 20, 20);

        DPolygons[0].updatePoylgon();

        for (int i = 0; i < NumberOf3DPolygons; i++)
            DPolygons[i].updatePoylgon();

        setOrder();
        for (int i = 0; i < NumberOfPolygons; i++)
            DrawablePolygons[NewOrder[i]].drawPolygon(g);
        SleepAndRefresh();
    }

    void setOrder() {
        double[] k  = new double[NumberOfPolygons];
        NewOrder = new int[NumberOfPolygons];

        for (int i = 0; i < NumberOfPolygons; i++) {
            k[i] = DrawablePolygons[i].AvgDistance;
            NewOrder[i] = i;
        }

        double temp;
        int tempr;
        for (int a = 0; a < k.length - 1; a++)
            for (int b = 0; b < k.length - 1; b++)
                if (k[b] < k[b + 1]) {
                    temp = k[b];
                    tempr = NewOrder[b];
                    NewOrder[b] = NewOrder[b + 1];
                    k[b] = k[b + 1];

                    NewOrder[b + 1] = tempr;
                    k[b + 1] = temp;
                }
    }


    void SleepAndRefresh() {
        while(true) {
            if ((System.currentTimeMillis() - LastRefresh) > SleepTime) {
                LastRefresh = System.currentTimeMillis();
                repaint();
                break;
            }
            else {
                try {
                    Thread.sleep((long)(SleepTime - (System.currentTimeMillis() - LastRefresh)));
                }
                catch(Exception e) {

                }
            }
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ViewFrom[0]--;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ViewFrom[0]++;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            ViewFrom[1]++;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ViewFrom[1]--;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
