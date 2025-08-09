package jakarta.validation;

import jakarta.validation.valueextraction.ValueExtractor;
import java.io.InputStream;

public interface Configuration {
   Configuration ignoreXmlConfiguration();

   Configuration messageInterpolator(MessageInterpolator var1);

   Configuration traversableResolver(TraversableResolver var1);

   Configuration constraintValidatorFactory(ConstraintValidatorFactory var1);

   Configuration parameterNameProvider(ParameterNameProvider var1);

   Configuration clockProvider(ClockProvider var1);

   Configuration addValueExtractor(ValueExtractor var1);

   Configuration addMapping(InputStream var1);

   Configuration addProperty(String var1, String var2);

   MessageInterpolator getDefaultMessageInterpolator();

   TraversableResolver getDefaultTraversableResolver();

   ConstraintValidatorFactory getDefaultConstraintValidatorFactory();

   ParameterNameProvider getDefaultParameterNameProvider();

   ClockProvider getDefaultClockProvider();

   BootstrapConfiguration getBootstrapConfiguration();

   ValidatorFactory buildValidatorFactory();
}
