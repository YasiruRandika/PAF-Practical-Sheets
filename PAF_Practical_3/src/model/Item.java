package model;

/* 
 * @author W.G. YASIRU RANDIKA 
 * IT19131184
 * 
 * */

public class Item {
	private int itemID;
	private String itemCode;
	private String itemName;
	private float itemPrice;
	private String itemDesc;
	
	//Default Constructor
	public Item() {
		super();
	}

	//Overloaded Constructor
	public Item(int itemID, String itemCode, String itemName, float itemPrice, String itemDesc) {
		super();
		this.itemID = itemID;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemDesc = itemDesc;
	}
	
	//Getters and Setters

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
}
