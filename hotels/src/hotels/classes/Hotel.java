package hotels.classes;

import java.util.ArrayList;

public class Hotel{
	ArrayList<Habitacions> habitacions = new ArrayList<Habitacions>();
	ArrayList<Clients> clients = new ArrayList<Clients>();
	ArrayList<Reserves> pendents = new ArrayList<Reserves>();
	ArrayList<Reserves> confirmades = new ArrayList<Reserves>();
	String nomhotel;
	
	public Hotel(String nomhotel) {
		super();
		this.nomhotel = nomhotel;
	}
	
	public ArrayList<Habitacions> getHabitacions() {
		return habitacions;
	}
	public void setHabitacions(ArrayList<Habitacions> habitacions) {
		this.habitacions = habitacions;
	}
	public ArrayList<Clients> getClients() {
		return clients;
	}
	public void setClients(ArrayList<Clients> clients) {
		this.clients = clients;
	}
	public ArrayList<Reserves> getPendents() {
		return pendents;
	}
	public void setPendents(ArrayList<Reserves> pendents) {
		this.pendents = pendents;
	}
	public ArrayList<Reserves> getConfirmades() {
		return confirmades;
	}
	public void setConfirmades(ArrayList<Reserves> confirmades) {
		this.confirmades = confirmades;
	}
	public String getNomhotel() {
		return nomhotel;
	}
	public void setNomhotel(String nomhotel) {
		this.nomhotel = nomhotel;
	}
	
	
}