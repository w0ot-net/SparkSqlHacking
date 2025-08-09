package org.glassfish.jersey.server.model.internal;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.sse.SseEventSink;
import java.io.Flushable;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.internal.inject.ConfiguredValidator;
import org.glassfish.jersey.server.model.Invocable;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.spi.internal.ParamValueFactoryWithSource;
import org.glassfish.jersey.server.spi.internal.ParameterValueHelper;
import org.glassfish.jersey.server.spi.internal.ResourceMethodDispatcher;
import org.glassfish.jersey.server.spi.internal.ValueParamProvider;

class JavaResourceMethodDispatcherProvider implements ResourceMethodDispatcher.Provider {
   private final Collection allValueProviders;

   JavaResourceMethodDispatcherProvider(Collection allValueProviders) {
      this.allValueProviders = allValueProviders;
   }

   public ResourceMethodDispatcher create(Invocable resourceMethod, InvocationHandler invocationHandler, ConfiguredValidator validator) {
      List<ParamValueFactoryWithSource<?>> valueProviders = ParameterValueHelper.createValueProviders(this.allValueProviders, resourceMethod);
      Class<?> returnType = resourceMethod.getHandlingMethod().getReturnType();
      ResourceMethodDispatcher resourceMethodDispatcher = null;
      if (Response.class.isAssignableFrom(returnType)) {
         resourceMethodDispatcher = new ResponseOutInvoker(resourceMethod, invocationHandler, valueProviders, validator);
      } else if (returnType != Void.TYPE) {
         if (returnType != Object.class && !GenericEntity.class.isAssignableFrom(returnType)) {
            resourceMethodDispatcher = new TypeOutInvoker(resourceMethod, invocationHandler, valueProviders, validator);
         } else {
            resourceMethodDispatcher = new ObjectOutInvoker(resourceMethod, invocationHandler, valueProviders, validator);
         }
      } else {
         int i = 0;

         for(Parameter parameter : resourceMethod.getParameters()) {
            if (SseTypeResolver.isSseSinkParam(parameter.getRawType())) {
               resourceMethodDispatcher = new SseEventSinkInvoker(resourceMethod, invocationHandler, valueProviders, validator, i);
               break;
            }

            ++i;
         }

         if (resourceMethodDispatcher == null) {
            resourceMethodDispatcher = new VoidOutInvoker(resourceMethod, invocationHandler, valueProviders, validator);
         }
      }

      return resourceMethodDispatcher;
   }

   private abstract static class AbstractMethodParamInvoker extends AbstractJavaResourceMethodDispatcher {
      private final List valueProviders;

      AbstractMethodParamInvoker(Invocable resourceMethod, InvocationHandler handler, List valueProviders, ConfiguredValidator validator) {
         super(resourceMethod, handler, validator);
         this.valueProviders = valueProviders;
      }

      final Object[] getParamValues(ContainerRequest request) {
         return ParameterValueHelper.getParameterValues(this.valueProviders, request);
      }
   }

   private static final class SseEventSinkInvoker extends AbstractMethodParamInvoker {
      private final int parameterIndex;

      SseEventSinkInvoker(Invocable resourceMethod, InvocationHandler handler, List valueProviders, ConfiguredValidator validator, int parameterIndex) {
         super(resourceMethod, handler, valueProviders, validator);
         this.parameterIndex = parameterIndex;
      }

      protected Response doDispatch(Object resource, ContainerRequest request) throws ProcessingException {
         Object[] paramValues = this.getParamValues(request);
         this.invoke(request, resource, paramValues);
         SseEventSink eventSink = (SseEventSink)paramValues[this.parameterIndex];
         if (eventSink == null) {
            throw new IllegalArgumentException("SseEventSink parameter detected, but not found.");
         } else {
            if (eventSink instanceof Flushable) {
               try {
                  ((Flushable)eventSink).flush();
               } catch (IOException var6) {
               }
            }

            return Response.ok().entity(eventSink).build();
         }
      }
   }

   private static final class VoidOutInvoker extends AbstractMethodParamInvoker {
      VoidOutInvoker(Invocable resourceMethod, InvocationHandler handler, List valueProviders, ConfiguredValidator validator) {
         super(resourceMethod, handler, valueProviders, validator);
      }

      protected Response doDispatch(Object resource, ContainerRequest containerRequest) throws ProcessingException {
         this.invoke(containerRequest, resource, this.getParamValues(containerRequest));
         return Response.noContent().build();
      }
   }

   private static final class ResponseOutInvoker extends AbstractMethodParamInvoker {
      ResponseOutInvoker(Invocable resourceMethod, InvocationHandler handler, List valueProviders, ConfiguredValidator validator) {
         super(resourceMethod, handler, valueProviders, validator);
      }

      protected Response doDispatch(Object resource, ContainerRequest containerRequest) throws ProcessingException {
         return (Response)Response.class.cast(this.invoke(containerRequest, resource, this.getParamValues(containerRequest)));
      }
   }

   private static final class ObjectOutInvoker extends AbstractMethodParamInvoker {
      ObjectOutInvoker(Invocable resourceMethod, InvocationHandler handler, List valueProviders, ConfiguredValidator validator) {
         super(resourceMethod, handler, valueProviders, validator);
      }

      protected Response doDispatch(Object resource, ContainerRequest containerRequest) throws ProcessingException {
         Object o = this.invoke(containerRequest, resource, this.getParamValues(containerRequest));
         if (o instanceof Response) {
            return (Response)Response.class.cast(o);
         } else {
            return o != null ? Response.ok().entity(o).build() : Response.noContent().build();
         }
      }
   }

   private static final class TypeOutInvoker extends AbstractMethodParamInvoker {
      private final Type t;

      TypeOutInvoker(Invocable resourceMethod, InvocationHandler handler, List valueProviders, ConfiguredValidator validator) {
         super(resourceMethod, handler, valueProviders, validator);
         this.t = resourceMethod.getHandlingMethod().getGenericReturnType();
      }

      protected Response doDispatch(Object resource, ContainerRequest containerRequest) throws ProcessingException {
         Object o = this.invoke(containerRequest, resource, this.getParamValues(containerRequest));
         if (o != null) {
            return o instanceof Response ? (Response)Response.class.cast(o) : Response.ok().entity(o).build();
         } else {
            return Response.noContent().build();
         }
      }
   }
}
