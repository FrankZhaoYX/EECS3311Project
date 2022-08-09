package parkingDB;

public class parkingPlot {
	private int plot_ID;
	private int plot_status; 
	
	public parkingPlot() {
		super();
	}
	
	public parkingPlot(int id, int status) {
		this.plot_ID = id;
		this.plot_status = status;
	}
	
	public int get_id() {
		return this.plot_ID;
	}
	
	public int get_status() {
		return this.plot_status;
	}
	
	public void set_id(int id) {
		this.plot_ID = id;
	}
	
	public void set_status(int status) {
		this.plot_status = status;
	}
	
}
