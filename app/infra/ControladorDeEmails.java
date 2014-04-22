package infra;

import play.api.templates.Html;
import models.Evento;

import com.typesafe.plugin.MailerAPI;
import com.typesafe.plugin.MailerPlugin;

public class ControladorDeEmails {

	
	public static void informaNovo(Evento evento){
		MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
		mail.setSubject("mailer");
		mail.addFrom("cadastros@agendatech.com.br");
		mail.addRecipient("algumeamil@gmail.com");
		Html render = views.html.email.novo.render(evento);		
		mail.sendHtml(render.body());
	}
}
