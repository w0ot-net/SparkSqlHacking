package org.apache.logging.log4j.core.config.plugins.validation;

import java.lang.annotation.Annotation;

public interface ConstraintValidator {
   void initialize(Annotation annotation);

   boolean isValid(String name, Object value);
}
