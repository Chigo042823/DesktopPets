import java.awt.Point;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ducky extends Pet {
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
                for (int i = 0; i < deltaX; i++) {
                    if (moveState == 0) {
                        if (i % 16 == 0) {
                            if (currentSprite > 3) {
                                currentSprite = 1;
                            }
                            makeIcon(label, getRightWalkPath() + currentSprite + ".png");
                            currentSprite++;
                        }
                        frame.setLocation((int) currentLocation.getX() + i, (int) currentLocation.getY());
                        try {
                            Thread.sleep(7);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            } else {
                for (int i = 0; i > deltaX; i--) {
                    if (moveState == 0) {
                        if (i % 16 == 0) {
                            if (currentSprite > 3) {
                                currentSprite = 1;
                            }
                            makeIcon(label, getLeftWalkPath() + currentSprite + ".png");
                            currentSprite++;
                        }
                        frame.setLocation((int) currentLocation.getX() + i, (int) currentLocation.getY());
                        try {
                            Thread.sleep(7);
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

    @Override
    public String getLeftWalkPath() {
        return "Sprites/Ducky/Walk/Left/Tile00";
    }

    @Override
    public String getRightWalkPath() {
        return "Sprites/Ducky/Walk/Right/Tile00";
    }

    @Override
    public String getIdlePath() {
        return "Sprites/Ducky/Idle/Tile00";
    }

    @Override
    public void attack() {
        JLabel label = this.petLabel;
        for (int i = 1; i < 6; i++) {
            makeIcon(label, "Sprites/Ducky/LeftRightCombo/Tile00" + i + ".png");
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

    @Override
    public void crawl() {
        Dimension resolution = screenResolution;
        JFrame frame = this.petFrame;
        JLabel label = this.petLabel;
        int currentSprite = 0;
        if (moveState == 3) {
            Point currentLocation = frame.getLocation();
            int nextLocation = (int) Math.floor((Math.random() * (resolution.getWidth() - label.getWidth())));
            int deltaX = (int) (nextLocation - currentLocation.getX());

            if (nextLocation > currentLocation.getX()) {
                for (int i = 0; i < deltaX; i++) {
                    if (moveState == 3) {
                        if (i % 16 == 0) {
                            if (currentSprite > 3) {
                                currentSprite = 0;
                            }
                            makeIcon(label, "Sprites/Ducky/Crawl/Right/Tile00" + currentSprite + ".png");
                            currentSprite++;
                        }
                        frame.setLocation((int) currentLocation.getX() + i, (int) currentLocation.getY());
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            } else {
                for (int i = 0; i > deltaX; i--) {
                    if (moveState == 3) {
                        if (i % 16 == 0) {
                            if (currentSprite > 3) {
                                currentSprite = 0;
                            }
                            makeIcon(label, "Sprites/Ducky/Crawl/Left/Tile00" + currentSprite + ".png");
                            currentSprite++;
                        }
                        frame.setLocation((int) currentLocation.getX() + i, (int) currentLocation.getY());
                        try {
                            Thread.sleep(10);
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

    @Override
    public void clicked() {
        int action = (int) Math.round(Math.random() * 0);
        System.out.println(action);
        switch (action) {
            case 0:
                crouch();
                break;
            default:
                break;
        }
        this.mouseClicked = false;
    }

    public void crouch() {
        JLabel label = this.petLabel;
        makeIcon(label, "Sprites/Ducky/Crouch/crouch.png");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
}
