package br.com.pcmaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcmaker.controller.dto.EmailMarketingDTO;
import br.com.pcmaker.service.EmailMarketingService;

@RestController
@RequestMapping("/emailMarketing")
public class EmailMarketingController {
	
	@Autowired
	EmailMarketingService service;

	@RequestMapping(value="enviar", method = RequestMethod.PUT)
    public void enviar(@RequestBody EmailMarketingDTO dto){
		service.enviar(dto);
    }
}
