package org.glassfish.jersey.client;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.client.internal.routing.ClientResponseMediaTypeDeterminer;
import org.glassfish.jersey.client.spi.PostInvocationInterceptor;
import org.glassfish.jersey.client.spi.PreInvocationInterceptor;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.model.internal.RankedComparator.Order;

class InvocationInterceptorStages {
   private static final Logger LOGGER = Logger.getLogger(InvocationInterceptorStages.class.getName());

   private InvocationInterceptorStages() {
   }

   static PreInvocationInterceptorStage createPreInvocationInterceptorStage(InjectionManager injectionManager) {
      return new PreInvocationInterceptorStage(injectionManager);
   }

   static PostInvocationInterceptorStage createPostInvocationInterceptorStage(InjectionManager injectionManager) {
      return new PostInvocationInterceptorStage(injectionManager);
   }

   private static ProcessingException createProcessingException(Throwable t) {
      ProcessingException processingException = createProcessingException(LocalizationMessages.EXCEPTION_SUPPRESSED());
      processingException.addSuppressed(t);
      return processingException;
   }

   private static ProcessingException createProcessingException(String message) {
      return new InvocationInterceptorException(message);
   }

   private static RuntimeException suppressExceptions(Deque throwables) {
      if (throwables.size() == 1 && RuntimeException.class.isInstance(throwables.getFirst())) {
         throw (RuntimeException)throwables.getFirst();
      } else {
         ProcessingException processingException = createProcessingException(LocalizationMessages.EXCEPTION_SUPPRESSED());

         for(Throwable throwable : throwables) {
            if (processingException.getCause() == null) {
               processingException.initCause(throwable);
            }

            processingException.addSuppressed(throwable);
         }

         return processingException;
      }
   }

   static class PreInvocationInterceptorStage {
      private Iterable preInvocationInterceptors;

      private PreInvocationInterceptorStage(InjectionManager injectionManager) {
         RankedComparator<PreInvocationInterceptor> comparator = new RankedComparator(Order.DESCENDING);
         this.preInvocationInterceptors = Providers.getAllProviders(injectionManager, PreInvocationInterceptor.class, comparator);
      }

      boolean hasPreInvocationInterceptors() {
         return this.preInvocationInterceptors.iterator().hasNext();
      }

      void beforeRequest(ClientRequest request) {
         LinkedList<Throwable> throwables = new LinkedList();
         ClientRequestContext requestContext = new InvocationInterceptorRequestContext(request);
         Iterator<PreInvocationInterceptor> preInvocationInterceptorIterator = this.preInvocationInterceptors.iterator();

         while(preInvocationInterceptorIterator.hasNext()) {
            try {
               ((PreInvocationInterceptor)preInvocationInterceptorIterator.next()).beforeRequest(requestContext);
            } catch (Throwable throwable) {
               InvocationInterceptorStages.LOGGER.log(Level.FINE, LocalizationMessages.PREINVOCATION_INTERCEPTOR_EXCEPTION(), throwable);
               throwables.add(throwable);
            }
         }

         if (!throwables.isEmpty()) {
            throw InvocationInterceptorStages.suppressExceptions(throwables);
         }
      }

      ClientRequestFilter createPreInvocationInterceptorFilter() {
         return new ClientRequestFilter() {
            public void filter(ClientRequestContext requestContext) throws IOException {
            }
         };
      }
   }

   static class PostInvocationInterceptorStage {
      private final Iterable postInvocationInterceptors;

      private PostInvocationInterceptorStage(InjectionManager injectionManager) {
         RankedComparator<PostInvocationInterceptor> comparator = new RankedComparator(Order.ASCENDING);
         this.postInvocationInterceptors = Providers.getAllProviders(injectionManager, PostInvocationInterceptor.class, comparator);
      }

      boolean hasPostInvocationInterceptor() {
         return this.postInvocationInterceptors.iterator().hasNext();
      }

      private ClientResponse afterRequestWithoutException(Iterator postInvocationInterceptors, InvocationInterceptorRequestContext requestContext, PostInvocationExceptionContext exceptionContext) {
         boolean withoutException = true;
         if (postInvocationInterceptors.hasNext()) {
            PostInvocationInterceptor postInvocationInterceptor = (PostInvocationInterceptor)postInvocationInterceptors.next();

            try {
               postInvocationInterceptor.afterRequest(requestContext, (ClientResponseContext)exceptionContext.getResponseContext().get());
            } catch (Throwable throwable) {
               InvocationInterceptorStages.LOGGER.log(Level.FINE, LocalizationMessages.POSTINVOCATION_INTERCEPTOR_EXCEPTION(), throwable);
               withoutException = false;
               exceptionContext.throwables.add(throwable);
            } finally {
               return withoutException ? this.afterRequestWithoutException(postInvocationInterceptors, requestContext, exceptionContext) : this.afterRequestWithException(postInvocationInterceptors, requestContext, exceptionContext);
            }
         } else {
            return exceptionContext.responseContext;
         }
      }

