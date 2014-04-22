package controllers.admin;

import infra.ControladorDeEmails;

import java.util.List;

import models.Evento;
import persistencia.Eventos;
import play.libs.F.Function;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import actions.PlayAuthenticatedSecured;

import com.avaje.ebean.Ebean;

@Security.Authenticated(PlayAuthenticatedSecured.class)
public class TodosEventosController extends Controller {

//	@With(AuthenticatedAction.class)
	public static Result todos() {
			List<Evento> aprovados = Eventos.aprovados(true);
			List<Evento> naoAprovados = Eventos.aprovados(false);
			return ok(views.html.eventos.admin.todos_eventos.render(naoAprovados,
					aprovados));
	}

	public static Promise<Result> aprova(Integer id) {
		final Evento evento = Ebean.find(Evento.class, id);
		evento.setAprovado(true);
		Ebean.update(evento);
		Promise<Void> enviandoEmail = Promise.promise(new Function0<Void>() {

			@Override
			public Void apply() throws Throwable {
				ControladorDeEmails.informaNovo(evento);
				return null;
			}
		});
		
		Promise<Result> result = enviandoEmail.map(new Function<Void, Result>() {
			@Override
			public Result apply(Void nada) throws Exception {
				// TODO Auto-generated method stub
				return redirect(controllers.admin.routes.TodosEventosController
						.todos());
			}

		});
		return result;
	}
}
