package Main;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;
import presentationLayer.*;

public class Main {
	public static void createNewRestaurant() {
		Restaurant r = new Restaurant();
		r.serializeRestaurant();
	} // when we want to reset the restaurant
	
	public static void main(String[] args) {
		//Main.createNewRestaurant();
		Subject subject = new Subject();
		RestaurantSerializator rs = new RestaurantSerializator();
		Restaurant restaurant = rs.deserializeRestaurant();
		AdminGUI adminView = new AdminGUI(restaurant);
		WaiterGUI waiterView = new WaiterGUI(restaurant, subject);
		ChefGUI chefView = new ChefGUI(subject);
		Controller controller = new Controller(adminView, waiterView, chefView, restaurant);
	}
}