      private ClientResponse afterRequestWithException(Iterator postInvocationInterceptors, InvocationInterceptorRequestContext requestContext, PostInvocationExceptionContext exceptionContext) {
         Throwable caught = null;
         if (postInvocationInterceptors.hasNext()) {
            PostInvocationInterceptor postInvocationInterceptor = (PostInvocationInterceptor)postInvocationInterceptors.next();

            try {
               postInvocationInterceptor.onException(requestContext, exceptionContext);
            } catch (Throwable throwable) {
               InvocationInterceptorStages.LOGGER.log(Level.FINE, LocalizationMessages.POSTINVOCATION_INTERCEPTOR_EXCEPTION(), throwable);
               caught = throwable;
            }

            try {
               resolveResponse(requestContext, exceptionContext);
            } catch (Throwable throwable) {
               InvocationInterceptorStages.LOGGER.log(Level.FINE, LocalizationMessages.POSTINVOCATION_INTERCEPTOR_EXCEPTION(), throwable);
               exceptionContext.throwables.add(throwable);
            } finally {
               if (caught != null) {
                  exceptionContext.throwables.add(caught);
               }

            }

            return exceptionContext.throwables.isEmpty() && exceptionContext.responseContext != null ? this.afterRequestWithoutException(postInvocationInterceptors, requestContext, exceptionContext) : this.afterRequestWithException(postInvocationInterceptors, requestContext, exceptionContext);
         } else {
            throw InvocationInterceptorStages.suppressExceptions(exceptionContext.throwables);
         }
      }

      ClientResponse afterRequest(ClientRequest request, ClientResponse response, Throwable previousException) {
         PostInvocationExceptionContext exceptionContext = new PostInvocationExceptionContext(response, previousException);
         InvocationInterceptorRequestContext requestContext = new InvocationInterceptorRequestContext(request);
         return previousException != null ? this.afterRequestWithException(this.postInvocationInterceptors.iterator(), requestContext, exceptionContext) : this.afterRequestWithoutException(this.postInvocationInterceptors.iterator(), requestContext, exceptionContext);
      }

      private static boolean resolveResponse(InvocationInterceptorRequestContext requestContext, PostInvocationExceptionContext exceptionContext) {
         if (exceptionContext.response != null) {
            exceptionContext.throwables.clear();
            ClientResponseMediaTypeDeterminer determiner = new ClientResponseMediaTypeDeterminer(requestContext.clientRequest.getWorkers());
            determiner.setResponseMediaTypeIfNotSet(exceptionContext.response, requestContext.getConfiguration());
            ClientResponse response = new ClientResponse(requestContext.clientRequest, exceptionContext.response);
            exceptionContext.responseContext = response;
            exceptionContext.response = null;
            return true;
         } else {
            return false;
         }
      }
   }

   private static class InvocationInterceptorRequestContext implements ClientRequestContext {
      private final ClientRequest clientRequest;

      private InvocationInterceptorRequestContext(ClientRequest clientRequestContext) {
         this.clientRequest = clientRequestContext;
      }

      public Object getProperty(String name) {
         return this.clientRequest.getProperty(name);
      }

      public Collection getPropertyNames() {
         return this.clientRequest.getPropertyNames();
      }

      public void setProperty(String name, Object object) {
         this.clientRequest.setProperty(name, object);
      }

      public void removeProperty(String name) {
         this.clientRequest.removeProperty(name);
      }

      public URI getUri() {
         return this.clientRequest.getUri();
      }

      public void setUri(URI uri) {
         this.clientRequest.setUri(uri);
      }

      public String getMethod() {
         return this.clientRequest.getMethod();
      }

      public void setMethod(String method) {
         this.clientRequest.setMethod(method);
      }

      public MultivaluedMap getHeaders() {
         return this.clientRequest.getHeaders();
      }

      public MultivaluedMap getStringHeaders() {
         return this.clientRequest.getStringHeaders();
      }

      public String getHeaderString(String name) {
         return this.clientRequest.getHeaderString(name);
      }

      public Date getDate() {
         return this.clientRequest.getDate();
      }

      public Locale getLanguage() {
         return this.clientRequest.getLanguage();
      }

      public MediaType getMediaType() {
         return this.clientRequest.getMediaType();
      }

      public List getAcceptableMediaTypes() {
         return this.clientRequest.getAcceptableMediaTypes();
      }

      public List getAcceptableLanguages() {
         return this.clientRequest.getAcceptableLanguages();
      }

      public Map getCookies() {
         return this.clientRequest.getCookies();
      }

      public boolean hasEntity() {
         return this.clientRequest.hasEntity();
      }

      public Object getEntity() {
         return this.clientRequest.getEntity();
      }

      public Class getEntityClass() {
         return this.clientRequest.getEntityClass();
      }

      public Type getEntityType() {
         return this.clientRequest.getEntityType();
      }

      public void setEntity(Object entity) {
         this.clientRequest.setEntity(entity);
      }

