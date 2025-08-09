package jakarta.validation;

public interface ConstraintValidatorFactory {
   ConstraintValidator getInstance(Class var1);

   void releaseInstance(ConstraintValidator var1);
}
