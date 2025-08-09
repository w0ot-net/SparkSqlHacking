package org.glassfish.jersey.server;

import jakarta.inject.Provider;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.ServiceUnavailableException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.CompletionCallback;
import jakarta.ws.rs.container.ConnectionCallback;
import jakarta.ws.rs.container.TimeoutHandler;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.guava.Preconditions;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.Closure;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.internal.util.collection.Refs;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.message.internal.HeaderValueException;
import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;
import org.glassfish.jersey.message.internal.OutboundMessageContext;
import org.glassfish.jersey.message.internal.TracingLogger;
import org.glassfish.jersey.message.internal.HeaderValueException.Context;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse.Builder;
import org.glassfish.jersey.process.internal.RequestContext;
import org.glassfish.jersey.process.internal.RequestScope;
import org.glassfish.jersey.process.internal.Stage;
import org.glassfish.jersey.process.internal.Stages;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.internal.ProcessingProviders;
import org.glassfish.jersey.server.internal.ServerTraceEvent;
import org.glassfish.jersey.server.internal.monitoring.EmptyRequestEventBuilder;
import org.glassfish.jersey.server.internal.monitoring.RequestEventBuilder;
import org.glassfish.jersey.server.internal.monitoring.RequestEventImpl;
import org.glassfish.jersey.server.internal.process.Endpoint;
import org.glassfish.jersey.server.internal.process.MappableException;
import org.glassfish.jersey.server.internal.process.RequestProcessingContext;
import org.glassfish.jersey.server.internal.routing.UriRoutingContext;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.glassfish.jersey.server.spi.ContainerResponseWriter;
import org.glassfish.jersey.server.spi.ExternalRequestContext;
import org.glassfish.jersey.server.spi.ExternalRequestScope;
import org.glassfish.jersey.server.spi.ResponseErrorMapper;
import org.glassfish.jersey.spi.ExceptionMappers;

public class ServerRuntime {
   private final Stage requestProcessingRoot;
   private final ProcessingProviders processingProviders;
   private final InjectionManager injectionManager;
   private final ScheduledExecutorService backgroundScheduler;
   private final Provider managedAsyncExecutor;
   private final RequestScope requestScope;
   private final ExceptionMappers exceptionMappers;
   private final ApplicationEventListener applicationEventListener;
   private final Configuration configuration;
   private final ExternalRequestScope externalRequestScope;
   private final TracingConfig tracingConfig;
   private final TracingLogger.Level tracingThreshold;
   private final boolean processResponseErrors;
   private final boolean disableLocationHeaderRelativeUriResolution;
   private final boolean rfc7231LocationHeaderRelativeUriResolution;

   static ServerRuntime createServerRuntime(InjectionManager injectionManager, ServerBootstrapBag bootstrapBag, Stage processingRoot, ApplicationEventListener eventListener, ProcessingProviders processingProviders) {
      ScheduledExecutorService scheduledExecutorServiceSupplier = (ScheduledExecutorService)injectionManager.getInstance(ScheduledExecutorService.class, new Annotation[]{BackgroundSchedulerLiteral.INSTANCE});
      Provider<ExecutorService> asyncExecutorServiceSupplier = () -> (ExecutorService)injectionManager.getInstance(ExecutorService.class, new Annotation[]{ManagedAsyncExecutorLiteral.INSTANCE});
      return new ServerRuntime(processingRoot, processingProviders, injectionManager, scheduledExecutorServiceSupplier, asyncExecutorServiceSupplier, bootstrapBag.getRequestScope(), bootstrapBag.getExceptionMappers(), eventListener, (ExternalRequestScope)injectionManager.getInstance(ExternalRequestScope.class), bootstrapBag.getConfiguration());
   }

   private ServerRuntime(Stage requestProcessingRoot, ProcessingProviders processingProviders, InjectionManager injectionManager, ScheduledExecutorService backgroundScheduler, Provider managedAsyncExecutorProvider, RequestScope requestScope, ExceptionMappers exceptionMappers, ApplicationEventListener applicationEventListener, ExternalRequestScope externalScope, Configuration configuration) {
      this.requestProcessingRoot = requestProcessingRoot;
      this.processingProviders = processingProviders;
      this.injectionManager = injectionManager;
      this.backgroundScheduler = backgroundScheduler;
      this.managedAsyncExecutor = managedAsyncExecutorProvider;
      this.requestScope = requestScope;
      this.exceptionMappers = exceptionMappers;
      this.applicationEventListener = applicationEventListener;
      this.externalRequestScope = externalScope;
      this.configuration = configuration;
      this.tracingConfig = TracingUtils.getTracingConfig(configuration);
      this.tracingThreshold = TracingUtils.getTracingThreshold(configuration);
      this.processResponseErrors = PropertiesHelper.isProperty(configuration.getProperty("jersey.config.server.exception.processResponseErrors"));
      this.disableLocationHeaderRelativeUriResolution = (Boolean)ServerProperties.getValue(configuration.getProperties(), "jersey.config.server.headers.location.relative.resolution.disabled", Boolean.FALSE, Boolean.class);
      this.rfc7231LocationHeaderRelativeUriResolution = (Boolean)ServerProperties.getValue(configuration.getProperties(), "jersey.config.server.headers.location.relative.resolution.rfc7231", Boolean.FALSE, Boolean.class);
   }

