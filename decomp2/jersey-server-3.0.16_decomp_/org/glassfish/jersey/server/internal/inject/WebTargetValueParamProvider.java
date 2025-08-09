package org.glassfish.jersey.server.internal.inject;

import jakarta.inject.Provider;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Configurable;
import jakarta.ws.rs.core.Configuration;
import java.lang.annotation.Annotation;
import java.security.AccessController;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.model.Parameter.Source;
import org.glassfish.jersey.server.ClientBinding;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ExtendedUriInfo;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.uri.internal.JerseyUriBuilder;

final class WebTargetValueParamProvider extends AbstractValueParamProvider {
   private final Function clientConfigProvider;
   private final Supplier serverConfig;
   private final ConcurrentMap managedClients;

   public WebTargetValueParamProvider(final Supplier serverConfig, Function clientConfigProvider) {
      super((Provider)null, Source.URI);
      this.clientConfigProvider = clientConfigProvider;
      this.serverConfig = serverConfig;
      this.managedClients = new ConcurrentHashMap();
      this.managedClients.put(WebTargetValueParamProvider.BindingModel.EMPTY, Values.lazy(new Value() {
         public ManagedClient get() {
            Client client;
            if (serverConfig.get() == null) {
               client = ClientBuilder.newClient();
            } else {
               ClientConfig clientConfig = new ClientConfig();
               WebTargetValueParamProvider.this.copyProviders((Configuration)serverConfig.get(), clientConfig);
               client = ClientBuilder.newClient(clientConfig);
            }

            return new ManagedClient(client, "");
         }
      }));
   }

   private void copyProviders(Configuration source, Configurable target) {
      Configuration targetConfig = target.getConfiguration();

      for(Class c : source.getClasses()) {
         if (!targetConfig.isRegistered(c)) {
            target.register(c, source.getContracts(c));
         }
      }

      for(Object o : source.getInstances()) {
         Class<?> c = o.getClass();
         if (!targetConfig.isRegistered(o)) {
            target.register(o, source.getContracts(c));
         }
      }

   }

   protected Function createValueProvider(final Parameter parameter) {
      return (Function)Errors.processWithException(new Producer() {
         public Function call() {
            String targetUriTemplate = parameter.getSourceName();
            if (targetUriTemplate != null && targetUriTemplate.length() != 0) {
               Class<?> rawParameterType = parameter.getRawType();
               if (rawParameterType == WebTarget.class) {
                  final BindingModel binding = WebTargetValueParamProvider.BindingModel.create((Collection)Arrays.asList(parameter.getAnnotations()));
                  Value<ManagedClient> client = (Value)WebTargetValueParamProvider.this.managedClients.get(binding);
                  if (client == null) {
                     client = Values.lazy(new Value() {
                        public ManagedClient get() {
                           String prefix = binding.getAnnotation().annotationType().getName() + ".";
                           String baseUriProperty = prefix + "baseUri";
                           Object bu = ((Configuration)WebTargetValueParamProvider.this.serverConfig.get()).getProperty(baseUriProperty);
                           String customBaseUri = bu != null ? bu.toString() : binding.baseUri();
                           String configClassProperty = prefix + "configClass";
                           ClientConfig cfg = WebTargetValueParamProvider.this.resolveConfig(configClassProperty, binding);
                           String inheritProvidersProperty = prefix + "inheritServerProviders";
                           if (PropertiesHelper.isProperty(((Configuration)WebTargetValueParamProvider.this.serverConfig.get()).getProperty(inheritProvidersProperty)) || binding.inheritProviders()) {
                              WebTargetValueParamProvider.this.copyProviders((Configuration)WebTargetValueParamProvider.this.serverConfig.get(), cfg);
                           }

                           String propertyPrefix = prefix + "property.";

                           for(String property : (Collection)((Configuration)WebTargetValueParamProvider.this.serverConfig.get()).getPropertyNames().stream().filter((propertyx) -> propertyx.startsWith(propertyPrefix)).collect(Collectors.toSet())) {
                              cfg.property(property.substring(propertyPrefix.length()), ((Configuration)WebTargetValueParamProvider.this.serverConfig.get()).getProperty(property));
                           }

                           return new ManagedClient(ClientBuilder.newClient(cfg), customBaseUri);
                        }
                     });
                     Value<ManagedClient> previous = (Value)WebTargetValueParamProvider.this.managedClients.putIfAbsent(binding, client);
                     if (previous != null) {
                        client = previous;
                     }
                  }

                  return new WebTargetValueSupplier(targetUriTemplate, client);
               } else {
                  Errors.warning(this, LocalizationMessages.UNSUPPORTED_URI_INJECTION_TYPE(rawParameterType));
                  return null;
               }
            } else {
               Errors.warning(this, LocalizationMessages.INJECTED_WEBTARGET_URI_INVALID(targetUriTemplate));
               return null;
            }
         }
      });
   }

