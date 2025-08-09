package org.glassfish.jersey.client;

import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.glassfish.jersey.client.spi.InvocationBuilderListener;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.model.internal.RankedComparator.Order;

class InvocationBuilderListenerStage {
   final Iterator invocationBuilderListenerIterator;

   InvocationBuilderListenerStage(InjectionManager injectionManager) {
      RankedComparator<InvocationBuilderListener> comparator = new RankedComparator(Order.ASCENDING);
      this.invocationBuilderListenerIterator = Providers.getAllProviders(injectionManager, InvocationBuilderListener.class, comparator).iterator();
   }

   void invokeListener(JerseyInvocation.Builder builder) {
      while(this.invocationBuilderListenerIterator.hasNext()) {
         ((InvocationBuilderListener)this.invocationBuilderListenerIterator.next()).onNewBuilder(new InvocationBuilderContextImpl(builder));
      }

   }

   private static class InvocationBuilderContextImpl implements InvocationBuilderListener.InvocationBuilderContext {
      private final JerseyInvocation.Builder builder;

      private InvocationBuilderContextImpl(JerseyInvocation.Builder builder) {
         this.builder = builder;
      }

      public InvocationBuilderListener.InvocationBuilderContext accept(String... mediaTypes) {
         this.builder.accept(mediaTypes);
         return this;
      }

      public InvocationBuilderListener.InvocationBuilderContext accept(MediaType... mediaTypes) {
         this.builder.accept(mediaTypes);
         return this;
      }

      public InvocationBuilderListener.InvocationBuilderContext acceptLanguage(Locale... locales) {
         this.builder.acceptLanguage(locales);
         return this;
      }

      public InvocationBuilderListener.InvocationBuilderContext acceptLanguage(String... locales) {
         this.builder.acceptLanguage(locales);
         return this;
      }

      public InvocationBuilderListener.InvocationBuilderContext acceptEncoding(String... encodings) {
         this.builder.acceptEncoding(encodings);
         return this;
      }

      public InvocationBuilderListener.InvocationBuilderContext cookie(Cookie cookie) {
         this.builder.cookie(cookie);
         return this;
      }

      public InvocationBuilderListener.InvocationBuilderContext cookie(String name, String value) {
         this.builder.cookie(name, value);
         return this;
      }

      public InvocationBuilderListener.InvocationBuilderContext cacheControl(CacheControl cacheControl) {
         this.builder.cacheControl(cacheControl);
         return this;
      }

      public List getAccepted() {
         return this.getHeader("Accept");
      }

      public List getAcceptedLanguages() {
         return this.getHeader("Accept-Language");
      }

      public List getCacheControls() {
         return (List)this.builder.request().getHeaders().get("Cache-Control");
      }

      public Configuration getConfiguration() {
         return this.builder.request().getConfiguration();
      }

      public Map getCookies() {
         return this.builder.request().getCookies();
      }

      public List getEncodings() {
         return this.getHeader("Accept-Encoding");
      }

      public List getHeader(String name) {
         return this.builder.request().getRequestHeader(name);
      }

      public MultivaluedMap getHeaders() {
         return this.builder.request().getHeaders();
      }

      public Object getProperty(String name) {
         return this.builder.request().getProperty(name);
      }

      public Collection getPropertyNames() {
         return this.builder.request().getPropertyNames();
      }

      public URI getUri() {
         return this.builder.request().getUri();
      }

      public InvocationBuilderListener.InvocationBuilderContext header(String name, Object value) {
         this.builder.header(name, value);
         return this;
      }

      public InvocationBuilderListener.InvocationBuilderContext headers(MultivaluedMap headers) {
         this.builder.headers(headers);
         return this;
      }

      public InvocationBuilderListener.InvocationBuilderContext property(String name, Object value) {
         this.builder.property(name, value);
         return this;
      }

      public void removeProperty(String name) {
         this.builder.request().removeProperty(name);
      }
   }
}
