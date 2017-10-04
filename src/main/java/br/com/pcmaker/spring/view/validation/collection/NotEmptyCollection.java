package br.com.pcmaker.spring.view.validation.collection;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD, ANNOTATION_TYPE  })
@Retention(RUNTIME)
@Constraint(validatedBy = NotEmptyCollectionValidator.class)
@Documented
public @interface NotEmptyCollection {

	String message() default "lista vazia ou nula";

	Class<?>[]groups() default {};

	Class<? extends Payload>[]payload() default {};

}
