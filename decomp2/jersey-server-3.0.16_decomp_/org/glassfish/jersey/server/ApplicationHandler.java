package org.glassfish.jersey.server;

import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.security.Principal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.internal.AutoDiscoverableConfigurator;
import org.glassfish.jersey.internal.BootstrapBag;
import org.glassfish.jersey.internal.BootstrapConfigurator;
import org.glassfish.jersey.internal.ContextResolverFactory;
import org.glassfish.jersey.internal.DynamicFeatureConfigurator;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.ExceptionMapperFactory;
import org.glassfish.jersey.internal.FeatureConfigurator;
import org.glassfish.jersey.internal.JaxrsProviders;
import org.glassfish.jersey.internal.Version;
import org.glassfish.jersey.internal.inject.Binder;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.CompositeBinder;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.internal.inject.InstanceBinding;
import org.glassfish.jersey.internal.inject.ParamConverterConfigurator;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.message.internal.MessageBodyFactory;
import org.glassfish.jersey.message.internal.MessagingBinders;
import org.glassfish.jersey.message.internal.NullOutputStream;
import org.glassfish.jersey.model.internal.ComponentBag;
import org.glassfish.jersey.model.internal.ManagedObjectsFinalizer;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.model.internal.RankedProvider;
import org.glassfish.jersey.process.internal.ChainableStage;
import org.glassfish.jersey.process.internal.RequestScope;
import org.glassfish.jersey.process.internal.Stage;
import org.glassfish.jersey.process.internal.Stages;
import org.glassfish.jersey.server.internal.JerseyRequestTimeoutHandler;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.internal.ProcessingProviders;
import org.glassfish.jersey.server.internal.inject.ParamExtractorConfigurator;
import org.glassfish.jersey.server.internal.inject.ValueParamProviderConfigurator;
import org.glassfish.jersey.server.internal.monitoring.ApplicationEventImpl;
import org.glassfish.jersey.server.internal.monitoring.CompositeApplicationEventListener;
import org.glassfish.jersey.server.internal.monitoring.MonitoringContainerListener;
import org.glassfish.jersey.server.internal.process.ReferencesInitializer;
import org.glassfish.jersey.server.internal.process.RequestProcessingConfigurator;
import org.glassfish.jersey.server.internal.process.RequestProcessingContext;
import org.glassfish.jersey.server.internal.process.RequestProcessingContextReference;
import org.glassfish.jersey.server.internal.routing.Routing;
import org.glassfish.jersey.server.model.ComponentModelValidator;
import org.glassfish.jersey.server.model.ModelProcessor;
import org.glassfish.jersey.server.model.ModelValidationException;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceModel;
import org.glassfish.jersey.server.model.internal.ModelErrors;
import org.glassfish.jersey.server.model.internal.ResourceMethodInvokerConfigurator;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.glassfish.jersey.server.spi.ContainerResponseWriter;

public final class ApplicationHandler implements ContainerLifecycleListener {
   private static final Logger LOGGER = Logger.getLogger(ApplicationHandler.class.getName());
   private static final SecurityContext DEFAULT_SECURITY_CONTEXT = new SecurityContext() {
      public boolean isUserInRole(String role) {
         return false;
      }

      public boolean isSecure() {
         return false;
      }

      public Principal getUserPrincipal() {
         return null;
      }

      public String getAuthenticationScheme() {
         return null;
      }
   };
   private Application application;
   private ResourceConfig runtimeConfig;
   private ServerRuntime runtime;
   private Iterable containerLifecycleListeners;
   private InjectionManager injectionManager;
   private MessageBodyWorkers msgBodyWorkers;
   private ManagedObjectsFinalizer managedObjectsFinalizer;

   public ApplicationHandler() {
      this(new Application());
   }

   public ApplicationHandler(Class jaxrsApplicationClass) {
      this.initialize((ApplicationConfigurator)(new ApplicationConfigurator(jaxrsApplicationClass)), (InjectionManager)Injections.createInjectionManager(), (Binder)null);
   }

   public ApplicationHandler(Application application) {
      this(application, (Binder)null, (Object)null);
   }

   public ApplicationHandler(Application application, Binder customBinder) {
      this(application, customBinder, (Object)null);
   }

   public ApplicationHandler(Application application, Binder customBinder, Object parentManager) {
      this.initialize(new ApplicationConfigurator(application), Injections.createInjectionManager(parentManager), customBinder);
   }

