package org.glassfish.jersey.client;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import java.util.Collection;

public class CustomProvidersFeature implements Feature {
   private final Collection providers;

   public CustomProvidersFeature(Collection providers) {
      this.providers = providers;
   }

   public boolean configure(FeatureContext context) {
      for(Class provider : this.providers) {
         context.register(provider);
      }

      return true;
   }
}
