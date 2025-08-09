package org.glassfish.jersey.server.internal.routing;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Configuration;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.guava.CacheBuilder;
import org.glassfish.jersey.internal.guava.CacheLoader;
import org.glassfish.jersey.internal.guava.LoadingCache;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.internal.JerseyResourceContext;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.model.ComponentModelValidator;
import org.glassfish.jersey.server.model.ModelProcessor;
import org.glassfish.jersey.server.model.ModelValidationException;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.glassfish.jersey.server.model.ResourceModel;
import org.glassfish.jersey.server.model.ResourceModelComponent;
import org.glassfish.jersey.server.model.internal.ModelErrors;
import org.glassfish.jersey.server.spi.internal.ValueParamProvider;

final class RuntimeLocatorModelBuilder {
   private static final Logger LOGGER = Logger.getLogger(RuntimeLocatorModelBuilder.class.getName());
   private final Configuration config;
   private final RuntimeModelBuilder runtimeModelBuilder;
   private final MessageBodyWorkers messageBodyWorkers;
   private final Collection valueSuppliers;
   private final JerseyResourceContext resourceContext;
   private final Iterable modelProcessors;
   private final Function createServiceFunction;
   private final LoadingCache cache;
   private final boolean disableValidation;
   private final boolean ignoreValidationErrors;
   private final boolean enableJerseyResourceCaching;

   RuntimeLocatorModelBuilder(Configuration config, MessageBodyWorkers messageBodyWorkers, Collection valueSuppliers, JerseyResourceContext resourceContext, RuntimeModelBuilder runtimeModelBuilder, Iterable modelProcessors, Function createServiceFunction) {
      this.config = config;
      this.messageBodyWorkers = messageBodyWorkers;
      this.valueSuppliers = valueSuppliers;
      this.runtimeModelBuilder = runtimeModelBuilder;
      this.resourceContext = resourceContext;
      this.modelProcessors = modelProcessors;
      this.createServiceFunction = createServiceFunction;
      this.disableValidation = (Boolean)ServerProperties.getValue(config.getProperties(), "jersey.config.server.resource.validation.disable", Boolean.FALSE, Boolean.class);
      this.ignoreValidationErrors = (Boolean)ServerProperties.getValue(config.getProperties(), "jersey.config.server.resource.validation.ignoreErrors", Boolean.FALSE, Boolean.class);
      this.enableJerseyResourceCaching = (Boolean)ServerProperties.getValue(config.getProperties(), "jersey.config.server.subresource.cache.jersey.resource.enabled", Boolean.FALSE, Boolean.class);
      int size = (Integer)ServerProperties.getValue(config.getProperties(), "jersey.config.server.subresource.cache.size", 64, Integer.class);
      int age = (Integer)ServerProperties.getValue(config.getProperties(), "jersey.config.server.subresource.cache.age", -1, Integer.class);
      CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder();
      if (size > 0) {
         cacheBuilder.maximumSize((long)size);
      } else {
         LOGGER.log(Level.CONFIG, LocalizationMessages.SUBRES_LOC_CACHE_INVALID_SIZE(size, 64));
         cacheBuilder.maximumSize(64L);
      }

      if (age > 0) {
         cacheBuilder.expireAfterAccess((long)age, TimeUnit.SECONDS);
      }

      this.cache = cacheBuilder.build(new CacheLoader() {
         public LocatorRouting load(LocatorCacheKey key) throws Exception {
            return key.clazz != null ? RuntimeLocatorModelBuilder.this.createRouting(key.clazz) : RuntimeLocatorModelBuilder.this.buildRouting(key.resource);
         }
      });
   }

   Router getRouter(ResourceMethod resourceMethod) {
      return new SubResourceLocatorRouter(this.createServiceFunction, this.valueSuppliers, resourceMethod, this.resourceContext, this);
   }

