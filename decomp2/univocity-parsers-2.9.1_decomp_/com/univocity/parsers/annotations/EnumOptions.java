package com.univocity.parsers.annotations;

import com.univocity.parsers.conversions.EnumSelector;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface EnumOptions {
   EnumSelector[] selectors() default {EnumSelector.NAME, EnumSelector.ORDINAL, EnumSelector.STRING};

   String customElement() default "";
}
