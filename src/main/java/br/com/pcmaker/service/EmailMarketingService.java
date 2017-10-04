package br.com.pcmaker.service;

import br.com.pcmaker.controller.dto.EmailMarketingDTO;

public interface EmailMarketingService {

	void enviar(EmailMarketingDTO dto);
	
}