   private void initialize(ApplicationConfigurator applicationConfigurator, InjectionManager injectionManager, Binder customBinder) {
      LOGGER.config(LocalizationMessages.INIT_MSG(Version.getBuildId()));
      this.injectionManager = injectionManager;
      this.injectionManager.register(CompositeBinder.wrap(new Binder[]{new ServerBinder(), customBinder}));
      this.managedObjectsFinalizer = new ManagedObjectsFinalizer(injectionManager);
      ServerBootstrapBag bootstrapBag = new ServerBootstrapBag();
      bootstrapBag.setManagedObjectsFinalizer(this.managedObjectsFinalizer);
      List<BootstrapConfigurator> bootstrapConfigurators = Arrays.asList(new RequestProcessingConfigurator(), new RequestScope.RequestScopeConfigurator(), new ParamConverterConfigurator(), new ParamExtractorConfigurator(), new ValueParamProviderConfigurator(), new JerseyResourceContextConfigurator(), new ComponentProviderConfigurator(), new JaxrsProviders.ProvidersConfigurator(), applicationConfigurator, new RuntimeConfigConfigurator(), new ContextResolverFactory.ContextResolversConfigurator(), new MessageBodyFactory.MessageBodyWorkersConfigurator(), new ExceptionMapperFactory.ExceptionMappersConfigurator(), new ResourceMethodInvokerConfigurator(), new ProcessingProvidersConfigurator(), new ContainerProviderConfigurator(RuntimeType.SERVER), new AutoDiscoverableConfigurator(RuntimeType.SERVER), new DynamicFeatureConfigurator(), new FeatureConfigurator(RuntimeType.SERVER));
      bootstrapConfigurators.forEach((configurator) -> configurator.init(injectionManager, bootstrapBag));
      this.runtime = (ServerRuntime)Errors.processWithException(() -> this.initialize(injectionManager, bootstrapConfigurators, bootstrapBag));
      this.containerLifecycleListeners = Providers.getAllProviders(injectionManager, ContainerLifecycleListener.class);
   }

