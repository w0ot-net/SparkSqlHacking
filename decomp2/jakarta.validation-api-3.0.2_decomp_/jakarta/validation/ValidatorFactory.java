package jakarta.validation;

public interface ValidatorFactory extends AutoCloseable {
   Validator getValidator();

   ValidatorContext usingContext();

   MessageInterpolator getMessageInterpolator();

   TraversableResolver getTraversableResolver();

   ConstraintValidatorFactory getConstraintValidatorFactory();

   ParameterNameProvider getParameterNameProvider();

   ClockProvider getClockProvider();

   Object unwrap(Class var1);

   void close();
}
