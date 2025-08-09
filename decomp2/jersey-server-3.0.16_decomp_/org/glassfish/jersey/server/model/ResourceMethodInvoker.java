package org.glassfish.jersey.server.model;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.DynamicFeature;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.WriterInterceptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.model.NameBound;
import org.glassfish.jersey.model.internal.ComponentBag;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.model.internal.RankedProvider;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ContainerResponse;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.internal.ProcessingProviders;
import org.glassfish.jersey.server.internal.inject.ConfiguredValidator;
import org.glassfish.jersey.server.internal.process.Endpoint;
import org.glassfish.jersey.server.internal.process.RequestProcessingContext;
import org.glassfish.jersey.server.model.internal.ResourceMethodDispatcherFactory;
import org.glassfish.jersey.server.model.internal.ResourceMethodInvocationHandlerFactory;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.spi.internal.ResourceMethodDispatcher;
import org.glassfish.jersey.server.spi.internal.ResourceMethodInvocationHandlerProvider;

public class ResourceMethodInvoker implements Endpoint, ResourceInfo {
   private final ResourceMethod method;
   private final Annotation[] methodAnnotations;
   private final Type invocableResponseType;
   private final boolean canUseInvocableResponseType;
   private final boolean isCompletionStageResponseType;
   private final boolean isCompletionStageResponseResponseType;
   private final Type completionStageResponseType;
   private final ResourceMethodDispatcher dispatcher;
   private final Method resourceMethod;
   private final Class resourceClass;
   private final List requestFilters;
   private final List responseFilters;
   private final Iterable readerInterceptors;
   private final Iterable writerInterceptors;

   private ResourceMethodInvoker(ResourceMethodDispatcher.Provider dispatcherProvider, ResourceMethodInvocationHandlerProvider invocationHandlerProvider, ResourceMethod method, ProcessingProviders processingProviders, InjectionManager injectionManager, Configuration globalConfig, ConfiguredValidator validator) {
      this.requestFilters = new ArrayList();
      this.responseFilters = new ArrayList();
      this.method = method;
      Invocable invocable = method.getInvocable();
      this.dispatcher = dispatcherProvider.create(invocable, invocationHandlerProvider.create(invocable), validator);
      this.resourceMethod = invocable.getHandlingMethod();
      this.resourceClass = invocable.getHandler().getHandlerClass();
      final ResourceMethodConfig config = new ResourceMethodConfig(globalConfig.getProperties());

      for(DynamicFeature dynamicFeature : processingProviders.getDynamicFeatures()) {
         dynamicFeature.configure(this, config);
      }

      ComponentBag componentBag = config.getComponentBag();
      List<Object> providers = new ArrayList(componentBag.getInstances(ComponentBag.excludeMetaProviders(injectionManager)));
      Set<Class<?>> providerClasses = componentBag.getClasses(ComponentBag.excludeMetaProviders(injectionManager));
      if (!providerClasses.isEmpty()) {
         injectionManager = Injections.createInjectionManager(injectionManager);
         injectionManager.register(new AbstractBinder() {
            protected void configure() {
               this.bind(config).to(Configuration.class);
            }
         });

         for(Class providerClass : providerClasses) {
            providers.add(injectionManager.createAndInitialize(providerClass));
         }
      }

      List<RankedProvider<ReaderInterceptor>> _readerInterceptors = new LinkedList();
      List<RankedProvider<WriterInterceptor>> _writerInterceptors = new LinkedList();
      List<RankedProvider<ContainerRequestFilter>> _requestFilters = new LinkedList();
      List<RankedProvider<ContainerResponseFilter>> _responseFilters = new LinkedList();

      for(Object provider : providers) {
         ContractProvider model = componentBag.getModel(provider.getClass());
         Set<Class<?>> contracts = model.getContracts();
         if (contracts.contains(WriterInterceptor.class)) {
            _writerInterceptors.add(new RankedProvider((WriterInterceptor)provider, model.getPriority(WriterInterceptor.class)));
         }

         if (contracts.contains(ReaderInterceptor.class)) {
            _readerInterceptors.add(new RankedProvider((ReaderInterceptor)provider, model.getPriority(ReaderInterceptor.class)));
         }

         if (contracts.contains(ContainerRequestFilter.class)) {
            _requestFilters.add(new RankedProvider((ContainerRequestFilter)provider, model.getPriority(ContainerRequestFilter.class)));
         }

         if (contracts.contains(ContainerResponseFilter.class)) {
            _responseFilters.add(new RankedProvider((ContainerResponseFilter)provider, model.getPriority(ContainerResponseFilter.class)));
         }
      }

      _readerInterceptors.addAll((Collection)StreamSupport.stream(processingProviders.getGlobalReaderInterceptors().spliterator(), false).collect(Collectors.toList()));
      _writerInterceptors.addAll((Collection)StreamSupport.stream(processingProviders.getGlobalWriterInterceptors().spliterator(), false).collect(Collectors.toList()));
      if (this.resourceMethod != null) {
         this.addNameBoundFiltersAndInterceptors(processingProviders, _requestFilters, _responseFilters, _readerInterceptors, _writerInterceptors, method);
      }

      this.readerInterceptors = Collections.unmodifiableList((List)StreamSupport.stream(Providers.sortRankedProviders(new RankedComparator(), _readerInterceptors).spliterator(), false).collect(Collectors.toList()));
      this.writerInterceptors = Collections.unmodifiableList((List)StreamSupport.stream(Providers.sortRankedProviders(new RankedComparator(), _writerInterceptors).spliterator(), false).collect(Collectors.toList()));
      this.requestFilters.addAll(_requestFilters);
      this.responseFilters.addAll(_responseFilters);
      this.methodAnnotations = invocable.getHandlingMethod().getDeclaredAnnotations();
      this.invocableResponseType = invocable.getResponseType();
      this.canUseInvocableResponseType = this.invocableResponseType != null && Void.TYPE != this.invocableResponseType && Void.class != this.invocableResponseType && (!(this.invocableResponseType instanceof Class) || !Response.class.isAssignableFrom((Class)this.invocableResponseType));
      this.isCompletionStageResponseType = ParameterizedType.class.isInstance(this.invocableResponseType) && CompletionStage.class.isAssignableFrom((Class)((ParameterizedType)this.invocableResponseType).getRawType());
      this.completionStageResponseType = this.isCompletionStageResponseType ? ((ParameterizedType)this.invocableResponseType).getActualTypeArguments()[0] : null;
      this.isCompletionStageResponseResponseType = Class.class.isInstance(this.completionStageResponseType) && Response.class.isAssignableFrom((Class)this.completionStageResponseType);
   }