   private ServerRuntime initialize(InjectionManager injectionManager, List bootstrapConfigurators, ServerBootstrapBag bootstrapBag) {
      this.application = bootstrapBag.getApplication();
      this.runtimeConfig = bootstrapBag.getRuntimeConfig();
      injectionManager.register(new MessagingBinders.MessageBodyProviders(this.application.getProperties(), RuntimeType.SERVER));
      if (this.application instanceof ResourceConfig) {
         ((ResourceConfig)this.application).lock();
      }

      CompositeApplicationEventListener compositeListener = null;
      Errors.mark();

      try {
         if (!(Boolean)CommonProperties.getValue(this.runtimeConfig.getProperties(), RuntimeType.SERVER, "jersey.config.disableAutoDiscovery", Boolean.FALSE, Boolean.class)) {
            this.runtimeConfig.configureAutoDiscoverableProviders(injectionManager, bootstrapBag.getAutoDiscoverables());
         } else {
            this.runtimeConfig.configureForcedAutoDiscoverableProviders(injectionManager);
         }

         this.runtimeConfig.configureMetaProviders(injectionManager, bootstrapBag.getManagedObjectsFinalizer());
         ResourceBagConfigurator resourceBagConfigurator = new ResourceBagConfigurator();
         resourceBagConfigurator.init(injectionManager, bootstrapBag);
         this.runtimeConfig.lock();
         ExternalRequestScopeConfigurator externalRequestScopeConfigurator = new ExternalRequestScopeConfigurator();
         externalRequestScopeConfigurator.init(injectionManager, bootstrapBag);
         ModelProcessorConfigurator modelProcessorConfigurator = new ModelProcessorConfigurator();
         modelProcessorConfigurator.init(injectionManager, bootstrapBag);
         ResourceModelConfigurator resourceModelConfigurator = new ResourceModelConfigurator();
         resourceModelConfigurator.init(injectionManager, bootstrapBag);
         ServerExecutorProvidersConfigurator executorProvidersConfigurator = new ServerExecutorProvidersConfigurator();
         executorProvidersConfigurator.init(injectionManager, bootstrapBag);
         injectionManager.completeRegistration();
         bootstrapConfigurators.forEach((configurator) -> configurator.postInit(injectionManager, bootstrapBag));
         resourceModelConfigurator.postInit(injectionManager, bootstrapBag);
         Iterable<ApplicationEventListener> appEventListeners = Providers.getAllProviders(injectionManager, ApplicationEventListener.class, new RankedComparator());
         if (appEventListeners.iterator().hasNext()) {
            ResourceBag resourceBag = bootstrapBag.getResourceBag();
            compositeListener = new CompositeApplicationEventListener(appEventListeners);
            compositeListener.onEvent(new ApplicationEventImpl(ApplicationEvent.Type.INITIALIZATION_START, this.runtimeConfig, this.runtimeConfig.getComponentBag().getRegistrations(), resourceBag.classes, resourceBag.instances, (ResourceModel)null));
         }

         if (!this.disableValidation()) {
            ComponentModelValidator validator = new ComponentModelValidator(bootstrapBag.getValueParamProviders(), bootstrapBag.getMessageBodyWorkers());
            validator.validate(bootstrapBag.getResourceModel());
         }

         if (Errors.fatalIssuesFound() && !this.ignoreValidationError()) {
            throw new ModelValidationException(LocalizationMessages.RESOURCE_MODEL_VALIDATION_FAILED_AT_INIT(), ModelErrors.getErrorsAsResourceModelIssues(true));
         }
      } finally {
         if (this.ignoreValidationError()) {
            Errors.logErrors(true);
            Errors.reset();
         } else {
            Errors.unmark();
         }

      }

      this.msgBodyWorkers = bootstrapBag.getMessageBodyWorkers();
      ProcessingProviders processingProviders = bootstrapBag.getProcessingProviders();
      ContainerFilteringStage preMatchRequestFilteringStage = new ContainerFilteringStage(processingProviders.getPreMatchFilters(), processingProviders.getGlobalResponseFilters());
      ChainableStage<RequestProcessingContext> routingStage = Routing.forModel(bootstrapBag.getResourceModel().getRuntimeResourceModel()).resourceContext(bootstrapBag.getResourceContext()).configuration(this.runtimeConfig).entityProviders(this.msgBodyWorkers).valueSupplierProviders(bootstrapBag.getValueParamProviders()).modelProcessors(Providers.getAllRankedSortedProviders(injectionManager, ModelProcessor.class)).createService((serviceType) -> Injections.getOrCreate(injectionManager, serviceType)).processingProviders(processingProviders).resourceMethodInvokerBuilder(bootstrapBag.getResourceMethodInvokerBuilder()).buildStage();
      ContainerFilteringStage resourceFilteringStage = new ContainerFilteringStage(processingProviders.getGlobalRequestFilters(), (Iterable)null);
      ReferencesInitializer referencesInitializer = new ReferencesInitializer(injectionManager, () -> (RequestProcessingContextReference)injectionManager.getInstance(RequestProcessingContextReference.class));
      Stage<RequestProcessingContext> rootStage = Stages.chain(referencesInitializer).to(preMatchRequestFilteringStage).to(routingStage).to(resourceFilteringStage).build(Routing.matchedEndpointExtractor());
      ServerRuntime serverRuntime = ServerRuntime.createServerRuntime(injectionManager, bootstrapBag, rootStage, compositeListener, processingProviders);
      ComponentBag componentBag = this.runtimeConfig.getComponentBag();
      ResourceBag resourceBag = bootstrapBag.getResourceBag();

      for(Object instance : componentBag.getInstances(ComponentBag.excludeMetaProviders(injectionManager))) {
         injectionManager.inject(instance);
      }

      for(Object instance : resourceBag.instances) {
         injectionManager.inject(instance);
      }

      logApplicationInitConfiguration(injectionManager, resourceBag, processingProviders);
      if (compositeListener != null) {
         ApplicationEvent initFinishedEvent = new ApplicationEventImpl(ApplicationEvent.Type.INITIALIZATION_APP_FINISHED, this.runtimeConfig, componentBag.getRegistrations(), resourceBag.classes, resourceBag.instances, bootstrapBag.getResourceModel());
         compositeListener.onEvent(initFinishedEvent);
         MonitoringContainerListener containerListener = (MonitoringContainerListener)injectionManager.getInstance(MonitoringContainerListener.class);
         containerListener.init(compositeListener, initFinishedEvent);
      }

      return serverRuntime;
   }

   private boolean ignoreValidationError() {
      return (Boolean)ServerProperties.getValue(this.runtimeConfig.getProperties(), "jersey.config.server.resource.validation.ignoreErrors", Boolean.FALSE, Boolean.class);
   }

   private boolean disableValidation() {
      return (Boolean)ServerProperties.getValue(this.runtimeConfig.getProperties(), "jersey.config.server.resource.validation.disable", Boolean.FALSE, Boolean.class);
   }

