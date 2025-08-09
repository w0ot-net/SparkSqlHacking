package org.glassfish.jersey.process.internal;

import jakarta.inject.Named;
import jakarta.inject.Qualifier;
import jakarta.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.inject.Binding;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.DisposableSupplier;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.inject.SupplierInstanceBinding;
import org.glassfish.jersey.internal.util.ExtendedLogger;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.spi.ExecutorServiceProvider;
import org.glassfish.jersey.spi.ScheduledExecutorServiceProvider;

public final class ExecutorProviders {
   private static final ExtendedLogger LOGGER;

   private ExecutorProviders() {
      throw new AssertionError("Instantiation not allowed.");
   }

   public static void registerExecutorBindings(InjectionManager injectionManager) {
      List<ExecutorServiceProvider> executorProviders = getExecutorProviders(injectionManager, ExecutorServiceProvider.class);
      List<ScheduledExecutorServiceProvider> scheduledProviders = getExecutorProviders(injectionManager, ScheduledExecutorServiceProvider.class);
      registerExecutorBindings(injectionManager, executorProviders, scheduledProviders);
   }

   private static List getExecutorProviders(InjectionManager injectionManager, Class providerClass) {
      Set<T> customProviders = Providers.getCustomProviders(injectionManager, providerClass);
      Set<T> defaultProviders = Providers.getProviders(injectionManager, providerClass);
      defaultProviders.removeAll(customProviders);
      List<T> executorProviders = new LinkedList(customProviders);
      executorProviders.addAll(defaultProviders);
      return executorProviders;
   }

   public static void registerExecutorBindings(InjectionManager injectionManager, List executorProviders, List scheduledProviders) {
      Map<Class<? extends Annotation>, List<ExecutorServiceProvider>> executorProviderMap = getQualifierToProviderMap(executorProviders);

      for(Map.Entry qualifierToProviders : executorProviderMap.entrySet()) {
         Class<? extends Annotation> qualifierAnnotationClass = (Class)qualifierToProviders.getKey();
         Iterator<ExecutorServiceProvider> bucketProviderIterator = ((List)qualifierToProviders.getValue()).iterator();
         ExecutorServiceProvider executorProvider = (ExecutorServiceProvider)bucketProviderIterator.next();
         logExecutorServiceProvider(qualifierAnnotationClass, bucketProviderIterator, executorProvider);
         SupplierInstanceBinding<ExecutorService> descriptor = (SupplierInstanceBinding)((SupplierInstanceBinding)Bindings.supplier((Supplier)(new ExecutorServiceSupplier(executorProvider))).in(Singleton.class)).to(ExecutorService.class);
         Annotation qualifier = executorProvider.getClass().getAnnotation(qualifierAnnotationClass);
         if (qualifier instanceof Named) {
            descriptor.named(((Named)qualifier).value());
         } else {
            descriptor.qualifiedBy(qualifier);
         }

         injectionManager.register((Binding)descriptor);
      }

      Map<Class<? extends Annotation>, List<ScheduledExecutorServiceProvider>> schedulerProviderMap = getQualifierToProviderMap(scheduledProviders);

      for(Map.Entry qualifierToProviders : schedulerProviderMap.entrySet()) {
         Class<? extends Annotation> qualifierAnnotationClass = (Class)qualifierToProviders.getKey();
         Iterator<ScheduledExecutorServiceProvider> bucketProviderIterator = ((List)qualifierToProviders.getValue()).iterator();
         ScheduledExecutorServiceProvider executorProvider = (ScheduledExecutorServiceProvider)bucketProviderIterator.next();
         logScheduledExecutorProvider(qualifierAnnotationClass, bucketProviderIterator, executorProvider);
         SupplierInstanceBinding<ScheduledExecutorService> descriptor = (SupplierInstanceBinding)((SupplierInstanceBinding)Bindings.supplier((Supplier)(new ScheduledExecutorServiceSupplier(executorProvider))).in(Singleton.class)).to(ScheduledExecutorService.class);
         if (!executorProviderMap.containsKey(qualifierAnnotationClass)) {
            descriptor.to(ExecutorService.class);
         }

         Annotation qualifier = executorProvider.getClass().getAnnotation(qualifierAnnotationClass);
         if (qualifier instanceof Named) {
            descriptor.named(((Named)qualifier).value());
         } else {
            descriptor.qualifiedBy(qualifier);
         }

         injectionManager.register((Binding)descriptor);
      }

   }

