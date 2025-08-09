package jakarta.validation;

import java.util.Map;
import java.util.Set;

public interface BootstrapConfiguration {
   String getDefaultProviderClassName();

   String getConstraintValidatorFactoryClassName();

   String getMessageInterpolatorClassName();

   String getTraversableResolverClassName();

   String getParameterNameProviderClassName();

   String getClockProviderClassName();

   Set getValueExtractorClassNames();

   Set getConstraintMappingResourcePaths();

   boolean isExecutableValidationEnabled();

   Set getDefaultValidatedExecutableTypes();

   Map getProperties();
}
