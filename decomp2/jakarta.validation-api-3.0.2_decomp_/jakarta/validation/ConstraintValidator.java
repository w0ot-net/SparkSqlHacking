package jakarta.validation;

import java.lang.annotation.Annotation;

public interface ConstraintValidator {
   default void initialize(Annotation constraintAnnotation) {
   }

   boolean isValid(Object var1, ConstraintValidatorContext var2);
}
