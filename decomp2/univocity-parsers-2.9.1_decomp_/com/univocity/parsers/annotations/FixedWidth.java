package com.univocity.parsers.annotations;

import com.univocity.parsers.fixed.FieldAlignment;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface FixedWidth {
   int value() default -1;

   FieldAlignment alignment() default FieldAlignment.LEFT;

   char padding() default ' ';

   boolean keepPadding() default false;

   int from() default -1;

   int to() default -1;
}
