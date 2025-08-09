package jakarta.xml.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE})
public @interface XmlSchema {
   String NO_LOCATION = "##generate";

   XmlNs[] xmlns() default {};

   String namespace() default "";

   XmlNsForm elementFormDefault() default XmlNsForm.UNSET;

   XmlNsForm attributeFormDefault() default XmlNsForm.UNSET;

   String location() default "##generate";
}
