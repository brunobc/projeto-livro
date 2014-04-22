package persistencia;

import models.Usuario;
import play.libs.F.Option;

import com.avaje.ebean.Ebean;

public class Usuarios {

	public static Option<Usuario> existe(String email, String senha) {
		Usuario usuario = Ebean.find(Usuario.class).where().eq("email", email)
				.eq("senha", senha).findUnique();	
		if(usuario==null){
			return Option.<Usuario>None();
		}
		return Option.Some(usuario);
		
	}

}
