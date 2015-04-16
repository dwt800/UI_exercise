import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UI_5stars extends JFrame implements ActionListener {
    int step;
    JButton butnStart = new JButton("重新开始");
    FiveChess chess = new FiveChess();
    FlowLayout flow = new FlowLayout();
    JPanel panelPaint=new JPanel(),panelEast=new JPanel();//panelPaint为画五子棋的面板

    UI_5stars(String s) {
        super(s);
        this.setBounds(100, 100, 600, 600);
        setLayout(new BorderLayout());
        setVisible(true);
        setResizable(false);
        add(panelPaint);
        panelEast.add(butnStart);
        add(panelEast,BorderLayout.EAST);
        butnStart.addActionListener(this);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(e.getModifiers()==InputEvent.BUTTON3_MASK)
                return;
                int x = e.getX()-3;//鼠标单击位置为在整个窗体中的位置,包括标题栏和侧边
                int y = e.getY()-23;
                System.out.println("鼠标单击位置：" + x + "," + y);
                Point index = chess.getIndex(x, y);//得到行列
                if (index != null) {
                    drawNode(index.x, index.y);
                    step++;
                    if (chess.isWin(index.x, index.y)) {
                        String str = chess.white ? "黑棋" : "白棋";
                        System.out.println(str + "在第" + step + "步赢了");
                        int option = JOptionPane.showConfirmDialog(
                                UI_5stars.this,
                                str + "在第" + step + "步赢了,是否再来一局", "五子棋",
                                JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            step = 0;// 重新置为零
                            clearExist();//清除已有棋子
                            chess.restate();//数据清零
                            repaint();
                            }
                        }
                    }
                }
            });
        repaint();
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    public void drawNode(int i, int j) {
        int x = chess.node[i][j].x;
        int y = chess.node[i][j].y;
        int state = chess.node[i][j].state;
        Graphics g = panelPaint.getGraphics();
        if (state == 1)
        g.setColor(Color.black);
        else if (state == 2)
        g.setColor(Color.white);
        g.drawOval(x - 6, y - 6, 12, 12);
        g.fillOval(x - 6, y - 6, 12, 12);
        }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == butnStart) {
            if (step != 0) {
                int option = JOptionPane.showConfirmDialog(UI_5stars.this, "是否放弃重来",
                        "五子棋", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    clearExist();
                    chess.restate();
                    step = 0;
                    repaint();
                    }
                }
            }
        }

    public void paint(Graphics gg) {// Graphics是在整个窗体描绘
        super.paint(gg);
        Graphics g=panelPaint.getGraphics();
        g.setColor(Color.magenta);
        for (int i = 1; i <= FiveChess.row_column; i++) {
            g.drawLine(10, i * FiveChess.interval, 548, i  * FiveChess.interval);
            g.drawLine(i * FiveChess.interval, 10, i * FiveChess.interval, 540);
            }
        drawExistNode();
        }

    void drawExistNode() {
        Graphics g = panelPaint.getGraphics();
        for (int i = 0; i < FiveChess.row_column; i++)
        for (int j = 0; j < FiveChess.row_column; j++) {
            if (chess.node[i][j].state == 1 || chess.node[i][j].state == 2) {
                if (chess.node[i][j].state == 1)
                g.setColor(Color.black);
                else if (chess.node[i][j].state == 2)
                g.setColor(Color.white);
                g.drawOval(chess.node[i][j].x - 6, chess.node[i][j].y - 6,
                        12, 12);
                g.fillOval(chess.node[i][j].x - 6, chess.node[i][j].y - 6,
                        12, 12);
                }
            }
        }

    public void clearExist() {
        Graphics g = panelPaint.getGraphics();
        g.clearRect(0, 0, 600, 600);
        }

    public static void main(String[] args) {
        UI_5stars cFrm=new UI_5stars("五子棋");
        }
    }

