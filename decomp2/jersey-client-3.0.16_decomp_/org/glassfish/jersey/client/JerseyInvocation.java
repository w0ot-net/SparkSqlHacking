package org.glassfish.jersey.client;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.RedirectionException;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.ServiceUnavailableException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.CompletionStageRxInvoker;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.client.ResponseProcessingException;
import jakarta.ws.rs.client.RxInvoker;
import jakarta.ws.rs.client.RxInvokerProvider;
import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.Status.Family;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.BiFunction;
import java.util.logging.Logger;
import org.glassfish.jersey.client.internal.ClientResponseProcessingException;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.MapPropertiesDelegate;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.inject.ServiceHolder;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.process.internal.RequestScope;
import org.glassfish.jersey.spi.ExecutorServiceProvider;

public class JerseyInvocation implements Invocation {
   private static final Logger LOGGER = Logger.getLogger(JerseyInvocation.class.getName());
   private final ClientRequest requestContext;
   private final boolean copyRequestContext;
   private boolean ignoreResponseException;
   private static final Map METHODS = initializeMap();

   private JerseyInvocation(Builder builder) {
      this(builder, false);
   }

   private JerseyInvocation(Builder builder, boolean copyRequestContext) {
      this.validateHttpMethodAndEntity(builder.requestContext);
      this.requestContext = new ClientRequest(builder.requestContext);
      this.copyRequestContext = copyRequestContext;
      Object value = builder.requestContext.getConfiguration().getProperty("jersey.config.client.ignoreExceptionResponse");
      if (value != null) {
         Boolean booleanValue = (Boolean)PropertiesHelper.convertValue(value, Boolean.class);
         if (booleanValue != null) {
            this.ignoreResponseException = booleanValue;
         }
      }

   }

   private static Map initializeMap() {
      Map<String, EntityPresence> map = new HashMap();
      map.put("DELETE", JerseyInvocation.EntityPresence.MUST_BE_NULL);
      map.put("GET", JerseyInvocation.EntityPresence.MUST_BE_NULL);
      map.put("HEAD", JerseyInvocation.EntityPresence.MUST_BE_NULL);
      map.put("OPTIONS", JerseyInvocation.EntityPresence.OPTIONAL);
      map.put("PATCH", JerseyInvocation.EntityPresence.MUST_BE_PRESENT);
      map.put("POST", JerseyInvocation.EntityPresence.OPTIONAL);
      map.put("PUT", JerseyInvocation.EntityPresence.MUST_BE_PRESENT);
      map.put("TRACE", JerseyInvocation.EntityPresence.MUST_BE_NULL);
      return map;
   }

   private void validateHttpMethodAndEntity(ClientRequest request) {
      boolean suppressExceptions = PropertiesHelper.isProperty(request.getConfiguration().getProperty("jersey.config.client.suppressHttpComplianceValidation"));
      Object shcvProperty = request.getProperty("jersey.config.client.suppressHttpComplianceValidation");
      if (shcvProperty != null) {
         suppressExceptions = PropertiesHelper.isProperty(shcvProperty);
      }

      String method = request.getMethod();
      EntityPresence entityPresence = (EntityPresence)METHODS.get(method.toUpperCase(Locale.ROOT));
      if (entityPresence == JerseyInvocation.EntityPresence.MUST_BE_NULL && request.hasEntity()) {
         if (!suppressExceptions) {
            throw new IllegalStateException(LocalizationMessages.ERROR_HTTP_METHOD_ENTITY_NOT_NULL(method));
         }

         LOGGER.warning(LocalizationMessages.ERROR_HTTP_METHOD_ENTITY_NOT_NULL(method));
      } else if (entityPresence == JerseyInvocation.EntityPresence.MUST_BE_PRESENT && !request.hasEntity()) {
         if (!suppressExceptions) {
            throw new IllegalStateException(LocalizationMessages.ERROR_HTTP_METHOD_ENTITY_NULL(method));
         }

         LOGGER.warning(LocalizationMessages.ERROR_HTTP_METHOD_ENTITY_NULL(method));
      }

   }

   private ClientRequest requestForCall(ClientRequest requestContext) {
      return this.copyRequestContext ? new ClientRequest(requestContext) : requestContext;
   }

