package persistencia;

import java.util.Calendar;
import java.util.List;

import models.Evento;

import com.avaje.ebean.Ebean;

public class Eventos {

	public static List<Evento> aprovados(boolean situacao) {
		return Ebean.find(Evento.class).where().eq("aprovado", situacao)
				.findList();
	}

	public static List<Evento> paraAcontecer() {
		Calendar hoje = Calendar.getInstance();
		hoje.set(Calendar.HOUR_OF_DAY, 0);
		hoje.set(Calendar.MINUTE, 0);
		hoje.set(Calendar.SECOND, 0);
		return Ebean.find(Evento.class).where().eq("aprovado", true)
				.ge("dataDeInicio", hoje.getTime()).findList();
	}
}
