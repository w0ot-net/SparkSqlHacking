package jakarta.validation.metadata;

import jakarta.validation.ConstraintTarget;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ConstraintDescriptor {
   Annotation getAnnotation();

   String getMessageTemplate();

   Set getGroups();

   Set getPayload();

   ConstraintTarget getValidationAppliesTo();

   List getConstraintValidatorClasses();

   Map getAttributes();

   Set getComposingConstraints();

   boolean isReportAsSingleViolation();

   ValidateUnwrappedValue getValueUnwrapping();

   Object unwrap(Class var1);
}
