package dataLayer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import businessLayer.Restaurant;

public class RestaurantSerializator {
	public void serializeRestaurant(Restaurant restaurant) {
		FileOutputStream file;
		try {
			file = new FileOutputStream("restaurant");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(restaurant);
			file.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Restaurant deserializeRestaurant() {
		Restaurant restaurant = null;
		try {
			FileInputStream file = new FileInputStream("restaurant");
			ObjectInputStream in = new ObjectInputStream(file);
			restaurant = (Restaurant)in.readObject();
			in.close();
			file.close();
		}catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restaurant;
	}
}
