import java.io.*;
import java.util.ArrayList;

public class Streams {
    private static File clientFile = new File("/F:/Clients.txt");
    private static File employeeFile = new File("/F:/Employees.txt");
    private static File floorFile = new File("/F:/Rooms.txt");

    private static FileInputStream reader;
    private static ObjectInputStream readerStream;

    //Client Streams
    public static void fileWriterClient(ArrayList<Client> clients) throws Exception {
        FileOutputStream writer = new FileOutputStream(clientFile);
        ObjectOutputStream writerStream = new ObjectOutputStream(writer);
        writerStream.writeObject(clients);
        writerStream.flush();
        writerStream.close();
    }

    public static void fileReaderClient(ArrayList<Client> clients) throws Exception {
        if (clientFile.length() != 0) {
            FileInputStream readerClient = new FileInputStream(clientFile);
            readerStream = new ObjectInputStream(readerClient);

            clients.clear(); // Clear the existing clients
            clients.addAll((ArrayList<Client>) readerStream.readObject());
            readerStream.close();
        }
    }
//Employee Streams

    public static void fileWriterEmployee(ArrayList<Employee> employees) throws Exception {
        FileOutputStream writer = new FileOutputStream(employeeFile);
        ObjectOutputStream writerStream = new ObjectOutputStream(writer);
        writerStream.writeObject(employees);
        writerStream.flush();
        writerStream.close();
    }

    public static void fileReaderEmployee(ArrayList<Employee> employees) throws Exception {
        if (clientFile.length() != 0) {
            FileInputStream readerClient = new FileInputStream(employeeFile);
            readerStream = new ObjectInputStream(readerClient);

            employees.clear(); // Clear the existing clients
            employees.addAll((ArrayList<Employee>) readerStream.readObject());
            readerStream.close();
        }
    }

    //Floor and Room Streams
    public static void fileWriterFloorAndRoom(ArrayList<Floor> floors) throws Exception {
        FileOutputStream writer = new FileOutputStream(floorFile);
        ObjectOutputStream writerStream = new ObjectOutputStream(writer);
        writerStream.writeObject(floors);
        writerStream.flush();
        writerStream.close();
    }

    public static void fileReaderFloor(ArrayList<Floor> floors) throws Exception {
        if (floorFile.length() != 0) {
            FileInputStream readerClient = new FileInputStream(floorFile);
            readerStream = new ObjectInputStream(readerClient);
            floors.clear(); // Clear the existing clients
            floors.addAll((ArrayList<Floor>) readerStream.readObject());
            readerStream.close();
        }
    }

}
