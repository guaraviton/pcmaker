package br.com.pcmaker.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public abstract class CookieUtils {

	public static void createCookie(HttpServletResponse response, String nome, String valor, String path, String dominio, int expires){
    	Cookie cookie = new Cookie(nome, valor);
    	if(StringUtils.isNotBlank(path)){
    		cookie.setPath(path);
    	}
		if(StringUtils.isNotBlank(dominio)){
			cookie.setDomain(dominio);	
		}
		cookie.setMaxAge(expires);	
		response.addCookie(cookie);		
    }
}
