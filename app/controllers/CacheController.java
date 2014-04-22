package controllers;

import actions.PlayAuthenticatedSecured;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class CacheController extends Controller{

	@Security.Authenticated(PlayAuthenticatedSecured.class)
	public static Result invalidate(){
		String key = request().getQueryString("key");
		Cache.remove(key);		
		return ok();
	}
}
