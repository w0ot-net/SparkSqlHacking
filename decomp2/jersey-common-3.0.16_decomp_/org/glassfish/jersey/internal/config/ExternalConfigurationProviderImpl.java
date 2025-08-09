package org.glassfish.jersey.internal.config;

import java.util.Map;
import org.glassfish.jersey.spi.ExternalConfigurationModel;
import org.glassfish.jersey.spi.ExternalConfigurationProvider;

public class ExternalConfigurationProviderImpl implements ExternalConfigurationProvider {
   protected final ExternalConfigurationModel model;

   protected ExternalConfigurationProviderImpl(ExternalConfigurationModel model) {
      this.model = model;
   }

   public Map getProperties() {
      return this.model.getProperties();
   }

   public ExternalConfigurationModel getConfiguration() {
      return this.model;
   }

   public ExternalConfigurationModel merge(ExternalConfigurationModel input) {
      return input == null ? this.model : this.model.mergeProperties(input.getProperties());
   }
}