   public void process(final ContainerRequest request) {
      TracingUtils.initTracingSupport(this.tracingConfig, this.tracingThreshold, request);
      TracingUtils.logStart(request);
      UriRoutingContext routingContext = request.getUriRoutingContext();
      RequestEventBuilder monitoringEventBuilder = EmptyRequestEventBuilder.INSTANCE;
      RequestEventListener monitoringEventListener = null;
      if (this.applicationEventListener != null) {
         monitoringEventBuilder = (new RequestEventImpl.Builder()).setContainerRequest(request).setExtendedUriInfo(routingContext);
         monitoringEventListener = this.applicationEventListener.onRequest(monitoringEventBuilder.build(RequestEvent.Type.START));
      }

      request.setProcessingProviders(this.processingProviders);
      final RequestProcessingContext context = new RequestProcessingContext(this.injectionManager, request, routingContext, monitoringEventBuilder, monitoringEventListener);
      request.checkState();
      final Responder responder = new Responder(context, this);
      RequestContext requestScopeInstance = this.requestScope.createContext();
      final AsyncResponderHolder asyncResponderHolder = new AsyncResponderHolder(responder, this.externalRequestScope, requestScopeInstance, this.externalRequestScope.open(this.injectionManager));
      context.initAsyncContext(asyncResponderHolder);

      try {
         this.requestScope.runInScope(requestScopeInstance, new Runnable() {
            public void run() {
               try {
                  if (!ServerRuntime.this.disableLocationHeaderRelativeUriResolution) {
                     URI uriToUse = ServerRuntime.this.rfc7231LocationHeaderRelativeUriResolution ? request.getRequestUri() : request.getBaseUri();
                     Builder.setBaseUri(uriToUse);
                  }

                  Ref<Endpoint> endpointRef = Refs.emptyRef();
                  RequestProcessingContext data = (RequestProcessingContext)Stages.process(context, ServerRuntime.this.requestProcessingRoot, endpointRef);
                  Endpoint endpoint = (Endpoint)endpointRef.get();
                  if (endpoint == null) {
                     throw new NotFoundException();
                  }

                  ContainerResponse response = (ContainerResponse)endpoint.apply(data);
                  if (!asyncResponderHolder.isAsync()) {
                     responder.process(response);
                  } else {
                     ServerRuntime.this.externalRequestScope.suspend(asyncResponderHolder.externalContext, ServerRuntime.this.injectionManager);
                  }
               } catch (Throwable throwable) {
                  responder.process(throwable);
               } finally {
                  asyncResponderHolder.release();
                  Builder.clearBaseUri();
               }

            }
         });
      } catch (RuntimeException illegalStateException) {
         if (!IllegalStateException.class.isInstance(illegalStateException.getCause()) || !this.injectionManager.isShutdown()) {
            throw illegalStateException;
         }
      }

   }

   ScheduledExecutorService getBackgroundScheduler() {
      return this.backgroundScheduler;
   }

   private static void ensureAbsolute(URI location, MultivaluedMap headers, ContainerRequest request, boolean incompatible) {
      if (location != null && !location.isAbsolute()) {
         URI uri = incompatible ? request.getRequestUri() : request.getBaseUri();
         headers.putSingle("Location", uri.resolve(location));
      }
   }

   private static class AsyncResponderHolder implements Value {
      private final Responder responder;
      private final ExternalRequestScope externalScope;
      private final RequestContext requestContext;
      private final ExternalRequestContext externalContext;
      private volatile AsyncResponder asyncResponder;

      private AsyncResponderHolder(Responder responder, ExternalRequestScope externalRequestScope, RequestContext requestContext, ExternalRequestContext externalContext) {
         this.responder = responder;
         this.externalScope = externalRequestScope;
         this.requestContext = requestContext;
         this.externalContext = externalContext;
      }

      public AsyncContext get() {
         AsyncResponder ar = new AsyncResponder(this.responder, this.requestContext, this.externalScope, this.externalContext);
         this.asyncResponder = ar;
         return ar;
      }

      public boolean isAsync() {
         AsyncResponder ar = this.asyncResponder;
         return ar != null && !ar.isRunning();
      }

