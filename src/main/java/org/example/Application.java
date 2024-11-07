package org.example;

public class Application {

    OutputDevice outputDevice = new OutputDevice();
    InputDevice inputDevice = new InputDevice();

    public void run() {

        outputDevice.beforeLogin();
        Integer option;
        while (true) {
            option = inputDevice.chooseOption();

            if (option == 1) {
                Employee employee = new Employee("John Carter");
                employee.login();
                System.out.println(employee.name);
                employee.whileIsLoggedIn();
                employee.logout();
            } else if (option == 2) {
                Manager manager = new Manager("Alice Johnson");
                manager.login();
                System.out.println(manager.name);
                manager.whileIsLoggedIn();
                manager.logout();
            } else if (option == 3) {
                BigBoss boss = new BigBoss("Babtan Alexandru");
                boss.login();
                boss.whileIsLoggedIn();
                boss.logout();
            } else if (option == 4 ) {
                System.exit(0);
            } else {
                System.out.println("Write a valid option!\n");
            }
        }
    }
}
