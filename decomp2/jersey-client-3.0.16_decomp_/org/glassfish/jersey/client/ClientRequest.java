package org.glassfish.jersey.client;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Variant;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.WriterInterceptor;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.http.HttpHeaders;
import org.glassfish.jersey.internal.MapPropertiesDelegate;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.internal.PropertiesResolver;
import org.glassfish.jersey.internal.guava.Preconditions;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.InjectionManagerSupplier;
import org.glassfish.jersey.internal.util.ExceptionUtils;
import org.glassfish.jersey.internal.util.collection.LazyValue;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.message.internal.HeaderUtils;
import org.glassfish.jersey.message.internal.OutboundMessageContext;

public class ClientRequest extends OutboundMessageContext implements ClientRequestContext, HttpHeaders, InjectionManagerSupplier, PropertiesResolver {
   private final ClientConfig clientConfig;
   private final PropertiesDelegate propertiesDelegate;
   private URI requestUri;
   private String httpMethod;
   private Response abortResponse;
   private MessageBodyWorkers workers;
   private boolean asynchronous;
   private boolean entityWritten;
   private Iterable writerInterceptors;
   private Iterable readerInterceptors;
   private boolean ignoreUserAgent;
   private LazyValue propertiesResolver = Values.lazy(() -> PropertiesResolver.create(this.getConfiguration(), this.getPropertiesDelegate()));
   private Future cancellable;
   private static final Logger LOGGER = Logger.getLogger(ClientRequest.class.getName());

   protected ClientRequest(URI requestUri, ClientConfig clientConfig, PropertiesDelegate propertiesDelegate) {
      super(clientConfig.getConfiguration());
      this.cancellable = ClientRequest.NotCancellable.INSTANCE;
      clientConfig.checkClient();
      this.requestUri = requestUri;
      this.clientConfig = clientConfig;
      this.propertiesDelegate = propertiesDelegate;
   }

   public ClientRequest(ClientRequest original) {
      super(original);
      this.cancellable = ClientRequest.NotCancellable.INSTANCE;
      this.requestUri = original.requestUri;
      this.httpMethod = original.httpMethod;
      this.workers = original.workers;
      this.clientConfig = original.clientConfig.snapshot();
      this.asynchronous = original.isAsynchronous();
      this.readerInterceptors = original.readerInterceptors;
      this.writerInterceptors = original.writerInterceptors;
      this.propertiesDelegate = new MapPropertiesDelegate(original.propertiesDelegate);
      this.ignoreUserAgent = original.ignoreUserAgent;
      this.cancellable = original.cancellable;
   }

   public Object resolveProperty(String name, Class type) {
      return ((PropertiesResolver)this.propertiesResolver.get()).resolveProperty(name, type);
   }

   public Object resolveProperty(String name, Object defaultValue) {
      return ((PropertiesResolver)this.propertiesResolver.get()).resolveProperty(name, defaultValue);
   }

   public Object getProperty(String name) {
      return this.propertiesDelegate.getProperty(name);
   }

   public Collection getPropertyNames() {
      return this.propertiesDelegate.getPropertyNames();
   }

   public void setProperty(String name, Object object) {
      this.propertiesDelegate.setProperty(name, object);
   }

   public void removeProperty(String name) {
      this.propertiesDelegate.removeProperty(name);
   }

   PropertiesDelegate getPropertiesDelegate() {
      return this.propertiesDelegate;
   }

   ClientRuntime getClientRuntime() {
      return this.clientConfig.getRuntime();
   }

   public URI getUri() {
      return this.requestUri;
   }

   public void setUri(URI uri) {
      this.requestUri = uri;
   }

   public String getMethod() {
      return this.httpMethod;
   }

   public void setMethod(String method) {
      this.httpMethod = method;
   }

   public JerseyClient getClient() {
      return this.clientConfig.getClient();
   }

   public void abortWith(Response response) {
      this.abortResponse = response;
   }

   public Response getAbortResponse() {
      return this.abortResponse;
   }

   public Configuration getConfiguration() {
      return this.clientConfig.getRuntime().getConfig();
   }

   ClientConfig getClientConfig() {
      return this.clientConfig;
   }

   public List getRequestHeader(String name) {
      return HeaderUtils.asStringList((List)this.getHeaders().get(name), this.clientConfig.getConfiguration());
   }

   public MultivaluedMap getRequestHeaders() {
      return HeaderUtils.asStringHeaders(this.getHeaders(), this.clientConfig.getConfiguration());
   }

   public Map getCookies() {
      return super.getRequestCookies();
   }

   public MessageBodyWorkers getWorkers() {
      return this.workers;
   }

   public void setWorkers(MessageBodyWorkers workers) {
      this.workers = workers;
   }

   public void accept(MediaType... types) {
      this.getHeaders().addAll("Accept", (Object[])types);
   }

   public void accept(String... types) {
      this.getHeaders().addAll("Accept", (Object[])types);
   }

   public void acceptLanguage(Locale... locales) {
      this.getHeaders().addAll("Accept-Language", (Object[])locales);
   }

   public void acceptLanguage(String... locales) {
      this.getHeaders().addAll("Accept-Language", (Object[])locales);
   }

   public void cookie(Cookie cookie) {
      this.getHeaders().add("Cookie", cookie);
   }

   public void cacheControl(CacheControl cacheControl) {
      this.getHeaders().add("Cache-Control", cacheControl);
   }

   public void encoding(String encoding) {
      if (encoding == null) {
         this.getHeaders().remove("Content-Encoding");
      } else {
         this.getHeaders().putSingle("Content-Encoding", encoding);
      }

   }

