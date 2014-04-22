package controllers.admin;

import models.Usuario;
import akka.util.Crypt;

import com.avaje.ebean.Ebean;

import play.data.DynamicForm.Dynamic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class UsuariosController extends Controller{
	
	private static Form<Dynamic> form = Form.form();

	public static Result cria(){
		Form<Dynamic> formPreenchido = form.bindFromRequest();
		String email = formPreenchido.data().get("email");
		String senha = Crypt.sha1(formPreenchido.data().get("senha"));
		Usuario novo = new Usuario(email,senha);
		Ebean.save(novo);
		return ok();
	}
	
	public static Result form(){
		return ok(views.html.usuarios.novo.render());
	}
}
