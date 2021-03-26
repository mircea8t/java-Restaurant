package businessLayer;
import java.util.List;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem{
	private List<MenuItem> list = new ArrayList<MenuItem>();
	private double price;
	
	public CompositeProduct(int id, String name, int quantity, byte type, double price, List<MenuItem> list) {
		super(id, name, quantity, type, true);
		this.price = price;
		this.list = list;
	}
	
	public void addItem(MenuItem item) {
		this.list.add(item);
	}
	
	public void removeItem(String name) {
		int pos = 0;
		int i = 0;
		for(MenuItem it: this.list) {
			if(it.getName() == name) {
				pos = i;
				break;
			}
			i++;
		}
		list.remove(pos);
	}
	
	public List<MenuItem> getList(){
		return this.list;
	}
	
	public void setList(List<MenuItem> newList) {
		this.list = newList;
	}
	
	@Override
	public double computePrice() {
		double sumPrice = 0;
		for(MenuItem it: this.list) {
			sumPrice = sumPrice + it.computePrice();
		}
		return sumPrice;
	}
}