      public void release() {
         if (this.asyncResponder == null) {
            this.requestContext.release();
         }

      }
   }

   private static class Responder {
      private static final Logger LOGGER = Logger.getLogger(Responder.class.getName());
      private final RequestProcessingContext processingContext;
      private final ServerRuntime runtime;
      private final CompletionCallbackRunner completionCallbackRunner = new CompletionCallbackRunner();
      private final ConnectionCallbackRunner connectionCallbackRunner = new ConnectionCallbackRunner();
      private final TracingLogger tracingLogger;

      public Responder(RequestProcessingContext processingContext, ServerRuntime runtime) {
         this.processingContext = processingContext;
         this.runtime = runtime;
         this.tracingLogger = TracingLogger.getInstance(processingContext.request());
      }

      public void process(ContainerResponse response) {
         this.processingContext.monitoringEventBuilder().setContainerResponse(response);
         response = this.processResponse(response);
         this.release(response);
      }

      private ContainerResponse processResponse(ContainerResponse response) {
         Stage<ContainerResponse> respondingRoot = this.processingContext.createRespondingRoot();
         if (respondingRoot != null) {
            response = (ContainerResponse)Stages.process(response, respondingRoot);
         }

         this.writeResponse(response);
         this.completionCallbackRunner.onComplete((Throwable)null);
         return response;
      }

      public void process(Throwable throwable) {
         ContainerRequest request = this.processingContext.request();
         this.processingContext.monitoringEventBuilder().setException(throwable, RequestEvent.ExceptionCause.ORIGINAL);
         this.processingContext.triggerEvent(RequestEvent.Type.ON_EXCEPTION);
         ContainerResponse response = null;

         try {
            Response exceptionResponse = this.mapException(throwable);

            try {
               try {
                  response = this.convertResponse(exceptionResponse);
                  if (!this.runtime.disableLocationHeaderRelativeUriResolution) {
                     ServerRuntime.ensureAbsolute(response.getLocation(), response.getHeaders(), request, this.runtime.rfc7231LocationHeaderRelativeUriResolution);
                  }

                  this.processingContext.monitoringEventBuilder().setContainerResponse(response).setResponseSuccessfullyMapped(true);
               } finally {
                  this.processingContext.triggerEvent(RequestEvent.Type.EXCEPTION_MAPPING_FINISHED);
               }

               this.processResponse(response);
            } catch (Throwable respError) {
               LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_PROCESSING_RESPONSE_FROM_ALREADY_MAPPED_EXCEPTION());
               this.processingContext.monitoringEventBuilder().setException(respError, RequestEvent.ExceptionCause.MAPPED_RESPONSE);
               this.processingContext.triggerEvent(RequestEvent.Type.ON_EXCEPTION);
               throw respError;
            }
         } catch (Throwable var26) {
            Throwable responseError = var26;
            if (throwable != var26 && (!(throwable instanceof MappableException) || throwable.getCause() != var26)) {
               LOGGER.log(Level.FINE, LocalizationMessages.ERROR_EXCEPTION_MAPPING_ORIGINAL_EXCEPTION(), throwable);
            }

            if (!this.processResponseError(var26)) {
               LOGGER.log(Level.FINE, LocalizationMessages.ERROR_EXCEPTION_MAPPING_THROWN_TO_CONTAINER(), var26);

               try {
                  request.getResponseWriter().failure(responseError);
               } finally {
                  this.completionCallbackRunner.onComplete(var26);
               }
            }
         } finally {
            this.release(response);
         }

      }

      private boolean processResponseError(Throwable responseError) {
         boolean processed = false;
         if (this.runtime.processResponseErrors) {
            Iterable<ResponseErrorMapper> mappers = Providers.getAllProviders(this.runtime.injectionManager, ResponseErrorMapper.class);
            ContainerResponse processedResponse = null;

            try {
               Response processedError = null;

               for(ResponseErrorMapper mapper : mappers) {
                  processedError = mapper.toResponse(responseError);
                  if (processedError != null) {
                     break;
                  }
               }

               if (processedError != null) {
                  processedResponse = this.processResponse(new ContainerResponse(this.processingContext.request(), processedError));
                  processed = true;
               }
            } catch (Throwable throwable) {
               LOGGER.log(Level.FINE, LocalizationMessages.ERROR_EXCEPTION_MAPPING_PROCESSED_RESPONSE_ERROR(), throwable);
            } finally {
               if (processedResponse != null) {
                  this.release(processedResponse);
               }

            }
         }

         return processed;
      }

      private ContainerResponse convertResponse(Response exceptionResponse) {
         ContainerResponse containerResponse = new ContainerResponse(this.processingContext.request(), exceptionResponse);
         containerResponse.setMappedFromException(true);
         return containerResponse;
      }

