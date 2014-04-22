package infra

import java.util.Calendar
import java.text.DateFormatSymbols
import java.util.Locale

object FormatadorDeDatas {

  implicit def calendar2PimpedCalendar(calendar: Calendar) = new {
    def dayAsNumber = calendar.get(Calendar.DAY_OF_MONTH)

    //como o mês começa em zero posso usar ele como indice do array
    def monthAsString = calendar.getDisplayName(Calendar.MONTH,Calendar.LONG,new Locale("pt","BR"))

  }
}