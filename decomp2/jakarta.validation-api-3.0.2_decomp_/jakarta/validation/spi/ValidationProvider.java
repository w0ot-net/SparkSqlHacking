package jakarta.validation.spi;

import jakarta.validation.Configuration;
import jakarta.validation.ValidatorFactory;

public interface ValidationProvider {
   Configuration createSpecializedConfiguration(BootstrapState var1);

   Configuration createGenericConfiguration(BootstrapState var1);

   ValidatorFactory buildValidatorFactory(ConfigurationState var1);
}
