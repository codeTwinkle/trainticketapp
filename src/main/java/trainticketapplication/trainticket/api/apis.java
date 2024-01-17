package trainticketapplication.trainticket.api;

	Purchase Ticket API:
		Endpoint: /purchase_ticket
		Method: POST
		Payload: { "from": "London", "to": "France", "user": { "firstName": "John", "lastName": "Doe", "email": "john.doe@example.com" }, "pricePaid": 20 }
		Receipt Details API:
		Endpoint: /receipt_details/{userId}
		Method: GET
		Returns: { "from": "London", "to": "France", "user": { "firstName": "John", "lastName": "Doe", "email": "john.doe@example.com" }, "pricePaid": 20, "seat": "A12" }
		View Users and Seats API:
		Endpoint: /view_users_seats/{section}
		Method: GET
		Returns: [{ "firstName": "John", "lastName": "Doe", "email": "john.doe@example.com", "seat": "A12" }, ...]
		Remove User from Train API:
		Endpoint: /remove_user/{userId}
		Method: DELETE
		Returns: { "message": "User removed successfully." }
		Modify User's Seat API:
		Endpoint: /modify_seat/{userId}
		Method: PUT
		Payload: { "newSeat": "B7" }
		Returns: { "message": "Seat modified successfully." }

