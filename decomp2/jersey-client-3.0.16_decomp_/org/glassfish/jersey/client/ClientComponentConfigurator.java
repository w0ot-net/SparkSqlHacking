package org.glassfish.jersey.client;

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
import org.glassfish.jersey.spi.ComponentProvider;

class ClientComponentConfigurator implements BootstrapConfigurator {
   private static final Comparator RANKED_COMPARATOR;

   public void init(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
      LazyValue<Collection<ComponentProvider>> componentProviders = Values.lazy(() -> (List)getRankedComponentProviders().stream().map(RankedProvider::getProvider).peek((provider) -> provider.initialize(injectionManager)).collect(Collectors.toList()));
      ((ClientBootstrapBag)bootstrapBag).setComponentProviders(componentProviders);
   }

   public void postInit(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
      ((Collection)((ClientBootstrapBag)bootstrapBag).getComponentProviders().get()).forEach(ComponentProvider::done);
   }

   private static Collection getRankedComponentProviders() throws ServiceConfigurationError {
      return (Collection)StreamSupport.stream(ServiceFinder.find(ComponentProvider.class).spliterator(), false).map(RankedProvider::new).sorted(RANKED_COMPARATOR).collect(Collectors.toList());
   }

   static {
      RANKED_COMPARATOR = new RankedComparator(Order.DESCENDING);
   }
}
