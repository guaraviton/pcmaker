package br.com.pcmaker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmaker.common.util.StringUtils;
import br.com.pcmaker.controller.dto.EmailMarketingDTO;
import br.com.pcmaker.entity.Cliente;
import br.com.pcmaker.service.ClienteService;
import br.com.pcmaker.service.EmailMarketingService;
import br.com.pcmaker.service.EmailService;

@Service
public class EmailMarketingServiceImpl implements EmailMarketingService {

	@Autowired
	EmailService emailService;
	
	@Autowired
	ClienteService clienteService;
	
	@Override
	public void enviar(EmailMarketingDTO dto) {
		emailService.enviar(null, getDestinatarios(dto.getEmailDestinatarios(), dto.getEnviarTodosClientes()), dto.getAssunto(), dto.getTexto());
	}
	
	private String[] getDestinatarios(List<String> destinatarios, Boolean enviarTodosClientes) {
		List<String> destinatariosEmail = new ArrayList<String>();
		for(String destinatarioDTO : destinatarios){
			if(StringUtils.isBlank(destinatarioDTO)){
				continue;
			}
			destinatariosEmail.add(destinatarioDTO);
		}
		if(enviarTodosClientes!= null && enviarTodosClientes){
			List<Cliente> clientes = clienteService.query();
			for(Cliente cliente : clientes){
				destinatariosEmail.add(cliente.getEmail());
			}
		}
		return destinatariosEmail.toArray(new String[destinatariosEmail.size()]);
	}

}
