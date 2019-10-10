package co.uniquindio.grid.dto.utils;

import java.sql.Timestamp;
import java.util.Date;

public class Utilidades {

	/*
	 * Metodo que da un timestamp apartir de un tipo date
	 */
	public static Timestamp DateToTimestamp(Date date) {

		long time = date.getTime();
		System.out.println("Time in Milliseconds: " + time);

		Timestamp ts = new Timestamp(time);

		return ts;
	}

}
