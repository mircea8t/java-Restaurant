package presentationLayer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import businessLayer.*;
import businessLayer.MenuItem;
import dataLayer.*;

public class AdminGUI extends JFrame {
	private IRestaurantProcessing rp;
	private JFrame frame = new JFrame();
	private JButton next = new JButton("Composed Menu Items");
	private JButton returnButton = new JButton("Base Menu Items");
	private JLabel itemId = new JLabel("ID:");
	private JTextField itemIdTxt = new JTextField("");
	private JLabel itemName = new JLabel("Name:");
	private JTextField itemNameTxt = new JTextField("");
	private JLabel itemQuantity = new JLabel("Quantity:");
	private JTextField itemQuantityTxt = new JTextField("");
	private JLabel itemType = new JLabel("Type:");
	private JTextField itemTypeTxt = new JTextField("");
	private JLabel itemPrice = new JLabel("Price:");
	private JTextField itemPriceTxt = new JTextField("");
	private JButton addItem = new JButton("Add");
	private JButton editItem = new JButton("Edit");
	private JButton deleteBaseItem = new JButton("Delete");
	private JButton deleteComposeItem = new JButton("Delete");
	private JTextField nameToBeRemoved = new JTextField("");
	private DefaultTableModel defaultTable;
	private JTable itemTable;
	private JScrollPane itemJsp;
	private CardLayout cardLayout = new CardLayout();
	private JPanel cardPanel = new JPanel();
	private JPanel defaultPanel = new JPanel();
	private JPanel secondPanel = new JPanel();
	private JLabel menuName = new JLabel("Name");
	private JTextField menuNameTxt = new JTextField("");
	private JComboBox<String> itemList;
	private JButton select = new JButton("Select Ingredient");
	private JButton remove = new JButton("Remove Ingredient");
	private JButton createItem = new JButton("Create Item");
	private JLabel ingredients = new JLabel("Ingredients: ");
	private JLabel ingredientsTxt = new JLabel("");
	private DefaultTableModel defaultTable2;
	private JTable itemTable2;
	private JScrollPane itemJsp2;
	
