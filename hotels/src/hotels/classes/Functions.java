package hotels.classes;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import com.toedter.calendar.JCalendar;

public class Functions {

	public static boolean existeixelclient(String dni, ArrayList<Clients> clients) {
		for (Clients c : clients) {
			if (c.getDni().equals(dni)) {

				return true;
			}
		}
		return false;
	}

	public static Clients retornaclient(String dni, ArrayList<Clients> client) {
		for (Clients c : client) {
			if (dni.equals(c.getDni())) {
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

	public static boolean existeixlhabitacio(Hotel hotel, String numhabi) {
		for (Habitacions h : hotel.getHabitacions()) {
			if (h.getNumhabitacio() == Integer.parseInt(numhabi)) {
				return true;
			}
		}
		return false;
	}

	public static int retornarnumhabitacio(Hotel hotel, String numhabi) {
		for (Habitacions h : hotel.getHabitacions()) {
			if (h.getNumhabitacio() == Integer.parseInt(numhabi)) {
				return h.getCapacitat();
			}
		}
		return 0;
	}

	public static void actualitzahabitacio(Hotel hotel, String capacitat, String numhabi) {
		for (Habitacions h : hotel.getHabitacions()) {
			if (h.getNumhabitacio() == Integer.parseInt(numhabi))
				;
			h.setCapacitat(Integer.parseInt(capacitat));
		}
	}
	public static boolean estaocupat(Hotel hotel, LocalDate entrada, LocalDate sortida, String numeropersones) {
		int limit = 2;
		for (Habitacions h : hotel.getHabitacions()) {
			for (int i = 0; i < limit; i++) {
				int capacitat = Integer.parseInt(numeropersones) + i;
				for (Reserves r : hotel.pendents) {

					if (r.getHabitacio().getCapacitat() == capacitat && (
		                    (entrada.isAfter(r.getEntrada()) && entrada.isBefore(r.getSortida())) ||
		                    (sortida.isAfter(r.getEntrada()) && sortida.isBefore(r.getSortida())) ||
		                    (entrada.isBefore(r.getEntrada()) && sortida.isAfter(r.getSortida())) )) {
						return true;
					}
				}
				for (Reserves r : hotel.confirmades) {

					if (r.getHabitacio().getCapacitat() == capacitat && (
		                    (entrada.isAfter(r.getEntrada()) && entrada.isBefore(r.getSortida())) ||
		                    (sortida.isAfter(r.getEntrada()) && sortida.isBefore(r.getSortida())) ||
		                    (entrada.isBefore(r.getEntrada()) && sortida.isAfter(r.getSortida())) )) {
						return true;
					}
				}
				if (h.getCapacitat() == capacitat) {
					return true;
				}
			}
		}

		return false;
	}

	public static Habitacions afegirhabi(Hotel hotel, LocalDate entrada, LocalDate sortida, String numeropersones) {
		int limit = 2;
		for (Habitacions h : hotel.getHabitacions()) {
			for (int i = 0; i < limit; i++) {
				int capacitat = Integer.parseInt(numeropersones) + i;
				for (Reserves r : hotel.pendents) {
					if (h.getNumhabitacio() == r.getHabitacio().getNumhabitacio()) {

						if (r.getHabitacio().getCapacitat() == capacitat && (
			                    (entrada.isAfter(r.getEntrada()) && entrada.isBefore(r.getSortida())) ||
			                    (sortida.isAfter(r.getEntrada()) && sortida.isBefore(r.getSortida())) ||
			                    (entrada.isBefore(r.getEntrada()) && sortida.isAfter(r.getSortida())) )) {
							return h;
						}
					}
				}
				

				for (Reserves r : hotel.confirmades) {
					if (h.getNumhabitacio() == r.getHabitacio().getNumhabitacio()) {
						if (r.getHabitacio().getCapacitat() == capacitat && (
			                    (entrada.isAfter(r.getEntrada()) && entrada.isBefore(r.getSortida())) ||
			                    (sortida.isAfter(r.getEntrada()) && sortida.isBefore(r.getSortida())) ||
			                    (entrada.isBefore(r.getEntrada()) && sortida.isAfter(r.getSortida())) )) {
							return h;
						}
					}

				}
				if (h.getCapacitat() == capacitat) {
					return h;
				}
			}
		}
		return null;

	}

}