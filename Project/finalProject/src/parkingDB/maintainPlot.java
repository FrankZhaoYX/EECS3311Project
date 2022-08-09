package parkingDB;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class maintainPlot {
	private ArrayList<parkingPlot> parking = new ArrayList<parkingPlot>();
	private final String path = "D:\\EECS3311\\Winter\\Project\\parkingPlot.csv";
	
	public maintainPlot() throws Exception {
		super();
		this.parking = load();
	}
	
	public ArrayList<parkingPlot> load() throws Exception{
		CsvReader reader = new CsvReader(this.path); 
		reader.readHeaders();
		ArrayList<parkingPlot> plots = new ArrayList<parkingPlot>();
		while(reader.readRecord()){ 
			parkingPlot tmpP = new parkingPlot();
			tmpP.set_id(Integer.valueOf(reader.get("id")));
			tmpP.set_status(Integer.valueOf(reader.get("status")));
			plots.add(tmpP);
		}
		return plots;
	}
	
	public void update() {
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(this.path, false), ',');
			csvOutput.write("id");
			csvOutput.write("status");
			csvOutput.endRecord();
			
			for(parkingPlot p: this.parking) {
				csvOutput.write(String.valueOf(p.get_id()));
				csvOutput.write(String.valueOf(p.get_status()));
				csvOutput.endRecord();
			}
			csvOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public boolean add_plot(int id) throws Exception {
		this.parking = load();
		for(parkingPlot p: this.parking) {
			if(p.get_id() == id) {
				return false;
			}
		}
		parkingPlot newP = new parkingPlot();
		newP.set_id(id);
		newP.set_status(0);
		parking.add(newP);
		update();
		return true;
	}
	public boolean add_plot() throws Exception {
		this.parking = load();
		int tmp = this.parking.size();
		for(parkingPlot p: this.parking) {
			if(p.get_id() == this.parking.size()) {
				tmp++;
			}
		}
		parkingPlot newP = new parkingPlot();
		newP.set_id(tmp);
		newP.set_status(0);
		parking.add(newP);
		update();
		return true;
	}
	
	public boolean remove_plot(int id) throws Exception {
		boolean id_exist = false;
		this.parking = load();
		parkingPlot tmp = new parkingPlot();
		for(parkingPlot p: this.parking) {
			if(p.get_id() == id) {
				id_exist = true;
				tmp = p;
				if(p.get_status()==1) {
					return false;
				}
			}
		}
		if(id_exist) {
			if(parking.remove(tmp)) {
				update();
				return true;
			}
		}
		return false;
	}
	
	public boolean check_plot_Available(int id) throws Exception {
		this.parking = load();
		for(parkingPlot p: this.parking) {
			if(p.get_id() == id && p.get_status()==0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean Occupid_plot(int id) throws Exception {
		this.parking = load();
		for(parkingPlot p:this.parking) {
			if(p.get_id()==id) {
				p.set_status(1);
				update();
				return true;
			}
		}
		return false;
	}
	
	public boolean Free_plot(int id) throws Exception {
		this.parking = load();
		for(parkingPlot p:this.parking) {
			if(p.get_id()==id) {
				p.set_status(0);
				update();
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<parkingPlot> Available_plot() throws Exception{
		this.parking = load();
		ArrayList<parkingPlot> tmp = new ArrayList<parkingPlot>();
		for(parkingPlot p: this.parking) {
			if(p.get_status()==0) {
				tmp.add(p);
			}
		}
		return tmp;
	}
	
	public ArrayList<parkingPlot> get_parkingPlot() throws Exception{
		this.parking = load();
		return this.parking;
	}

}