   public Response invoke() throws ProcessingException, WebApplicationException {
      ClientRuntime runtime = this.request().getClientRuntime();
      RequestScope requestScope = runtime.getRequestScope();
      return (Response)this.runInScope(() -> new InboundJaxrsResponse(runtime.invoke(this.requestForCall(this.requestContext)), requestScope), requestScope);
   }

   public Object invoke(Class responseType) throws ProcessingException, WebApplicationException {
      if (responseType == null) {
         throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
      } else {
         ClientRuntime runtime = this.request().getClientRuntime();
         RequestScope requestScope = runtime.getRequestScope();
         return this.runInScope(() -> this.translate(runtime.invoke(this.requestForCall(this.requestContext)), requestScope, responseType), requestScope);
      }
   }

   public Object invoke(GenericType responseType) throws ProcessingException, WebApplicationException {
      if (responseType == null) {
         throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
      } else {
         ClientRuntime runtime = this.request().getClientRuntime();
         RequestScope requestScope = runtime.getRequestScope();
         return this.runInScope(() -> this.translate(runtime.invoke(this.requestForCall(this.requestContext)), requestScope, responseType), requestScope);
      }
   }

   private Object runInScope(Producer producer, RequestScope scope) throws ProcessingException, WebApplicationException {
      return scope.runInScope(() -> this.call(producer, scope));
   }

   private Object call(Producer producer, RequestScope scope) throws ProcessingException, WebApplicationException {
      try {
         return producer.call();
      } catch (ClientResponseProcessingException crpe) {
         throw new ResponseProcessingException((Response)this.translate(crpe.getClientResponse(), scope, Response.class), crpe.getCause());
      } catch (ProcessingException ex) {
         if (WebApplicationException.class.isInstance(ex.getCause())) {
            throw (WebApplicationException)ex.getCause();
         } else {
            throw ex;
         }
      }
   }

   public Future submit() {
      CompletableFuture<Response> responseFuture = new CompletableFuture();
      ClientRuntime runtime = this.request().getClientRuntime();
      this.requestContext.setCancellable(responseFuture);
      runtime.submit(runtime.createRunnableForAsyncProcessing(this.requestForCall(this.requestContext), new InvocationResponseCallback(responseFuture, (request, scope) -> (Response)this.translate(request, scope, Response.class))));
      return responseFuture;
   }

   public Future submit(Class responseType) {
      if (responseType == null) {
         throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
      } else {
         CompletableFuture<T> responseFuture = new CompletableFuture();
         ClientRuntime runtime = this.request().getClientRuntime();
         this.requestContext.setCancellable(responseFuture);
         runtime.submit(runtime.createRunnableForAsyncProcessing(this.requestForCall(this.requestContext), new InvocationResponseCallback(responseFuture, (request, scope) -> this.translate(request, scope, responseType))));
         return responseFuture;
      }
   }

