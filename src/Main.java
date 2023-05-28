import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        //ID Generator
        Random idGenerator = new Random();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Floor> floors = new ArrayList<>();

        Streams.fileReaderClient(clients);
        Streams.fileReaderEmployee(employees);
        Streams.fileReaderFloor(floors);


        System.out.println("Enter 1: To Register\nEnter 2: To LogIn");
        int logOrReg = sc.nextInt();
        while (logOrReg == 1 || logOrReg == 2) {
            switch (logOrReg) {
                case 1:
                    System.out.println("Enter 1 To Register As a Client\nEnter 2 To Register As an Employee");
                    int entering = sc.nextInt();
                    while (entering == 1 || entering == 2) {
                        switch (entering) {
                            //Client Registration
                            case 1:
                                clientRegistration(sc, idGenerator, floors, clients);
                                break;
                            //Employee Registration
                            case 2:
                                employeeRegistration(sc, idGenerator, floors, employees, clients);
                                break;
                        }
                        System.out.println("Type 1:To Register As a Client" +
                                "\nType 2:To Register As an Employee" +
                                "\nType 0:To Exit This Phase");
                        entering = sc.nextInt();
                    }
                    break;
                //Log In
                case 2:
                    System.out.println("Enter 1: Log In As A Client" +
                            "\nEnter 2: Log In As An Employee" +
                            "\nEnter 0: To Exit");
                    //Enter 1 For Client and 2 for Employee
                    entering = sc.nextInt();
                    while (entering == 1 || entering == 2) {
                        switch (entering) {
                            //Log In As A Client
                            case 1:
                                clientLogIn(sc, floors, clients);
                                break;
                            //Log In As An Employee
                            case 2:
                                employeeLogIn(sc, floors, employees, clients);
                                break;
                        }
                        System.out.println("Enter 1: Log In As A Client" +
                                "\nEnter 2: Log In As An Employee" +
                                "\nEnter 0: To Exit");
                        entering = sc.nextInt();
                    }
                    break;
            }
            System.out.println("Enter 1: To Register\nEnter 2: To LogIn\nEnter 0: To Exit");
            logOrReg = sc.nextInt();
        }
    }

    public static void employeeLogIn(Scanner sc, ArrayList<Floor> floors, ArrayList<Employee> employees, ArrayList<Client> clients) throws Exception {
        System.out.println("Enter Username!");
        String username = sc.next();
        System.out.println("Enter Password!");
        String password = sc.next().trim();
        for (Employee x : employees) {
            if (x.getUsername().equals(username) && x.getPassword().equals(password)) {
                employeeOptions(sc, floors, clients, x);
                Streams.fileWriterFloorAndRoom(floors);
                Streams.fileWriterEmployee(employees);
            }
        }
    }

    public static void clientLogIn(Scanner sc, ArrayList<Floor> floors, ArrayList<Client> clients) throws Exception {
        System.out.println("Enter Username!");
        String username = sc.next();
        System.out.println("Enter Password!");
        String password = sc.next().trim();
        for (Client x : clients) {
            if (x.getUsername().equals(username) && x.getPassword().equals(password)) {
                clientOptions(sc, floors, x);
                Streams.fileWriterClient(clients);
                Streams.fileWriterFloorAndRoom(floors);
            }
        }
    }

    //Client Registration
    public static void clientRegistration(Scanner sc, Random idGenerator, ArrayList<Floor> floors, ArrayList<Client> clients) throws Exception {
        System.out.println("Enter Your Username");
        String username = sc.next();

        System.out.println("Enter Your Password");
        String password = sc.next().trim();

        int idGen = idGenerator.nextInt();

        System.out.println("Enter Your Gender!" + "\nEnter 1:Male\nEnter 2:Female");
        int genderSelection = sc.nextInt();
        Gender gender = null;
        if (genderSelection == 1) {
            gender = Gender.MALE;
        } else if (genderSelection == 2) {
            gender = Gender.FEMALE;
        }
        Client c = new Client(username, password, idGen, gender);
        clients.add(c);
        Streams.fileWriterClient(clients);
        clientOptions(sc, floors, c);
        Streams.fileWriterClient(clients);
        Streams.fileWriterFloorAndRoom(floors);

    }

    //Client Options
    public static void clientOptions(Scanner sc, ArrayList<Floor> floors, Client c) {
        System.out.println("Options!");
        System.out.println("Enter 1:Reserve A Room" +
                "\nEnter 2: Search For A Room" +
                "\nEnter 3: Search For Rooms In A Specific Range By Area" +
                "\nEnter 4: Search For Rooms In A Specific Range By Price" +
                "\nEnter 0: To Exit");
        int options = sc.nextInt();
        while (options != 0) {
            switch (options) {
                //Reserve A Room
                case 1:
                    reserveRoom(sc, floors, c);
                    break;
                //Search For A Room
                case 2:
                    searchBetweenRooms(sc, floors);
                    break;
                //Search For Rooms In A Specific Range By Area
                case 3:
                    searchBetweenRoomsArea(sc, floors);
                    break;
                case 4:
                    searchBetweenRoomsPrice(sc, floors);
                    break;
            }
            System.out.println("Enter 1:Reserve A Room" +
                    "\nEnter 2: Search For A Room" +
                    "\nEnter 3: Search For Rooms In A Specific Range By Area" +
                    "\nEnter 4: Search For Rooms In A Specific Range By Price" +
                    "\nEnter 0: To Exit");
            options = sc.nextInt();
        }
    }

    //Employee Registration
    public static void employeeRegistration(Scanner sc, Random idGenerator, ArrayList<Floor> floors, ArrayList<Employee> employees, ArrayList<Client> clients) throws Exception {
        System.out.println("Enter Your Username!");
        String username = sc.next();

        System.out.println("Enter Your Password!");
        String password = sc.next().trim();
        System.out.println("Enter Your Gender!" + "\nEnter 1:Male\nEnter 2:Female");
        int genderSelection = sc.nextInt();
        Gender gender = null;
        if (genderSelection == 1) {
            gender = Gender.MALE;
        } else if (genderSelection == 2) {
            gender = Gender.FEMALE;
        }

        int idGen = idGenerator.nextInt();
        Employee e = new Employee(username, password, idGen, gender);
        employees.add(e);
        Streams.fileWriterEmployee(employees);
        employeeOptions(sc, floors, clients, e);
        Streams.fileWriterEmployee(employees);
        Streams.fileWriterFloorAndRoom(floors);
    }

    //Employee Options
    public static void employeeOptions(Scanner sc, ArrayList<Floor> floors, ArrayList<Client> clients, Employee e) {
        System.out.println("Options!");
        System.out.println("Enter 1: Create Room" +
                "\nEnter 2: Change The Description Of A Room" +
                "\nEnter 3: Get Data Of A Client" +
                "\nEnter 4: Search For A Room" +
                "\nEnter 5: Search For Rooms In A Specific Range By Area" +
                "\nEnter 6: Search For Rooms In A Specific Range By Price" +
                "\nEnter 0: Exit");
        int options = sc.nextInt();
        while (options != 0) {
            switch (options) {

                //Create A Room
                case 1:
                    createRoom(sc, floors, e);
                    break;
                //Change The Description
                case 2:
                    changeDescription(sc, floors, e);
                    break;
                //Get Data Of A Client
                case 3:
                    getDataOfClient(sc, floors, clients, e);
                    break;

                //Search For A Room
                case 4:
                    searchBetweenRooms(sc, floors);
                    break;
                //Search For Rooms In A Specific Range By Area
                case 5:
                    searchBetweenRoomsArea(sc, floors);
                    //Search For Rooms In A Specific Range By Price
                case 6:
                    searchBetweenRoomsPrice(sc, floors);
                    break;
            }
            System.out.println("Enter 1: Create Room" +
                    "\nEnter 2: Change The Description Of A Room" +
                    "\nEnter 3: Get Data Of A Client" +
                    "\nEnter 4: Search For A Room" +
                    "\nEnter 5 Search For Rooms In A Specific Range By Area" +
                    "\nEnter 6 Search For Rooms In A Specific Range By Price" +
                    "\nEnter 0: Exit");
            options = sc.nextInt();
        }
    }

    //Create A Room
    public static void createRoom(Scanner sc, ArrayList<Floor> floors, Employee e) {
        try {
            System.out.println("Enter The Floor Number!");
            int floorNumber = sc.nextInt();
            System.out.println("Enter The Room Number!");
            int roomNumber = sc.nextInt();
            System.out.println("Enter The Price Of The Room!");
            double price = sc.nextDouble();
            System.out.println("Enter The Area Of The Room!");
            double area = sc.nextDouble();
            System.out.println("Enter The Description Of The Room!");
            String description = sc.next();
            e.createRoom(floors, floorNumber, roomNumber, price, area, description);
        } catch (InputMismatchException x) {
            System.out.println("Wrong Input Type!");
        }
    }

    //Change Description
    public static void changeDescription(Scanner sc, ArrayList<Floor> floors, Employee e) {
        try {
            System.out.println("Enter The Floor Number!");
            int floorNumber = sc.nextInt();
            System.out.println("Enter The Room Number!");
            int roomNumber = sc.nextInt();
            System.out.println("Enter The New Description!");
            String description = sc.next();
            e.changeDescription(floors, floorNumber, roomNumber, description);
        } catch (InputMismatchException x) {
            System.out.println("Wrong Input Type!");
        } catch (NullPointerException x) {
            System.out.println("The Floor Or Room Doesn't Exist!");
        }
    }

    //Get Data Of A Client
    public static void getDataOfClient(Scanner sc, ArrayList<Floor> floors, ArrayList<Client> clients, Employee e) {
        try {
            System.out.println("Enter The Name Of The Client!");
            String clientName = sc.next();
            e.getDataOfClient(clients, clientName);
        } catch (InputMismatchException x) {
            System.out.println("Wrong Input Type!");
        } catch (NullPointerException x) {
            System.out.println("The Client Doesn't Exist!");
        }
    }

    //Reserve A Room
    public static void reserveRoom(Scanner sc, ArrayList<Floor> floors, Client c) {
        try {
            System.out.println("Enter The Floor Number!");
            int floorNumber = sc.nextInt();
            System.out.println("Enter The Room Number!");
            int roomNumber = sc.nextInt();
            c.reserveRoom(floors, c, floorNumber, roomNumber);
        } catch (InputMismatchException e) {
            System.out.println("Wrong Input Type!");
        } catch (NullPointerException e) {
            System.out.println("The Floor Or Room Doesn't Exist!");
        }
    }

    //Search For A Room
    public static void searchBetweenRooms(Scanner sc, ArrayList<Floor> floors) {
        try {
            System.out.println("Enter The Floor Number!");
            int floorNumber = sc.nextInt();
            System.out.println("Enter The Room Number!");
            int roomNumber = sc.nextInt();
            Room.searchBetweenRooms(floors, floorNumber, roomNumber);
        } catch (InputMismatchException e) {
            System.out.println("Wrong Input Type!");
        } catch (NullPointerException e) {
            System.out.println("The Floor Or Room Doesn't Exist!");
        }
    }

    //Search Between Rooms By Area
    public static void searchBetweenRoomsArea(Scanner sc, ArrayList<Floor> floors) {
        try {

            System.out.println("Enter The Range For Your Search By Area!\nEnter The First Number!");
            double min = sc.nextInt();
            System.out.println("Enter The Second Number!");
            double max = sc.nextInt();
            Room.searchBetweenRoomsArea(min, max, floors);
        } catch (InputMismatchException e) {
            System.out.println("Wrong Input Type!");
        } catch (NullPointerException e) {
            System.out.println("The Floor Or Room Doesn't Exist!");
        }
    }

    //Search Between Rooms By Price
    public static void searchBetweenRoomsPrice(Scanner sc, ArrayList<Floor> floors) {
        try {
            System.out.println("Enter The Range For Your Search By Price!\nEnter The First Number!");
            double min = sc.nextInt();
            System.out.println("Enter The Second Number!");
            double max = sc.nextInt();
            Room.searchBetweenRoomsPrice(min, max, floors);
        } catch (InputMismatchException e) {
            System.out.println("Wrong Input Type!");
        } catch (NullPointerException e) {
            System.out.println("The Floor Or Room Doesn't Exist!");
        }
    }
}