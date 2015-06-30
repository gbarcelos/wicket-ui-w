package br.com.oak.wicket.util;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface PaginaWeb {

	String nome();
	
	String parteUrl() default "";
	
	String extensao() default "";

	String descricao() default "";
}