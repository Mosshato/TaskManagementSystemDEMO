package org.example;
import java.util.Scanner;

public class InputDevice {

    public Integer chooseOption(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            int option = Integer.parseInt(input);
            return option;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void inputGiveTask() {
    }


}