      public void setEntity(Object entity, Annotation[] annotations, MediaType mediaType) {
         this.clientRequest.setEntity(entity, annotations, mediaType);
      }

      public Annotation[] getEntityAnnotations() {
         return this.clientRequest.getEntityAnnotations();
      }

      public OutputStream getEntityStream() {
         return this.clientRequest.getEntityStream();
      }

      public void setEntityStream(OutputStream outputStream) {
         this.clientRequest.setEntityStream(outputStream);
      }

      public Client getClient() {
         return this.clientRequest.getClient();
      }

      public Configuration getConfiguration() {
         return this.clientRequest.getConfiguration();
      }

      public void abortWith(Response response) {
         if (this.clientRequest.getAbortResponse() != null) {
            InvocationInterceptorStages.LOGGER.warning(LocalizationMessages.PREINVOCATION_INTERCEPTOR_MULTIPLE_ABORTIONS());
            throw new IllegalStateException(LocalizationMessages.PREINVOCATION_INTERCEPTOR_MULTIPLE_ABORTIONS());
         } else {
            InvocationInterceptorStages.LOGGER.finer(LocalizationMessages.PREINVOCATION_INTERCEPTOR_ABORT_WITH());
            this.clientRequest.abortWith(response);
         }
      }
   }

   private static class PostInvocationExceptionContext implements PostInvocationInterceptor.ExceptionContext {
      private ClientResponse responseContext;
      private LinkedList throwables;
      private Response response;

      private PostInvocationExceptionContext(ClientResponse responseContext, Throwable throwable) {
         this.response = null;
         this.responseContext = responseContext;
         this.throwables = new LinkedList();
         if (throwable != null) {
            if (InvocationInterceptorException.class.isInstance(throwable)) {
               for(Throwable t : throwable.getSuppressed()) {
                  this.throwables.add(t);
               }
            } else {
               this.throwables.add(throwable);
            }
         }

      }

      public Optional getResponseContext() {
         return this.responseContext == null ? Optional.empty() : Optional.of(new InvocationInterceptorResponseContext(this.responseContext));
      }

      public Deque getThrowables() {
         return this.throwables;
      }

      public void resolve(Response response) {
         if (this.response != null) {
            InvocationInterceptorStages.LOGGER.warning(LocalizationMessages.POSTINVOCATION_INTERCEPTOR_MULTIPLE_RESOLVES());
            throw new IllegalStateException(LocalizationMessages.POSTINVOCATION_INTERCEPTOR_MULTIPLE_RESOLVES());
         } else {
            InvocationInterceptorStages.LOGGER.finer(LocalizationMessages.POSTINVOCATION_INTERCEPTOR_RESOLVE());
            this.response = response;
         }
      }
   }

   private static class InvocationInterceptorResponseContext implements ClientResponseContext {
      private final ClientResponse clientResponse;

      private InvocationInterceptorResponseContext(ClientResponse clientResponse) {
         this.clientResponse = clientResponse;
      }

      public int getStatus() {
         return this.clientResponse.getStatus();
      }

      public void setStatus(int code) {
         this.clientResponse.setStatus(code);
      }

      public Response.StatusType getStatusInfo() {
         return this.clientResponse.getStatusInfo();
      }

      public void setStatusInfo(Response.StatusType statusInfo) {
         this.clientResponse.setStatusInfo(statusInfo);
      }

      public MultivaluedMap getHeaders() {
         return this.clientResponse.getHeaders();
      }

      public String getHeaderString(String name) {
         return this.clientResponse.getHeaderString(name);
      }

      public Set getAllowedMethods() {
         return this.clientResponse.getAllowedMethods();
      }

      public Date getDate() {
         return this.clientResponse.getDate();
      }

      public Locale getLanguage() {
         return this.clientResponse.getLanguage();
      }

      public int getLength() {
         return this.clientResponse.getLength();
      }

      public MediaType getMediaType() {
         return this.clientResponse.getMediaType();
      }

      public Map getCookies() {
         return this.clientResponse.getCookies();
      }

      public EntityTag getEntityTag() {
         return this.clientResponse.getEntityTag();
      }

      public Date getLastModified() {
         return this.clientResponse.getLastModified();
      }

      public URI getLocation() {
         return this.clientResponse.getLocation();
      }

      public Set getLinks() {
         return this.clientResponse.getLinks();
      }

      public boolean hasLink(String relation) {
         return this.clientResponse.hasLink(relation);
      }

      public Link getLink(String relation) {
         return this.clientResponse.getLink(relation);
      }

      public Link.Builder getLinkBuilder(String relation) {
         return this.clientResponse.getLinkBuilder(relation);
      }

      public boolean hasEntity() {
         return this.clientResponse.hasEntity();
      }

      public InputStream getEntityStream() {
         return this.clientResponse.getEntityStream();
      }

      public void setEntityStream(InputStream input) {
         this.clientResponse.setEntityStream(input);
      }
   }

   private static class InvocationInterceptorException extends ProcessingException {
      private InvocationInterceptorException(String message) {
         super(message);
      }
   }
}
