package com.abastos.service.impl;

import java.util.Map;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import com.abastos.dao.ContenidoDAO;
import com.abastos.dao.jdbc.ContenidoDAOImpl;
import com.abastos.service.MailService;
import com.abastos.service.exceptions.MailException;
import com.abastos.service.utils.VelocityUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MailServiceImpl implements MailService {

	public MailServiceImpl() {

	}

	private static final String EMAIL = "abastosmarketplace@gmail.com";
	private static final String PASSWORD = "27004Pardoba";
	public void sendMail(Map<String, Object> mapa, Long templat, String...to) throws MailException {
		try {
			String templ = VelocityUtils.generateTemplate(templat, mapa);
			
			JsonObject json = new Gson().fromJson(templ, JsonObject.class);
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(EMAIL, PASSWORD));
			email.setSSLOnConnect(true);
			email.setFrom(EMAIL);
			email.setSubject(json.get("subject").getAsString());
			email.setMsg(json.get("mensaje").getAsString());
			email.addTo(to);
			email.send();


		}catch(EmailException se) {
			throw new MailException("Intentando enviar email "
					+ " from " + EMAIL + " a " + to + se);
		}
	}
	public void sendMailHtml(Map<String, Object> mapa, Long templat, String...to) throws MailException {
		try {
			String templ = VelocityUtils.generateTemplate(templat, mapa);
		
			JsonObject json = new Gson().fromJson(templ, JsonObject.class);
		
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(EMAIL, PASSWORD));
			email.setSSLOnConnect(true);
			email.setFrom(EMAIL,PASSWORD);
			email.setSubject(json.get("subject").getAsString());
			email.setHtmlMsg(json.get("mensaje").getAsString());
			email.addTo(to[0]);
			email.send();


		}catch(EmailException se) {
			throw new MailException("Intentando enviar email "
					+ " from " + EMAIL + " a " + to[0] + se);
		}
	}

}
