package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.internal.RuntimeDelegateDecorator;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.GuardianStringKeyMultivaluedMap;
import org.glassfish.jersey.internal.util.collection.LazyValue;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.io.spi.FlushedCloseable;

public class OutboundMessageContext extends MessageHeaderMethods {
   private static final Annotation[] EMPTY_ANNOTATIONS = new Annotation[0];
   private static final List WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST;
   private final GuardianStringKeyMultivaluedMap headers;
   private final CommittingOutputStream committingOutputStream;
   private Configuration configuration;
   private LazyValue mediaTypeCache;
   private Object entity;
   private GenericType entityType;
   private Annotation[] entityAnnotations;
   private OutputStream entityStream;

   public OutboundMessageContext(Configuration configuration) {
      super(configuration);
      this.entityAnnotations = EMPTY_ANNOTATIONS;
      this.configuration = configuration;
      this.headers = new GuardianStringKeyMultivaluedMap(HeaderUtils.createOutbound());
      this.committingOutputStream = new CommittingOutputStream();
      this.entityStream = this.committingOutputStream;
      this.mediaTypeCache = this.mediaTypeCache();
      this.headers.setGuard("Content-Type");
   }

   public OutboundMessageContext(OutboundMessageContext original) {
      super((MessageHeaderMethods)original);
      this.entityAnnotations = EMPTY_ANNOTATIONS;
      this.headers = new GuardianStringKeyMultivaluedMap(HeaderUtils.createOutbound());
      this.headers.setGuard("Content-Type");
      this.headers.putAll(original.headers);
      this.committingOutputStream = new CommittingOutputStream();
      this.entityStream = this.committingOutputStream;
      this.entity = original.entity;
      this.entityType = original.entityType;
      this.entityAnnotations = original.entityAnnotations;
      this.configuration = original.configuration;
      this.mediaTypeCache = this.mediaTypeCache();
   }

   /** @deprecated */
   @Deprecated
   public OutboundMessageContext() {
      this((Configuration)null);
   }

   public void replaceHeaders(MultivaluedMap headers) {
      this.getHeaders().clear();
      if (headers != null) {
         this.getHeaders().putAll(headers);
      }

   }

   public MultivaluedMap getStringHeaders() {
      return HeaderUtils.asStringHeaders(this.headers, (RuntimeDelegate)this.runtimeDelegateDecorator);
   }

   public String getHeaderString(String name) {
      return HeaderUtils.asHeaderString(this.headers.get(name), this.runtimeDelegateDecorator);
   }

   public HeaderValueException.Context getHeaderValueExceptionContext() {
      return HeaderValueException.Context.OUTBOUND;
   }

   public MultivaluedMap getHeaders() {
      return this.headers;
   }

   public MediaType getMediaType() {
      if (this.headers.isObservedAndReset("Content-Type") && this.mediaTypeCache.isInitialized()) {
         this.mediaTypeCache = this.mediaTypeCache();
      }

      return (MediaType)this.mediaTypeCache.get();
   }

   private LazyValue mediaTypeCache() {
      return Values.lazy((Value)(() -> (MediaType)this.singleHeader("Content-Type", MediaType.class, RuntimeDelegateDecorator.configured(this.configuration).createHeaderDelegate(MediaType.class)::fromString, false)));
   }

   public List getAcceptableMediaTypes() {
      List<Object> values = this.headers.get("Accept");
      if (values != null && !values.isEmpty()) {
         List<MediaType> result = new ArrayList(values.size());
         boolean conversionApplied = false;

         for(Object value : values) {
            try {
               if (value instanceof MediaType) {
                  AcceptableMediaType _value = AcceptableMediaType.valueOf((MediaType)value);
                  conversionApplied = _value != value;
                  result.add(_value);
               } else {
                  conversionApplied = true;
                  result.addAll(HttpHeaderReader.readAcceptMediaType(HeaderUtils.asString(value, this.runtimeDelegateDecorator)));
               }
            } catch (ParseException e) {
               throw this.exception("Accept", value, e);
            }
         }

         if (conversionApplied) {
            this.headers.put("Accept", (List)result.stream().map((mediaType) -> mediaType).collect(Collectors.toList()));
         }

         return Collections.unmodifiableList(result);
      } else {
         return WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST;
      }
   }

   public List getAcceptableLanguages() {
      List<Object> values = this.headers.get("Accept-Language");
      if (values != null && !values.isEmpty()) {
         List<Locale> result = new ArrayList(values.size());
         boolean conversionApplied = false;

         for(Object value : values) {
            if (value instanceof Locale) {
               result.add((Locale)value);
            } else {
               conversionApplied = true;

               try {
                  result.addAll((Collection)HttpHeaderReader.readAcceptLanguage(HeaderUtils.asString(value, this.runtimeDelegateDecorator)).stream().map(LanguageTag::getAsLocale).collect(Collectors.toList()));
               } catch (ParseException e) {
                  throw this.exception("Accept-Language", value, e);
               }
            }
         }

         if (conversionApplied) {
            this.headers.put("Accept-Language", (List)result.stream().map((locale) -> locale).collect(Collectors.toList()));
         }

         return Collections.unmodifiableList(result);
      } else {
         return Collections.singletonList((new AcceptableLanguageTag("*", (String)null)).getAsLocale());
      }
   }

