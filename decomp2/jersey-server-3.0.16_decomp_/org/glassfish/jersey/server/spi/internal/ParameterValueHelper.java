package org.glassfish.jersey.server.spi.internal;

import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;
import org.glassfish.jersey.model.Parameter.Source;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.internal.process.MappableException;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.model.Parameterized;

public final class ParameterValueHelper {
   public static Object[] getParameterValues(List valueProviders, ContainerRequest request) {
      Object[] params = new Object[valueProviders.size()];

      try {
         int entityProviderIndex = -1;
         int index = 0;

         for(ParamValueFactoryWithSource paramValProvider : valueProviders) {
            if (paramValProvider.getSource().equals(Source.ENTITY)) {
               entityProviderIndex = index++;
            } else {
               params[index++] = paramValProvider.apply(request);
            }
         }

         if (entityProviderIndex != -1) {
            params[entityProviderIndex] = ((ParamValueFactoryWithSource)valueProviders.get(entityProviderIndex)).apply(request);
         }

         return params;
      } catch (WebApplicationException e) {
         throw e;
      } catch (MessageBodyProviderNotFoundException e) {
         throw new NotSupportedException(e);
      } catch (ProcessingException e) {
         throw e;
      } catch (RuntimeException e) {
         if (e.getCause() instanceof WebApplicationException) {
            throw (WebApplicationException)e.getCause();
         } else {
            throw new MappableException("Exception obtaining parameters", e);
         }
      }
   }

   public static List createValueProviders(Collection valueSuppliers, Parameterized parameterized) {
      if (null != parameterized.getParameters() && 0 != parameterized.getParameters().size()) {
         List<ValueParamProvider> valueParamProviders = (List)valueSuppliers.stream().sorted((o1, o2) -> o2.getPriority().getWeight() - o1.getPriority().getWeight()).collect(Collectors.toList());
         boolean entityParamFound = false;
         List<ParamValueFactoryWithSource<?>> providers = new ArrayList(parameterized.getParameters().size());

         for(Parameter parameter : parameterized.getParameters()) {
            org.glassfish.jersey.model.Parameter.Source parameterSource = parameter.getSource();
            entityParamFound = entityParamFound || Source.ENTITY == parameterSource;
            Function<ContainerRequest, ?> valueFunction = getParamValueProvider(valueParamProviders, parameter);
            if (valueFunction != null) {
               providers.add(wrapParamValueProvider(valueFunction, parameterSource));
            } else {
               providers.add((Object)null);
            }
         }

         if (!entityParamFound && Collections.frequency(providers, (Object)null) == 1) {
            int entityParamIndex = providers.lastIndexOf((Object)null);
            Parameter parameter = (Parameter)parameterized.getParameters().get(entityParamIndex);
            if (Source.UNKNOWN == parameter.getSource() && !parameter.isQualified()) {
               Parameter overriddenParameter = Parameter.overrideSource(parameter, Source.ENTITY);
               Function<ContainerRequest, ?> valueFunction = getParamValueProvider(valueParamProviders, overriddenParameter);
               if (valueFunction != null) {
                  providers.set(entityParamIndex, wrapParamValueProvider(valueFunction, overriddenParameter.getSource()));
               } else {
                  providers.set(entityParamIndex, (Object)null);
               }
            }
         }

         return providers;
      } else {
         return Collections.emptyList();
      }
   }

   private static ParamValueFactoryWithSource wrapParamValueProvider(Function factory, org.glassfish.jersey.model.Parameter.Source paramSource) {
      return new ParamValueFactoryWithSource(factory, paramSource);
   }

   private static Function getParamValueProvider(Collection valueProviders, Parameter parameter) {
      Function<ContainerRequest, ?> valueProvider = null;

      for(Iterator<ValueParamProvider> vfpIterator = valueProviders.iterator(); valueProvider == null && vfpIterator.hasNext(); valueProvider = ((ValueParamProvider)vfpIterator.next()).getValueProvider(parameter)) {
      }

      return valueProvider;
   }

   private ParameterValueHelper() {
   }
}
