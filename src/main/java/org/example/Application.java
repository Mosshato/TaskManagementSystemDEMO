package org.example;

public class Application {

    OutputDevice outputDevice = new OutputDevice();
    InputDevice inputDevice = new InputDevice();

    public void run(String[] args) {

        //outputDevice.beforeLogin();
        Integer option = Integer.valueOf(args[0]);
        while (true) {
            //option = inputDevice.chooseOption();

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
                System.out.println(boss.name);
                boss.whileIsLoggedIn();
                boss.logout();
            } else if (option == 4 ) {
                System.exit(0);
            } else {
                System.out.println(option + " is not a valid option!\n");
                System.exit(0);
            }
        }
    }
}