   private void addNameBoundProviders(Collection targetCollection, NameBound nameBound, MultivaluedMap nameBoundProviders, MultivaluedMap nameBoundProvidersInverse) {
      MultivaluedMap<RankedProvider<T>, Class<? extends Annotation>> foundBindingsMap = new MultivaluedHashMap();

      for(Class nameBinding : nameBound.getNameBindings()) {
         Iterable<RankedProvider<T>> providers = (Iterable)nameBoundProviders.get(nameBinding);
         if (providers != null) {
            for(RankedProvider provider : providers) {
               foundBindingsMap.add(provider, nameBinding);
            }
         }
      }

      for(Map.Entry entry : foundBindingsMap.entrySet()) {
         RankedProvider<T> provider = (RankedProvider)entry.getKey();
         List<Class<? extends Annotation>> foundBindings = (List)entry.getValue();
         List<Class<? extends Annotation>> providerBindings = (List)nameBoundProvidersInverse.get(provider);
         if (foundBindings.size() == providerBindings.size()) {
            targetCollection.add(provider);
         }
      }

   }

   private void addNameBoundFiltersAndInterceptors(ProcessingProviders processingProviders, Collection targetRequestFilters, Collection targetResponseFilters, Collection targetReaderInterceptors, Collection targetWriterInterceptors, NameBound nameBound) {
      this.addNameBoundProviders(targetRequestFilters, nameBound, processingProviders.getNameBoundRequestFilters(), processingProviders.getNameBoundRequestFiltersInverse());
      this.addNameBoundProviders(targetResponseFilters, nameBound, processingProviders.getNameBoundResponseFilters(), processingProviders.getNameBoundResponseFiltersInverse());
      this.addNameBoundProviders(targetReaderInterceptors, nameBound, processingProviders.getNameBoundReaderInterceptors(), processingProviders.getNameBoundReaderInterceptorsInverse());
      this.addNameBoundProviders(targetWriterInterceptors, nameBound, processingProviders.getNameBoundWriterInterceptors(), processingProviders.getNameBoundWriterInterceptorsInverse());
   }

   public Method getResourceMethod() {
      return this.resourceMethod;
   }

   public Class getResourceClass() {
      return this.resourceClass;
   }

   public ContainerResponse apply(RequestProcessingContext processingContext) {
      ContainerRequest request = processingContext.request();
      Object resource = processingContext.routingContext().peekMatchedResource();
      if ((this.method.isSuspendDeclared() || this.method.isManagedAsyncDeclared() || this.method.isSse()) && !processingContext.asyncContext().suspend()) {
         throw new ProcessingException(LocalizationMessages.ERROR_SUSPENDING_ASYNC_REQUEST());
      } else if (this.method.isManagedAsyncDeclared()) {
         processingContext.asyncContext().invokeManaged(() -> {
            Response response = this.invoke(processingContext, resource);
            return this.method.isSuspendDeclared() ? null : response;
         });
         return null;
      } else {
         Response response = this.invoke(processingContext, resource);
         if (this.method.isSse()) {
            return null;
         } else {
            if (response.hasEntity()) {
               Object entityFuture = response.getEntity();
               if (entityFuture instanceof CompletionStage) {
                  CompletionStage completionStage = (CompletionStage)entityFuture;
                  if (!processingContext.asyncContext().suspend()) {
                     throw new ProcessingException(LocalizationMessages.ERROR_SUSPENDING_ASYNC_REQUEST());
                  }

                  completionStage.whenComplete(this.whenComplete(processingContext));
                  return null;
               }
            }

            return new ContainerResponse(request, response);
         }
      }
   }