   LocatorRouting getRouting(Class locatorClass) {
      try {
         return (LocatorRouting)this.cache.get(new LocatorCacheKey(locatorClass));
      } catch (ExecutionException ee) {
         LOGGER.log(Level.FINE, LocalizationMessages.SUBRES_LOC_CACHE_LOAD_FAILED(locatorClass), ee);
         return this.createRouting(locatorClass);
      }
   }

   LocatorRouting getRouting(Resource subresource) {
      if (this.enableJerseyResourceCaching) {
         try {
            return (LocatorRouting)this.cache.get(new LocatorCacheKey(subresource));
         } catch (ExecutionException ee) {
            LOGGER.log(Level.FINE, LocalizationMessages.SUBRES_LOC_CACHE_LOAD_FAILED(subresource), ee);
            return this.buildRouting(subresource);
         }
      } else {
         return this.buildRouting(subresource);
      }
   }

   boolean isCached(Class srlClass) {
      return this.cache.getIfPresent(srlClass) != null;
   }

   private LocatorRouting createRouting(Class locatorClass) {
      Resource.Builder builder = Resource.builder(locatorClass, this.disableValidation);
      if (builder == null) {
         builder = Resource.builder().name(locatorClass.getName());
      }

      return this.buildRouting(builder.build());
   }

   private LocatorRouting buildRouting(Resource subResource) {
      ResourceModel model = (new ResourceModel.Builder(true)).addResource(subResource).build();
      ResourceModel enhancedModel = this.enhance(model);
      if (!this.disableValidation) {
         this.validateResource(enhancedModel);
      }

      Resource enhancedLocator = (Resource)enhancedModel.getResources().get(0);

      for(Class handlerClass : enhancedLocator.getHandlerClasses()) {
         this.resourceContext.bindResource(handlerClass);
      }

      return new LocatorRouting(enhancedModel, this.runtimeModelBuilder.buildModel(enhancedModel.getRuntimeResourceModel(), true));
   }

   private void validateResource(final ResourceModelComponent component) {
      Errors.process(new Runnable() {
         public void run() {
            ComponentModelValidator validator = new ComponentModelValidator(RuntimeLocatorModelBuilder.this.valueSuppliers, RuntimeLocatorModelBuilder.this.messageBodyWorkers);
            validator.validate(component);
            if (Errors.fatalIssuesFound() && !RuntimeLocatorModelBuilder.this.ignoreValidationErrors) {
               throw new ModelValidationException(LocalizationMessages.ERROR_VALIDATION_SUBRESOURCE(), ModelErrors.getErrorsAsResourceModelIssues());
            }
         }
      });
   }

   private ResourceModel enhance(ResourceModel subResourceModel) {
      for(ModelProcessor modelProcessor : this.modelProcessors) {
         subResourceModel = modelProcessor.processSubResource(subResourceModel, this.config);
         this.validateSubResource(subResourceModel);
      }

      return subResourceModel;
   }

   private void validateSubResource(ResourceModel subResourceModel) {
      if (subResourceModel.getResources().size() != 1) {
         throw new ProcessingException(LocalizationMessages.ERROR_SUB_RESOURCE_LOCATOR_MORE_RESOURCES(subResourceModel.getResources().size()));
      }
   }

   private static class LocatorCacheKey {
      private final Class clazz;
      private final Resource resource;

      public LocatorCacheKey(Class clazz) {
         this(clazz, (Resource)null);
      }

      public LocatorCacheKey(Resource resource) {
         this((Class)null, resource);
      }

      private LocatorCacheKey(Class clazz, Resource resource) {
         this.clazz = clazz;
         this.resource = resource;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            LocatorCacheKey that = (LocatorCacheKey)o;
            if (this.clazz != null) {
               if (!this.clazz.equals(that.clazz)) {
                  return false;
               }
            } else if (that.clazz != null) {
               return false;
            }

            if (this.resource != null) {
               if (!this.resource.equals(that.resource)) {
                  return false;
               }
            } else if (that.resource != null) {
               return false;
            }

            return true;
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.clazz != null ? this.clazz.hashCode() : 0;
         result = 31 * result + (this.resource != null ? this.resource.hashCode() : 0);
         return result;
      }
   }
}
