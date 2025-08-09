package org.glassfish.jersey.internal;

import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.Configuration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.spi.AutoDiscoverable;

public class AutoDiscoverableConfigurator extends AbstractServiceFinderConfigurator {
   public AutoDiscoverableConfigurator(RuntimeType runtimeType) {
      super(AutoDiscoverable.class, runtimeType);
   }

   public void init(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
      Configuration configuration = bootstrapBag.getConfiguration();
      Stream var10000 = this.loadImplementations(configuration.getProperties()).stream().peek((implClass) -> injectionManager.register(Bindings.service(implClass).to(AutoDiscoverable.class)));
      injectionManager.getClass();
      List<AutoDiscoverable> autoDiscoverables = (List)var10000.map(injectionManager::createAndInitialize).collect(Collectors.toList());
      bootstrapBag.setAutoDiscoverables(autoDiscoverables);
   }
}
