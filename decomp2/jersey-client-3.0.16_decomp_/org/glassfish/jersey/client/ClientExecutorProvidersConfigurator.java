package org.glassfish.jersey.client;

import jakarta.ws.rs.core.Configuration;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.BootstrapBag;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.InstanceBinding;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.model.internal.ComponentBag;
import org.glassfish.jersey.process.internal.AbstractExecutorProvidersConfigurator;
import org.glassfish.jersey.spi.ExecutorServiceProvider;
import org.glassfish.jersey.spi.ScheduledExecutorServiceProvider;

class ClientExecutorProvidersConfigurator extends AbstractExecutorProvidersConfigurator {
   private static final Logger LOGGER = Logger.getLogger(ClientExecutorProvidersConfigurator.class.getName());
   private static final ExecutorService MANAGED_EXECUTOR_SERVICE = lookupManagedExecutorService();
   private final ComponentBag componentBag;
   private final JerseyClient client;
   private final ExecutorService customExecutorService;
   private final ScheduledExecutorService customScheduledExecutorService;

   ClientExecutorProvidersConfigurator(ComponentBag componentBag, JerseyClient client, ExecutorService customExecutorService, ScheduledExecutorService customScheduledExecutorService) {
      this.componentBag = componentBag;
      this.client = client;
      this.customExecutorService = customExecutorService;
      this.customScheduledExecutorService = customScheduledExecutorService;
   }

   public void init(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
      Configuration configuration = bootstrapBag.getConfiguration();
      Map<String, Object> runtimeProperties = configuration.getProperties();
      ExecutorService clientExecutorService = this.client.getExecutorService() == null ? this.customExecutorService : this.client.getExecutorService();
      ExecutorServiceProvider defaultAsyncExecutorProvider;
      if (clientExecutorService != null) {
         defaultAsyncExecutorProvider = new ClientExecutorServiceProvider(clientExecutorService);
      } else {
         Integer asyncThreadPoolSize = (Integer)ClientProperties.getValue(runtimeProperties, "jersey.config.client.async.threadPoolSize", Integer.class);
         if (asyncThreadPoolSize != null) {
            asyncThreadPoolSize = asyncThreadPoolSize < 0 ? 0 : asyncThreadPoolSize;
            InstanceBinding<Integer> asyncThreadPoolSizeBinding = (InstanceBinding)Bindings.service(asyncThreadPoolSize).named("ClientAsyncThreadPoolSize");
            injectionManager.register(asyncThreadPoolSizeBinding);
            defaultAsyncExecutorProvider = new DefaultClientAsyncExecutorProvider(asyncThreadPoolSize, configuration);
         } else if (MANAGED_EXECUTOR_SERVICE != null) {
            defaultAsyncExecutorProvider = new ClientExecutorServiceProvider(MANAGED_EXECUTOR_SERVICE);
         } else {
            defaultAsyncExecutorProvider = new DefaultClientAsyncExecutorProvider(0, configuration);
         }
      }

      InstanceBinding<ExecutorServiceProvider> executorBinding = (InstanceBinding)Bindings.service(defaultAsyncExecutorProvider).to(ExecutorServiceProvider.class);
      injectionManager.register(executorBinding);
      ScheduledExecutorService clientScheduledExecutorService = this.client.getScheduledExecutorService() == null ? this.customScheduledExecutorService : this.client.getScheduledExecutorService();
      ScheduledExecutorServiceProvider defaultScheduledExecutorProvider;
      if (clientScheduledExecutorService != null) {
         defaultScheduledExecutorProvider = new ClientScheduledExecutorServiceProvider(Values.of(clientScheduledExecutorService));
      } else {
         ScheduledExecutorService scheduledExecutorService = this.lookupManagedScheduledExecutorService();
         defaultScheduledExecutorProvider = (ScheduledExecutorServiceProvider)(scheduledExecutorService == null ? new DefaultClientBackgroundSchedulerProvider() : new ClientScheduledExecutorServiceProvider(Values.of(scheduledExecutorService)));
      }

      InstanceBinding<ScheduledExecutorServiceProvider> schedulerBinding = (InstanceBinding)Bindings.service(defaultScheduledExecutorProvider).to(ScheduledExecutorServiceProvider.class);
      injectionManager.register(schedulerBinding);
      this.registerExecutors(injectionManager, this.componentBag, defaultAsyncExecutorProvider, defaultScheduledExecutorProvider, bootstrapBag.getManagedObjectsFinalizer());
   }

   private static ExecutorService lookupManagedExecutorService() {
      try {
         Class<?> aClass = (Class)AccessController.doPrivileged(ReflectionHelper.classForNamePA("javax.naming.InitialContext"));
         Object initialContext = aClass.newInstance();
         Method lookupMethod = aClass.getMethod("lookup", String.class);
         return (ExecutorService)lookupMethod.invoke(initialContext, "java:comp/DefaultManagedExecutorService");
      } catch (Exception e) {
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, e.getMessage(), e);
         }
      } catch (LinkageError var4) {
      }

      return null;
   }

   private ScheduledExecutorService lookupManagedScheduledExecutorService() {
      try {
         Class<?> aClass = (Class)AccessController.doPrivileged(ReflectionHelper.classForNamePA("javax.naming.InitialContext"));
         Object initialContext = aClass.newInstance();
         Method lookupMethod = aClass.getMethod("lookup", String.class);
         return (ScheduledExecutorService)lookupMethod.invoke(initialContext, "java:comp/DefaultManagedScheduledExecutorService");
      } catch (Exception e) {
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, e.getMessage(), e);
         }
      } catch (LinkageError var5) {
      }

      return null;
   }

   @ClientAsyncExecutor
   public static class ClientExecutorServiceProvider implements ExecutorServiceProvider {
      private final ExecutorService executorService;

      ClientExecutorServiceProvider(ExecutorService executorService) {
         this.executorService = executorService;
      }

      public ExecutorService getExecutorService() {
         return this.executorService;
      }

      public void dispose(ExecutorService executorService) {
      }
   }

   @ClientBackgroundScheduler
   public static class ClientScheduledExecutorServiceProvider implements ScheduledExecutorServiceProvider {
      private final Value executorService;

      ClientScheduledExecutorServiceProvider(Value executorService) {
         this.executorService = executorService;
      }

      public ScheduledExecutorService getExecutorService() {
         return (ScheduledExecutorService)this.executorService.get();
      }

      public void dispose(ExecutorService executorService) {
      }
   }
}
