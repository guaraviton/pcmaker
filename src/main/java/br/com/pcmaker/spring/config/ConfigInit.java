package br.com.pcmaker.spring.config;

import javax.sql.DataSource;

import org.apache.commons.configuration2.DatabaseConfiguration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.DatabaseBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.ClasspathLocationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigInit {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigInit.class);
	private static final String PROPERTIES_FILE_NAME = "configuracao.properties";
	private static final String PROPERTIES_LOCAL_FILE_NAME = "localhost.properties";
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public FileBasedConfiguration configuracaoGlobal() throws Exception {
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		.configure(params.properties()
		.setLocationStrategy(new ClasspathLocationStrategy())
		.setFileName(PROPERTIES_FILE_NAME));
		return builder.getConfiguration();
	}
	
	@Bean
	public FileBasedConfiguration configuracaoLocal() throws Exception {
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		.configure(params.properties()
		.setLocationStrategy(new ClasspathLocationStrategy())
		.setFileName(PROPERTIES_LOCAL_FILE_NAME));
		try{
			return builder.getConfiguration();
		}catch(ConfigurationException e){
			LOGGER.warn("Nao encontrou arquivo local " + PROPERTIES_LOCAL_FILE_NAME);
		}
		return null;
	}
	
	@Bean
	public DatabaseConfiguration configuracaoDS() throws Exception {
		BasicConfigurationBuilder<DatabaseConfiguration> builder = new BasicConfigurationBuilder<DatabaseConfiguration>(DatabaseConfiguration.class);
		DatabaseBuilderParameters databaseBuilderParameters = new Parameters().database();
		databaseBuilderParameters
	    .setDataSource(dataSource)
	    .setTable("configuracao")
	    .setKeyColumn("chave")
	    .setValueColumn("valor");
		builder.configure(databaseBuilderParameters);
		return builder.getConfiguration();
	}
	
}
