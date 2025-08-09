package org.glassfish.jersey.internal.inject;

import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class ParamConverterFactory implements ParamConverterProvider {
   private final List converterProviders;

   public ParamConverterFactory(Set providers, Set customProviders) {
      Set<ParamConverterProvider> copyProviders = new LinkedHashSet(providers);
      this.converterProviders = new ArrayList();
      this.converterProviders.addAll(customProviders);
      copyProviders.removeAll(customProviders);
      this.converterProviders.addAll(copyProviders);
   }

   public ParamConverter getConverter(Class rawType, Type genericType, Annotation[] annotations) {
      for(ParamConverterProvider provider : this.converterProviders) {
         ParamConverter<T> converter = provider.getConverter(rawType, genericType, annotations);
         if (converter != null) {
            return converter;
         }
      }

      return null;
   }
}