   private ClientConfig resolveConfig(String configClassProperty, BindingModel binding) {
      Class<? extends Configuration> configClass = binding.getConfigClass();
      Object _cc = ((Configuration)this.serverConfig.get()).getProperty(configClassProperty);
      if (_cc != null) {
         Class<?> cc;
         if (_cc instanceof String) {
            cc = (Class)AccessController.doPrivileged(ReflectionHelper.classForNamePA((String)_cc));
         } else if (_cc instanceof Class) {
            cc = (Class)_cc;
         } else {
            cc = null;
         }

         if (cc != null && Configuration.class.isAssignableFrom(cc)) {
            configClass = cc.asSubclass(Configuration.class);
         } else {
            Errors.warning(this, LocalizationMessages.ILLEGAL_CLIENT_CONFIG_CLASS_PROPERTY_VALUE(configClassProperty, _cc, configClass.getName()));
         }
      }

      Configuration cfg = (Configuration)this.clientConfigProvider.apply(configClass);
      return cfg instanceof ClientConfig ? (ClientConfig)cfg : (new ClientConfig()).loadFrom(cfg);
   }

   private static class ManagedClient {
      private final Client instance;
      private final String customBaseUri;

      private ManagedClient(Client instance, String customBaseUri) {
         this.instance = instance;
         this.customBaseUri = customBaseUri;
      }
   }

   private static class BindingModel {
      public static final BindingModel EMPTY = new BindingModel((Annotation)null);
      private final Annotation annotation;
      private final Class configClass;
      private final boolean inheritProviders;
      private final String baseUri;

      public static BindingModel create(Annotation binding) {
         return binding != null && binding.annotationType().getAnnotation(ClientBinding.class) != null ? new BindingModel(binding) : EMPTY;
      }

      public static BindingModel create(Collection bindingCandidates) {
         Collection<Annotation> filtered = (Collection)bindingCandidates.stream().filter((input) -> input != null && input.annotationType().getAnnotation(ClientBinding.class) != null).collect(Collectors.toList());
         if (filtered.isEmpty()) {
            return EMPTY;
         } else if (filtered.size() > 1) {
            throw new ProcessingException("Too many client binding annotations.");
         } else {
            return new BindingModel((Annotation)filtered.iterator().next());
         }
      }

      private BindingModel(Annotation annotation) {
         if (annotation == null) {
            this.annotation = null;
            this.configClass = ClientConfig.class;
            this.inheritProviders = true;
            this.baseUri = "";
         } else {
            this.annotation = annotation;
            ClientBinding cba = (ClientBinding)annotation.annotationType().getAnnotation(ClientBinding.class);
            this.configClass = cba.configClass();
            this.inheritProviders = cba.inheritServerProviders();
            this.baseUri = cba.baseUri();
         }

      }

      public Annotation getAnnotation() {
         return this.annotation;
      }

      public Class getConfigClass() {
         return this.configClass;
      }

      public boolean inheritProviders() {
         return this.inheritProviders;
      }

      public String baseUri() {
         return this.baseUri;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            BindingModel that = (BindingModel)o;
            return this.annotation != null ? this.annotation.equals(that.annotation) : that.annotation == null;
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.annotation != null ? this.annotation.hashCode() : 0;
      }

      public String toString() {
         return "BindingModel{binding=" + this.annotation + ", configClass=" + this.configClass + ", inheritProviders=" + this.inheritProviders + ", baseUri=" + this.baseUri + '}';
      }
   }

   private static final class WebTargetValueSupplier implements Function {
      private final String uri;
      private final Value client;

      WebTargetValueSupplier(String uri, Value client) {
         this.uri = uri;
         this.client = client;
      }

      public WebTarget apply(ContainerRequest containerRequest) {
         ExtendedUriInfo uriInfo = containerRequest.getUriInfo();
         Map<String, Object> pathParamValues = (Map)uriInfo.getPathParameters().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (stringObjectEntry) -> {
            List<String> input = (List)stringObjectEntry.getValue();
            return input.isEmpty() ? null : input.get(0);
         }));
         JerseyUriBuilder uriBuilder = (new JerseyUriBuilder()).uri(this.uri).resolveTemplates(pathParamValues);
         ManagedClient managedClient = (ManagedClient)this.client.get();
         if (!uriBuilder.isAbsolute()) {
            String customBaseUri = managedClient.customBaseUri;
            String rootUri = customBaseUri.isEmpty() ? uriInfo.getBaseUri().toString() : customBaseUri;
            uriBuilder = (new JerseyUriBuilder()).uri(rootUri).path(uriBuilder.toTemplate());
         }

         return managedClient.instance.target(uriBuilder);
      }
   }
}
