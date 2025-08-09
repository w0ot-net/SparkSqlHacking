package jakarta.validation;

import jakarta.validation.metadata.ConstraintDescriptor;

public interface ConstraintViolation {
   String getMessage();

   String getMessageTemplate();

   Object getRootBean();

   Class getRootBeanClass();

   Object getLeafBean();

   Object[] getExecutableParameters();

   Object getExecutableReturnValue();

   Path getPropertyPath();

   Object getInvalidValue();

   ConstraintDescriptor getConstraintDescriptor();

   Object unwrap(Class var1);
}
