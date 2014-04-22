package persistencia;

import infra.TestHelper;

import java.util.List;

import models.Evento;

import org.junit.Assert;
import org.junit.Test;

import com.avaje.ebean.Ebean;
import com.google.common.collect.Lists;

public class EventosTest extends TestHelper {

	@Override
	public void popula() {
		Evento evento = novoEvento(true, "test@gmail.com");
		Evento evento2 = novoEvento(false, "test2@gmail.com");
		Ebean.save(Lists.newArrayList(evento, evento2));
	}

	@Test
	public void deveriaListarApenasOsAprovados() {
		executaFakeApplication(new Runnable() {

			@Override
			public void run() {
				List<Evento> aprovados = Eventos.aprovados(true);
				Assert.assertEquals(1, aprovados.size());
				Assert.assertEquals("test@gmail.com", aprovados.get(0)
						.getEmailParaContato());
			}
		});
	}

	@Test
	public void deveriaListarApenasOsNaoAprovados() {
		executaFakeApplication(new Runnable() {

			@Override
			public void run() {
				List<Evento> aprovados = Eventos.aprovados(false);
				Assert.assertEquals(1, aprovados.size());
				Assert.assertEquals("test2@gmail.com", aprovados.get(0)
						.getEmailParaContato());
			}
		});
		
	}
}
