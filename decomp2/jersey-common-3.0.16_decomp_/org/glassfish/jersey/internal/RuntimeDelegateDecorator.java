package org.glassfish.jersey.internal;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.Variant;
import jakarta.ws.rs.ext.RuntimeDelegate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

public class RuntimeDelegateDecorator {
   public static RuntimeDelegate configured(Configuration configuration) {
      return new ConfigurableRuntimeDelegate(RuntimeDelegate.getInstance(), configuration);
   }

   private static class ConfigurableRuntimeDelegate extends RuntimeDelegate {
      private final RuntimeDelegate runtimeDelegate;
      private final Configuration configuration;
      private static final Set headerDelegateProviders;

      private ConfigurableRuntimeDelegate(RuntimeDelegate runtimeDelegate, Configuration configuration) {
         this.runtimeDelegate = runtimeDelegate;
         this.configuration = configuration;
      }

      public UriBuilder createUriBuilder() {
         return this.runtimeDelegate.createUriBuilder();
      }

      public Response.ResponseBuilder createResponseBuilder() {
         return this.runtimeDelegate.createResponseBuilder();
      }

      public Variant.VariantListBuilder createVariantListBuilder() {
         return this.runtimeDelegate.createVariantListBuilder();
      }

      public Object createEndpoint(Application application, Class endpointType) throws IllegalArgumentException, UnsupportedOperationException {
         return this.runtimeDelegate.createEndpoint(application, endpointType);
      }

      public RuntimeDelegate.HeaderDelegate createHeaderDelegate(Class type) throws IllegalArgumentException {
         RuntimeDelegate.HeaderDelegate<T> headerDelegate = null;
         if (this.configuration == null || PropertiesHelper.isMetaInfServicesEnabled(this.configuration.getProperties(), this.configuration.getRuntimeType())) {
            headerDelegate = this._createHeaderDelegate(type);
         }

         if (headerDelegate == null) {
            headerDelegate = this.runtimeDelegate.createHeaderDelegate(type);
         }

         return headerDelegate;
      }

      public Link.Builder createLinkBuilder() {
         return this.runtimeDelegate.createLinkBuilder();
      }

      private RuntimeDelegate.HeaderDelegate _createHeaderDelegate(Class type) {
         for(HeaderDelegateProvider hp : headerDelegateProviders) {
            if (hp.supports(type)) {
               return hp;
            }
         }

         return null;
      }

      static {
         Set<HeaderDelegateProvider> hps = new HashSet();

         for(HeaderDelegateProvider provider : ServiceFinder.find(HeaderDelegateProvider.class, true)) {
            hps.add(provider);
         }

         headerDelegateProviders = Collections.unmodifiableSet(hps);
      }
   }
}
