package br.com.pcmaker.common.util;

import java.io.File;
import java.io.InputStream;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import br.com.pcmaker.common.exception.GlobalException;

public class XmlUtil {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object parse(File file, Class classe) {
		try {
			Serializer serializer = new Persister();
			return serializer.read(classe, file);
		} catch (Exception e) {
			throw new GlobalException("Erro no parse do xml " + file.getName(), e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object parse(InputStream is, Class classe) {
		try {
			Serializer serializer = new Persister();
			return serializer.read(classe, is);
		} catch (Exception e) {
			throw new GlobalException("Erro no parse do xml ", e);
		}
	}
	
}
