import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> availableRooms;

    public RoomInventory() {
        availableRooms = new HashMap<>();
        availableRooms.put("Single", 2);
        availableRooms.put("Suite", 1);
    }

    public boolean isAvailable(String roomType) {
        return availableRooms.getOrDefault(roomType, 0) > 0;
    }

    public void decrement(String roomType) {
        availableRooms.put(roomType, availableRooms.get(roomType) - 1);
    }
}

class RoomAllocationService {
    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String roomType = reservation.getRoomType();
        if (inventory.isAvailable(roomType)) {
            String roomId = generateRoomId(roomType);
            allocatedRoomIds.add(roomId);
            assignedRoomsByType.computeIfAbsent(roomType, k -> new HashSet<>()).add(roomId);
            inventory.decrement(roomType);
            System.out.println("Booking confirmed for Guest: " + reservation.getGuestName() + ", Room ID: " + roomId);
        } else {
            System.out.println("No rooms available for Guest: " + reservation.getGuestName());
        }
    }

    private String generateRoomId(String roomType) {
        int count = assignedRoomsByType.getOrDefault(roomType, new HashSet<>()).size() + 1;
        return roomType + "-" + count;
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        Queue<Reservation> requestQueue = new LinkedList<>();
        requestQueue.add(new Reservation("Abhi", "Single"));
        requestQueue.add(new Reservation("Subha", "Single"));
        requestQueue.add(new Reservation("Vanmathi", "Suite"));

        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        while (!requestQueue.isEmpty()) {
            Reservation reservation = requestQueue.poll();
            service.allocateRoom(reservation, inventory);
        }
    }
}