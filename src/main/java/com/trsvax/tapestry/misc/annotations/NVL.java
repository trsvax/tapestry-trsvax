package com.trsvax.tapestry.misc.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NVL {
	
	public boolean persist() default true;

}
