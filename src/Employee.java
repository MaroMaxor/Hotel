import java.util.ArrayList;

public class Employee extends Person implements java.io.Serializable {
    public Employee(String username, String password, int id, Gender gender) {

        super(username, password, id, gender);
    }


    //Change Description
    public void changeDescription(ArrayList<Floor> floors, int floorNumber, int roomNumber, String description) {
        for (Floor x : floors) {
            if(floorNumber == x.getFloorNumber()) {
               x.rooms.get(roomNumber - 1).setDescription(description);
            }
        }
    }

    //Get The Data Of A Specific Client
    public void getDataOfClient(ArrayList<Client> clients, String clientName) {
        for (Client client : clients) {
            if (client.getUsername().startsWith(clientName))
                client.display();
        }

    }

    //To Create Floor and Room
    public void createRoom(ArrayList<Floor> floors, int floorNumber, int roomNumber, double area, double price, String description) {

        int index = -1;
        for (int i = 0; i < floors.size(); i++) {
            if (floors.get(i).getFloorNumber() == floorNumber) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            Floor floor = new Floor();
            floor.setFloorNumber(floorNumber);
            Room r = new Room(roomNumber, price, area, description);
            floor.rooms.add(r);
            floors.add(floor);
        } else {
            Room r = new Room(roomNumber, price, area, description);
            floors.get(index).rooms.add(r);
        }

    }

    public void display() {
        System.out.println("Username : " + getUsername() + "\nID : " + getId() + "\nGender : " + getGender());
    }


}
