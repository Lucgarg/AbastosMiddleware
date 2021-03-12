package com.abastos.service;

import java.util.Map;

import javax.swing.text.html.HTML;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import com.abastos.service.exceptions.MailException;

public interface MailService {
			public void sendMail(Map<String, Object> mapa,Long templat, String...to) throws MailException;
			public void sendMailHtml(Map<String, Object> mapa, Long templat, String...to) throws MailException;
			
	}
	

