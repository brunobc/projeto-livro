package actions;

import controllers.routes;
import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.SimpleResult;

public class AuthenticatedAction extends Action.Simple{

	@Override
	public Promise<SimpleResult> call(Context ctx) throws Throwable {
		String email = ctx.session().get("email");
		if(email!=null){
			return delegate.call(ctx);
			
		}		
		//é só para envelopar numa promise mesmo. Não quer dizer que é um processo assincrono. Podia ser.
		ctx.flash().put("nao_logado","Para continuar precisa estar logado");
		return F.Promise.pure(redirect(routes.LoginController.form()));
	}

}
