package jakarta.validation.bootstrap;

import jakarta.validation.Configuration;
import jakarta.validation.ValidationProviderResolver;

public interface ProviderSpecificBootstrap {
   ProviderSpecificBootstrap providerResolver(ValidationProviderResolver var1);

   Configuration configure();
}
