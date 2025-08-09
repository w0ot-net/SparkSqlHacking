package org.glassfish.jersey.process.internal;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.glassfish.jersey.internal.BootstrapConfigurator;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.model.internal.ComponentBag;
import org.glassfish.jersey.model.internal.ManagedObjectsFinalizer;
import org.glassfish.jersey.spi.ExecutorServiceProvider;
import org.glassfish.jersey.spi.ScheduledExecutorServiceProvider;

public abstract class AbstractExecutorProvidersConfigurator implements BootstrapConfigurator {
   private static final Function CAST_TO_EXECUTOR_PROVIDER;
   private static final Function CAST_TO_SCHEDULED_EXECUTOR_PROVIDER;

   protected void registerExecutors(InjectionManager injectionManager, ComponentBag componentBag, ExecutorServiceProvider defaultAsyncExecutorProvider, ScheduledExecutorServiceProvider defaultScheduledExecutorProvider, ManagedObjectsFinalizer finalizer) {
      Stream var10000 = componentBag.getClasses(ComponentBag.EXECUTOR_SERVICE_PROVIDER_ONLY).stream();
      injectionManager.getClass();
      List<ExecutorServiceProvider> customExecutors = (List)Stream.concat(var10000.map(injectionManager::createAndInitialize), componentBag.getInstances(ComponentBag.EXECUTOR_SERVICE_PROVIDER_ONLY).stream()).map(CAST_TO_EXECUTOR_PROVIDER).collect(Collectors.toList());
      customExecutors.add(defaultAsyncExecutorProvider);
      customExecutors.stream().forEach((e) -> finalizer.registerForPreDestroyCall(e));
      var10000 = componentBag.getClasses(ComponentBag.SCHEDULED_EXECUTOR_SERVICE_PROVIDER_ONLY).stream();
      injectionManager.getClass();
      List<ScheduledExecutorServiceProvider> customScheduledExecutors = (List)Stream.concat(var10000.map(injectionManager::createAndInitialize), componentBag.getInstances(ComponentBag.SCHEDULED_EXECUTOR_SERVICE_PROVIDER_ONLY).stream()).map(CAST_TO_SCHEDULED_EXECUTOR_PROVIDER).collect(Collectors.toList());
      customScheduledExecutors.add(defaultScheduledExecutorProvider);
      customScheduledExecutors.stream().forEach((e) -> finalizer.registerForPreDestroyCall(e));
      ExecutorProviders.registerExecutorBindings(injectionManager, customExecutors, customScheduledExecutors);
   }

   static {
      ExecutorServiceProvider.class.getClass();
      CAST_TO_EXECUTOR_PROVIDER = ExecutorServiceProvider.class::cast;
      ScheduledExecutorServiceProvider.class.getClass();
      CAST_TO_SCHEDULED_EXECUTOR_PROVIDER = ScheduledExecutorServiceProvider.class::cast;
   }
}
