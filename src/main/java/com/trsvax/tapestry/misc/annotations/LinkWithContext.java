package com.trsvax.tapestry.misc.annotations;



public @interface LinkWithContext {
	public static final class DEFAULT {}

	Class<?> page() default DEFAULT.class;
}
