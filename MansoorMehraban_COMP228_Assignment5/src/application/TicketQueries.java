package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketQueries {
	   private static final String URL = "jdbc:derby:ticketDataBase;create=true;user=mansoor;password=mansoor";
	   private static final String USERNAME = "mansoor";
	   private static final String PASSWORD = "mansoor";

	   private Connection connection;
	   private PreparedStatement selectAllTickets;       
	   private PreparedStatement insertNewTicket;    
	   
	   private PreparedStatement updateExistingTicket;
	   private PreparedStatement deleteExistingTicket;
	   
	   private PreparedStatement countExistingTicket;
	    
	   // constructor
	   public TicketQueries() {
	      try {
	         connection = 
	            DriverManager.getConnection(URL, USERNAME, PASSWORD);

	         selectAllTickets = connection.prepareStatement(
	            "SELECT * FROM Tickets ORDER BY Destination, DepartureTime");        
	         
	         insertNewTicket = connection.prepareStatement(         
	            "INSERT INTO Tickets " +                           
	            "(Destination, DepartureTime, ArrivalTime, TicketNumber, SeatNumber) " +     
	            "VALUES (?, ?, ?, ?, ?)");
	         
	         updateExistingTicket = connection.prepareStatement(
	        		 "UPDATE Tickets " + "SET Destination=?,"
	        		 		+ "DepartureTime=(?),ArrivalTime=(?),"
	        		 		+ "TicketNumber=(?), SeatNumber=(?) WHERE TicketId=(?)");
	         
	         deleteExistingTicket = connection.prepareStatement(
	        		 "DELETE FROM Tickets "
	        		 + "WHERE TicketId=(?)");
//	        		 + "Destination=? AND ArrivalTime=? "
//	        		 + "AND TicketNumber=? AND SeatNumber=?");
	         
	         countExistingTicket = connection.prepareStatement(
	        		 "SELECT COUNT(*) FROM Tickets");
	      } 
	      catch (SQLException sqlException) {
	         sqlException.printStackTrace();
	         System.exit(1);
	      } 
	   } 
	   
	   public List<Ticket> getAllTickets() {
	      try (ResultSet resultSet = selectAllTickets.executeQuery()) {
	         List<Ticket> results = new ArrayList<Ticket>();
	         
	         while (resultSet.next()) {
	            results.add(new Ticket(
	               resultSet.getInt("TicketId"),
	               resultSet.getString("Destination"),
	               resultSet.getString("DepartureTime"),
	               resultSet.getString("ArrivalTime"),
	               resultSet.getString("TicketNumber"),
	               resultSet.getInt("SeatNumber")));
	         } 

	         return results;
	      }
	      catch (SQLException sqlException) {
	         sqlException.printStackTrace();         
	      }
	      
	      return null;
	   }
	   
	   public int addTicket(int ticketId, String destination, String departureTime, 
	      String arrivalTime, String ticketNumber, int seatNumber) {
	      
	      try {
	         insertNewTicket.setString(1, destination);
	         insertNewTicket.setString(2, departureTime);
	         insertNewTicket.setString(3, arrivalTime);  
	         insertNewTicket.setString(4, ticketNumber);
	         insertNewTicket.setInt(5, seatNumber);

	         return insertNewTicket.executeUpdate();         
	      }
	      catch (SQLException sqlException) {
	         sqlException.printStackTrace();
	         return 0;
	      }
	   }
	   
	   public int updateTicket(int ticketId, String destination, String departureTime,
			   String arrivalTime, String ticketNumber, int seatNumber) {
		   try {
			   updateExistingTicket.setString(1, destination);
			   updateExistingTicket.setString(2, departureTime);
			   updateExistingTicket.setString(3, arrivalTime);
			   updateExistingTicket.setString(4, ticketNumber);
			   updateExistingTicket.setInt(5, seatNumber);
			   updateExistingTicket.setInt(6, ticketId);
			   
			   return updateExistingTicket.executeUpdate();
		   }
		   catch (SQLException sqlException) {
			   sqlException.printStackTrace();
			   return 0;
		   }
		   
	   }
	   
	   public int deleteTicket(int ticketId, String destination, String departureTime,
			   String arrivalTime, String ticketNumber, int seatNumber) {
		   try {
			   deleteExistingTicket.setInt(1, ticketId);
//			   deleteExistingTicket.setString(1, destination);
//			   deleteExistingTicket.setString(2, departureTime);
//			   deleteExistingTicket.setString(3, arrivalTime);
//			   deleteExistingTicket.setString(4, ticketNumber);
//			   deleteExistingTicket.setInt(5, seatNumber);
			   
			   return deleteExistingTicket.executeUpdate();
		   }
		   catch (SQLException sqlException) {
			   sqlException.printStackTrace();
			   return 0;
		   }
		   
	   }
	   
	   public int countTickets() {
		   try (ResultSet resultSet = countExistingTicket.executeQuery()){
			   int count = 0;
			   while(resultSet.next()) {
				   count = resultSet.getInt(1);
				   
			   }
			   return count;
		   }
		   catch (SQLException sqlException) {
			   sqlException.printStackTrace();
			   return 0;
		   }
	   }
	   
	   public void close() {
	      try {
	         connection.close();
	      } 
	      catch (SQLException sqlException) {
	         sqlException.printStackTrace();
	      } 
	   }

}
