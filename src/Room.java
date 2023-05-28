import java.util.ArrayList;

public class Room implements java.io.Serializable {
    private int roomNumber;
    private double price;
    private double area;
    private String description;
    private roomStatus reservationStatus = roomStatus.NotReserved;
    private Client client;

    public Room(int roomNumber, double price, double area, String description) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.area = area;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public roomStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(roomStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    //Get The Data Of The Room
    public void displayRoom(int floorNumber) {
        if (client == null) {
            System.out.println("Floor Number : " + floorNumber +
                    "\nRoom Number : " + roomNumber +
                    "\nRoom Status : " + reservationStatus +
                    "\nDescription : " + description +
                    "\nArea : " + area + "\n\n");
        } else {

            System.out.println("Floor Number : " + floorNumber +
                    "\nRoom Number : " + roomNumber +
                    "\nRoom Status : " + reservationStatus +
                    "\nDescription : " + description +
                    "\nClient Name : " + client.getUsername() + "\n\n");
        }
    }

    //Search For A Specific Room
    public static void searchBetweenRooms(ArrayList<Floor> f, int floorNumber1, int roomNumber1) {
        for (Floor x : f) {
            if (floorNumber1 == x.getFloorNumber()) {
                x.rooms.get(roomNumber1 - 1).displayRoom(floorNumber1);
                break;
            }
        }
    }

    //Search Between A Range Of Area
    public static void searchBetweenRoomsArea(double min, double max, ArrayList<Floor> f) {
        max = Math.max(min, max);
        min = Math.min(min, max);
        for (int i = 0; i < f.size(); i++) {
            for (int j = 0; j < f.get(i).rooms.size(); j++) {
                if (f.get(i).rooms.get(j).getArea() >= min && f.get(i).rooms.get(j).getArea() <= max) {
                    f.get(i).rooms.get(j).displayRoom(f.get(j).getFloorNumber());
                    System.out.println("\n\n");
                }
            }
        }
    }

    //Search Between A Range Of Price
    public static void searchBetweenRoomsPrice(double min, double max, ArrayList<Floor> f) {
        max = Math.max(min, max);
        min = Math.min(min, max);
        for (int i = 0; i < f.size(); i++) {
            for (int j = 0; j < f.get(i).rooms.size(); j++) {
                if (f.get(i).rooms.get(j).getPrice() >= min && f.get(i).rooms.get(j).getPrice() <= max) {
                    f.get(i).rooms.get(j).displayRoom(f.get(j).getFloorNumber());
                    System.out.println("\n\n");
                }
            }
        }
    }
}
