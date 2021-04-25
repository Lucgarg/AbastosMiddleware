package com.abastos.service.impl;

import java.util.Map;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import com.abastos.configuration.ConfigurationManager;

import com.abastos.service.MailService;
import com.abastos.service.exceptions.MailException;
import com.abastos.service.utils.VelocityUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MailServiceImpl implements MailService {

	public MailServiceImpl() {

	}

	private static final String EMAIL = "mailService.user";
	private static final String PASSWORD = "mailService.password";
	private static final String HOST = "mailService.hostName";
	private static final String PORT = "mailService.port";
	private static final String TEMPL_SUBCJ = "subject";
	private static final String TEMPL_MENS = "mensaje";
	private static ConfigurationManager cfg = ConfigurationManager.getInstance();
	public void sendMail(Map<String, Object> mapa, Long templat, String...to) throws MailException {
		try {
			String templ = VelocityUtils.generateTemplate(templat, mapa);
			
			JsonObject json = new Gson().fromJson(templ, JsonObject.class);
			Email email = new SimpleEmail();
			email.setHostName(cfg.getParameter(HOST));
			email.setSmtpPort(Integer.valueOf(cfg.getParameter(PORT)));
			email.setAuthenticator(new DefaultAuthenticator(cfg.getParameter(EMAIL), cfg.getParameter(PASSWORD)));
			email.setSSLOnConnect(true);
			email.setFrom(cfg.getParameter(EMAIL));
			email.setSubject(json.get(TEMPL_SUBCJ).getAsString());
			email.setMsg(json.get(TEMPL_MENS).getAsString());
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
			email.setHostName(cfg.getParameter(HOST));
			email.setSmtpPort(Integer.valueOf(cfg.getParameter(PORT)));
			email.setAuthenticator(new DefaultAuthenticator(cfg.getParameter(EMAIL), cfg.getParameter(PASSWORD)));
			email.setSSLOnConnect(true);
			email.setFrom(cfg.getParameter(EMAIL));
			email.setSubject(json.get(TEMPL_SUBCJ).getAsString());
			email.setHtmlMsg(json.get(TEMPL_MENS).getAsString());
			email.addTo(to[0]);
			email.send();


		}catch(EmailException se) {
			throw new MailException("Intentando enviar email "
					+ " from " + EMAIL + " a " + to[0] + se);
		}
	}

}
