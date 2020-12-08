package objects;

public class Entry {
	//fields
	private int totalPop; //total population
	private String state; //state
	private String county; //County
	private int income; //Income
	private float poverty; //poverty
	
	public Entry() {
		totalPop = 0;
		state = new String();
		county = new String();
		income = 0;
		poverty = 0.0f;
		
	}
	
	//accessor methods
	public int getTotalPop() {
		return totalPop;
	}
	
	public String getState() {
		return state;
	}
	
	public String getCounty() {
		return county;
	}
	
	public int getIncome() {
		return income;
	}
	
	public float getPoverty() {
		return poverty;
	}
	
	//modifier methods
	public void setTotalPop(int p) {
		totalPop = p;
	}
	
	public void setState(String str) {
		state = str;
	}
	
	public void setCounty(String str) {
		county = str;
	}
	
	public void setIncome(int i) {
		income = i;
	}
	
	public void setPoverty(float p) {
		poverty = p;
	}
	
	public String toString() {
		return "Total Pop: " + totalPop + ", State: " + state + ", County: " + county + ", Income: " + income + ", Poverty: " + poverty;
	}
	
	public String toStringByState() {
		return "State: " + state + ", Total Pop: " + totalPop + ", County: " + county + ", Income: " + income + ", Poverty: " + poverty;
	}
	
	public String toStringByCounty() {
		return "County: " + county + ", State: " + state + ", Total Pop: " + totalPop + ", Income: " + income + ", Poverty: " + poverty;
	}
	
	public String toStringByIncome() {
		return "Income " + income + ", State: " + state + ", County: " + county + ", Total Pop: " + totalPop + ", Poverty: " + poverty;
	}
	
	public String toStringByPoverty() {
		return "Poverty: " + poverty + ", State: " + state + ", County: " + county + ", Income: " + income + ", Total Pop: " + totalPop;
	}
	
	
	
	
}
