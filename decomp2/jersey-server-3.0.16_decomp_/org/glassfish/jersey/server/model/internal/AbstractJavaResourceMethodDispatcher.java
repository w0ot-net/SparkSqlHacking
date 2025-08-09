package org.glassfish.jersey.server.model.internal;

import jakarta.validation.ValidationException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.ResponseProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.Response.Status;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.PrivilegedAction;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.glassfish.jersey.message.internal.TracingLogger;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.SubjectSecurityContext;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.internal.ServerTraceEvent;
import org.glassfish.jersey.server.internal.inject.ConfiguredValidator;
import org.glassfish.jersey.server.internal.process.MappableException;
import org.glassfish.jersey.server.model.Invocable;
import org.glassfish.jersey.server.spi.internal.ResourceMethodDispatcher;

abstract class AbstractJavaResourceMethodDispatcher implements ResourceMethodDispatcher {
   private final Method method;
   private final InvocationHandler methodHandler;
   private final Invocable resourceMethod;
   private final ConfiguredValidator validator;

   AbstractJavaResourceMethodDispatcher(Invocable resourceMethod, InvocationHandler methodHandler, ConfiguredValidator validator) {
      this.method = this.getPublic(resourceMethod.getHandlingMethod(), resourceMethod.getDefinitionMethod());
      this.methodHandler = methodHandler;
      this.resourceMethod = resourceMethod;
      this.validator = validator;
   }

   private Method getPublic(Method handlingMethod, Method definitionMethod) {
      if (handlingMethod == definitionMethod) {
         return handlingMethod;
      } else {
         boolean publicHandling = ReflectionHelper.isPublic(handlingMethod) && ReflectionHelper.isPublic(handlingMethod.getDeclaringClass());
         return publicHandling ? handlingMethod : definitionMethod;
      }
   }

   public final Response dispatch(Object resource, ContainerRequest request) throws ProcessingException {
      Response response = null;

      try {
         response = this.doDispatch(resource, request);
         if (response instanceof OutboundJaxrsResponse) {
            OutboundJaxrsResponse responseImpl = (OutboundJaxrsResponse)response;
            OutboundMessageContext context = responseImpl.getContext();
            if (context.isCommitted()) {
               throw new ResponseProcessingException(response, LocalizationMessages.ERROR_RESPONSE_ALREADY_COMMITED());
            }
         }
      } finally {
         TracingLogger.getInstance(request).log(ServerTraceEvent.DISPATCH_RESPONSE, new Object[]{response});
      }

      return response;
   }

   protected abstract Response doDispatch(Object var1, ContainerRequest var2) throws ProcessingException;

   final Object invoke(final ContainerRequest containerRequest, final Object resource, final Object... args) throws ProcessingException {
      try {
         if (this.validator != null) {
            this.validator.validateResourceAndInputParams(resource, this.resourceMethod, args);
         }

         PrivilegedAction invokeMethodAction = new PrivilegedAction() {
            public Object run() {
               TracingLogger tracingLogger = TracingLogger.getInstance(containerRequest);
               long timestamp = tracingLogger.timestamp(ServerTraceEvent.METHOD_INVOKE);

               try {
                  Object result = AbstractJavaResourceMethodDispatcher.this.methodHandler.invoke(resource, AbstractJavaResourceMethodDispatcher.this.method, args);
                  if (result instanceof CompletionStage) {
                     CompletableFuture resultFuture;
                     try {
                        resultFuture = ((CompletionStage)result).toCompletableFuture();
                     } catch (UnsupportedOperationException var16) {
                        Object var7 = result;
                        return var7;
                     }

                     if (resultFuture != null && resultFuture.isDone()) {
                        if (resultFuture.isCancelled()) {
                           Response e = Response.status(Status.SERVICE_UNAVAILABLE).build();
                           return e;
                        }

                        try {
                           Object ex = resultFuture.get();
                           return ex;
                        } catch (ExecutionException e) {
                           throw new InvocationTargetException(e.getCause());
                        }
                     }
                  }

                  Object resultFuturex = result;
                  return resultFuturex;
               } catch (IllegalArgumentException | UndeclaredThrowableException | IllegalAccessException ex) {
                  throw new ProcessingException(LocalizationMessages.ERROR_RESOURCE_JAVA_METHOD_INVOCATION(), ex);
               } catch (InvocationTargetException ex) {
                  throw AbstractJavaResourceMethodDispatcher.mapTargetToRuntimeEx(ex.getCause());
               } catch (Throwable t) {
                  throw new ProcessingException(t);
               } finally {
                  tracingLogger.logDuration(ServerTraceEvent.METHOD_INVOKE, timestamp, new Object[]{resource, AbstractJavaResourceMethodDispatcher.this.method});
               }
            }
         };
         SecurityContext securityContext = containerRequest.getSecurityContext();
         Object invocationResult = securityContext instanceof SubjectSecurityContext ? ((SubjectSecurityContext)securityContext).doAsSubject(invokeMethodAction) : invokeMethodAction.run();
         if (this.validator != null) {
            this.validator.validateResult(resource, this.resourceMethod, invocationResult);
         }

         return invocationResult;
      } catch (ValidationException ex) {
         throw new MappableException(ex);
      }
   }

   private static RuntimeException mapTargetToRuntimeEx(Throwable throwable) {
      return (RuntimeException)(throwable instanceof WebApplicationException ? (WebApplicationException)throwable : new MappableException(throwable));
   }

   public String toString() {
      return this.method.toString();
   }
}
