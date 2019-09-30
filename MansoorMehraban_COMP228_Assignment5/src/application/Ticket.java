package application;

public class Ticket {
	private int ticketId;
	private String destination;
	private String departureTime;
	private String arrivalTime;
	private String ticketNumber;
	private int seatNumber;
	
	public Ticket() {}
	
	public Ticket(int ticketId, String destination,
			String departureTime,
			String arrivalTime,
			String ticketNumber,
			int seatNumber) {
		this.ticketId = ticketId;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.ticketNumber = ticketNumber;
		this.seatNumber = seatNumber;
	}
	
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDestination() {
		return destination;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	
	public String toString() {
		return getDestination() +", "+ getDepartureTime();
	}
}
