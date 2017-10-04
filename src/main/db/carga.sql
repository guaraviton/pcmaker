INSERT INTO `configuracao` (`id`,`chave`,`valor`,`descricao`) VALUES (1,'jwt.header','Authorization', 'Nome do token no header');
INSERT INTO `configuracao` (`id`,`chave`,`valor`,`descricao`) VALUES (2,'jwt.secret','p3_mak3r', 'Chave usada para assinatura digital do token');
INSERT INTO `configuracao` (`id`,`chave`,`valor`,`descricao`) VALUES (3,'jwt.expiration','30', 'Tempo de expiracao do token em minutos');
INSERT INTO `configuracao` (`id`,`chave`,`valor`,`descricao`) VALUES (4,'senha.admin','f865b53623b121fd34ee5426c792e5c33af8c227', 'Senha digest para acesso via admin');
INSERT INTO `configuracao` (`id`,`chave`,`valor`,`descricao`, `parametro`) VALUES (5,'texto.email.retirada.equipamento', 'Prezado Cliente<div><br></div><div>o seu equipamento já se encontra disponível para retirada.</div><div><br></div><div>Para retirada, será necessária a apresentação de algum documento de identificação e o documento de solicitação do serviço.</div><div><br></div><div>Número do Serviço: #NUMERO_SERVICO#</div><div>Data de Entrada: #DATA_ENTRADA#<br></div><div><br></div><div>Att</div><div><img src=\"http://pcmaker-guaraviton.rhcloud.com/assets/img/pcmaker_sm.png\"><br></div>', 'Texto para envio de email de saida de equipamento', 'S');
INSERT INTO `configuracao` (`id`,`chave`,`valor`,`descricao`, `parametro`) VALUES (6,'texto.email.abertura.ordem.servico', 'Prezado Cliente<div><br></div><div>segue abaixo os dados da ordem de serviço solicitada.<br></div><div><br></div><div>Número do Serviço: #NUMERO_SERVICO#</div><div>Data de Entrada: #DATA_ENTRADA#</div><div><br></div><div>Att</div><div><img src="http://pcmaker-guaraviton.rhcloud.com/assets/img/pcmaker_sm.png"></div>', 'Conteúdo de e-mail para envio de abertura de ordem de serviço', 'S');


INSERT INTO `perfil` (`id`,`nome`) VALUES (1,'admin');
INSERT INTO `perfil` (`id`,`nome`) VALUES (2,'tecnico');
INSERT INTO `perfil` (`id`,`nome`) VALUES (3,'atendemente');

INSERT INTO `pcmaker`.`etapa` (`id`, `nome`) VALUES ('1', 'Aberta');
INSERT INTO `pcmaker`.`etapa` (`id`, `nome`) VALUES ('2', 'Análise Orcamento');
INSERT INTO `pcmaker`.`etapa` (`id`, `nome`) VALUES ('3', 'Pendente de Aprovação do Orçamento pelo Cliente');
INSERT INTO `pcmaker`.`etapa` (`id`, `nome`) VALUES ('4', 'Orcamento Reprovado Cliente');
INSERT INTO `pcmaker`.`etapa` (`id`, `nome`) VALUES ('5', 'Orçamento Aprovado Cliente');
INSERT INTO `pcmaker`.`etapa` (`id`, `nome`) VALUES ('6', 'Executando Serviço');
INSERT INTO `pcmaker`.`etapa` (`id`, `nome`) VALUES ('7', 'Serviço Finalizado');
INSERT INTO `pcmaker`.`etapa` (`id`, `nome`) VALUES ('8', 'Aguardando Retirada');
INSERT INTO `pcmaker`.`etapa` (`id`, `nome`) VALUES ('9', 'Finalizada');
INSERT INTO `pcmaker`.`etapa` (`id`, `nome`) VALUES ('10', 'Cancelada');