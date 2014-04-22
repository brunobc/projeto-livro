package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;


public class CookieLang extends Controller {

  public static Result mudaIdioma(){
    String lang = request().getQueryString("lang");    
    response().setCookie(Play.langCookieName(),lang);    
    return redirect(routes.EventosController.lista());

  }
  
  public static Result limpaIdioma(){
	  response().discardCookie(Play.langCookieName());
	  return redirect(routes.EventosController.lista());
	  
  }
}