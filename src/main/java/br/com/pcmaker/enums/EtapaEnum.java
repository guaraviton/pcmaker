package br.com.pcmaker.enums;

import br.com.pcmaker.common.util.MessageUtils;
import br.com.pcmaker.entity.Etapa;

public enum EtapaEnum {
	
	ABERTA(1){
		@Override
        public String toString() {
            return MessageUtils.get("label.aberta");
        }
	}, ANALISE_ORCAMENTO(2){
		@Override
        public String toString() {
            return MessageUtils.get("label.analise.orcamento");
        }
	}, PENDENTE_APROVACAO_ORCAMENTO_CLIENTE(3){
		@Override
        public String toString() {
            return MessageUtils.get("label.pendente.aprovacao.orcamento.cliente");
        }
	}, ORCAMENTO_REPROVADO_CLIENTE(4){
		@Override
        public String toString() {
            return MessageUtils.get("label.orcamento.reprovado.cliente");
        }
	}, ORCAMENTO_APROVADO_CLIENTE(5){
		@Override
        public String toString() {
            return MessageUtils.get("label.orcamento.aprovado.cliente");
        }
	}, EXECUTANDO_SERVICO(6){
		@Override
        public String toString() {
            return MessageUtils.get("label.executando.servico");
        }
	}, SERVICO_FINALIZADO(7){
		@Override
        public String toString() {
            return MessageUtils.get("label.servico.finalizado");
        }
	}, AGUARDANDO_RETIRADA_CLIENTE(8){
		@Override
        public String toString() {
            return MessageUtils.get("label.aguardando.retirada.cliente");
        }
	}, FINALIZADO(9){
		@Override
        public String toString() {
            return MessageUtils.get("label.finalizado");
        }
	},
	CANCELADA(10){
		@Override
        public String toString() {
            return MessageUtils.get("label.cancelada");
        }
	};
	
	Integer id;
	
    private EtapaEnum(final Integer id) {
    	this.id = id;
    }
    
    public static EtapaEnum getEtapaOrdemServico(final Integer id) {
    	for (EtapaEnum e : EtapaEnum.values()) {
            if (e.id == id) {
            	return e;
            }
    	}
    	return null;
    }
    
    public Etapa getEtapa() {
		return new Etapa(getId(), this.toString());
	}

	public Integer getId() {
		return id;
	}
 
}
