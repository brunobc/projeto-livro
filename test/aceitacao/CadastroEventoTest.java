package aceitacao;

import infra.TestHelper;

import org.junit.Assert;
import org.junit.Test;

import play.libs.F;
import play.test.TestBrowser;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

public class CadastroEventoTest extends TestHelper{
	
	@Test
	public void deveriaCadastrarUmNovoEvento() {
		F.Callback<TestBrowser> fluxoDoUsuario = new F.Callback<TestBrowser>() {

			@Override
			public void invoke(TestBrowser navegador) throws Throwable {
				navegador.goTo("http://localhost:9001/eventos/novo");
				navegador.fill("input[name='nome']").with("Conexão Java");
				navegador.fill("input[name='emailParaContato']").with(
						"contato@caelum.com.br");
				navegador.fill("textarea[name='descricao']").with(
						"Evento para quem está comecando no java");
				navegador.fill("select[name='estado']").with("SAO_PAULO");
				navegador.fill("input[name='site']").with(
						"http://www.conexaojava.com.br/");
				navegador.fill("input[name='twitter']").with("@conexaojava");
				navegador.fill("input[name='dataDeInicio']").with("2014-04-13");
				navegador.fill("input[name='dataDeFim']").with("2014-04-16");
				navegador.submit("input[type='submit']");
				Assert.assertEquals(
						"evento cadastrado com sucesso, aguarde a moderacao",
						navegador.$(".msg_sucesso").getText());
			}
		};
		executaAceitacao(fluxoDoUsuario);
	}
}
