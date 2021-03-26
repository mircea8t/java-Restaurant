package presentationLayer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import businessLayer.Restaurant;
import businessLayer.IRestaurantProcessing;
import businessLayer.MenuItem;
import businessLayer.Order;

public class WaiterGUI extends JFrame {
	private IRestaurantProcessing rp;
	private JFrame frame = new JFrame();
	private JComboBox<String> menuList = new JComboBox<String>();
	private JScrollPane orderJsp;
	private JTable orderTable;
	private DefaultTableModel defaultTable;
	private JButton createOrder = new JButton("Create Order");
	private JLabel items = new JLabel("Items: ");
	private JLabel itemsListTxt = new JLabel("");
	private JLabel table = new JLabel("Table");
	private JTextField tableTxt = new JTextField("");
	private JButton computeBill = new JButton("Compute bill");
	private JButton selectItem = new JButton("Select");
	private Subject subject;
	
	private String billDate;
	private int billOrderId;
	private int billTableNumber;
	private double billPrice;
	
	public WaiterGUI(Restaurant rp, Subject subject) {
		this.subject = subject;
		this.rp = rp;
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel defaultPanel = new JPanel();
		defaultPanel.setLayout(new BoxLayout(defaultPanel, BoxLayout.Y_AXIS));
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		JPanel panel4 = new JPanel();
		panel4.setLayout(new FlowLayout());
		JPanel panel5 = new JPanel();
		panel5.setLayout(new FlowLayout());
		
		List<String[]> toAdd = baseTable();
		List<String[]> toAdd2 = compositeTable();
		for(int i=0; i<toAdd.size(); i++) {
			menuList.addItem(toAdd.get(i)[1]);
		}
		for(int i=0; i<toAdd2.size(); i++) {
			menuList.addItem(toAdd2.get(i)[1]);
		}
		
		
		menuList.setPreferredSize(new Dimension(150,30));
		items.setFont(new Font("Serif", Font.PLAIN, 30));
		table.setFont(new Font("Serif", Font.PLAIN, 30));
		itemsListTxt.setPreferredSize(new Dimension(300,60));
		selectItem.setPreferredSize(new Dimension(100,35));
		tableTxt.setPreferredSize(new Dimension(50,35));
		createOrder.setPreferredSize(new Dimension(200,40));
		computeBill.setPreferredSize(new Dimension(200,40));
		String[] tableHeader = {"Order", "Date", "Table"};
		defaultTable = new DefaultTableModel(tableHeader, 0);
		orderTable = new JTable(defaultTable);
		orderJsp = new JScrollPane(orderTable);
		
		
		panel1.add(menuList);
		panel1.add(items);
		panel1.add(itemsListTxt);
		panel1.add(selectItem);
		panel2.add(table);
		panel2.add(tableTxt);
		panel3.add(createOrder);
		panel4.add(computeBill);
		panel5.add(orderJsp);
		
		defaultPanel.add(panel1);
		defaultPanel.add(panel2);
		defaultPanel.add(panel3);
		defaultPanel.add(panel4);
		defaultPanel.add(panel5);
		
		frame.setContentPane(defaultPanel);
		frame.setVisible(true);
		
	}
	
	public void computeBill() {
		rp.generateBill(this.billDate, this.billOrderId, this.billTableNumber, this.billPrice);
	}
	
	public void saveBillParameters(String date, int id, int tableNumber, double price) {
		this.billDate = date;
		this.billOrderId = id;
		this.billTableNumber = tableNumber;
		this.billPrice = price;
	}
	
	public List<String[]> baseTable() {
		List<MenuItem> lst = this.rp.getMenuItems();
		List<String[]> toAdd = new ArrayList<String[]>();
		for(int i=0; i<lst.size(); i++) {
			if(lst.get(i).getIsComposite() == false)
			{
				String[] item = {Integer.toString(lst.get(i).getId()), lst.get(i).getName(), Double.toString(lst.get(i).getQuantity()), Double.toString(lst.get(i).computePrice())};
				toAdd.add(item);
			}	
		}
		return toAdd;
	}
	
	public List<String[]> compositeTable() {
		List<MenuItem> lst = this.rp.getMenuItems();
		List<String[]> toAdd = new ArrayList<String[]>();
		for(int i=0; i<lst.size(); i++) {
			if(lst.get(i).getIsComposite() == true)
			{
				String[] item = {Integer.toString(lst.get(i).getId()), lst.get(i).getName(), Double.toString(lst.get(i).getQuantity()), Double.toString(lst.get(i).computePrice())};
				toAdd.add(item);
			}	
		}
		return toAdd;
	}
	
	public void createOrder() {
		int id = orderTable.getRowCount() + 1;
		Date date = new Date();
		String tableNb = tableTxt.getText();
		String[] toAdd = {Integer.toString(id), date.toString(), tableNb};
		defaultTable.addRow(toAdd);
		String[] items = itemsListTxt.getText().split(", ");
		ArrayList<MenuItem> itemsInMenu = new ArrayList<MenuItem>();
		String itemsForOrder = new String();
		for(int i=0; i<items.length; i++) {
			for(MenuItem itMenu: rp.getMenuItems()) {
				if(items[i].contentEquals(itMenu.getName()))
				{
					itemsForOrder = itemsForOrder.concat(itMenu.getName() + " ");
					itemsInMenu.add(itMenu);
				}
			}
		}
		Order o = rp.createOrder(id, Integer.parseInt(tableNb), date, itemsInMenu);
		double price = rp.computePrice(o);
		saveBillParameters(date.toString(), id, Integer.parseInt(tableNb), price);
		subject.setState("Order " + id + ";      for the table: " + tableNb + ";      items ordered: " + itemsForOrder);
	}
	
	public void selectItem() {
		String item = (String)menuList.getSelectedItem();
		String newText = new String();
		if(itemsListTxt.getText().length() == 0)
		{
			newText = itemsListTxt.getText().concat(item);
		}
		else
		{
			newText = itemsListTxt.getText().concat(", " + item);
		}
		itemsListTxt.setText(newText);
	}
	
	
	public JComboBox<String> getComboBox() {
		return this.menuList;
	}
	
	public void addActionListener(ActionListener createOrder, ActionListener computePrice, ActionListener selectItem) {
		this.createOrder.addActionListener(createOrder);
		this.computeBill.addActionListener(computePrice);  
		this.selectItem.addActionListener(selectItem);
	}
	
	public void resetItemList() {
		this.itemsListTxt.setText("");
	}
}
