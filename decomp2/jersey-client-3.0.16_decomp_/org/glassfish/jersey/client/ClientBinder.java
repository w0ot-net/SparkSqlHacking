package org.glassfish.jersey.client;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.MessageBodyReader;
import java.util.Map;
import java.util.function.Supplier;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.internal.inject.ClassBinding;
import org.glassfish.jersey.internal.inject.ReferencingFactory;
import org.glassfish.jersey.internal.inject.SupplierClassBinding;
import org.glassfish.jersey.internal.inject.SupplierInstanceBinding;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.message.internal.MessagingBinders;
import org.glassfish.jersey.process.internal.RequestScoped;

class ClientBinder extends AbstractBinder {
   private final Map clientRuntimeProperties;

   ClientBinder(Map clientRuntimeProperties) {
      this.clientRuntimeProperties = clientRuntimeProperties;
   }

   protected void configure() {
      this.install(new AbstractBinder[]{new MessagingBinders.MessageBodyProviders(this.clientRuntimeProperties, RuntimeType.CLIENT), new MessagingBinders.HeaderDelegateProviders()});
      ((SupplierInstanceBinding)this.bindFactory(ReferencingFactory.referenceFactory()).to(new GenericType() {
      })).in(RequestScoped.class);
      ((SupplierClassBinding)this.bindFactory(RequestContextInjectionFactory.class).to(ClientRequest.class)).in(RequestScoped.class);
      ((SupplierClassBinding)((SupplierClassBinding)((SupplierClassBinding)this.bindFactory(RequestContextInjectionFactory.class).to(HttpHeaders.class)).proxy(true)).proxyForSameScope(false)).in(RequestScoped.class);
      ((SupplierInstanceBinding)this.bindFactory(ReferencingFactory.referenceFactory()).to(new GenericType() {
      })).in(RequestScoped.class);
      ((SupplierClassBinding)this.bindFactory(PropertiesDelegateFactory.class, Singleton.class).to(PropertiesDelegate.class)).in(RequestScoped.class);
      ((ClassBinding)this.bind(ChunkedInputReader.class).to(MessageBodyReader.class)).in(Singleton.class);
   }

   private static class RequestContextInjectionFactory extends ReferencingFactory {
      @Inject
      public RequestContextInjectionFactory(Provider referenceFactory) {
         super(referenceFactory);
      }
   }

   private static class PropertiesDelegateFactory implements Supplier {
      private final Provider requestProvider;

      @Inject
      private PropertiesDelegateFactory(Provider requestProvider) {
         this.requestProvider = requestProvider;
      }

      public PropertiesDelegate get() {
         return ((ClientRequest)this.requestProvider.get()).getPropertiesDelegate();
      }
   }
}
