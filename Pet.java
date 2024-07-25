import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Pet implements Runnable {

    final Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();

    public JFrame petFrame = new JFrame();
    public JLabel petLabel = new JLabel();
    public JLabel heartLabel = new JLabel();
    public int moveState = 0;
    public int mouseHover = 0;
    public boolean mouseClicked = false; 
    public Thread heartThread = new Thread();
    
    public void idle() {
        JLabel label = this.petLabel;
        for (int i = 1; i < 4; i++) {
            makeIcon(label, getIdlePath() + i + ".png");
            try {
                Thread.sleep(125);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void makeIcon(JLabel label, String filePath) {
        ImageIcon icon = new ImageIcon(filePath);
        Image img = icon.getImage();
        Image imgScaled = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScaled);
        label.setIcon(scaledIcon);
    }
    
    public JFrame init() {
        JFrame mainFrame = this.petFrame;
        JLabel label = this.petLabel;
        JLabel heartLabel = this.heartLabel;

        ImageIcon favIcon = new ImageIcon(getIdlePath() + "1.png");

        mainFrame.setSize(100, 100);
        mainFrame.setUndecorated(true);
        mainFrame.setBackground(new Color(0, 0, 0, 0));
        mainFrame.setAlwaysOnTop(true);
        mainFrame.setIconImage(favIcon.getImage());

        label.setBounds(0, 0, 100, 100);

        heartLabel.setBounds(15, 0, 35, 30);

        mainFrame.add(heartLabel);
        mainFrame.add(label);

        Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();

        if (getLeftWalkPath().equals("Sprites/Ducky/Walk/Left/Tile00")) {
            mainFrame.setLocation((int) screenResolution.getWidth() - 90, (int) screenResolution.getHeight() - 75);
        } else {
            mainFrame.setLocation((int) screenResolution.getWidth() - 90, (int) screenResolution.getHeight() - 100);
        }

        mainFrame.setVisible(true);

        label.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                mouseClicked = true;
                System.out.println("CLicked");
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                moveState = 1;
                mouseHover = 1;
                System.out.println("Petting"); 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                moveState = 0;
                mouseHover = 0;
                System.out.println("Stopped");
            }
            
        });

        return mainFrame;
    }

    public void run() {
        init();
        int frames = 0;
        int decision = (int) Math.round((Math.random() * (2)));
        while (true) {
            if (mouseClicked) {
                clicked();
                System.out.println(mouseClicked);
                continue;
            }
            if (mouseHover == 1) {
                this.hover();
                continue;
            }
            if (frames % 6 == 0 && mouseHover != 1) {
                decision = (int) Math.round((Math.random() * (3)));
                // decision = 3;
                moveState = decision;
                System.out.println("Decision: " + decision);
            }
            switch (moveState) {
                case 0:
                    this.move();
                    break;
                case 1:
                    this.idle();
                    break;
                case 2:
                    this.move();
                    break;
                case 3:
                    this.crawl();
                    break;
                default:
                    break;
            }    
            frames++;
        }
    }

    public void hearts() {
        JLabel label = this.heartLabel;
        for (int i = 0; i < 24; i++) {
            makeIcon(label, getHeartsPath(i) + i + ".png");
            try {
                Thread.sleep(125);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        makeIcon(label, "");
    }

    public String getHeartsPath(int i) {
        if (i > 9) {
            return "Sprites/Heart/Tile0";
        }
        return "Sprites/Heart/Tile00";
    }

    public Thread getThreadByName(String threadName) {
    Thread __tmp = null;

    Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
    Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);

    for (int i = 0; i < threadArray.length; i++) {
        if (threadArray[i].getName().equals(threadName))
            __tmp =  threadArray[i];
    }

    return __tmp;
}

    public abstract void hover();

    public abstract String getLeftWalkPath();
    public abstract void attack();
    public abstract void crawl();
    public abstract String getRightWalkPath();
    public abstract String getIdlePath();
    public abstract void move();
    public abstract void clicked();
}
