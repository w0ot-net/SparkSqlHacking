package org.glassfish.jersey.server.internal.inject;

import jakarta.inject.Singleton;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;
import org.glassfish.jersey.internal.inject.Binding;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.ContextInjectionResolver;
import org.glassfish.jersey.internal.inject.ForeignDescriptor;
import org.glassfish.jersey.internal.inject.Injectee;
import org.glassfish.jersey.internal.inject.InjecteeImpl;
import org.glassfish.jersey.internal.util.collection.Cache;
import org.glassfish.jersey.internal.util.collection.LazyValue;
import org.glassfish.jersey.model.Parameter.Source;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.spi.internal.ValueParamProvider;

@Singleton
class DelegatedInjectionValueParamProvider implements ValueParamProvider {
   private final LazyValue resolver;
   private final Function foreignDescriptorFactory;
   private final Cache descriptorCache = new Cache((parameter) -> {
      Class<?> rawType = parameter.getRawType();
      return rawType.isInterface() && !(parameter.getType() instanceof ParameterizedType) ? this.createDescriptor(rawType) : null;
   });

   public DelegatedInjectionValueParamProvider(LazyValue resolver, Function foreignDescriptorFactory) {
      this.resolver = resolver;
      this.foreignDescriptorFactory = foreignDescriptorFactory;
   }

   public Function getValueProvider(Parameter parameter) {
      org.glassfish.jersey.model.Parameter.Source paramSource = parameter.getSource();
      return paramSource == Source.CONTEXT ? (containerRequest) -> ((ContextInjectionResolver)this.resolver.get()).resolve(this.getInjectee(parameter)) : null;
   }

   public ValueParamProvider.PriorityType getPriority() {
      return ValueParamProvider.Priority.LOW;
   }

   private Injectee getInjectee(Parameter parameter) {
      InjecteeImpl injectee = new InjecteeImpl();
      injectee.setRequiredType(parameter.getType());
      injectee.setInjecteeClass(parameter.getRawType());
      ForeignDescriptor proxyDescriptor = (ForeignDescriptor)this.descriptorCache.apply(parameter);
      if (proxyDescriptor != null) {
         injectee.setInjecteeDescriptor(proxyDescriptor);
      }

      return injectee;
   }

   private ForeignDescriptor createDescriptor(Class clazz) {
      return (ForeignDescriptor)this.foreignDescriptorFactory.apply(Bindings.serviceAsContract(clazz).in(RequestScoped.class));
   }
}