   private BiConsumer whenComplete(RequestProcessingContext processingContext) {
      return (entity, exception) -> {
         if (exception != null) {
            if (exception instanceof CancellationException) {
               processingContext.asyncContext().resume(Response.status(Status.SERVICE_UNAVAILABLE).build());
            } else {
               processingContext.asyncContext().resume((Throwable)exception);
            }
         } else {
            processingContext.asyncContext().resume(entity);
         }

      };
   }

   private Response invoke(RequestProcessingContext context, Object resource) {
      context.triggerEvent(RequestEvent.Type.RESOURCE_METHOD_START);
      context.push((Function)((response) -> {
         if (response != null && !response.isMappedFromException()) {
            Annotation[] entityAnn = response.getEntityAnnotations();
            if (this.methodAnnotations.length > 0) {
               if (entityAnn.length == 0) {
                  response.setEntityAnnotations(this.methodAnnotations);
               } else {
                  Annotation[] mergedAnn = (Annotation[])Arrays.copyOf(this.methodAnnotations, this.methodAnnotations.length + entityAnn.length);
                  System.arraycopy(entityAnn, 0, mergedAnn, this.methodAnnotations.length, entityAnn.length);
                  response.setEntityAnnotations(mergedAnn);
               }
            }

            if (this.canUseInvocableResponseType && response.hasEntity() && !(response.getEntityType() instanceof ParameterizedType)) {
               response.setEntityType(this.unwrapInvocableResponseType(context.request(), response.getEntityType()));
            }

            return response;
         } else {
            return response;
         }
      }));

      Response jaxrsResponse;
      try {
         jaxrsResponse = this.dispatcher.dispatch(resource, context.request());
      } finally {
         context.triggerEvent(RequestEvent.Type.RESOURCE_METHOD_FINISHED);
      }

      if (jaxrsResponse == null) {
         jaxrsResponse = Response.noContent().build();
      }

      return jaxrsResponse;
   }

   private Type unwrapInvocableResponseType(ContainerRequest request, Type entityType) {
      if (this.isCompletionStageResponseType && (Boolean)request.resolveProperty("jersey.config.server.unwrap.completion.stage.writer.enable", (Object)Boolean.TRUE)) {
         return this.isCompletionStageResponseResponseType ? entityType : this.completionStageResponseType;
      } else {
         return this.invocableResponseType;
      }
   }

   public Iterable getRequestFilters() {
      return this.requestFilters;
   }

   public Iterable getResponseFilters() {
      return this.responseFilters;
   }

   public Iterable getWriterInterceptors() {
      return this.writerInterceptors;
   }

   public Iterable getReaderInterceptors() {
      return this.readerInterceptors;
   }

   public String toString() {
      return this.method.getInvocable().getHandlingMethod().toString();
   }

   public static class Builder {
      private ResourceMethodDispatcherFactory resourceMethodDispatcherFactory;
      private ResourceMethodInvocationHandlerFactory resourceMethodInvocationHandlerFactory;
      private InjectionManager injectionManager;
      private Configuration configuration;
      private Supplier configurationValidator;

      public Builder resourceMethodDispatcherFactory(ResourceMethodDispatcherFactory resourceMethodDispatcherFactory) {
         this.resourceMethodDispatcherFactory = resourceMethodDispatcherFactory;
         return this;
      }

      public Builder resourceMethodInvocationHandlerFactory(ResourceMethodInvocationHandlerFactory resourceMethodInvocationHandlerFactory) {
         this.resourceMethodInvocationHandlerFactory = resourceMethodInvocationHandlerFactory;
         return this;
      }

      public Builder injectionManager(InjectionManager injectionManager) {
         this.injectionManager = injectionManager;
         return this;
      }

      public Builder configuration(Configuration configuration) {
         this.configuration = configuration;
         return this;
      }

      public Builder configurationValidator(Supplier configurationValidator) {
         this.configurationValidator = configurationValidator;
         return this;
      }

      public ResourceMethodInvoker build(ResourceMethod method, ProcessingProviders processingProviders) {
         if (this.resourceMethodDispatcherFactory == null) {
            throw new NullPointerException("ResourceMethodDispatcherFactory is not set.");
         } else if (this.resourceMethodInvocationHandlerFactory == null) {
            throw new NullPointerException("ResourceMethodInvocationHandlerFactory is not set.");
         } else if (this.injectionManager == null) {
            throw new NullPointerException("DI injection manager is not set.");
         } else if (this.configuration == null) {
            throw new NullPointerException("Configuration is not set.");
         } else if (this.configurationValidator == null) {
            throw new NullPointerException("Configuration validator is not set.");
         } else {
            return new ResourceMethodInvoker(this.resourceMethodDispatcherFactory, this.resourceMethodInvocationHandlerFactory, method, processingProviders, this.injectionManager, this.configuration, (ConfiguredValidator)this.configurationValidator.get());
         }
      }
   }
}