class FiveChess {
    boolean white;
    public static final int bound = 7;// 在离中心为7的范围内单击为有效单击(即下棋)
    public static final int row_column = 20;
    public static final int interval=26;//五子棋之间的距离
    Node node[][];

    public FiveChess() {
        init();
        }

    private void init() {
        node = new Node[row_column][row_column];
        for (int i = 0; i < row_column; i++)
        for (int j = 0; j < row_column; j++) {
            node[i][j] = new Node();
            node[i][j].x = (i + 1) * interval;
            node[i][j].y = (j + 1) * interval;
            }
        }

    Point getIndex(int x, int y) {
        int i = x / interval - 1;
        int j = y / interval - 1;
        if (i == -1)//边界
        i++;
        if (j == -1)
        j++;
        for (int p = i; p < i + 2; p++)
        for (int q = j; q < j + 2; q++) {
            if (p > row_column - 1 || q > row_column - 1) {
                System.out.println("越界");
                return null;
                }
            if (x > node[p][q].x - bound && x < node[p][q].x + bound
            && y > node[p][q].y - bound && y < node[p][q].y + bound)
            if (node[p][q].state == 0) {// 在范围内且此处没棋,才有效
                node[p][q].state = white ? 2 : 1;
                white = !white;
                return new Point(p, q);
                }
            }
        return null;
        }

    boolean isWin(int i, int j) {
        return (horizontal(i, j) || vertical(i, j) || diagonalSE(i, j) || diagonalNE(
                i, j));
        }

    private boolean vertical(int i, int j) {
        int count = 1;
        int up = j - 1;// 垂直向上方向判断
        while (up >= 0 && node[i][up].state == node[i][j].state) {
            count++;
            up--;// 千万注意自增自减运算
            if (count >= 5)
            return true;
            }
        int down = j + 1;// 接着垂直向下方向判断
        while (down < row_column && node[i][down].state == node[i][j].state) {
            count++;
            down++;
            if (count >= 5)
            return true;
            }
        return false;
        }

    private boolean horizontal(int i, int j) {
        int count = 1;
        int left = i - 1;//水平向左判断
        while (left >= 0 && node[left][j].state == node[i][j].state) {
            count++;
            left--;
            if (count >= 5)
            return true;
            }
        int right = i + 1;//水平向右判断
        while (right < row_column && node[right][j].state == node[i][j].state) {
            count++;
            right++;
            if (count >= 5)
            return true;
            }
        return false;
        }

    private boolean diagonalSE(int i, int j) {
        int count = 1;
        int north = j - 1;
        int west = i - 1;//西北方向判断
        while (north >= 0 && west >= 0
        && node[west][north].state == node[i][j].state) {
            count++;
            north--;
            west--;
            if (count >= 5)
            return true;
            }
        int south = j + 1;
        int east = i + 1;//接着东南方向判断
        while (south < row_column && east < row_column
        && node[east][south].state == node[i][j].state) {
            count++;
            south++;
            east++;
            if (count >= 5)
            return true;
            }
        return false;
        }

    private boolean diagonalNE(int i, int j) {
        int count = 1;
        int north = j - 1;
        int east = i + 1;//东北方向判断
        while (north >= 0 && east < row_column
        && node[east][north].state == node[i][j].state) {
            count++;
            north--;
            east++;
            if (count >= 5)
            return true;
            }
        int west = i - 1;
        int south = j + 1;//接着西南方向判断
        while (west >= 0 && south < row_column
        && node[west][south].state == node[i][j].state) {
            count++;
            west--;
            south++;
            if (count >= 5)
            return true;
            }
        return false;
        }

    void restate() {
        for (int i = 0; i < row_column; i++)
        for (int j = 0; j < row_column; j++) {
            node[i][j].state = 0;
            white = false;
            }
        }
    }

class Node {
    int x;
    int y;
    int state;// 0无棋子,1黑棋,2白棋
    }
