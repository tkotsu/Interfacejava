package hotels.classes;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import com.toedter.calendar.JCalendar;

public class Functions{
	
	public static boolean existeixelclient(String dni, ArrayList<Clients> clients) {
		for (Clients c : clients) {
			if(c.getDni().equals(dni)) {
				return true;
			}
		}
		return false;
	}
	
	public static Clients retornaclient(String dni, ArrayList<Clients> client) {
		for (Clients c : client) {
			if(dni.equals(c.getDni())) {
				return c;
			}
		}
		return null;
	}

	public static LocalDate dataentrada(JCalendar jcdata) {
		Long dataNano = jcdata.getDate().getTime();
        LocalDate data = Instant.ofEpochMilli(dataNano).atZone(ZoneId.systemDefault()).toLocalDate();
        return data;
	}
	
}