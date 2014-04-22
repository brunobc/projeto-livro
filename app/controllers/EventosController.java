package controllers;

import java.io.IOException;
import java.util.List;

import models.Evento;
import persistencia.Eventos;
import play.Logger;
import play.api.http.MediaRange;
import play.cache.Cache;
import play.data.Form;
import play.i18n.Lang;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.Ebean;
import com.github.asouza.play.paperclip.PaperClipPlugin;
import com.github.asouza.play.paperclip.UploadedImage;
import com.github.asouza.play.paperclip.formatter.UploadedImageFormatter;
import play.Play;

public class EventosController extends Controller {

	private static Form<Evento> eventoForm = Form.form(Evento.class);
	private static PaperClipPlugin plugin = Play.application().plugin(PaperClipPlugin.class);

	public static Result novo() {
		return ok(views.html.eventos.novo.render(eventoForm));
	}

	public static Result cria() throws IOException {
		Form<Evento> formFromRequest = eventoForm.bindFromRequest();
		if (formFromRequest.hasErrors()) {
			return badRequest(views.html.eventos.novo.render(formFromRequest));
		}
		Evento evento = formFromRequest.get();
		String destino = gravaDestaque();
		if (destino != null) {
			evento.setCaminhoImagem(destino);
		}
		Ebean.save(evento);
		return redirect(routes.EventosController.lista());
	}

	private static String gravaDestaque() throws IOException {
		UploadedImage image = UploadedImageFormatter.toUploadedImage(request(),"destaque");
		UploadedImage croppedImage = plugin.centeredCrop(image,200,200);		
		return croppedImage.save("destaques");
	}

	// duracao em segundos, 0 é infinito.
	// @Cached(key = "home", duration = 3600)
	// @BodyParser.Of(BodyParser.Json.class)
	public static Result lista() {
		Logger.info("Listando os eventos aprovados");
		List<Evento> aprovados = Eventos.paraAcontecer();
		List<MediaRange> acceptedTypes = request().acceptedTypes();
		List<Lang> acceptLanguages = request().acceptLanguages();
		String key = "text/html;pt_br";
		// Http.Context.current().clearLang();
		// if(!acceptedTypes.isEmpty()){
		// MediaRange media = acceptedTypes.get(0);
		// key = media.toString();
		// }
		// if(!acceptLanguages.isEmpty()){
		// Lang preferredLang = acceptLanguages.get(0);
		// key = key +";"+preferredLang.code();
		// }
		// if(Cache.get("home_"+key)!=null){
		// Status status = (Status) Cache.get("home_"+key);
		// return status;
		// }
		if (request().accepts("text/html")) {
			Status status = ok(views.html.eventos.lista.render(aprovados));
			Cache.set("home_" + key, status);
			return status;
		}
		if (request().accepts("application/json")) {
			Status status = ok(Json.toJson(aprovados));
			Cache.set("home_" + key, status);
			return status;
		}
		return status(NOT_ACCEPTABLE);
	}

	public static Result listaPaginado(int pagina) {
		return TODO;
	}

	public static Result visualiza(Integer id) {
		return TODO;
	}

}
