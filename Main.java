import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String args[]) {
        
        File config = new File("config.txt");

        try {
            Scanner sc = new Scanner(config);
            int c = 1;
            while (sc.hasNextLine()) {
                if (c != 11) {
                    c++;
                    sc.nextLine();
                } else {
                    new Thread(strToPet(sc.nextLine())).start();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Pet strToPet(String str) {
        Pet pet = new Ducky();
        switch (str.toLowerCase().trim()) {
            case "ducky":
                pet = new Ducky();
                break;
            case "bird":
                pet = new Bird();
                break;
            case "cat":
                pet = new Cat();
                break;
            default:
                JOptionPane.showMessageDialog(null, str.toLowerCase() + " is not a viable pet choice");
                System.exit(0);
                break;
        }
        return pet;
    }
}