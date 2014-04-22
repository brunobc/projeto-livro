package converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import play.data.format.Formatters.SimpleFormatter;
import views.html.main;

public class Html5CalendarFormatter extends SimpleFormatter<Calendar> {

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Calendar parse(String value, Locale locale) throws ParseException {
		if (!value.trim().isEmpty()) {
			Date date = formatter.parse(value);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		}
		return null;
	}

	@Override
	public String print(Calendar value, Locale locale) {
		if (value != null) {
			return formatter.format(value.getTime());
		}
		return "";
	}
	
}
