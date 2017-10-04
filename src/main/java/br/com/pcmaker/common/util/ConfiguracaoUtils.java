package br.com.pcmaker.common.util;

import org.apache.commons.configuration2.DatabaseConfiguration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para leitura das propriedades de configuracao do arquivo configuracao.properties
 * @author y2jm
 *
 */
@Component
public class ConfiguracaoUtils {
	
	private static FileBasedConfiguration configuracaoGlobal;
	private static DatabaseConfiguration configuracaoDS;
	public final static String SENHA_ADMIN = "senha.admin";
	
	public static String get(String key){
		String valor;
		valor = configuracaoGlobal.getString(key);
		if(StringUtils.isNotBlank(valor)){
			return valor;
		}
		return configDB(key);
	}
	
	public static String getSenhaAdmin() {
		return configuracaoDS.getString(SENHA_ADMIN);
	}

	private static String configDB(String key) {
		return configuracaoDS.getString(key);
	}
	
	@Autowired
	public void setConfiguracaoGlobal(FileBasedConfiguration configuracaoGlobal) {
		ConfiguracaoUtils.configuracaoGlobal = configuracaoGlobal;
	}

	@Autowired
	public void setConfiguracaoDS(DatabaseConfiguration configuracaoDS) {
		ConfiguracaoUtils.configuracaoDS = configuracaoDS;
	}
	
	public static void main(String[] args) {
		System.out.println(DigestUtils.sha1Hex("admin123"));
	}
	
}
