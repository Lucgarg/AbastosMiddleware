package com.abastos.service;

import java.util.Map;

import com.abastos.service.exceptions.MailException;

public interface MailService {
			public void sendMail(Map<String, Object> mapa,Long templat, String...to) throws MailException;
			public void sendMailHtml(Map<String, Object> mapa, Long templat, String...to) throws MailException;
			
	}
	