   public Set getLinks() {
      List<Object> values = this.headers.get("Link");
      if (values != null && !values.isEmpty()) {
         Set<Link> result = new HashSet(values.size());
         boolean conversionApplied = false;

         for(Object value : values) {
            if (value instanceof Link) {
               result.add((Link)value);
            } else {
               conversionApplied = true;

               try {
                  result.add(Link.valueOf(HeaderUtils.asString(value, this.runtimeDelegateDecorator)));
               } catch (IllegalArgumentException e) {
                  throw this.exception("Link", value, e);
               }
            }
         }

         if (conversionApplied) {
            this.headers.put("Link", (List)result.stream().map((link) -> link).collect(Collectors.toList()));
         }

         return Collections.unmodifiableSet(result);
      } else {
         return Collections.emptySet();
      }
   }

   public boolean hasEntity() {
      return this.entity != null;
   }

   public Object getEntity() {
      return this.entity;
   }

   public void setEntity(Object entity) {
      this.setEntity(entity, ReflectionHelper.genericTypeFor(entity));
   }

   public void setEntity(Object entity, Annotation[] annotations) {
      this.setEntity(entity, ReflectionHelper.genericTypeFor(entity));
      this.setEntityAnnotations(annotations);
   }

   private void setEntity(Object entity, GenericType type) {
      if (entity instanceof GenericEntity) {
         this.entity = ((GenericEntity)entity).getEntity();
      } else {
         this.entity = entity;
      }

      this.entityType = type;
   }

   public void setEntity(Object entity, Type type, Annotation[] annotations) {
      this.setEntity(entity, new GenericType(type));
      this.setEntityAnnotations(annotations);
   }

   public void setEntity(Object entity, Annotation[] annotations, MediaType mediaType) {
      this.setEntity(entity, annotations);
      this.setMediaType(mediaType);
   }

   public void setMediaType(MediaType mediaType) {
      this.headers.putSingle((String)"Content-Type", mediaType);
   }

   public Class getEntityClass() {
      return this.entityType == null ? null : this.entityType.getRawType();
   }

   public Type getEntityType() {
      return this.entityType == null ? null : this.entityType.getType();
   }

   public void setEntityType(Type type) {
      this.entityType = new GenericType(type);
   }

   public Annotation[] getEntityAnnotations() {
      return (Annotation[])this.entityAnnotations.clone();
   }

   public void setEntityAnnotations(Annotation[] annotations) {
      this.entityAnnotations = annotations == null ? EMPTY_ANNOTATIONS : annotations;
   }

   public OutputStream getEntityStream() {
      return this.entityStream;
   }

   public void setEntityStream(OutputStream outputStream) {
      this.entityStream = outputStream;
   }

   public void enableBuffering(Configuration configuration) {
      Integer bufferSize = (Integer)CommonProperties.getValue(configuration.getProperties(), configuration.getRuntimeType(), "jersey.config.contentLength.buffer", Integer.class);
      if (bufferSize != null) {
         this.committingOutputStream.enableBuffering(bufferSize);
      } else {
         this.committingOutputStream.enableBuffering();
      }

   }

   public void setStreamProvider(StreamProvider streamProvider) {
      this.committingOutputStream.setStreamProvider(streamProvider);
   }

   public void commitStream() throws IOException {
      if (!this.committingOutputStream.isCommitted()) {
         this.entityStream.flush();
         if (!this.committingOutputStream.isCommitted()) {
            this.committingOutputStream.commit();
            this.committingOutputStream.flush();
         }
      }

   }

   public boolean isCommitted() {
      return this.committingOutputStream.isCommitted();
   }

   public void close() {
      if (this.hasEntity()) {
         try {
            OutputStream es = this.getEntityStream();
            if (!FlushedCloseable.class.isInstance(es)) {
               es.flush();
            }

            es.close();
         } catch (IOException e) {
            Logger.getLogger(OutboundMessageContext.class.getName()).log(Level.FINE, e.getMessage(), e);
         } finally {
            if (!this.committingOutputStream.isClosed()) {
               try {
                  this.committingOutputStream.close();
               } catch (IOException e) {
                  Logger.getLogger(OutboundMessageContext.class.getName()).log(Level.FINE, e.getMessage(), e);
               }
            }

         }
      }

   }

   void setConfiguration(Configuration configuration) {
      this.configuration = configuration;
      this.runtimeDelegateDecorator = RuntimeDelegateDecorator.configured(configuration);
   }

   public Configuration getConfiguration() {
      return this.configuration;
   }

   static {
      WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST = Collections.singletonList(MediaTypes.WILDCARD_ACCEPTABLE_TYPE);
   }

   public interface StreamProvider {
      OutputStream getOutputStream(int var1) throws IOException;
   }
}
