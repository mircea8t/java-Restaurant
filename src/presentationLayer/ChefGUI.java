package presentationLayer;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ChefGUI extends Observer{
	private JFrame frame = new JFrame();
	private JLabel orders = new JLabel("Orders for chef: ");
	private JTextArea textArea = new JTextArea();
	private JScrollPane textScroll;
	private Subject s;
	private String order = new String();
	
	public ChefGUI(Subject s) {
		this.s = s;
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel defaultPanel = new JPanel();
		defaultPanel.setLayout(new FlowLayout());
		orders.setFont(new Font("Serif", Font.PLAIN, 20));
		textScroll = new JScrollPane(textArea);
		textScroll.setPreferredSize(new Dimension(700,500));
		defaultPanel.add(orders);
		defaultPanel.add(textScroll);
		
		frame.setContentPane(defaultPanel);
		frame.setVisible(true);
	}
	
	public void setInfo(String toAdd) {
		this.textArea.setText(toAdd);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		order = order.concat(s.getState() + '\n');
		this.textArea.setText(order);
	}
}
