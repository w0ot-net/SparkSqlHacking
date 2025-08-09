package org.glassfish.jersey.internal;

import jakarta.ws.rs.RuntimeType;
import java.util.Map;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.internal.util.PropertiesHelper;

public class ServiceFinderBinder extends AbstractBinder {
   private final Class contract;
   private final Map applicationProperties;
   private final RuntimeType runtimeType;

   public ServiceFinderBinder(Class contract, Map applicationProperties, RuntimeType runtimeType) {
      this.contract = contract;
      this.applicationProperties = applicationProperties;
      this.runtimeType = runtimeType;
   }

   protected void configure() {
      if (PropertiesHelper.isMetaInfServicesEnabled(this.applicationProperties, this.runtimeType)) {
         for(Class t : ServiceFinder.find(this.contract, true).toClassArray()) {
            this.bind(t).to(this.contract);
         }
      }

   }
}
