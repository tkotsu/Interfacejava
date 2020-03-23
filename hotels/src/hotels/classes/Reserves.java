package hotels.classes;

import java.time.LocalDate;

public class Reserves{
	Clients client;
	int numpersones;
	Habitacions habitacio;
	LocalDate entrada, sortida;
	
	public Reserves(Clients client) {
		super();
		this.client = client;
	}
	
	public Clients getClient() {
		return client;
	}
	public void setClient(Clients client) {
		this.client = client;
	}
	public int getNumpersones() {
		return numpersones;
	}
	public void setNumpersones(int numpersones) {
		this.numpersones = numpersones;
	}
	public Habitacions getHabitacio() {
		return habitacio;
	}
	public void setHabitacio(Habitacions habitacio) {
		this.habitacio = habitacio;
	}
	public LocalDate getEntrada() {
		return entrada;
	}
	public void setEntrada(LocalDate entrada) {
		this.entrada = entrada;
	}
	public LocalDate getSortida() {
		return sortida;
	}
	public void setSortida(LocalDate sortida) {
		this.sortida = sortida;
	}
	
	public String[] posanttaula() {
		String[] array = new String[4];
        array[0]=this.entrada.getDayOfMonth()+"-"+this.entrada.getMonthValue()+"-"+this.entrada.getYear();
        array[1]=client.getDni();
        array[2]=this.numeropersones+"";
        array[3]="";
        return array;
	}
	
}