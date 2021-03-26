package dataLayer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {
	public void createBill(String date, int orderNumber, int tableNumber, double total) {
		File file = new File("bill" + orderNumber + ".txt");
		try {
			PrintWriter pw = new PrintWriter(file);
			String text = "Order " + orderNumber;
			String emissionDate = " Printed on: " + date;
			String table = " For table: " + tableNumber;
			String totalMoney = "Total: " + total + "$";
			pw.println(text);
			pw.println(emissionDate);
			pw.println(table);
			pw.write(totalMoney);
			pw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
