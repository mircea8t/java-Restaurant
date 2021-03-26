package businessLayer;

import java.util.Date;

public class Order implements java.io.Serializable{
	private int orderId;
	private int table;
	private Date date;
	
	public Order(int id, int tableNr, Date date) {
		this.orderId = id;
		this.table = tableNr;
		this.date = date;
	}
	
	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash*13 + this.orderId;
		hash = hash*13 + this.table;
		hash = hash*13 + this.date.hashCode();
		return hash;
	}
	
	public int getId() {
		return this.orderId;
	}
	
	public int getTableNumber() {
		return this.table;
	}
	
	public void setTableNumber(int newNumber) {
		this.table = newNumber;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date newDate) {
		this.date = newDate;
	}
}
