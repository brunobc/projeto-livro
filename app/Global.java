import java.lang.reflect.Method;
import java.util.Calendar;

import play.Application;
import play.GlobalSettings;
import play.api.mvc.EssentialAction;
import play.api.mvc.EssentialFilter;
import play.api.mvc.Handler;
import play.data.format.Formatters;
import play.i18n.Lang;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Http.RequestHeader;
import play.mvc.SimpleResult;
import converters.Html5CalendarFormatter;


public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		Formatters.register(Calendar.class,new Html5CalendarFormatter());
	}
	
	/*
	 * http://stackoverflow.com/questions/20624936/in-play-framework-2-x-how-to-add-a-global-action-filter/20639179#20639179(non-Javadoc)
	 * 
	 */
	@Override
	public Action onRequest(Request request, Method action) {
		return new Action.Simple() {
			@Override
			public Promise<SimpleResult> call(Context context) throws Throwable {
				//context.clearLang();
				return delegate.call(context);
			}
	     };
	}
	
}
