package br.com.pcmaker.spring.view.validation.collection;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@SuppressWarnings("rawtypes")
public class NotEmptyCollectionValidator implements ConstraintValidator<NotEmptyCollection, Collection> {

	@Override
	public void initialize(NotEmptyCollection constraintAnnotation) {
	}

	@Override
	public boolean isValid(Collection lista, ConstraintValidatorContext constraintContext) {
		if(lista == null || lista.isEmpty() ){
			return false;
		}
		return true;
	}

}
