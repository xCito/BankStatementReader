import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Transaction {
	
	String name;
	LocalDate date;
	double amount;
	String type; 		//(expense, deposit, withdraw)
	
	public Transaction(String nm, double amt, LocalDate dt, String tp) {
		this.name = nm;
		this.amount = amt;
		this.date = dt;
		this.type = tp;
	}
	
	// Getter
	public String getName() {
		return name;
	}
	public Double getAmount() {
		return amount; //String.format("$%.2f", amount);
	}
	public String getType() {
		return type;
	}
	public String getDate() {
		return date.toString();
	}
	public String getDateFormatted() {
		return date.getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + " " + date.getDayOfMonth() + ", " + date.getYear();
	}
	
	
	// Setter
	public void setName( String n ) {
		name = n;
	}
	public void setAmount( double amt ) {
		amount = amt;
	}
	public void setType( String t ) {
		type = t;
	}
	public void setDate( LocalDate d ) {
		date = d;
	}
	
	
	public String toString() {
		return date.toString() + "," + name + "," + amount + "," + type;
	}
}
