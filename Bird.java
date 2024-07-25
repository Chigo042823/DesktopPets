import java.awt.Point;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Bird extends Pet {

    @Override
    public void move() {
        Dimension resolution = screenResolution;
        JFrame frame = this.petFrame;
        JLabel label = this.petLabel;
        int currentSprite = 1;
        if (moveState == 0) {
            Point currentLocation = frame.getLocation();
            int nextLocationX = (int) Math.floor((Math.random() * (resolution.getWidth() - label.getWidth())));
            int nextLocationY = (int) Math.floor((Math.random() * (resolution.getHeight() - label.getHeight())));
            int deltaX = (int) (nextLocationX - currentLocation.getX());
            double slope = (nextLocationY - currentLocation.getY())/deltaX;

            if (nextLocationX > currentLocation.getX()) {
                for (int i = 0; i < deltaX; i++) {
                    if (moveState == 0) {
                        try {
                            Thread.sleep(4);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if (i % 16 == 0) {
                            if (currentSprite > 6) {
                                currentSprite = 1;
                            }
                            makeIcon(label, getRightWalkPath() + currentSprite + ".png");
                            currentSprite++;
                        }
                        frame.setLocation((int) currentLocation.getX() + i, (int) currentLocation.getY() + (int) ((double) i * (slope)));
                    } else {
                        break;
                    }
                }
            } else {
                for (int i = 0; i > deltaX; i--) {
                    if (moveState == 0) {
                        try {
                            Thread.sleep(4);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if (i % 16 == 0) {
                            if (currentSprite > 6) {
                                currentSprite = 1;
                            }
                            makeIcon(label, getLeftWalkPath() + currentSprite + ".png");
                            currentSprite++;
                        }
                        frame.setLocation((int) currentLocation.getX() + i, (int) currentLocation.getY() + (int) ((double) i * slope));
                    } else {
                        break;
                    }
                }
            }
        }
        idle();
    }

    @Override
    public String getLeftWalkPath() {
        return "Sprites/Bird/Walk/Left/Tile00";
    }

    @Override
    public String getRightWalkPath() {
        return "Sprites/Bird/Walk/Right/Tile00";
    }

    @Override
    public String getIdlePath() {
        return "Sprites/Bird/Idle/Tile00";
    }

    @Override
    public void attack() {
        this.idle();
    }

    @Override
    public void crawl() {
        this.idle();
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
        this.idle();
    }
}
