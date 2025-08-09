package jakarta.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Resources.class)
public @interface Resource {
   String name() default "";

   String lookup() default "";

   Class type() default Object.class;

   AuthenticationType authenticationType() default Resource.AuthenticationType.CONTAINER;

   boolean shareable() default true;

   String mappedName() default "";

   String description() default "";

   public static enum AuthenticationType {
      CONTAINER,
      APPLICATION;
   }
}