	public AdminGUI(Restaurant rp) {
		this.rp = rp;
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardPanel.setLayout(cardLayout);
		defaultPanel.setLayout(new BoxLayout(defaultPanel, BoxLayout.Y_AXIS));
		secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.Y_AXIS));
		cardPanel.add(defaultPanel, "1");
		cardPanel.add(secondPanel, "2");
		cardLayout.show(cardPanel, "1");  
		JPanel switchPage = new JPanel();
		switchPage.setLayout(new FlowLayout());
		JPanel menuItemData = new JPanel();
		menuItemData.setLayout(new FlowLayout());
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		JPanel table = new JPanel();
		table.setLayout(new FlowLayout());
		itemName.setFont(new Font("Serif", Font.PLAIN, 20));
		itemId.setFont(new Font("Serif", Font.PLAIN, 20));
		itemQuantity.setFont(new Font("Serif", Font.PLAIN, 20));
		itemType.setFont(new Font("Serif", Font.PLAIN, 20));
		itemPrice.setFont(new Font("Serif", Font.PLAIN, 20));
		itemNameTxt.setPreferredSize(new Dimension(75,30));
		itemIdTxt.setPreferredSize(new Dimension(75,30));
		itemQuantityTxt.setPreferredSize(new Dimension(75,30));
		itemTypeTxt.setPreferredSize(new Dimension(75,30));
		itemPriceTxt.setPreferredSize(new Dimension(75,30));
		addItem.setPreferredSize(new Dimension(80,30));
		deleteBaseItem.setPreferredSize(new Dimension(80,30));
		editItem.setPreferredSize(new Dimension(80,30));
		next.setPreferredSize(new Dimension(200,30));
		
		switchPage.add(next);
		menuItemData.add(itemId);
		menuItemData.add(itemIdTxt);
		menuItemData.add(itemName);
		menuItemData.add(itemNameTxt);
		menuItemData.add(itemQuantity);
		menuItemData.add(itemQuantityTxt);
		menuItemData.add(itemType);
		menuItemData.add(itemTypeTxt);
		menuItemData.add(itemPrice);
		menuItemData.add(itemPriceTxt);
		buttons.add(addItem);
		buttons.add(editItem);
		buttons.add(deleteBaseItem);
		String[] tableHeader = {"ID", "Name", "Quantity", "Type", "Price"};
		defaultTable = new DefaultTableModel(tableHeader, 0);
		itemTable = new JTable(defaultTable);
		itemJsp = new JScrollPane(itemTable);
		
		List<String[]> toAdd = baseItems();
		for(int i=0; i<toAdd.size(); i++) {
			defaultTable.addRow(toAdd.get(i));	
		}
		
		table.add(itemJsp);
		defaultPanel.add(menuItemData);
		defaultPanel.add(buttons);
		defaultPanel.add(table);
		defaultPanel.add(switchPage);
		
		
		JPanel switchPage2 = new JPanel();
		switchPage2.setLayout(new BoxLayout(switchPage2, BoxLayout.Y_AXIS));
		JLabel space2 = new JLabel("      ");
		space2.setPreferredSize(new Dimension(150,30));
		returnButton.setPreferredSize(new Dimension(200,30));
		menuName.setFont(new Font("Serif", Font.PLAIN, 20));
		ingredients.setFont(new Font("Serif", Font.PLAIN, 20));
		menuNameTxt.setPreferredSize(new Dimension(80,30));
		nameToBeRemoved.setPreferredSize(new Dimension(80,30));
		ingredientsTxt.setPreferredSize(new Dimension(400,30));
		select.setPreferredSize(new Dimension(150,30));
		remove.setPreferredSize(new Dimension(150,30));
		deleteComposeItem.setPreferredSize(new Dimension(80,30));
		itemList = new JComboBox<String>();
		itemList.setPreferredSize(new Dimension(150,30));
		
		for(int i=0; i<toAdd.size(); i++) {
			itemList.addItem(toAdd.get(i)[1]);
		}
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		JPanel panel4 = new JPanel();
		panel4.setLayout(new FlowLayout());
		JPanel panel5 = new JPanel();
		panel5.setLayout(new FlowLayout());
		
		String[] tableHeader2 = {"ID", "Name", "Quantity","Type" , "Price"};
		defaultTable2 = new DefaultTableModel(tableHeader2, 0);
		itemTable2 = new JTable(defaultTable2);
		itemJsp2 = new JScrollPane(itemTable2);
		
		List<String[]> toAdd2 = compositeItems();
		for(int i=0; i<toAdd2.size(); i++) {
			defaultTable2.addRow(toAdd2.get(i));	
		}
		
		panel1.add(itemList);
		panel1.add(select);
		panel1.add(remove);
		panel2.add(ingredients);
		panel2.add(ingredientsTxt);
		panel2.add(menuName);
		panel2.add(menuNameTxt);
		panel2.add(createItem);
		panel3.add(deleteComposeItem);
		panel3.add(nameToBeRemoved);
		panel4.add(itemJsp2);
		panel5.add(returnButton);
		switchPage2.add(panel1);
		switchPage2.add(panel2);
		switchPage2.add(panel3);
		switchPage2.add(panel4);
		switchPage2.add(panel5);
		secondPanel.add(switchPage2);
		
		
		frame.setContentPane(cardPanel);
		frame.setVisible(true);
		
	}
	
	public List<String[]> baseItems() {
		List<MenuItem> list = this.rp.getMenuItems();
		List<String[]> toAdd = new ArrayList<String[]>();
		for(MenuItem itMenuItem: list) {
			if(itMenuItem.getIsComposite() == false)
			{
				String quant = "";
				String typeString = "";
				if (itMenuItem.getType() == 0) {
					quant = "g";
					typeString = "food";
				}
				else {
					quant = "ml";
					typeString = "drinks";
				}
				
				String[] item = {Integer.toString(itMenuItem.getId()), itMenuItem.getName(), Integer.toString(itMenuItem.getQuantity()) + quant, typeString, Double.toString(itMenuItem.computePrice())};
				toAdd.add(item);
			}	
		}
		return toAdd;
	}
	
	public List<String[]> compositeItems() {
		List<MenuItem> list = this.rp.getMenuItems();
		List<String[]> toAdd = new ArrayList<String[]>();
		for(MenuItem itMenuItem: list) {
			if(itMenuItem.getIsComposite() == false)
			{
				String quant = "";
				String typeString = "";
				if (itMenuItem.getType() == 0) {
					quant = "g";
					typeString = "food";
				}
				String[] item = {Integer.toString(itMenuItem.getId()), itMenuItem.getName(), Integer.toString(itMenuItem.getQuantity()) + quant, typeString, Double.toString(itMenuItem.computePrice())};
				toAdd.add(item);
			}	
		}
		return toAdd;
	}
	
	public String deleteComposeItem() {
		String nameToDelete = nameToBeRemoved.getText();
		for(int i=0; i<itemTable2.getRowCount(); i++) { 
			if(itemTable2.getValueAt(i, 1).equals(nameToDelete)) {  
				itemList.removeItem(itemTable2.getValueAt(i, 1));
				defaultTable2.removeRow(i);
				rp.deleteItem(nameToDelete);
			}
		}	
		rp.serializeRestaurant();
		return nameToDelete;
	}
	
	public String deleteBaseItem() {
		String nameToDelete = itemNameTxt.getText();
		for(int i=0; i<itemTable.getRowCount(); i++) { 
			if(itemTable.getValueAt(i, 1).equals(nameToDelete)) {  
				itemList.removeItem(itemTable.getValueAt(i, 1));
				defaultTable.removeRow(i);
				rp.deleteItem(nameToDelete);
			}
		}	
		rp.serializeRestaurant();
		return nameToDelete;
	}	
	
	public String[] editBaseItem() {
		int id = Integer.parseInt(itemIdTxt.getText());
		String toRemove = (String) itemTable.getValueAt(id-1, 1);
		itemList.removeItem(toRemove);
		String oldName = "";
		String newName = itemNameTxt.getText();
		String[] names = {"",""};
		names[0] = newName;
		itemList.addItem(itemNameTxt.getText());
		String strId = itemIdTxt.getText();
		for(int i=0; i<itemTable.getRowCount(); i++) {
			if(itemTable.getValueAt(i, 0).equals(strId)) {
				oldName = (String) itemTable.getValueAt(id-1, 1);
				defaultTable.setValueAt(itemNameTxt.getText(), i, 1);
				defaultTable.setValueAt(itemQuantityTxt.getText(), i, 2);
				defaultTable.setValueAt(itemPriceTxt.getText(), i, 4);
				rp.editItem(newName, Integer.parseInt(itemQuantityTxt.getText()), Double.parseDouble(itemPriceTxt.getText()), null);
			}
		}
		names[1] = oldName;
		rp.serializeRestaurant();
		return names;
	}
	
	public void selectItem() {
		String item = itemList.getSelectedItem().toString();
		String newText = new String();
		if(ingredientsTxt.getText().length() == 0)
		{
			newText = ingredientsTxt.getText().concat(item);
		}
		else
		{
			newText = ingredientsTxt.getText().concat(", " + item);
		}
		ingredientsTxt.setText(newText);
		rp.serializeRestaurant();
	}
	
	public void removeItemFromIngredientList() {
		String item = itemList.getSelectedItem().toString();
		String ingr = ingredientsTxt.getText();
		String newText = new String();
		if(ingr.contains(item))
		{
			newText = ingr.replace(item, "");
		}
		if(newText.length() != 0 && newText.length() != 1 )
		{
			if(newText.charAt(0) == ',' || newText.charAt(1) == ',')
			{
				newText = newText.replaceFirst(",", "");
			}
		}
		for(int i=2; i<newText.length(); i++) {
			if((newText.charAt(i-2) == ',') && (newText.charAt(i-1) == ' ') && (newText.charAt(i) == ',') )
			{
				newText = newText.replaceFirst(",", "");
			}
		}
		ingredientsTxt.setText(newText);
		rp.serializeRestaurant();
	}
	
	public String addNewItem() {
		int id = itemTable.getRowCount() + 1;
		itemList.addItem(itemNameTxt.getText());
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		byte type = 0;
		if(itemTypeTxt.getText().contentEquals("drinks")) {
			type = 1;
		}	
		else {
			if(itemTypeTxt.getText().contentEquals("food") == false) {
				JOptionPane.showMessageDialog(frame, "The only types permited are: food, drinks");
				return null;
			}
		}
		if(type == 0) {
			String[] toAdd = {Integer.toString(id), itemNameTxt.getText(), itemQuantityTxt.getText() + "g", itemTypeTxt.getText(), itemPriceTxt.getText()};
			defaultTable.addRow(toAdd);
		}else {
				String[] toAdd = {Integer.toString(id), itemNameTxt.getText(), itemQuantityTxt.getText() + "ml", itemTypeTxt.getText(), itemPriceTxt.getText()};
				defaultTable.addRow(toAdd);
		}
		rp.createNewItem(id, itemNameTxt.getText(), Integer.parseInt(itemQuantityTxt.getText()), type, Double.parseDouble(itemPriceTxt.getText()), menuItemList);
		rp.serializeRestaurant();			
		return itemNameTxt.getText();
	}
	
	public String createComposeItem() {
		int id = itemTable2.getRowCount() + 1;
		String name = menuNameTxt.getText();
		byte type = 0; //compose items will always be food
		String[] items = ingredientsTxt.getText().split(", ");
		ArrayList<MenuItem> itemsInMenu = new ArrayList<MenuItem>();
		double price = 0;
		int quantity = 0;
		List<MenuItem> newList = new ArrayList<MenuItem>();
		for(int i=0; i<items.length; i++) {
			for(MenuItem itMenu: rp.getMenuItems()) {
				if(items[i].contentEquals(itMenu.getName()))
				{
					newList.add(itMenu);
				}
			}
		}
		for(MenuItem itMenu: newList) {
			itemsInMenu.add(itMenu);
			quantity = quantity + itMenu.getQuantity();
			price = price +  itMenu.computePrice();
		}
		String[] toAdd = {Integer.toString(id), name, Integer.toString(quantity) + "g", "food", Double.toString(price)};
		rp.createNewItem(id, name, quantity, type, price, itemsInMenu);
		defaultTable2.addRow(toAdd);
		itemList.addItem(name);
		rp.serializeRestaurant();
		return name;
		
	}
	
	public void addActionListener(ActionListener addItem, ActionListener editItem, ActionListener deleteBaseItem, ActionListener next, ActionListener ret, ActionListener select, ActionListener remove, ActionListener createItem, ActionListener deleteComposeItem) {
		this.addItem.addActionListener(addItem);
		this.editItem.addActionListener(editItem);
		this.deleteBaseItem.addActionListener(deleteBaseItem);
		this.next.addActionListener(next);
		this.returnButton.addActionListener(ret);
		this.select.addActionListener(select);
		this.remove.addActionListener(remove);
		this.createItem.addActionListener(createItem);
		this.deleteComposeItem.addActionListener(deleteComposeItem);
	}
	
	public CardLayout getCl() {
		return this.cardLayout;
	}
	
	public JPanel getCardPanel() {
		return this.cardPanel;
	}
}
