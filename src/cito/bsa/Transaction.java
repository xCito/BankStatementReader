package cito.bsa;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Transaction {
	
	protected String name;
	protected LocalDate date;
	protected double amount;
	protected String type; 		//(expense, deposit, withdraw)
	
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
	public LocalDate getLocalDate() {
		return date;
	}
	public String getDateFormatted() {
		String str = "";
		str += date.getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + " ";
		str += date.getDayOfMonth() + ", ";
		str += date.getYear();
		return  str;
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
