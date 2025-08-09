package jakarta.validation;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ConstraintViolationException extends ValidationException {
   private final Set constraintViolations;

   public ConstraintViolationException(String message, Set constraintViolations) {
      super(message);
      if (constraintViolations == null) {
         this.constraintViolations = null;
      } else {
         this.constraintViolations = new HashSet(constraintViolations);
      }

   }

   public ConstraintViolationException(Set constraintViolations) {
      this(constraintViolations != null ? toString(constraintViolations) : null, constraintViolations);
   }

   public Set getConstraintViolations() {
      return this.constraintViolations;
   }

   private static String toString(Set constraintViolations) {
      return (String)constraintViolations.stream().map((cv) -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage()).collect(Collectors.joining(", "));
   }
}
