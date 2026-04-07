import java.util.Map;
import java.util.HashMap;

class Room {
    private String type;
    private int beds;
    private int size;
    private double price;
    private int available;

    public Room(String type, int beds, int size, double price, int available) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
        this.available = available;
    }

    public String getType() {
        return type;
    }

    public int getAvailable() {
        return available;
    }

    public void displayDetails() {
        System.out.println(type + " Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
        System.out.println();
    }
}

class RoomInventory {
    private Map<String, Integer> availability;

    public RoomInventory(Room single, Room doubleRoom, Room suite) {
        availability = new HashMap<>();
        availability.put("Single", single.getAvailable());
        availability.put("Double", doubleRoom.getAvailable());
        availability.put("Suite", suite.getAvailable());
    }

    public Map<String, Integer> getRoomAvailability() {
        return availability;
    }
}

class RoomSearchService {
    public void searchAvailableRooms(RoomInventory inventory, Room single, Room doubleRoom, Room suite) {
        Map<String, Integer> availability = inventory.getRoomAvailability();
        if (availability.get("Single") > 0) {
            single.displayDetails();
        }
        if (availability.get("Double") > 0) {
            doubleRoom.displayDetails();
        }
        if (availability.get("Suite") > 0) {
            suite.displayDetails();
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        Room single = new Room("Single", 1, 250, 1500.0, 5);
        Room doubleRoom = new Room("Double", 2, 400, 2500.0, 3);
        Room suite = new Room("Suite", 3, 750, 5000.0, 2);

        RoomInventory inventory = new RoomInventory(single, doubleRoom, suite);
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, single, doubleRoom, suite);
    }
}