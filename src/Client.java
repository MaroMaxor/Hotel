import java.util.ArrayList;


public class Client extends Person implements Compare, java.io.Serializable {

    public Client(String username, String password, int id, Gender gender) {
        super(username, password, id, gender);
    }

    ArrayList<Room> roomsReserved = new ArrayList<>();

    //Reserve A Room
    public void reserveRoom(ArrayList<Floor> floors, Client c, int floorNumber, int roomNumber) {
        for (Floor x : floors) {
            for (int i = 0; i < x.rooms.size(); i++) {
                if (x.getFloorNumber() == floorNumber && x.rooms.get(i).getRoomNumber() == roomNumber
                        && x.rooms.get(i).getReservationStatus() == roomStatus.NotReserved) {

                    roomsReserved.add(x.rooms.get(i));
                    x.rooms.get(i).setReservationStatus(roomStatus.Reserved);
                    x.rooms.get(i).setClient(c);
                    System.out.println(" The Room Is Reserved Successfully!!");
                }
            }
        }
    }

    //To Display The Data Of A Client
    public void display() {
        System.out.println("Username : " + getUsername() + "\nID : " + getId() + "\nGender : " + getGender());
        System.out.println("Rooms Reserved : ");
        for (Room room : roomsReserved) {
            System.out.println("\nRoom : " + room.getRoomNumber() + "\n");
        }

    }

    //Compare Between Rooms By Price
    public String compareBetweenRoomsPrice(ArrayList<Floor> f, int floorNumber1, int roomNumber1, int floorNumber2, int roomNumber2) {
        Room r1 = null;
        Room r2 = null;
        //Room 1
        for (Floor x : f) {
            for (int i = 0; i < x.rooms.size(); i++) {
                if (x.getFloorNumber() == floorNumber1 && x.rooms.get(i).getRoomNumber() == roomNumber1) {
                    r1 = x.rooms.get(i);
                    break;
                }
            }
        }
        //Room 2
        for (Floor x : f) {
            for (int i = 0; i < x.rooms.size(); i++) {
                if (x.getFloorNumber() == floorNumber2 && x.rooms.get(i).getRoomNumber() == roomNumber2) {
                    r2 = x.rooms.get(i);
                    break;
                }
            }
        }
        if (r1 != null || r2 != null) {
            if (r1.getPrice() > r2.getPrice()) {
                double amount = r1.getPrice() - r2.getPrice();
                return "Room Number: " + roomNumber1 + " in Floor Number: " + floorNumber1 + " (More Expensive) "
                        + "Room Number: " + roomNumber2 + " in Floor Number: " + floorNumber2
                        + "\nBy Amount(" + amount + ")";

            } else if (r2.getPrice() > r1.getPrice()) {
                double amount = r2.getPrice() - r1.getPrice();
                return "Room Number: " + roomNumber2 + " in Floor Number: " + floorNumber2 + " (More Expensive) "
                        + "Room Number: " + roomNumber1 + " in Floor Number: " + floorNumber1
                        + "\nBy Amount(" + amount + ")";

            } else {
                return "The Two Rooms Are The Same Price: " + r1.getPrice();
            }
        } else {
            return "Either One Of The Rooms Or Both Doesn't Exist";
        }
    }
}




