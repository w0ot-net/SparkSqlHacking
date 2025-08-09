package jakarta.xml.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface XmlElementRef {
   Class type() default DEFAULT.class;

   String namespace() default "";

   String name() default "##default";

   boolean required() default true;

   public static final class DEFAULT {
      private DEFAULT() {
      }
   }
}
