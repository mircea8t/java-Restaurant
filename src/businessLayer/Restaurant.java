package businessLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dataLayer.*;

public class Restaurant implements IRestaurantProcessing, java.io.Serializable {
	private List<MenuItem> menu = new ArrayList<MenuItem>();
	private Map<Order, List<MenuItem>> orders = new HashMap<Order, List<MenuItem>>();
	
	@Override
	public List<MenuItem> getMenuItems(){
		return this.menu;
	}

	@Override
	public void createNewItem(int id, String name, int quantity, byte type, double price, List<MenuItem> ingredients) {
		// TODO Auto-generated method stub
		MenuItem item = null;
		if (ingredients == null) {
			item = new BaseProduct(id, name, quantity, type, price);
		}
		else {
			item = new CompositeProduct(id, name, quantity, type, price, ingredients);
		}
		menu.add(item);
	}

	@Override
	public void editItem(String name, int quantity, double price, List<MenuItem> ingredients) {
		// TODO Auto-generated method stub
		boolean founded = false;
		int i = 0;
		while ((founded == false) &&(i < menu.size())) {
			MenuItem auxItem = menu.get(i);
			if(auxItem.getName() == name) {
				founded = true;
				int auxId = auxItem.getId();
				byte auxType = auxItem.getType();
				menu.remove(i);
				createNewItem(auxId, name, quantity, auxType, price, ingredients);
			}else {
				i++;
			}
		}
	}

	@Override
	public void deleteItem(String name) {
		// TODO Auto-generated method stub
		boolean founded = false;
		int i = 0;
		while((founded == false) && (i < menu.size())) {
			MenuItem auxItem = menu.get(i);
			if(auxItem.getName() == name) {
				founded = true;
			}else {
				i++;
			}
		}
		if(founded) {
			menu.remove(i);
		}
	}

	@Override
	public Order createOrder(int id, int tableNumber, Date date, List<MenuItem> menu) {
		// TODO Auto-generated method stub
		Order order = new Order(id, tableNumber, date);
		orders.put(order, menu);
		return order;
	}

	@Override
	public double computePrice(Order order) {
		// TODO Auto-generated method stub
		List<MenuItem> ingredients = orders.get(order);
		double total = 0;
		for(MenuItem ingredient: ingredients) {
			total = total + ingredient.computePrice();
		}
		
		return total;
	}

	@Override
	public void generateBill(String date, int orderNumber, int tableNumber, double price) {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter();
		fw.createBill(date, orderNumber, tableNumber, price);
	}
	
	@Override
	public void serializeRestaurant() {
		RestaurantSerializator rs = new RestaurantSerializator();
		rs.serializeRestaurant(this);
	}
}
