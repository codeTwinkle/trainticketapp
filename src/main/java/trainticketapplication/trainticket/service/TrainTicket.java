package trainticketapplication.trainticket.service;

//s	import java.util.*;

	class TrainTicket {
	    String from;
	    String to;
	    String firstName;
	    String lastName;
	    String email;
	    double pricePaid;
	    String seat;

	    TrainTicket(String from, String to, String firstName, String lastName, String email, double pricePaid, String seat) {
	        this.from = from;
	        this.to = to;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.pricePaid = pricePaid;
	        this.seat = seat;
	    }
	}

	class TrainService {
	    private static Map<String, TrainTicket> tickets = new HashMap<>();

	    static void purchaseTicket(String from, String to, String firstName, String lastName, String email, double pricePaid) {
	        // Assume simple seat allocation: alternating between section A and B
	        String section = tickets.size() % 2 == 0 ? "A" : "B";
	        String seat = section + (tickets.size() / 2 + 1);

	        TrainTicket ticket = new TrainTicket(from, to, firstName, lastName, email, pricePaid, seat);
	        tickets.put(email, ticket);
	    }

	    static TrainTicket getReceipt(String email) {
	        return tickets.get(email);
	    }

	    static List<TrainTicket> getUsersAndSeatsBySection(String section) {
	        List<TrainTicket> result = new ArrayList<>();
	        for (TrainTicket ticket : tickets.values()) {
	            if (ticket.seat.startsWith(section)) {
	                result.add(ticket);
	            }
	        }
	        return result;
	    }

	    static void removeUser(String email) {
	        tickets.remove(email);
	    }

	    static void modifyUserSeat(String email, String newSeat) {
	        TrainTicket ticket = tickets.get(email);
	        if (ticket != null) {
	            ticket.seat = newSeat;
	        }
	    }
	}

	public class TrainApp {
	    public static void main(String[] args) {
	        TrainService.purchaseTicket("London", "France", "John", "Doe", "john.doe@example.com", 20);
	        TrainService.purchaseTicket("London", "France", "Jane", "Smith", "jane.smith@example.com", 20);

	        System.out.println("Receipt for John Doe: " + TrainService.getReceipt("john.doe@example.com"));
	        System.out.println("Users in Section A: " + TrainService.getUsersAndSeatsBySection("A"));

	        TrainService.removeUser("john.doe@example.com");
	        System.out.println("User removed. Users in Section A: " + TrainService.getUsersAndSeatsBySection("A"));

	        TrainService.modifyUserSeat("jane.smith@example.com", "A3");
	        System.out.println("User seat modified. Users in Section A: " + TrainService.getUsersAndSeatsBySection("A"));
	    }
	}
}
