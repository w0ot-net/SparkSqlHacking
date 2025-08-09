package org.apache.logging.log4j.core.config.plugins.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.logging.log4j.core.util.ReflectionUtil;

public final class ConstraintValidators {
   private ConstraintValidators() {
   }

   public static Collection findValidators(final Annotation... annotations) {
      Collection<ConstraintValidator<?>> validators = new ArrayList();

      for(Annotation annotation : annotations) {
         Class<? extends Annotation> type = annotation.annotationType();
         if (type.isAnnotationPresent(Constraint.class)) {
            ConstraintValidator<?> validator = getValidator(annotation, type);
            if (validator != null) {
               validators.add(validator);
            }
         }
      }

      return validators;
   }

   private static ConstraintValidator getValidator(final Annotation annotation, final Class type) {
      Constraint constraint = (Constraint)type.getAnnotation(Constraint.class);
      Class<? extends ConstraintValidator<?>> validatorClass = constraint.value();
      if (type.equals(getConstraintValidatorAnnotationType(validatorClass))) {
         ConstraintValidator<A> validator = (ConstraintValidator)ReflectionUtil.instantiate(validatorClass);
         validator.initialize(annotation);
         return validator;
      } else {
         return null;
      }
   }

   private static Type getConstraintValidatorAnnotationType(final Class type) {
      for(Type parentType : type.getGenericInterfaces()) {
         if (parentType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)parentType;
            if (ConstraintValidator.class.equals(parameterizedType.getRawType())) {
               return parameterizedType.getActualTypeArguments()[0];
            }
         }
      }

      return Void.TYPE;
   }
}
