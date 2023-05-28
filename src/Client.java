import java.util.ArrayList;


public class Client extends Person implements Compare, java.io.Serializable {
    private double wallet = 0;

    public Client(String username, String password, int id, Gender gender) {
        super(username, password, id, gender);
    }

    ArrayList<Room> roomsReserved = new ArrayList<>();

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    //Reserve A Room
    public void reserveRoom(ArrayList<Floor> f, Client c, int floorNumber, int roomNumber) {
        if (f.get(floorNumber - 1).rooms.get(roomNumber - 1).getReservationStatus().equals(roomStatus.Reserved)) {
            System.out.println("Sorry The Room Is Reserved!");
        } else {
            roomsReserved.add(f.get(floorNumber - 1).rooms.get(roomNumber - 1));
            f.get(floorNumber - 1).rooms.get(roomNumber - 1).setReservationStatus(roomStatus.Reserved);
            f.get(floorNumber - 1).rooms.get(roomNumber - 1).setClient(c);
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
    public String compareBetweenRoomsPrice(ArrayList<Floor>f, int floorNumber1, int roomNumber1, int floorNumber2, int roomNumber2) {

        if (f.get(floorNumber1- 1).rooms.get(roomNumber1-1).getPrice() > f.get(floorNumber2- 1).rooms.get(roomNumber2-1).getPrice()) {
            double amount = f.get(floorNumber1- 1).rooms.get(roomNumber1-1).getPrice() - f.get(floorNumber2- 1).rooms.get(roomNumber2-1).getPrice();
            return "Room Number: " + roomNumber1 + " in Floor Number: " + floorNumber1 + " (More Expensive) "
                    + "Room Number: " + roomNumber2 + " in Floor Number: " + floorNumber2
                    + "\nBy Amount(" + amount + ")";

        } else if (f.get(floorNumber2- 1).rooms.get(roomNumber2-1).getPrice() > f.get(floorNumber1- 1).rooms.get(roomNumber1-1).getPrice()) {
            double amount = f.get(floorNumber2- 1).rooms.get(roomNumber2-1).getPrice() - f.get(floorNumber1- 1).rooms.get(roomNumber1-1).getPrice();
            return "Room Number: " + roomNumber2 + " in Floor Number: " + floorNumber2 + " (More Expensive) "
                    + "Room Number: " + roomNumber1 + " in Floor Number: " + floorNumber1
                    + "\nBy Amount(" + amount + ")";

        } else {
            return "The Two Rooms Are The Same Price: " + f.get(floorNumber1- 1).rooms.get(roomNumber1-1).getPrice();
        }
    }
}




