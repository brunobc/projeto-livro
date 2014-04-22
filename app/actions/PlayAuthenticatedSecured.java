package actions;

import controllers.routes;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Context;

public class PlayAuthenticatedSecured extends Security.Authenticator{
	
	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("email");
	}
	
	@Override
	public Result onUnauthorized(Context ctx) {
		ctx.flash().put("nao_logado","Para continuar precisa estar logado");
		return redirect(routes.LoginController.form());
	}

}
