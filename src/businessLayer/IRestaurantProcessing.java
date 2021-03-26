package businessLayer;
import java.util.Date;
import java.util.List;
public interface IRestaurantProcessing {
	
	public List<MenuItem> getMenuItems();

	public void createNewItem(int id, String name, int quantity, byte type, double price, List<MenuItem> ingredients);

	public void editItem(String name, int quantity, double price, List<MenuItem> ingredients);
	
	public void deleteItem(String name);
	
	public Order createOrder(int id, int tableNumber, Date date, List<MenuItem> menu);
	
	public double computePrice(Order order);
	
	public void generateBill(String date, int orderNumber, int tableNumber, double price);
	
	public void serializeRestaurant(); 
}
