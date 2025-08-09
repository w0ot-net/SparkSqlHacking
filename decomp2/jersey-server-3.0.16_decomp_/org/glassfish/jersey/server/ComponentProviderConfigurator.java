package org.glassfish.jersey.server;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.glassfish.jersey.internal.BootstrapBag;
import org.glassfish.jersey.internal.BootstrapConfigurator;
import org.glassfish.jersey.internal.ServiceConfigurationError;
import org.glassfish.jersey.internal.ServiceFinder;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.util.collection.LazyValue;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.model.internal.RankedProvider;
import org.glassfish.jersey.model.internal.RankedComparator.Order;
import org.glassfish.jersey.server.spi.ComponentProvider;

class ComponentProviderConfigurator implements BootstrapConfigurator {
   private static final Comparator RANKED_COMPARATOR;

   public void init(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
      ServerBootstrapBag serverBag = (ServerBootstrapBag)bootstrapBag;
      LazyValue<Collection<ComponentProvider>> componentProviders = Values.lazy(() -> (List)getRankedComponentProviders().stream().map(RankedProvider::getProvider).peek((provider) -> provider.initialize(injectionManager)).collect(Collectors.toList()));
      serverBag.setComponentProviders(componentProviders);
   }

   public void postInit(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
      ServerBootstrapBag serverBag = (ServerBootstrapBag)bootstrapBag;
      ((Collection)serverBag.getComponentProviders().get()).forEach(org.glassfish.jersey.spi.ComponentProvider::done);
   }

   private static Collection getRankedComponentProviders() throws ServiceConfigurationError {
      return (Collection)StreamSupport.stream(ServiceFinder.find(ComponentProvider.class).spliterator(), false).map(RankedProvider::new).sorted(RANKED_COMPARATOR).collect(Collectors.toList());
   }

   static {
      RANKED_COMPARATOR = new RankedComparator(Order.DESCENDING);
   }
}