   public void language(String language) {
      if (language == null) {
         this.getHeaders().remove("Content-Language");
      } else {
         this.getHeaders().putSingle("Content-Language", language);
      }

   }

   public void language(Locale language) {
      if (language == null) {
         this.getHeaders().remove("Content-Language");
      } else {
         this.getHeaders().putSingle("Content-Language", language);
      }

   }

   public void type(MediaType type) {
      this.setMediaType(type);
   }

   public void type(String type) {
      this.type(type == null ? null : MediaType.valueOf(type));
   }

   public void variant(Variant variant) {
      if (variant == null) {
         this.type((MediaType)null);
         this.language((String)null);
         this.encoding((String)null);
      } else {
         this.type(variant.getMediaType());
         this.language(variant.getLanguage());
         this.encoding(variant.getEncoding());
      }

   }

   public boolean isAsynchronous() {
      return this.asynchronous;
   }

   void setAsynchronous(boolean async) {
      this.asynchronous = async;
   }

   public void enableBuffering() {
      this.enableBuffering(this.getConfiguration());
   }

   public void writeEntity() throws IOException {
      Preconditions.checkState(!this.entityWritten, LocalizationMessages.REQUEST_ENTITY_ALREADY_WRITTEN());
      this.entityWritten = true;
      this.ensureMediaType();
      GenericType<?> entityType = new GenericType(this.getEntityType());
      this.doWriteEntity(this.workers, entityType);
   }

   void doWriteEntity(MessageBodyWorkers writeWorkers, GenericType entityType) throws IOException {
      OutputStream entityStream = null;
      boolean connectionFailed = false;
      boolean runtimeException = false;

      try {
         entityStream = writeWorkers.writeTo(this.getEntity(), entityType.getRawType(), entityType.getType(), this.getEntityAnnotations(), this.getMediaType(), this.getHeaders(), this.getPropertiesDelegate(), this.getEntityStream(), this.writerInterceptors);
         this.setEntityStream(entityStream);
      } catch (IOException e) {
         connectionFailed = true;
         throw e;
      } catch (RuntimeException e) {
         runtimeException = true;
         throw e;
      } finally {
         if (!connectionFailed) {
            if (entityStream != null) {
               try {
                  entityStream.close();
               } catch (IOException e) {
                  ExceptionUtils.conditionallyReThrow(e, !runtimeException, LOGGER, LocalizationMessages.ERROR_CLOSING_OUTPUT_STREAM(), Level.FINE);
               } catch (RuntimeException e) {
                  ExceptionUtils.conditionallyReThrow(e, !runtimeException, LOGGER, LocalizationMessages.ERROR_CLOSING_OUTPUT_STREAM(), Level.FINE);
               }
            }

            try {
               this.commitStream();
            } catch (IOException e) {
               ExceptionUtils.conditionallyReThrow(e, !runtimeException, LOGGER, LocalizationMessages.ERROR_COMMITTING_OUTPUT_STREAM(), Level.FINE);
            } catch (RuntimeException e) {
               ExceptionUtils.conditionallyReThrow(e, !runtimeException, LOGGER, LocalizationMessages.ERROR_COMMITTING_OUTPUT_STREAM(), Level.FINE);
            }
         }

      }

   }

   private void ensureMediaType() {
      if (this.getMediaType() == null) {
         GenericType<?> entityType = new GenericType(this.getEntityType());
         List<MediaType> mediaTypes = this.workers.getMessageBodyWriterMediaTypes(entityType.getRawType(), entityType.getType(), this.getEntityAnnotations());
         this.setMediaType(this.getMediaType(mediaTypes));
      }

   }

   private MediaType getMediaType(List mediaTypes) {
      if (mediaTypes.isEmpty()) {
         return MediaType.APPLICATION_OCTET_STREAM_TYPE;
      } else {
         MediaType mediaType = (MediaType)mediaTypes.get(0);
         if (mediaType.isWildcardType() || mediaType.isWildcardSubtype()) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM_TYPE;
         }

         return mediaType;
      }
   }

   void setWriterInterceptors(Iterable writerInterceptors) {
      this.writerInterceptors = writerInterceptors;
   }

   public Iterable getWriterInterceptors() {
      return this.writerInterceptors;
   }

   public Iterable getReaderInterceptors() {
      return this.readerInterceptors;
   }

   void setReaderInterceptors(Iterable readerInterceptors) {
      this.readerInterceptors = readerInterceptors;
   }

   public InjectionManager getInjectionManager() {
      return this.getClientRuntime().getInjectionManager();
   }

   public boolean ignoreUserAgent() {
      return this.ignoreUserAgent;
   }

   public void ignoreUserAgent(boolean ignore) {
      this.ignoreUserAgent = ignore;
   }

   void setCancellable(Future cancellable) {
      this.cancellable = cancellable;
   }

   public void cancel(boolean mayInterruptIfRunning) {
      this.cancellable.cancel(mayInterruptIfRunning);
   }

   public boolean isCancelled() {
      return this.cancellable.isCancelled();
   }

   private static class NotCancellable implements Future {
      public static final Future INSTANCE = new NotCancellable();
      private boolean isCancelled = false;

      public boolean cancel(boolean mayInterruptIfRunning) {
         this.isCancelled = true;
         return this.isCancelled;
      }

      public boolean isCancelled() {
         return this.isCancelled;
      }

      public boolean isDone() {
         return false;
      }

      public Object get() throws InterruptedException, ExecutionException {
         return null;
      }

      public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
         return null;
      }
   }
}
