package presentationLayer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;

public class Controller {
	private AdminGUI adminView;
	private WaiterGUI waiterView;
	private ChefGUI chefView;
	private Restaurant restaurant;
	
	public Controller(AdminGUI admin, WaiterGUI waiter, ChefGUI chef, Restaurant r) {
		this.restaurant = r;
		this.adminView = admin;
		this.waiterView = waiter;
		this.chefView = chef; 
		adminView.addActionListener(new AddItem(), new EditItem(), new DeleteBaseItem(), new Next(), new Back(), new Select(), new Remove(), new CreateItem(), new DeleteComposeItem());
		waiterView.addActionListener(new CreateOrder(), new ComputeBill(), new SelectItem());
	}
	class CreateOrder implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			waiterView.createOrder();
			chefView.update();
			waiterView.resetItemList();
		}
	}
	
	class ComputeBill implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			waiterView.computeBill();
		}
	}
	
	class SelectItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			waiterView.selectItem();
		}
	}
	
	class Select implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			adminView.selectItem();
		}
	}
	
	class Remove implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			adminView.removeItemFromIngredientList();
		}
	}
	
	
	class CreateItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String name = adminView.createComposeItem();
			waiterView.getComboBox().addItem(name);
		}
	}
	
	class Next implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			adminView.getCl().show(adminView.getCardPanel(), "2");
		}
	}
	
	class Back implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			adminView.getCl().show(adminView.getCardPanel(), "1");
		}
		
	}
	
	class AddItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String name = adminView.addNewItem();
			waiterView.getComboBox().addItem(name);
		}
	}
	
	class EditItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String[] name = adminView.editBaseItem();
			waiterView.getComboBox().removeItem(name[1]);
			waiterView.getComboBox().addItem(name[0]);
		}
	}
	
	class DeleteBaseItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String name = adminView.deleteBaseItem();
			waiterView.getComboBox().removeItem(name);
		}	
	}
	
	class DeleteComposeItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String name = adminView.deleteComposeItem();
			waiterView.getComboBox().removeItem(name);
		}
	}
}
