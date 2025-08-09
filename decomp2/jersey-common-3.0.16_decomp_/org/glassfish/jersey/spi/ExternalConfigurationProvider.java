package org.glassfish.jersey.spi;

import java.util.Map;

public interface ExternalConfigurationProvider {
   Map getProperties();

   ExternalConfigurationModel getConfiguration();

   ExternalConfigurationModel merge(ExternalConfigurationModel var1);
}
