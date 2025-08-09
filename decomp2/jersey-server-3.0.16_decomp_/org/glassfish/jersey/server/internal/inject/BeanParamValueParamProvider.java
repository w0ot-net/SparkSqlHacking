package org.glassfish.jersey.server.internal.inject;

import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import java.util.function.Function;
import org.glassfish.jersey.internal.inject.Bindings;
import org.glassfish.jersey.internal.inject.ForeignDescriptor;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.util.collection.Cache;
import org.glassfish.jersey.model.Parameter.Source;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.model.Parameter;

@Singleton
final class BeanParamValueParamProvider extends AbstractValueParamProvider {
   private final InjectionManager injectionManager;

   public BeanParamValueParamProvider(Provider mpep, InjectionManager injectionManager) {
      super(mpep, Source.BEAN_PARAM);
      this.injectionManager = injectionManager;
   }

   public Function createValueProvider(Parameter parameter) {
      return new BeanParamValueProvider(this.injectionManager, parameter);
   }

   private static final class BeanParamValueProvider implements Function {
      private final Parameter parameter;
      private final InjectionManager injectionManager;
      private final Cache descriptorCache;

      private BeanParamValueProvider(InjectionManager injectionManager, Parameter parameter) {
         this.descriptorCache = new Cache(new Function() {
            public ForeignDescriptor apply(Class key) {
               return BeanParamValueProvider.this.injectionManager.createForeignDescriptor(Bindings.serviceAsContract(key).in(RequestScoped.class));
            }
         });
         this.injectionManager = injectionManager;
         this.parameter = parameter;
      }

      public Object apply(ContainerRequest request) {
         Class<?> rawType = this.parameter.getRawType();
         Object fromHk2 = this.injectionManager.getInstance(rawType);
         if (fromHk2 != null) {
            return fromHk2;
         } else {
            ForeignDescriptor foreignDescriptor = (ForeignDescriptor)this.descriptorCache.apply(rawType);
            return this.injectionManager.getInstance(foreignDescriptor);
         }
      }
   }
}
