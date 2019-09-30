package application;

import java.awt.Desktop.Action;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainController implements Initializable {
	
	@FXML private ListView<Ticket> listView;
	@FXML private TextField ticketIdTextField;
	@FXML private TextField destinationTextField;
	@FXML private TextField departureTimeTextField;
	@FXML private TextField arrivalTimeTextField;
	@FXML private TextField ticketNumberTextField;
	@FXML private TextField seatNumberTextField;
	
	private final TicketQueries ticketQueries = new TicketQueries();
	
	private final ObservableList<Ticket> ticketList  =
			FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(ticketQueries.countTickets() < 5) {
			ticketQueries.addTicket(123,"Cairo",
					"6:30am", "3:00am", "1234", 101);
			ticketQueries.addTicket(1234, "Toronto",
					"9:30am", "6:00am", "91919",
					39393);
			ticketQueries.addTicket(123445, "Vancouver",
					"2:30am", "9:00am", "12309",
					293);
			ticketQueries.addTicket(4545, "Toronto",
					"1:30am", "10:00am", "919102",
					101);
			ticketQueries.addTicket(7565, "Toronto",
					"3:30am", "11:00am", "91919",
					12883);
		}
		listView.setItems(ticketList);
		getAllEntries();
		
		listView.getSelectionModel().selectedItemProperty().addListener(
				(observableValue, oldValue, newValue) -> {
					displayTicket(newValue);
				}
				);
		}
	
	private void getAllEntries() {
		ticketList.setAll(ticketQueries.getAllTickets());
		selectFirstEntry();
	}
	
	private void selectFirstEntry() {
		listView.getSelectionModel().selectFirst();
	}
	
	private void displayTicket(Ticket ticket) {
		if (ticket != null) {
			ticketIdTextField.setText(String.valueOf(ticket.getTicketId()));
			destinationTextField.setText(ticket.getDestination());
			departureTimeTextField.setText(ticket.getDepartureTime());
			arrivalTimeTextField.setText(ticket.getArrivalTime());
			ticketNumberTextField.setText(ticket.getTicketNumber());
			seatNumberTextField.setText(String.valueOf(ticket.getSeatNumber()));
		}
		else {
			ticketIdTextField.clear();
			destinationTextField.clear();
			departureTimeTextField.clear();
			arrivalTimeTextField.clear();
			ticketNumberTextField.clear();
			seatNumberTextField.clear();
		}
	}
	
	@FXML
	void addEntryButtonPressed(ActionEvent event) {
		int result = ticketQueries.addTicket(
				Integer.valueOf(ticketIdTextField.getText()),
				destinationTextField.getText(),
				departureTimeTextField.getText(),
				arrivalTimeTextField.getText(),
				ticketNumberTextField.getText(),
				Integer.valueOf(seatNumberTextField.getText()));
		if(result == 1) {
			displayAlert(AlertType.INFORMATION, "Entry Added",
					"New entry successfully added.");
		}
		else {
			displayAlert(AlertType.ERROR, "Entry Not Added",
					"Unable to add entry.");
		}
		
		getAllEntries();
	}
	
	@FXML
	void updateEntryButtonPressed(ActionEvent event) {
		int result = ticketQueries.updateTicket(
				Integer.valueOf(ticketIdTextField.getText()),
				destinationTextField.getText(),
				departureTimeTextField.getText(),
				arrivalTimeTextField.getText(),
				ticketNumberTextField.getText(),
				Integer.valueOf(seatNumberTextField.getText()));
		if(result == 1) {
			displayAlert(AlertType.INFORMATION, "Update Completed",
					"Update successfully added.");
		}
		else {
			displayAlert(AlertType.ERROR, "Update Not Successful",
					"Unable to update entry.");
		}
		
		getAllEntries();
	}
	
	@FXML
	void deleteEntryButtonPressed(ActionEvent event) {
		int result = ticketQueries.deleteTicket(
				Integer.valueOf(ticketIdTextField.getText()),
				destinationTextField.getText(),
				departureTimeTextField.getText(),
				arrivalTimeTextField.getText(),
				ticketNumberTextField.getText(),
				Integer.valueOf(seatNumberTextField.getText()));
		if(result == 1) {
			displayAlert(AlertType.INFORMATION, "Deleted Entry",
					"Entry successfully deleted.");
		}
		else {
			displayAlert(AlertType.ERROR, "Entry Not Deleted",
					"Unable to delete entry.");
		}
		
		getAllEntries();
	}
	
	private void displayAlert(
		      AlertType type, String title, String message) {
		      Alert alert = new Alert(type);
		      alert.setTitle(title);
		      alert.setContentText(message);
		      alert.showAndWait();
		   }
}