   private static void logScheduledExecutorProvider(Class qualifierAnnotationClass, Iterator bucketProviderIterator, ScheduledExecutorServiceProvider executorProvider) {
      if (LOGGER.isLoggable(Level.CONFIG)) {
         LOGGER.config(LocalizationMessages.USING_SCHEDULER_PROVIDER(executorProvider.getClass().getName(), qualifierAnnotationClass.getName()));
         if (bucketProviderIterator.hasNext()) {
            StringBuilder msg = new StringBuilder(((ScheduledExecutorServiceProvider)bucketProviderIterator.next()).getClass().getName());

            while(bucketProviderIterator.hasNext()) {
               msg.append(", ").append(((ScheduledExecutorServiceProvider)bucketProviderIterator.next()).getClass().getName());
            }

            LOGGER.config(LocalizationMessages.IGNORED_SCHEDULER_PROVIDERS(msg.toString(), qualifierAnnotationClass.getName()));
         }
      }

   }

   private static void logExecutorServiceProvider(Class qualifierAnnotationClass, Iterator bucketProviderIterator, ExecutorServiceProvider executorProvider) {
      if (LOGGER.isLoggable(Level.CONFIG)) {
         LOGGER.config(LocalizationMessages.USING_EXECUTOR_PROVIDER(executorProvider.getClass().getName(), qualifierAnnotationClass.getName()));
         if (bucketProviderIterator.hasNext()) {
            StringBuilder msg = new StringBuilder(((ExecutorServiceProvider)bucketProviderIterator.next()).getClass().getName());

            while(bucketProviderIterator.hasNext()) {
               msg.append(", ").append(((ExecutorServiceProvider)bucketProviderIterator.next()).getClass().getName());
            }

            LOGGER.config(LocalizationMessages.IGNORED_EXECUTOR_PROVIDERS(msg.toString(), qualifierAnnotationClass.getName()));
         }
      }

   }

   private static Map getQualifierToProviderMap(List executorProviders) {
      Map<Class<? extends Annotation>, List<T>> executorProviderMap = new HashMap();

      for(ExecutorServiceProvider provider : executorProviders) {
         for(Class qualifier : ReflectionHelper.getAnnotationTypes(provider.getClass(), Qualifier.class)) {
            List<T> providersForQualifier;
            if (!executorProviderMap.containsKey(qualifier)) {
               providersForQualifier = new LinkedList();
               executorProviderMap.put(qualifier, providersForQualifier);
            } else {
               providersForQualifier = (List)executorProviderMap.get(qualifier);
            }

            providersForQualifier.add(provider);
         }
      }

      return executorProviderMap;
   }

   static {
      LOGGER = new ExtendedLogger(Logger.getLogger(ExecutorProviders.class.getName()), Level.FINEST);
   }

   private static class ExecutorServiceSupplier implements DisposableSupplier {
      private final ExecutorServiceProvider executorProvider;

      private ExecutorServiceSupplier(ExecutorServiceProvider executorServiceProvider) {
         this.executorProvider = executorServiceProvider;
      }

      public ExecutorService get() {
         return this.executorProvider.getExecutorService();
      }

      public void dispose(ExecutorService instance) {
         this.executorProvider.dispose(instance);
      }
   }

   private static class ScheduledExecutorServiceSupplier implements DisposableSupplier {
      private final ScheduledExecutorServiceProvider executorProvider;

      private ScheduledExecutorServiceSupplier(ScheduledExecutorServiceProvider executorServiceProvider) {
         this.executorProvider = executorServiceProvider;
      }

      public ScheduledExecutorService get() {
         return this.executorProvider.getExecutorService();
      }

      public void dispose(ScheduledExecutorService instance) {
         this.executorProvider.dispose(instance);
      }
   }
}
