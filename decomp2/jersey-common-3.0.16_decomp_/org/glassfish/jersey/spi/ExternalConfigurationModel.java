package org.glassfish.jersey.spi;

import java.util.Map;
import java.util.Optional;
import org.glassfish.jersey.ExtendedConfig;

public interface ExternalConfigurationModel extends ExtendedConfig {
   Object as(String var1, Class var2);

   Optional getOptionalProperty(String var1, Class var2);

   ExternalConfigurationModel mergeProperties(Map var1);

   Object getConfig();
}
