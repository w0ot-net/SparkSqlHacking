package org.glassfish.jersey;

import jakarta.ws.rs.RuntimeType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.glassfish.jersey.internal.AbstractServiceFinderConfigurator;
import org.glassfish.jersey.internal.BootstrapBag;
import org.glassfish.jersey.internal.ServiceFinder;
import org.glassfish.jersey.internal.spi.AutoDiscoverable;
import org.glassfish.jersey.internal.util.PropertiesHelper;

public abstract class AbstractFeatureConfigurator extends AbstractServiceFinderConfigurator {
   protected AbstractFeatureConfigurator(Class contract, RuntimeType runtimeType) {
      super(contract, runtimeType);
   }

   protected List loadImplementations(Map applicationProperties, ClassLoader loader) {
      return PropertiesHelper.isMetaInfServicesEnabled(applicationProperties, this.getRuntimeType()) ? (List)Stream.of(ServiceFinder.find(this.getContract(), loader, true).toClassArray()).collect(Collectors.toList()) : Collections.emptyList();
   }

   protected void registerFeatures(Collection features, BootstrapBag bootstrapBag) {
      List<AutoDiscoverable> autoDiscoverables = new ArrayList();
      features.forEach((feature) -> autoDiscoverables.add(registerClass(feature)));
      bootstrapBag.getAutoDiscoverables().addAll(autoDiscoverables);
   }

   private static AutoDiscoverable registerClass(Class classToRegister) {
      return (context) -> {
         if (!context.getConfiguration().isRegistered(classToRegister)) {
            context.register(classToRegister, 2000);
         }

      };
   }
}
