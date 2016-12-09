public class Item {
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private String name;
	private int price;
	private int quantity;
	
	public Item() {
		this.name = "(null)";
		this.price = 0;
		this.quantity = 0;
	}
	
	public Item(String name, int price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return this.name +" costs: $"+price +" ("+quantity+" in stock)";
	}
}