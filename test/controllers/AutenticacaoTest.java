package controllers;

import infra.TestHelper;
import models.Evento;
import models.Usuario;

import org.junit.Assert;
import org.junit.Test;

import play.mvc.HandlerRef;
import play.mvc.Result;
import play.test.FakeRequest;
import akka.util.Crypt;

import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;

import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.redirectLocation;

public class AutenticacaoTest extends TestHelper {

	private Evento novoEvento;

	@Override
	protected void popula() {
		novoEvento = novoEvento(false, "contato@gmail.com");

		Ebean.save(novoEvento);
		Ebean.save(new Usuario("test@gmail.com", Crypt.sha1("123456")));
	}

	@Test
	public void naoDeveriaAcessarAAdministracaoSemEstarLogado() {
		executaFakeApplication(new Runnable() {

			@Override
			public void run() {
				Result result = callAction(
						controllers.admin.routes.ref.TodosEventosController
								.todos(),
						fakeRequest());
				Assert.assertEquals("/login", redirectLocation(result));

			}
		});
	}

	@Test
	public void deveriaAcessarAAdministracaoDepoisDeLogar() {

		executaFakeApplication(new Runnable() {

			@Override
			public void run() {
				FakeRequest request = fakeRequest().withFormUrlEncodedBody(
						ImmutableMap.of("email", "test@gmail.com", "senha",
								"123456"));
				Result result = callAction(
						controllers.routes.ref.LoginController.loga(), request);
				Assert.assertEquals("/eventos/admin", redirectLocation(result));
			}
		});
	}

	@Test
	public void depoisDeAprovadoOEventoDeveriaAparecerNaHome() {
		executaFakeApplication(new Runnable() {

			@Override
			public void run() {

				FakeRequest requestDeLogin = fakeRequest().withSession("email",
						"test@gmail.com");
				callAction(controllers.admin.routes.ref.TodosEventosController
						.aprova(novoEvento.getId()),requestDeLogin);
				Result result = callAction(
						controllers.routes.ref.EventosController.lista(),
						fakeRequest());
				String html = contentAsString(result);
				Assert.assertTrue(html.contains(novoEvento.getNome()));
			}
		});
	}
}
