import java.awt.Point;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Cat extends Pet {
    @Override
    public void move() {
        Dimension resolution = screenResolution;
        JFrame frame = this.petFrame;
        JLabel label = this.petLabel;
        int currentSprite = 1;
        if (moveState == 0) {
            Point currentLocation = frame.getLocation();
            int nextLocation = (int) Math.floor((Math.random() * (resolution.getWidth() - label.getWidth())));
            int deltaX = (int) (nextLocation - currentLocation.getX());

            if (nextLocation > currentLocation.getX()) {
                String path = getRightWalkPath();
                for (int i = 0; i < deltaX; i++) {
                    if (moveState == 0) {
                        if (i % 16 == 0) {
                            if (currentSprite > 6) {
                                currentSprite = 1;
                            }
                            makeIcon(label, path + currentSprite + ".png");
                            currentSprite++;
                        }
                        frame.setLocation((int) currentLocation.getX() + i, (int) currentLocation.getY());
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            } else {
                String path = getLeftWalkPath();
                for (int i = 0; i > deltaX; i--) {
                    if (moveState == 0) {
                        if (i % 16 == 0) {
                            if (currentSprite > 6) {
                                currentSprite = 1;
                            }
                            makeIcon(label, path + currentSprite + ".png");
                            currentSprite++;
                        }
                        frame.setLocation((int) currentLocation.getX() + i, (int) currentLocation.getY());
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        idle();
    }

    //TODO put lep & right in path 
    @Override
    public String getLeftWalkPath() {
        int rand = (int) Math.round(Math.random() * (2));
        String path = new String();
        System.out.println(rand);
        switch (rand) {
            case 0:
                path = "Sprites/Cat/Walk/Left/Tile00";
                break;
            case 1:
                path = "Sprites/Cat/Jump/Left/tile00";
                break;
            case 2:
                path = "Sprites/Cat/Run/Left/tile00";
                break;
            default:
                break;
        }
        return path;
    }

    @Override
    public String getRightWalkPath() {
        int rand = (int) Math.round(Math.random() * (2));
        String path = new String();
        System.out.println(rand);
        switch (rand) {
            case 0:
                path = "Sprites/Cat/Walk/Right/Tile00";
                break;
            case 1:
                path = "Sprites/Cat/Jump/Right/Tile00";
                break;
            case 2:
                path = "Sprites/Cat/Run/Right/Tile00";
                break;
            default:
                break;
        }
        return path;
    }

    @Override
    public String getIdlePath() {
        return "Sprites/Cat/Idle/Tile00";
    }

    @Override
    public void attack() {
        idle();
    }

    @Override
    public void crawl() {
        idle();
    }

    @Override
    public void hover() {
        if (!this.heartThread.isAlive()) {
            this.heartThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    hearts();
                }
                
            });
            this.heartThread.start();
        }
        this.attack();
    }

    @Override
    public void clicked() {
        int action = (int) Math.round(Math.random() * 1);
        System.out.println(action);
        switch (action) {
            case 0:
                scared();
                break;
            case 1:
                wave();
                break;
            default:
                break;
        }
        this.mouseClicked = false;
    }

    public void scared() {
        JLabel label = this.petLabel;
        for (int i = 0; i <= 7; i++) {
            makeIcon(label, "Sprites/Cat/Scared/Tile00" + i + ".png");
            try {
                Thread.sleep(75);
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

    public void wave() {
        JLabel label = this.petLabel;
        for (int i = 0; i < 6; i++) {
            makeIcon(label, "Sprites/Cat/Wave/Tile00" + i + ".png");
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
}
