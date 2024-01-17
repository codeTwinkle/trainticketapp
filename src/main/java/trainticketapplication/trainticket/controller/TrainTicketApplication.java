package trainticketapplication.trainticket.controller;

	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

	import java.util.HashMap;
	import java.util.Map;

	@SpringBootApplication
	@RestController
	public class TrainTicketApplication {

	    private final Map<Integer, Map<String, String>> usersData = new HashMap<>();
	    private int userCount = 0;

	    @PostMapping("/purchase-ticket")
	    public ResponseEntity<Map<String, String>> purchaseTicket(@RequestBody Map<String, Object> requestData) {
	        Map<String, String> userData = (Map<String, String>) requestData.get("user");
	        String seatSection = (userCount % 2 == 0) ? "A" : "B";
	        String seatNumber = seatSection + (userCount + 1);

	        Map<String, String> receipt = new HashMap<>();
	        receipt.put("from", (String) requestData.get("from"));
	        receipt.put("to", (String) requestData.get("to"));
	        receipt.put("user", userData.get("firstName") + " " + userData.get("lastName"));
	        receipt.put("pricePaid", "$20");
	        receipt.put("seatSection", seatSection);
	        receipt.put("seatNumber", seatNumber);

	        usersData.put(++userCount, receipt);

	        return new ResponseEntity<>(receipt, HttpStatus.CREATED);
	    }

	    @GetMapping("/view-receipt/{userId}")
	    public ResponseEntity<Map<String, String>> viewReceipt(@PathVariable int userId) {
	        Map<String, String> receipt = usersData.get(userId);

	        if (receipt != null) {
	            return ResponseEntity.ok(receipt);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
	        }
	    }

	    public static void main(String[] args) {
	        SpringApplication.run(TrainTicketApplication.class, args);
	    }
}

