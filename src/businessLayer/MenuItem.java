package businessLayer;

public abstract class MenuItem implements java.io.Serializable{
	private int id;
	private String name;
	private int quantity; //grams for food, ml for drinks
	private byte type; //0 for food, 1 for drinks
	private boolean isComposite;
	
	public MenuItem(int id, String name, int quantity, byte type, boolean isComposite) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.type = type;
		this.isComposite = isComposite;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}
	
	public byte getType() {
		return this.type;
	}
	
	public boolean getIsComposite() {
		return this.isComposite;
	}
	
	public abstract double computePrice();
}
