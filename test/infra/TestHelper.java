package infra;

import java.util.Calendar;

import org.openqa.selenium.WebDriver;

import models.Evento;
import play.libs.F;
import play.test.TestBrowser;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.SqlUpdate;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.MySqlPlatform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;

import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

public class TestHelper {

	public void dropDb() {

		String serverName = "default";

		EbeanServer server = Ebean.getServer(serverName);

		ServerConfig config = new ServerConfig();

		DdlGenerator ddl = new DdlGenerator();
		ddl.setup((SpiEbeanServer) server, new MySqlPlatform(), config);

		// Drop
		ddl.runScript(false, ddl.generateDropDdl());

		SqlUpdate dropEvolutions = Ebean
				.createSqlUpdate("drop table play_evolutions");
		Ebean.execute(dropEvolutions);

	}

	/**
	 * Caso alguem queira popular o banco para uma situação.
	 */
	protected void popula() {
	}

	protected void executaFakeApplication(final Runnable runnable) {
		running(fakeApplication(), new Runnable() {

			@Override
			public void run() {
				try {
					popula();
					runnable.run();
				} finally {
					dropDb();
				}
			}
		});
	}

	protected void executaAceitacao(final F.Callback<TestBrowser> callback) {
		Class<? extends WebDriver> hTMLUNIT = HTMLUNIT;
		running(testServer(9001, fakeApplication()), hTMLUNIT,
				new F.Callback<TestBrowser>() {

					@Override
					public void invoke(TestBrowser navegador) throws Throwable {
						popula();
						callback.invoke(navegador);
						dropDb();
					}

				});
	}

	protected Evento novoEvento(boolean aprovado, String emailContato) {
		Evento evento = new Evento();
		Calendar hoje = Calendar.getInstance();
		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_WEEK, 2);
		evento.setDataDeInicio(hoje);
		evento.setDataDeFim(amanha);
		evento.setDescricao("descricao teste");
		evento.setAprovado(aprovado);
		evento.setNome("evento de teste");
		evento.setEmailParaContato(emailContato);
		return evento;
	}
}
