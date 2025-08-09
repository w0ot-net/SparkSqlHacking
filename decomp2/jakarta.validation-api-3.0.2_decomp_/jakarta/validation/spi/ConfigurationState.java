package jakarta.validation.spi;

import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorFactory;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.ParameterNameProvider;
import jakarta.validation.TraversableResolver;
import java.util.Map;
import java.util.Set;

public interface ConfigurationState {
   boolean isIgnoreXmlConfiguration();

   MessageInterpolator getMessageInterpolator();

   Set getMappingStreams();

   Set getValueExtractors();

   ConstraintValidatorFactory getConstraintValidatorFactory();

   TraversableResolver getTraversableResolver();

   ParameterNameProvider getParameterNameProvider();

   ClockProvider getClockProvider();

   Map getProperties();
}
