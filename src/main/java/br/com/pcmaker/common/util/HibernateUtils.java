package br.com.pcmaker.common.util;

import org.hibernate.proxy.HibernateProxy;

public class HibernateUtils {

	public static Boolean isInstance(Object proxy, Class classeComparacao){
		if (isProxy(proxy)) {
	        return getRealObject(proxy).getClass().isAssignableFrom(classeComparacao);
	    }
		return proxy.getClass().isAssignableFrom(classeComparacao);
	}
	
	public static Object getRealObject(Object proxy){
		if (isProxy(proxy)) {
			return ((HibernateProxy) proxy).getHibernateLazyInitializer().getImplementation();
	    }
		return proxy;
	}
	
	public static Boolean isProxy(Object objeto){
		return objeto instanceof HibernateProxy;
	}
}
