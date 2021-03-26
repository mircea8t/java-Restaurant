package businessLayer;

public class BaseProduct extends MenuItem{
	private double price;
	
	public BaseProduct(int id, String name, int quantity, byte type, double price) {
		super(id, name, quantity, type, false);
		this.price = price;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	@Override
	public double computePrice() {
		return this.price;
	}
}