      private Response mapException(Throwable originalThrowable) throws Throwable {
         LOGGER.log(Level.FINER, LocalizationMessages.EXCEPTION_MAPPING_START(), originalThrowable);
         ThrowableWrap wrap = new ThrowableWrap(originalThrowable);
         wrap.tryMappableException();

         do {
            Throwable throwable = wrap.getCurrent();
            if (wrap.isInMappable() || throwable instanceof WebApplicationException) {
               if (this.runtime.processResponseErrors && throwable instanceof InternalServerErrorException && throwable.getCause() instanceof MessageBodyProviderNotFoundException) {
                  throw throwable;
               }

               Response waeResponse = null;
               if (throwable instanceof WebApplicationException) {
                  WebApplicationException webApplicationException = (WebApplicationException)throwable;
                  this.processingContext.routingContext().setMappedThrowable(throwable);
                  waeResponse = webApplicationException.getResponse();
                  if (waeResponse != null && waeResponse.hasEntity()) {
                     LOGGER.log(Level.FINE, LocalizationMessages.EXCEPTION_MAPPING_WAE_ENTITY(waeResponse.getStatus()), throwable);
                     return waeResponse;
                  }
               }

               long timestamp = this.tracingLogger.timestamp(ServerTraceEvent.EXCEPTION_MAPPING);
               ExceptionMapper mapper = this.runtime.exceptionMappers.findMapping(throwable);
               if (mapper != null) {
                  this.processingContext.monitoringEventBuilder().setExceptionMapper(mapper);
                  this.processingContext.triggerEvent(RequestEvent.Type.EXCEPTION_MAPPER_FOUND);

                  try {
                     Response mappedResponse = mapper.toResponse(throwable);
                     if (this.tracingLogger.isLogEnabled(ServerTraceEvent.EXCEPTION_MAPPING)) {
                        this.tracingLogger.logDuration(ServerTraceEvent.EXCEPTION_MAPPING, timestamp, new Object[]{mapper, throwable, throwable.getLocalizedMessage(), mappedResponse != null ? mappedResponse.getStatusInfo() : "-no-response-"});
                     }

                     this.processingContext.routingContext().setMappedThrowable(throwable);
                     if (mappedResponse != null) {
                        if (LOGGER.isLoggable(Level.FINER)) {
                           String message = String.format("Exception '%s' has been mapped by '%s' to response '%s' (%s:%s).", throwable.getLocalizedMessage(), mapper.getClass().getName(), mappedResponse.getStatusInfo().getReasonPhrase(), mappedResponse.getStatusInfo().getStatusCode(), mappedResponse.getStatusInfo().getFamily());
                           LOGGER.log(Level.FINER, message);
                        }

                        return mappedResponse;
                     }

                     return Response.noContent().build();
                  } catch (Throwable mapperThrowable) {
                     LOGGER.log(Level.SEVERE, LocalizationMessages.EXCEPTION_MAPPER_THROWS_EXCEPTION(mapper.getClass()), mapperThrowable);
                     LOGGER.log(Level.SEVERE, LocalizationMessages.EXCEPTION_MAPPER_FAILED_FOR_EXCEPTION(), throwable);
                     return Response.serverError().build();
                  }
               }

               if (waeResponse != null) {
                  LOGGER.log(Level.FINE, LocalizationMessages.EXCEPTION_MAPPING_WAE_NO_ENTITY(waeResponse.getStatus()), throwable);
                  return waeResponse;
               }
            }

            if (throwable instanceof HeaderValueException && ((HeaderValueException)throwable).getContext() == Context.INBOUND) {
               return Response.status(Status.BAD_REQUEST).build();
            }

            if (!wrap.isInMappable() || !wrap.isWrapped()) {
               throw wrap.getWrappedOrCurrent();
            }
         } while(wrap.unwrap() != null);

         throw originalThrowable;
      }