   private static void logApplicationInitConfiguration(InjectionManager injectionManager, ResourceBag resourceBag, ProcessingProviders processingProviders) {
      if (LOGGER.isLoggable(Level.CONFIG)) {
         StringBuilder sb = (new StringBuilder(LocalizationMessages.LOGGING_APPLICATION_INITIALIZED())).append('\n');
         List<Resource> rootResourceClasses = resourceBag.getRootResources();
         if (!rootResourceClasses.isEmpty()) {
            sb.append(LocalizationMessages.LOGGING_ROOT_RESOURCE_CLASSES()).append(":");

            for(Resource r : rootResourceClasses) {
               for(Class clazz : r.getHandlerClasses()) {
                  sb.append('\n').append("  ").append(clazz.getName());
               }
            }
         }

         sb.append('\n');
         Set<MessageBodyReader> messageBodyReaders;
         Set<MessageBodyWriter> messageBodyWriters;
         if (LOGGER.isLoggable(Level.FINE)) {
            Spliterator<MessageBodyReader> mbrSpliterator = Providers.getAllProviders(injectionManager, MessageBodyReader.class).spliterator();
            messageBodyReaders = (Set)StreamSupport.stream(mbrSpliterator, false).collect(Collectors.toSet());
            Spliterator<MessageBodyWriter> mbwSpliterator = Providers.getAllProviders(injectionManager, MessageBodyWriter.class).spliterator();
            messageBodyWriters = (Set)StreamSupport.stream(mbwSpliterator, false).collect(Collectors.toSet());
         } else {
            messageBodyReaders = Providers.getCustomProviders(injectionManager, MessageBodyReader.class);
            messageBodyWriters = Providers.getCustomProviders(injectionManager, MessageBodyWriter.class);
         }

         printProviders(LocalizationMessages.LOGGING_PRE_MATCH_FILTERS(), processingProviders.getPreMatchFilters(), sb);
         printProviders(LocalizationMessages.LOGGING_GLOBAL_REQUEST_FILTERS(), processingProviders.getGlobalRequestFilters(), sb);
         printProviders(LocalizationMessages.LOGGING_GLOBAL_RESPONSE_FILTERS(), processingProviders.getGlobalResponseFilters(), sb);
         printProviders(LocalizationMessages.LOGGING_GLOBAL_READER_INTERCEPTORS(), processingProviders.getGlobalReaderInterceptors(), sb);
         printProviders(LocalizationMessages.LOGGING_GLOBAL_WRITER_INTERCEPTORS(), processingProviders.getGlobalWriterInterceptors(), sb);
         printNameBoundProviders(LocalizationMessages.LOGGING_NAME_BOUND_REQUEST_FILTERS(), processingProviders.getNameBoundRequestFilters(), sb);
         printNameBoundProviders(LocalizationMessages.LOGGING_NAME_BOUND_RESPONSE_FILTERS(), processingProviders.getNameBoundResponseFilters(), sb);
         printNameBoundProviders(LocalizationMessages.LOGGING_NAME_BOUND_READER_INTERCEPTORS(), processingProviders.getNameBoundReaderInterceptors(), sb);
         printNameBoundProviders(LocalizationMessages.LOGGING_NAME_BOUND_WRITER_INTERCEPTORS(), processingProviders.getNameBoundWriterInterceptors(), sb);
         printProviders(LocalizationMessages.LOGGING_DYNAMIC_FEATURES(), processingProviders.getDynamicFeatures(), sb);
         printProviders(LocalizationMessages.LOGGING_MESSAGE_BODY_READERS(), (Iterable)messageBodyReaders.stream().map(new WorkersToStringTransform()).collect(Collectors.toList()), sb);
         printProviders(LocalizationMessages.LOGGING_MESSAGE_BODY_WRITERS(), (Iterable)messageBodyWriters.stream().map(new WorkersToStringTransform()).collect(Collectors.toList()), sb);
         LOGGER.log(Level.CONFIG, sb.toString());
      }
   }

   private static void printNameBoundProviders(String title, Map providers, StringBuilder sb) {
      if (!providers.isEmpty()) {
         sb.append(title).append(":").append('\n');

         for(Map.Entry entry : providers.entrySet()) {
            for(RankedProvider rankedProvider : (List)entry.getValue()) {
               sb.append("   ").append(LocalizationMessages.LOGGING_PROVIDER_BOUND(rankedProvider, entry.getKey())).append('\n');
            }
         }
      }

   }

   private static void printProviders(String title, Iterable providers, StringBuilder sb) {
      Iterator<T> iterator = providers.iterator();
      boolean first = true;

      while(iterator.hasNext()) {
         if (first) {
            sb.append(title).append(":").append('\n');
            first = false;
         }

         T provider = (T)iterator.next();
         sb.append("   ").append(provider).append('\n');
      }

   }