   private Object translate(ClientResponse response, RequestScope scope, Class responseType) throws ProcessingException {
      if (responseType == Response.class) {
         return responseType.cast(new InboundJaxrsResponse(response, scope));
      } else if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
         try {
            return response.readEntity(responseType);
         } catch (ProcessingException ex) {
            if (ex.getClass() == ProcessingException.class) {
               throw new ResponseProcessingException(new InboundJaxrsResponse(response, scope), ex.getCause());
            } else {
               throw new ResponseProcessingException(new InboundJaxrsResponse(response, scope), ex);
            }
         } catch (WebApplicationException ex) {
            throw new ResponseProcessingException(new InboundJaxrsResponse(response, scope), ex);
         } catch (Exception ex) {
            throw new ResponseProcessingException(new InboundJaxrsResponse(response, scope), LocalizationMessages.UNEXPECTED_ERROR_RESPONSE_PROCESSING(), ex);
         }
      } else {
         throw this.convertToException(new InboundJaxrsResponse(response, scope));
      }
   }

   public Future submit(GenericType responseType) {
      if (responseType == null) {
         throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
      } else {
         CompletableFuture<T> responseFuture = new CompletableFuture();
         ClientRuntime runtime = this.request().getClientRuntime();
         this.requestContext.setCancellable(responseFuture);
         runtime.submit(runtime.createRunnableForAsyncProcessing(this.requestForCall(this.requestContext), new InvocationResponseCallback(responseFuture, (request, scope) -> this.translate(request, scope, responseType))));
         return responseFuture;
      }
   }

   private Object translate(ClientResponse response, RequestScope scope, GenericType responseType) throws ProcessingException {
      if (responseType.getRawType() == Response.class) {
         return new InboundJaxrsResponse(response, scope);
      } else if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
         try {
            return response.readEntity(responseType);
         } catch (ProcessingException ex) {
            throw new ResponseProcessingException(new InboundJaxrsResponse(response, scope), (Throwable)(ex.getCause() != null ? ex.getCause() : ex));
         } catch (WebApplicationException ex) {
            throw new ResponseProcessingException(new InboundJaxrsResponse(response, scope), ex);
         } catch (Exception ex) {
            throw new ResponseProcessingException(new InboundJaxrsResponse(response, scope), LocalizationMessages.UNEXPECTED_ERROR_RESPONSE_PROCESSING(), ex);
         }
      } else {
         throw this.convertToException(new InboundJaxrsResponse(response, scope));
      }
   }

   public Future submit(InvocationCallback callback) {
      return this.submit((GenericType)null, callback);
   }

   public Future submit(GenericType responseType, final InvocationCallback callback) {
      final CompletableFuture<T> responseFuture = new CompletableFuture();

      try {
         ReflectionHelper.DeclaringClassInterfacePair pair = ReflectionHelper.getClass(callback.getClass(), InvocationCallback.class);
         final Class<T> callbackParamClass;
         final Type callbackParamType;
         if (responseType != null) {
            callbackParamType = responseType.getType();
            callbackParamClass = ReflectionHelper.erasure(responseType.getRawType());
         } else {
            Type[] typeArguments = ReflectionHelper.getParameterizedTypeArguments(pair);
            if (typeArguments != null && typeArguments.length != 0) {
               callbackParamType = typeArguments[0];
            } else {
               callbackParamType = Object.class;
            }

            callbackParamClass = ReflectionHelper.erasure(callbackParamType);
         }

         ResponseCallback responseCallback = new ResponseCallback() {
            public void completed(ClientResponse response, RequestScope scope) {
               if (responseFuture.isCancelled()) {
                  response.close();
                  this.failed(new ProcessingException(new CancellationException(LocalizationMessages.ERROR_REQUEST_CANCELLED())));
               } else {
                  if (callbackParamClass == Response.class) {
                     T result = (T)callbackParamClass.cast(new InboundJaxrsResponse(response, scope));
                     responseFuture.complete(result);
                     callback.completed(result);
                  } else if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
                     T result = (T)response.readEntity(new GenericType(callbackParamType));
                     responseFuture.complete(result);
                     callback.completed(result);
                  } else {
                     this.failed(JerseyInvocation.this.convertToException(new InboundJaxrsResponse(response, scope)));
                  }

               }
            }

            public void failed(ProcessingException error) {
               Exception called = null;

               try {
                  if (error.getCause() instanceof WebApplicationException) {
                     responseFuture.completeExceptionally(error.getCause());
                  } else if (!responseFuture.isCancelled()) {
                     try {
                        JerseyInvocation.this.call(() -> {
                           throw error;
                        }, (RequestScope)null);
                     } catch (Exception ex) {
                        called = ex;
                        responseFuture.completeExceptionally(ex);
                     }
                  }
               } finally {
                  callback.failed((Throwable)(error.getCause() instanceof CancellationException ? error.getCause() : (called != null ? called : error)));
               }

            }
         };
         ClientRuntime runtime = this.request().getClientRuntime();
         this.requestContext.setCancellable(responseFuture);
         runtime.submit(runtime.createRunnableForAsyncProcessing(this.requestForCall(this.requestContext), responseCallback));
      } catch (Throwable error) {
         ProcessingException ce;
         if (error instanceof ClientResponseProcessingException) {
            ce = new ProcessingException(error.getCause());
            responseFuture.completeExceptionally(ce);
         } else if (error instanceof ProcessingException) {
            ce = (ProcessingException)error;
            responseFuture.completeExceptionally(ce);
         } else if (error instanceof WebApplicationException) {
            ce = new ProcessingException(error);
            responseFuture.completeExceptionally(error);
         } else {
            ce = new ProcessingException(error);
            responseFuture.completeExceptionally(ce);
         }

         callback.failed(ce);
      }

      return responseFuture;
   }

   public JerseyInvocation property(String name, Object value) {
      this.requestContext.setProperty(name, value);
      return this;
   }

   private ProcessingException convertToException(Response response) {
      int statusCode = response.getStatus();
      Response finalResponse = this.ignoreResponseException ? Response.status(statusCode).build() : response;

      try {
         response.bufferEntity();
         Response.Status status = Status.fromStatusCode(statusCode);
         WebApplicationException webAppException;
         if (status == null) {
            Response.Status.Family statusFamily = finalResponse.getStatusInfo().getFamily();
            webAppException = this.createExceptionForFamily(finalResponse, statusFamily);
         } else {
            switch (status) {
               case BAD_REQUEST:
                  webAppException = new BadRequestException(finalResponse);
                  break;
               case UNAUTHORIZED:
                  webAppException = new NotAuthorizedException(finalResponse);
                  break;
               case FORBIDDEN:
                  webAppException = new ForbiddenException(finalResponse);
                  break;
               case NOT_FOUND:
                  webAppException = new NotFoundException(finalResponse);
                  break;
               case METHOD_NOT_ALLOWED:
                  webAppException = new NotAllowedException(finalResponse);
                  break;
               case NOT_ACCEPTABLE:
                  webAppException = new NotAcceptableException(finalResponse);
                  break;
               case UNSUPPORTED_MEDIA_TYPE:
                  webAppException = new NotSupportedException(finalResponse);
                  break;
               case INTERNAL_SERVER_ERROR:
                  webAppException = new InternalServerErrorException(finalResponse);
                  break;
               case SERVICE_UNAVAILABLE:
                  webAppException = new ServiceUnavailableException(finalResponse);
                  break;
               default:
                  Response.Status.Family statusFamily = finalResponse.getStatusInfo().getFamily();
                  webAppException = this.createExceptionForFamily(finalResponse, statusFamily);
            }
         }

         return new ResponseProcessingException(finalResponse, webAppException);
      } catch (Throwable t) {
         return new ResponseProcessingException(finalResponse, LocalizationMessages.RESPONSE_TO_EXCEPTION_CONVERSION_FAILED(), t);
      }
   }

   private WebApplicationException createExceptionForFamily(Response response, Response.Status.Family statusFamily) {
      WebApplicationException webAppException;
      switch (statusFamily) {
         case REDIRECTION:
            webAppException = new RedirectionException(response);
            break;
         case CLIENT_ERROR:
            webAppException = new ClientErrorException(response);
            break;
         case SERVER_ERROR:
            webAppException = new ServerErrorException(response);
            break;
         default:
            webAppException = new WebApplicationException(response);
      }

      return webAppException;
   }

   ClientRequest request() {
      return this.requestContext;
   }

   public String toString() {
      return "JerseyInvocation [" + this.request().getMethod() + ' ' + this.request().getUri() + "]";
   }

   private static enum EntityPresence {
      MUST_BE_NULL,
      MUST_BE_PRESENT,
      OPTIONAL;
   }

   public static class Builder implements Invocation.Builder {
      private final ClientRequest requestContext;

      protected Builder(URI uri, ClientConfig configuration) {
         this.requestContext = new ClientRequest(uri, configuration, new MapPropertiesDelegate());
      }

      ClientRequest request() {
         return this.requestContext;
      }

      private void storeEntity(Entity entity) {
         if (entity != null) {
            this.requestContext.variant(entity.getVariant());
            this.requestContext.setEntity(entity.getEntity());
            this.requestContext.setEntityAnnotations(entity.getAnnotations());
         }

      }

      public JerseyInvocation build(String method) {
         this.requestContext.setMethod(method);
         return new JerseyInvocation(this, true);
      }

      public JerseyInvocation build(String method, Entity entity) {
         this.requestContext.setMethod(method);
         this.storeEntity(entity);
         return new JerseyInvocation(this, true);
      }

      public JerseyInvocation buildGet() {
         this.requestContext.setMethod("GET");
         return new JerseyInvocation(this, true);
      }

      public JerseyInvocation buildDelete() {
         this.requestContext.setMethod("DELETE");
         return new JerseyInvocation(this, true);
      }

      public JerseyInvocation buildPost(Entity entity) {
         this.requestContext.setMethod("POST");
         this.storeEntity(entity);
         return new JerseyInvocation(this, true);
      }

      public JerseyInvocation buildPut(Entity entity) {
         this.requestContext.setMethod("PUT");
         this.storeEntity(entity);
         return new JerseyInvocation(this, true);
      }

      public jakarta.ws.rs.client.AsyncInvoker async() {
         return new AsyncInvoker(this);
      }

      public Builder accept(String... mediaTypes) {
         this.requestContext.accept(mediaTypes);
         return this;
      }

      public Builder accept(MediaType... mediaTypes) {
         this.requestContext.accept(mediaTypes);
         return this;
      }

      public Invocation.Builder acceptEncoding(String... encodings) {
         this.requestContext.getHeaders().addAll("Accept-Encoding", (Object[])encodings);
         return this;
      }

      public Builder acceptLanguage(Locale... locales) {
         this.requestContext.acceptLanguage(locales);
         return this;
      }

      public Builder acceptLanguage(String... locales) {
         this.requestContext.acceptLanguage(locales);
         return this;
      }

      public Builder cookie(Cookie cookie) {
         this.requestContext.cookie(cookie);
         return this;
      }

      public Builder cookie(String name, String value) {
         this.requestContext.cookie(new Cookie(name, value));
         return this;
      }

      public Builder cacheControl(CacheControl cacheControl) {
         this.requestContext.cacheControl(cacheControl);
         return this;
      }

      public Builder header(String name, Object value) {
         MultivaluedMap<String, Object> headers = this.requestContext.getHeaders();
         if (value == null) {
            headers.remove(name);
         } else {
            headers.add(name, value);
         }

         if ("User-Agent".equalsIgnoreCase(name)) {
            this.requestContext.ignoreUserAgent(value == null);
         }

         return this;
      }

      public Builder headers(MultivaluedMap headers) {
         this.requestContext.replaceHeaders(headers);
         return this;
      }

      public Response get() throws ProcessingException {
         return this.method("GET");
      }

      public Object get(Class responseType) throws ProcessingException, WebApplicationException {
         return this.method("GET", responseType);
      }

      public Object get(GenericType responseType) throws ProcessingException, WebApplicationException {
         return this.method("GET", responseType);
      }

      public Response put(Entity entity) throws ProcessingException {
         return this.method("PUT", entity);
      }

      public Object put(Entity entity, Class responseType) throws ProcessingException, WebApplicationException {
         return this.method("PUT", entity, responseType);
      }

      public Object put(Entity entity, GenericType responseType) throws ProcessingException, WebApplicationException {
         return this.method("PUT", entity, responseType);
      }

      public Response post(Entity entity) throws ProcessingException {
         return this.method("POST", entity);
      }

      public Object post(Entity entity, Class responseType) throws ProcessingException, WebApplicationException {
         return this.method("POST", entity, responseType);
      }

      public Object post(Entity entity, GenericType responseType) throws ProcessingException, WebApplicationException {
         return this.method("POST", entity, responseType);
      }

      public Response delete() throws ProcessingException {
         return this.method("DELETE");
      }

      public Object delete(Class responseType) throws ProcessingException, WebApplicationException {
         return this.method("DELETE", responseType);
      }

      public Object delete(GenericType responseType) throws ProcessingException, WebApplicationException {
         return this.method("DELETE", responseType);
      }

      public Response head() throws ProcessingException {
         return this.method("HEAD");
      }

      public Response options() throws ProcessingException {
         return this.method("OPTIONS");
      }

      public Object options(Class responseType) throws ProcessingException, WebApplicationException {
         return this.method("OPTIONS", responseType);
      }

      public Object options(GenericType responseType) throws ProcessingException, WebApplicationException {
         return this.method("OPTIONS", responseType);
      }

      public Response trace() throws ProcessingException {
         return this.method("TRACE");
      }

      public Object trace(Class responseType) throws ProcessingException, WebApplicationException {
         return this.method("TRACE", responseType);
      }

      public Object trace(GenericType responseType) throws ProcessingException, WebApplicationException {
         return this.method("TRACE", responseType);
      }

      public Response method(String name) throws ProcessingException {
         this.requestContext.setMethod(name);
         return (new JerseyInvocation(this)).invoke();
      }

      public Object method(String name, Class responseType) throws ProcessingException, WebApplicationException {
         if (responseType == null) {
            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
         } else {
            this.requestContext.setMethod(name);
            return (new JerseyInvocation(this)).invoke(responseType);
         }
      }

      public Object method(String name, GenericType responseType) throws ProcessingException, WebApplicationException {
         if (responseType == null) {
            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
         } else {
            this.requestContext.setMethod(name);
            return (new JerseyInvocation(this)).invoke(responseType);
         }
      }

      public Response method(String name, Entity entity) throws ProcessingException {
         this.requestContext.setMethod(name);
         this.storeEntity(entity);
         return (new JerseyInvocation(this)).invoke();
      }

      public Object method(String name, Entity entity, Class responseType) throws ProcessingException, WebApplicationException {
         if (responseType == null) {
            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
         } else {
            this.requestContext.setMethod(name);
            this.storeEntity(entity);
            return (new JerseyInvocation(this)).invoke(responseType);
         }
      }

      public Object method(String name, Entity entity, GenericType responseType) throws ProcessingException, WebApplicationException {
         if (responseType == null) {
            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
         } else {
            this.requestContext.setMethod(name);
            this.storeEntity(entity);
            return (new JerseyInvocation(this)).invoke(responseType);
         }
      }

      public Builder property(String name, Object value) {
         this.requestContext.setProperty(name, value);
         return this;
      }

      public CompletionStageRxInvoker rx() {
         return (CompletionStageRxInvoker)this.rx(JerseyCompletionStageRxInvoker.class);
      }

      public RxInvoker rx(Class clazz) {
         if (clazz == JerseyCompletionStageRxInvoker.class) {
            ExecutorService configured = this.request().getClientConfig().getExecutorService();
            if (configured == null) {
               ExecutorService provided = this.executorService();
               if (provided != null) {
                  ((ClientConfig)this.request().getConfiguration()).executorService(provided);
               }
            }

            return new JerseyCompletionStageRxInvoker(this);
         } else {
            return this.createRxInvoker(clazz, this.executorService());
         }
      }

      private RxInvoker rx(Class clazz, ExecutorService executorService) {
         if (executorService == null) {
            throw new IllegalArgumentException(LocalizationMessages.NULL_INPUT_PARAMETER("executorService"));
         } else {
            return this.createRxInvoker(clazz, executorService);
         }
      }

      private ExecutorService executorService() {
         ExecutorService result = this.request().getClientConfig().getExecutorService();
         if (result != null) {
            return result;
         } else {
            List<ServiceHolder<ExecutorServiceProvider>> serviceHolders = this.requestContext.getInjectionManager().getAllServiceHolders(ExecutorServiceProvider.class, new Annotation[0]);
            BestServiceHolder best = (BestServiceHolder)serviceHolders.stream().map((x$0) -> new BestServiceHolder(x$0)).sorted((a, b) -> a.isBetterThen(b) ? -1 : 1).findFirst().get();
            return best.provider.getExecutorService();
         }
      }

      private RxInvoker createRxInvoker(Class clazz, ExecutorService executorService) {
         if (clazz == null) {
            throw new IllegalArgumentException(LocalizationMessages.NULL_INPUT_PARAMETER("clazz"));
         } else {
            for(RxInvokerProvider invokerProvider : Providers.getAllProviders(this.requestContext.getInjectionManager(), RxInvokerProvider.class)) {
               if (invokerProvider.isProviderFor(clazz)) {
                  RxInvoker rxInvoker = invokerProvider.getRxInvoker(this, executorService);
                  if (rxInvoker == null) {
                     throw new IllegalStateException(LocalizationMessages.CLIENT_RX_PROVIDER_NULL());
                  }

                  return rxInvoker;
               }
            }

            throw new IllegalStateException(LocalizationMessages.CLIENT_RX_PROVIDER_NOT_REGISTERED(clazz.getSimpleName()));
         }
      }

      public Builder setCancellable(Future cancellable) {
         this.requestContext.setCancellable(cancellable);
         return this;
      }

      private static final class BestServiceHolder {
         private final ExecutorServiceProvider provider;
         private final int value;

         private BestServiceHolder(ServiceHolder holder) {
            this.provider = (ExecutorServiceProvider)holder.getInstance();
            boolean isDefault = DefaultClientAsyncExecutorProvider.class.equals(holder.getImplementationClass()) || ClientExecutorProvidersConfigurator.ClientExecutorServiceProvider.class.equals(holder.getImplementationClass());
            boolean isAsync = holder.getImplementationClass().getAnnotation(ClientAsyncExecutor.class) != null;
            this.value = 10 * (isDefault ? 0 : 1) + (isAsync ? 1 : 0);
         }

         public boolean isBetterThen(BestServiceHolder other) {
            return this.value > other.value;
         }
      }
   }

   static class AsyncInvoker extends CompletableFutureAsyncInvoker implements jakarta.ws.rs.client.AsyncInvoker {
      private final Builder builder;

      AsyncInvoker(Builder request) {
         this.builder = request;
         this.builder.requestContext.setAsynchronous(true);
      }

      public CompletableFuture method(String name) {
         this.builder.requestContext.setMethod(name);
         return (CompletableFuture)(new JerseyInvocation(this.builder)).submit();
      }

      public CompletableFuture method(String name, Class responseType) {
         if (responseType == null) {
            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
         } else {
            this.builder.requestContext.setMethod(name);
            return (CompletableFuture)(new JerseyInvocation(this.builder)).submit(responseType);
         }
      }

      public CompletableFuture method(String name, GenericType responseType) {
         if (responseType == null) {
            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
         } else {
            this.builder.requestContext.setMethod(name);
            return (CompletableFuture)(new JerseyInvocation(this.builder)).submit(responseType);
         }
      }

      public CompletableFuture method(String name, InvocationCallback callback) {
         this.builder.requestContext.setMethod(name);
         return (CompletableFuture)(new JerseyInvocation(this.builder)).submit(callback);
      }

      public CompletableFuture method(String name, Entity entity) {
         this.builder.requestContext.setMethod(name);
         this.builder.storeEntity(entity);
         return (CompletableFuture)(new JerseyInvocation(this.builder)).submit();
      }

      public CompletableFuture method(String name, Entity entity, Class responseType) {
         if (responseType == null) {
            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
         } else {
            this.builder.requestContext.setMethod(name);
            this.builder.storeEntity(entity);
            return (CompletableFuture)(new JerseyInvocation(this.builder)).submit(responseType);
         }
      }

      public CompletableFuture method(String name, Entity entity, GenericType responseType) {
         if (responseType == null) {
            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
         } else {
            this.builder.requestContext.setMethod(name);
            this.builder.storeEntity(entity);
            return (CompletableFuture)(new JerseyInvocation(this.builder)).submit(responseType);
         }
      }

      public CompletableFuture method(String name, Entity entity, InvocationCallback callback) {
         this.builder.requestContext.setMethod(name);
         this.builder.storeEntity(entity);
         return (CompletableFuture)(new JerseyInvocation(this.builder)).submit(callback);
      }
   }

   private class InvocationResponseCallback implements ResponseCallback {
      private final CompletableFuture responseFuture;
      private final BiFunction producer;

      private InvocationResponseCallback(CompletableFuture responseFuture, BiFunction producer) {
         this.responseFuture = responseFuture;
         this.producer = producer;
      }

      public void completed(ClientResponse response, RequestScope scope) {
         if (this.responseFuture.isCancelled()) {
            response.close();
         } else {
            try {
               this.responseFuture.complete(this.producer.apply(response, scope));
            } catch (ProcessingException ex) {
               this.failed(ex);
            }

         }
      }

      public void failed(ProcessingException error) {
         if (!this.responseFuture.isCancelled()) {
            try {
               JerseyInvocation.this.call(() -> {
                  throw error;
               }, (RequestScope)null);
            } catch (Exception exception) {
               this.responseFuture.completeExceptionally(exception);
            }

         }
      }
   }
}