      private ContainerResponse writeResponse(final ContainerResponse response) {
         ContainerRequest request = this.processingContext.request();
         final ContainerResponseWriter writer = request.getResponseWriter();
         if (!this.runtime.disableLocationHeaderRelativeUriResolution) {
            ServerRuntime.ensureAbsolute(response.getLocation(), response.getHeaders(), response.getRequestContext(), this.runtime.rfc7231LocationHeaderRelativeUriResolution);
         }

         if (!response.hasEntity()) {
            this.tracingLogger.log(ServerTraceEvent.FINISHED, new Object[]{response.getStatusInfo()});
            this.tracingLogger.flush(response.getHeaders());
            writer.writeResponseStatusAndHeaders(0L, response);
            this.setWrittenResponse(response);
            return response;
         } else {
            Object entity = response.getEntity();
            boolean skipFinally = false;
            final boolean isHead = request.getMethod().equals("HEAD");
            boolean var26 = false;

            label355: {
               try {
                  var26 = true;
                  response.setStreamProvider(new OutboundMessageContext.StreamProvider() {
                     public OutputStream getOutputStream(int contentLength) throws IOException {
                        if (!Responder.this.runtime.disableLocationHeaderRelativeUriResolution) {
                           ServerRuntime.ensureAbsolute(response.getLocation(), response.getHeaders(), response.getRequestContext(), Responder.this.runtime.rfc7231LocationHeaderRelativeUriResolution);
                        }

                        OutputStream outputStream = writer.writeResponseStatusAndHeaders((long)contentLength, response);
                        return isHead ? null : outputStream;
                     }
                  });
                  if ((writer.enableResponseBuffering() || isHead) && !response.isChunked()) {
                     response.enableBuffering(this.runtime.configuration);
                  }

                  try {
                     response.setEntityStream(request.getWorkers().writeTo(entity, entity.getClass(), response.getEntityType(), response.getEntityAnnotations(), response.getMediaType(), response.getHeaders(), request.getPropertiesDelegate(), response.getEntityStream(), request.getWriterInterceptors()));
                  } catch (MappableException mpe) {
                     if (mpe.getCause() instanceof IOException) {
                        this.connectionCallbackRunner.onDisconnect(this.processingContext.asyncContext());
                     }

                     throw mpe;
                  }

                  this.tracingLogger.log(ServerTraceEvent.FINISHED, new Object[]{response.getStatusInfo()});
                  this.tracingLogger.flush(response.getHeaders());
                  this.setWrittenResponse(response);
                  var26 = false;
                  break label355;
               } catch (Throwable ex) {
                  if (!response.isCommitted()) {
                     skipFinally = true;
                     if (ex instanceof RuntimeException) {
                        throw (RuntimeException)ex;
                     }

                     throw new MappableException(ex);
                  }

                  LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_WRITING_RESPONSE_ENTITY(), ex);
                  var26 = false;
               } finally {
                  if (var26) {
                     if (!skipFinally) {
                        boolean close = !response.isChunked();
                        if (response.isChunked()) {
                           try {
                              response.commitStream();
                           } catch (Exception e) {
                              LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_COMMITTING_OUTPUT_STREAM(), e);
                              close = true;
                           }

                           ChunkedOutput chunked = (ChunkedOutput)entity;

                           try {
                              chunked.setContext(this.runtime.requestScope, this.runtime.requestScope.referenceCurrent(), request, response, this.connectionCallbackRunner);
                           } catch (IOException ex) {
                              LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_WRITING_RESPONSE_ENTITY_CHUNK(), ex);
                              close = true;
                           }

                           if (!chunked.isClosed() && !writer.suspend(0L, TimeUnit.SECONDS, (ContainerResponseWriter.TimeoutHandler)null)) {
                              LOGGER.fine(LocalizationMessages.ERROR_SUSPENDING_CHUNKED_OUTPUT_RESPONSE());
                           }
                        }

                        if (close) {
                           try {
                              response.close();
                           } catch (Exception e) {
                              LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_CLOSING_COMMIT_OUTPUT_STREAM(), e);
                           }
                        }
                     }

                  }
               }

               if (!skipFinally) {
                  boolean close = !response.isChunked();
                  if (response.isChunked()) {
                     try {
                        response.commitStream();
                     } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_COMMITTING_OUTPUT_STREAM(), e);
                        close = true;
                     }

                     ChunkedOutput chunked = (ChunkedOutput)entity;

                     try {
                        chunked.setContext(this.runtime.requestScope, this.runtime.requestScope.referenceCurrent(), request, response, this.connectionCallbackRunner);
                     } catch (IOException ex) {
                        LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_WRITING_RESPONSE_ENTITY_CHUNK(), ex);
                        close = true;
                     }

                     if (!chunked.isClosed() && !writer.suspend(0L, TimeUnit.SECONDS, (ContainerResponseWriter.TimeoutHandler)null)) {
                        LOGGER.fine(LocalizationMessages.ERROR_SUSPENDING_CHUNKED_OUTPUT_RESPONSE());
                     }
                  }

                  if (close) {
                     try {
                        response.close();
                     } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_CLOSING_COMMIT_OUTPUT_STREAM(), e);
                     }

                     return response;
                  }
               }

               return response;
            }

            if (!skipFinally) {
               boolean close = !response.isChunked();
               if (response.isChunked()) {
                  try {
                     response.commitStream();
                  } catch (Exception e) {
                     LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_COMMITTING_OUTPUT_STREAM(), e);
                     close = true;
                  }

                  ChunkedOutput chunked = (ChunkedOutput)entity;

                  try {
                     chunked.setContext(this.runtime.requestScope, this.runtime.requestScope.referenceCurrent(), request, response, this.connectionCallbackRunner);
                  } catch (IOException ex) {
                     LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_WRITING_RESPONSE_ENTITY_CHUNK(), ex);
                     close = true;
                  }

                  if (!chunked.isClosed() && !writer.suspend(0L, TimeUnit.SECONDS, (ContainerResponseWriter.TimeoutHandler)null)) {
                     LOGGER.fine(LocalizationMessages.ERROR_SUSPENDING_CHUNKED_OUTPUT_RESPONSE());
                  }
               }

               if (close) {
                  try {
                     response.close();
                  } catch (Exception e) {
                     LOGGER.log(Level.SEVERE, LocalizationMessages.ERROR_CLOSING_COMMIT_OUTPUT_STREAM(), e);
                  }
               }
            }

            return response;
         }
      }

      private void setWrittenResponse(ContainerResponse response) {
         this.processingContext.monitoringEventBuilder().setContainerResponse(response).setSuccess(response.getStatus() < Status.BAD_REQUEST.getStatusCode()).setResponseWritten(true);
      }

      private void release(ContainerResponse responseContext) {
         try {
            this.processingContext.closeableService().close();
            if (responseContext != null && !responseContext.isChunked()) {
               responseContext.close();
            }
         } catch (Throwable throwable) {
            LOGGER.log(Level.WARNING, LocalizationMessages.RELEASING_REQUEST_PROCESSING_RESOURCES_FAILED(), throwable);
         } finally {
            this.runtime.externalRequestScope.close();
            this.processingContext.triggerEvent(RequestEvent.Type.FINISHED);
         }

      }
   }

   private static class AsyncResponder implements AsyncContext, ContainerResponseWriter.TimeoutHandler, CompletionCallback {
      private static final Logger LOGGER = Logger.getLogger(AsyncResponder.class.getName());
      private static final TimeoutHandler DEFAULT_TIMEOUT_HANDLER = new TimeoutHandler() {
         public void handleTimeout(AsyncResponse asyncResponse) {
            throw new ServiceUnavailableException();
         }
      };
      private final Object stateLock = new Object();
      private AsyncContext.State state;
      private boolean cancelled;
      private final Responder responder;
      private final RequestContext requestContext;
      private final ExternalRequestContext foreignScopeInstance;
      private final ExternalRequestScope requestScopeListener;
      private volatile TimeoutHandler timeoutHandler;
      private final List callbackRunners;

      public AsyncResponder(Responder responder, RequestContext requestContext, ExternalRequestScope requestScopeListener, ExternalRequestContext foreignScopeInstance) {
         this.state = AsyncContext.State.RUNNING;
         this.cancelled = false;
         this.timeoutHandler = DEFAULT_TIMEOUT_HANDLER;
         this.responder = responder;
         this.requestContext = requestContext;
         this.foreignScopeInstance = foreignScopeInstance;
         this.requestScopeListener = requestScopeListener;
         this.callbackRunners = Collections.unmodifiableList(Arrays.asList(responder.completionCallbackRunner, responder.connectionCallbackRunner));
         responder.completionCallbackRunner.register(this);
      }

      public void onTimeout(ContainerResponseWriter responseWriter) {
         TimeoutHandler handler = this.timeoutHandler;

         try {
            synchronized(this.stateLock) {
               if (this.state == AsyncContext.State.SUSPENDED) {
                  handler.handleTimeout(this);
               }
            }
         } catch (Throwable throwable) {
            this.resume(throwable);
         }

      }

      public void onComplete(Throwable throwable) {
         synchronized(this.stateLock) {
            this.state = AsyncContext.State.COMPLETED;
         }
      }

      public void invokeManaged(final Producer producer) {
         ((ExecutorService)this.responder.runtime.managedAsyncExecutor.get()).submit(new Runnable() {
            public void run() {
               AsyncResponder.this.responder.runtime.requestScope.runInScope(AsyncResponder.this.requestContext, new Runnable() {
                  public void run() {
                     try {
                        AsyncResponder.this.requestScopeListener.resume(AsyncResponder.this.foreignScopeInstance, AsyncResponder.this.responder.runtime.injectionManager);
                        Response response = (Response)producer.call();
                        if (response != null) {
                           AsyncResponder.this.resume((Object)response);
                        }
                     } catch (Throwable t) {
                        AsyncResponder.this.resume(t);
                     }

                  }
               });
            }
         });
      }

      public boolean suspend() {
         synchronized(this.stateLock) {
            if (this.state == AsyncContext.State.RUNNING && this.responder.processingContext.request().getResponseWriter().suspend(0L, TimeUnit.SECONDS, this)) {
               this.state = AsyncContext.State.SUSPENDED;
               return true;
            } else {
               return false;
            }
         }
      }

      public boolean resume(final Object response) {
         return this.resume(new Runnable() {
            public void run() {
               try {
                  AsyncResponder.this.requestScopeListener.resume(AsyncResponder.this.foreignScopeInstance, AsyncResponder.this.responder.runtime.injectionManager);
                  Response jaxrsResponse = response instanceof Response ? (Response)response : Response.ok(response).build();
                  if (!AsyncResponder.this.responder.runtime.disableLocationHeaderRelativeUriResolution) {
                     ServerRuntime.ensureAbsolute(jaxrsResponse.getLocation(), jaxrsResponse.getHeaders(), AsyncResponder.this.responder.processingContext.request(), AsyncResponder.this.responder.runtime.rfc7231LocationHeaderRelativeUriResolution);
                  }

                  AsyncResponder.this.responder.process(new ContainerResponse(AsyncResponder.this.responder.processingContext.request(), jaxrsResponse));
               } catch (Throwable t) {
                  AsyncResponder.this.responder.process(t);
               }

            }
         });
      }

      public boolean resume(final Throwable error) {
         return this.resume(new Runnable() {
            public void run() {
               try {
                  AsyncResponder.this.requestScopeListener.resume(AsyncResponder.this.foreignScopeInstance, AsyncResponder.this.responder.runtime.injectionManager);
                  AsyncResponder.this.responder.process((Throwable)(new MappableException(error)));
               } catch (Throwable var2) {
               }

            }
         });
      }

      private boolean resume(Runnable handler) {
         synchronized(this.stateLock) {
            if (this.state != AsyncContext.State.SUSPENDED) {
               return false;
            }

            this.state = AsyncContext.State.RESUMED;
         }

         try {
            this.responder.runtime.requestScope.runInScope(this.requestContext, handler);
         } finally {
            this.requestContext.release();
         }

         return true;
      }

      public boolean cancel() {
         return this.cancel(new Value() {
            public Response get() {
               return Response.status(Status.SERVICE_UNAVAILABLE).build();
            }
         });
      }

      public boolean cancel(final int retryAfter) {
         return this.cancel(new Value() {
            public Response get() {
               return Response.status(Status.SERVICE_UNAVAILABLE).header("Retry-After", retryAfter).build();
            }
         });
      }

      public boolean cancel(final Date retryAfter) {
         return this.cancel(new Value() {
            public Response get() {
               return Response.status(Status.SERVICE_UNAVAILABLE).header("Retry-After", retryAfter).build();
            }
         });
      }

      private boolean cancel(final Value responseValue) {
         synchronized(this.stateLock) {
            if (this.cancelled) {
               return true;
            }

            if (this.state != AsyncContext.State.SUSPENDED) {
               return false;
            }

            this.state = AsyncContext.State.RESUMED;
            this.cancelled = true;
         }

         this.responder.runtime.requestScope.runInScope(this.requestContext, new Runnable() {
            public void run() {
               try {
                  AsyncResponder.this.requestScopeListener.resume(AsyncResponder.this.foreignScopeInstance, AsyncResponder.this.responder.runtime.injectionManager);
                  Response response = (Response)responseValue.get();
                  AsyncResponder.this.responder.process(new ContainerResponse(AsyncResponder.this.responder.processingContext.request(), response));
               } catch (Throwable t) {
                  AsyncResponder.this.responder.process(t);
               }

            }
         });
         return true;
      }

      public boolean isRunning() {
         synchronized(this.stateLock) {
            return this.state == AsyncContext.State.RUNNING;
         }
      }

      public boolean isSuspended() {
         synchronized(this.stateLock) {
            return this.state == AsyncContext.State.SUSPENDED;
         }
      }

      public boolean isCancelled() {
         synchronized(this.stateLock) {
            return this.cancelled;
         }
      }

      public boolean isDone() {
         synchronized(this.stateLock) {
            return this.state == AsyncContext.State.COMPLETED;
         }
      }

      public boolean setTimeout(long time, TimeUnit unit) {
         try {
            this.responder.processingContext.request().getResponseWriter().setSuspendTimeout(time, unit);
            return true;
         } catch (IllegalStateException ex) {
            LOGGER.log(Level.FINER, "Unable to set timeout on the AsyncResponse.", ex);
            return false;
         }
      }

      public void setTimeoutHandler(TimeoutHandler handler) {
         this.timeoutHandler = handler;
      }

      public Collection register(Class callback) {
         Preconditions.checkNotNull(callback, LocalizationMessages.PARAM_NULL("callback"));
         return this.register(Injections.getOrCreate(this.responder.runtime.injectionManager, callback));
      }

      public Map register(Class callback, Class... callbacks) {
         Preconditions.checkNotNull(callback, LocalizationMessages.PARAM_NULL("callback"));
         Preconditions.checkNotNull(callbacks, LocalizationMessages.CALLBACK_ARRAY_NULL());

         for(Class additionalCallback : callbacks) {
            Preconditions.checkNotNull(additionalCallback, LocalizationMessages.CALLBACK_ARRAY_ELEMENT_NULL());
         }

         Map<Class<?>, Collection<Class<?>>> results = new HashMap();
         results.put(callback, this.register(callback));

         for(Class c : callbacks) {
            results.put(c, this.register(c));
         }

         return results;
      }

      public Collection register(Object callback) {
         Preconditions.checkNotNull(callback, LocalizationMessages.PARAM_NULL("callback"));
         Collection<Class<?>> result = new LinkedList();

         for(AbstractCallbackRunner runner : this.callbackRunners) {
            if (runner.supports(callback.getClass()) && runner.register(callback)) {
               result.add(runner.getCallbackContract());
            }
         }

         return result;
      }

      public Map register(Object callback, Object... callbacks) {
         Preconditions.checkNotNull(callback, LocalizationMessages.PARAM_NULL("callback"));
         Preconditions.checkNotNull(callbacks, LocalizationMessages.CALLBACK_ARRAY_NULL());

         for(Object additionalCallback : callbacks) {
            Preconditions.checkNotNull(additionalCallback, LocalizationMessages.CALLBACK_ARRAY_ELEMENT_NULL());
         }

         Map<Class<?>, Collection<Class<?>>> results = new HashMap();
         results.put(callback.getClass(), this.register(callback));

         for(Object c : callbacks) {
            results.put(c.getClass(), this.register(c));
         }

         return results;
      }
   }

   abstract static class AbstractCallbackRunner {
      private final Queue callbacks = new ConcurrentLinkedQueue();
      private final Logger logger;

      protected AbstractCallbackRunner(Logger logger) {
         this.logger = logger;
      }

      public final boolean supports(Class callbackClass) {
         return this.getCallbackContract().isAssignableFrom(callbackClass);
      }

      public abstract Class getCallbackContract();

      public boolean register(Object callback) {
         return this.callbacks.offer(callback);
      }

      protected final void executeCallbacks(Closure invoker) {
         for(Object callback : this.callbacks) {
            try {
               invoker.invoke(callback);
            } catch (Throwable t) {
               this.logger.log(Level.WARNING, LocalizationMessages.ERROR_ASYNC_CALLBACK_FAILED(callback.getClass().getName()), t);
            }
         }

      }
   }

   private static class CompletionCallbackRunner extends AbstractCallbackRunner implements CompletionCallback {
      private static final Logger LOGGER = Logger.getLogger(CompletionCallbackRunner.class.getName());

      private CompletionCallbackRunner() {
         super(LOGGER);
      }

      public Class getCallbackContract() {
         return CompletionCallback.class;
      }

      public void onComplete(final Throwable throwable) {
         this.executeCallbacks(new Closure() {
            public void invoke(CompletionCallback callback) {
               callback.onComplete(throwable);
            }
         });
      }
   }

   private static class ConnectionCallbackRunner extends AbstractCallbackRunner implements ConnectionCallback {
      private static final Logger LOGGER = Logger.getLogger(ConnectionCallbackRunner.class.getName());

      private ConnectionCallbackRunner() {
         super(LOGGER);
      }

      public Class getCallbackContract() {
         return ConnectionCallback.class;
      }

      public void onDisconnect(final AsyncResponse disconnected) {
         this.executeCallbacks(new Closure() {
            public void invoke(ConnectionCallback callback) {
               callback.onDisconnect(disconnected);
            }
         });
      }
   }

   private static class ThrowableWrap {
      private final Throwable original;
      private Throwable wrapped;
      private Throwable current;
      private boolean inMappable;

      private ThrowableWrap(Throwable original) {
         this.wrapped = null;
         this.inMappable = false;
         this.original = original;
         this.current = original;
      }

      private Throwable getOriginal() {
         return this.original;
      }

      private Throwable getWrappedOrCurrent() {
         return this.wrapped != null ? this.wrapped : this.current;
      }

      private Throwable getCurrent() {
         return this.current;
      }

      private boolean isWrapped() {
         boolean isConcurrentWrap = CompletionException.class.isInstance(this.current) || ExecutionException.class.isInstance(this.current);
         return isConcurrentWrap;
      }

      private Throwable unwrap() {
         if (this.wrapped == null) {
            this.wrapped = this.current;
         }

         this.current = this.current.getCause();
         return this.current;
      }

      private boolean tryMappableException() {
         if (MappableException.class.isInstance(this.original)) {
            this.inMappable = true;
            this.current = this.original.getCause();
            return true;
         } else {
            return false;
         }
      }

      private boolean isInMappable() {
         return this.inMappable;
      }
   }
}
