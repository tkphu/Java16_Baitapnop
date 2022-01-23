package cybersoft.javabackend.gamedoanso.util;
/*
 * Mục đích: Xử lý LocalDateTime -> String
 * Người tạo: Trần Kim Phú
 * Ngày tạo: 23/01/2022
 * Version: 1.0
 * */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public static String toString(LocalDateTime date) {
		return date.format(formatter);
	}
}
