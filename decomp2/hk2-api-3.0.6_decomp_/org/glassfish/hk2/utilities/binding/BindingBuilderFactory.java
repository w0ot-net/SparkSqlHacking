package org.glassfish.hk2.utilities.binding;

import java.lang.annotation.Annotation;
import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.HK2Loader;

public class BindingBuilderFactory {
   public static void addBinding(BindingBuilder builder, DynamicConfiguration configuration) {
      if (builder instanceof AbstractBindingBuilder) {
         ((AbstractBindingBuilder)builder).complete(configuration, (HK2Loader)null);
      } else {
         throw new IllegalArgumentException("Unknown binding builder type: " + builder.getClass().getName());
      }
   }

   public static void addBinding(BindingBuilder builder, DynamicConfiguration configuration, HK2Loader defaultLoader) {
      if (builder instanceof AbstractBindingBuilder) {
         ((AbstractBindingBuilder)builder).complete(configuration, defaultLoader);
      } else {
         throw new IllegalArgumentException("Unknown binding builder type: " + builder.getClass().getName());
      }
   }

   public static ServiceBindingBuilder newFactoryBinder(Class factoryType, Class factoryScope) {
      return AbstractBindingBuilder.createFactoryBinder(factoryType, factoryScope);
   }

   public static ServiceBindingBuilder newFactoryBinder(Class factoryType) {
      return AbstractBindingBuilder.createFactoryBinder(factoryType, (Class)null);
   }

   public static ServiceBindingBuilder newFactoryBinder(Factory factory) {
      return AbstractBindingBuilder.createFactoryBinder(factory);
   }

   public static ServiceBindingBuilder newBinder(Class serviceType) {
      return AbstractBindingBuilder.create(serviceType, false);
   }

   public static ScopedBindingBuilder newBinder(Object service) {
      return AbstractBindingBuilder.create(service);
   }
}
