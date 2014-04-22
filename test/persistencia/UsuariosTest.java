package persistencia;

import infra.TestHelper;
import models.Usuario;

import org.junit.Assert;
import org.junit.Test;

import play.libs.F.Option;
import akka.util.Crypt;

import com.avaje.ebean.Ebean;

public class UsuariosTest extends TestHelper {

	protected void popula() {
		Usuario usuario = new Usuario("test@gmail.com", Crypt.sha1("123456"));
		Ebean.save(usuario);
	}

	@Test
	public void deveriaAcharOUsuarioLogado() {
		executaFakeApplication(new Runnable() {

			@Override
			public void run() {
				Option<Usuario> talvezUmUsuario = Usuarios.existe(
						"test@gmail.com", Crypt.sha1("123456"));
				Assert.assertTrue(talvezUmUsuario.isDefined());
				;
			}
		});

	}

}