   public Future apply(ContainerRequest requestContext) {
      return this.apply(requestContext, new NullOutputStream());
   }

   public Future apply(ContainerRequest request, OutputStream outputStream) {
      FutureResponseWriter responseFuture = new FutureResponseWriter(request.getMethod(), outputStream, this.runtime.getBackgroundScheduler());
      if (request.getSecurityContext() == null) {
         request.setSecurityContext(DEFAULT_SECURITY_CONTEXT);
      }

      request.setWriter(responseFuture);
      this.handle(request);
      return responseFuture;
   }

   public void handle(ContainerRequest request) {
      request.setWorkers(this.msgBodyWorkers);
      this.runtime.process(request);
   }

   public InjectionManager getInjectionManager() {
      return this.injectionManager;
   }

   public ResourceConfig getConfiguration() {
      return this.runtimeConfig;
   }

   public void onStartup(Container container) {
      for(ContainerLifecycleListener listener : this.containerLifecycleListeners) {
         listener.onStartup(container);
      }

   }

   public void onReload(Container container) {
      for(ContainerLifecycleListener listener : this.containerLifecycleListeners) {
         listener.onReload(container);
      }

   }

   public void onShutdown(Container container) {
      try {
         for(ContainerLifecycleListener listener : this.containerLifecycleListeners) {
            listener.onShutdown(container);
         }
      } finally {
         try {
            this.injectionManager.preDestroy(ResourceConfig.unwrapApplication(this.application));
         } finally {
            this.managedObjectsFinalizer.preDestroy();
            this.injectionManager.shutdown();
         }
      }

   }

   private class RuntimeConfigConfigurator implements BootstrapConfigurator {
      private RuntimeConfigConfigurator() {
      }

      public void init(InjectionManager injectionManager, BootstrapBag bootstrapBag) {
         ServerBootstrapBag serverBag = (ServerBootstrapBag)bootstrapBag;
         serverBag.setApplicationHandler(ApplicationHandler.this);
         serverBag.setConfiguration(ResourceConfig.createRuntimeConfig(serverBag.getApplication()));
         InstanceBinding<ApplicationHandler> handlerBinding = (InstanceBinding)Bindings.service(ApplicationHandler.this).to(ApplicationHandler.class);
         InstanceBinding<ResourceConfig> configBinding = (InstanceBinding)((InstanceBinding)Bindings.service(serverBag.getRuntimeConfig()).to(Configuration.class)).to(ServerConfig.class);
         injectionManager.register(handlerBinding);
         injectionManager.register(configBinding);
      }
   }

   private static class WorkersToStringTransform implements Function {
      private WorkersToStringTransform() {
      }

      public String apply(Object t) {
         return t != null ? t.getClass().getName() : null;
      }
   }

   private static class FutureResponseWriter extends CompletableFuture implements ContainerResponseWriter {
      private ContainerResponse response;
      private final String requestMethodName;
      private final OutputStream outputStream;
      private final JerseyRequestTimeoutHandler requestTimeoutHandler;

      private FutureResponseWriter(String requestMethodName, OutputStream outputStream, ScheduledExecutorService backgroundScheduler) {
         this.response = null;
         this.requestMethodName = requestMethodName;
         this.outputStream = outputStream;
         this.requestTimeoutHandler = new JerseyRequestTimeoutHandler(this, backgroundScheduler);
      }

      public OutputStream writeResponseStatusAndHeaders(long contentLength, ContainerResponse response) {
         this.response = response;
         if (contentLength >= 0L) {
            response.getHeaders().putSingle("Content-Length", Long.toString(contentLength));
         }

         return this.outputStream;
      }

      public boolean suspend(long time, TimeUnit unit, ContainerResponseWriter.TimeoutHandler handler) {
         return this.requestTimeoutHandler.suspend(time, unit, handler);
      }

      public void setSuspendTimeout(long time, TimeUnit unit) {
         this.requestTimeoutHandler.setSuspendTimeout(time, unit);
      }

      public void commit() {
         ContainerResponse current = this.response;
         if (current != null) {
            if ("HEAD".equals(this.requestMethodName) && current.hasEntity()) {
               current.setEntity((Object)null);
            }

            this.requestTimeoutHandler.close();
            super.complete(current);
         }

      }

      public void failure(Throwable error) {
         this.requestTimeoutHandler.close();
         super.completeExceptionally(error);
      }

      public boolean enableResponseBuffering() {
         return true;
      }
   }
}
