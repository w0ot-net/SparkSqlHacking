package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;
import javax.xml.transform.Source;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.internal.util.collection.GuardianStringKeyMultivaluedMap;
import org.glassfish.jersey.internal.util.collection.LazyValue;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.message.MessageBodyWorkers;

public abstract class InboundMessageContext extends MessageHeaderMethods {
   private static final InputStream EMPTY = new InputStream() {
      public int read() throws IOException {
         return -1;
      }

      public void mark(int readlimit) {
      }

      public void reset() throws IOException {
      }

      public boolean markSupported() {
         return true;
      }
   };
   private static final Annotation[] EMPTY_ANNOTATIONS = new Annotation[0];
   private static final List WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST;
   private final GuardianStringKeyMultivaluedMap headers;
   private final EntityContent entityContent;
   private final boolean translateNce;
   private MessageBodyWorkers workers;
   private final Configuration configuration;
   private LazyValue contentTypeCache;
   private LazyValue acceptTypeCache;

   public InboundMessageContext(Configuration configuration) {
      this(configuration, false);
   }

   public InboundMessageContext(Configuration configuration, boolean translateNce) {
      super(configuration);
      this.headers = new GuardianStringKeyMultivaluedMap(HeaderUtils.createInbound());
      this.entityContent = new EntityContent();
      this.translateNce = translateNce;
      this.configuration = configuration;
      this.contentTypeCache = this.contentTypeCache();
      this.acceptTypeCache = this.acceptTypeCache();
      this.headers.setGuard("Content-Type");
      this.headers.setGuard("Accept");
   }

   /** @deprecated */
   @Deprecated
   public InboundMessageContext() {
      this((Configuration)null);
   }

   /** @deprecated */
   @Deprecated
   public InboundMessageContext(boolean translateNce) {
      this((Configuration)null, translateNce);
   }

   public InboundMessageContext header(String name, Object value) {
      this.getHeaders().add(name, HeaderUtils.asString(value, this.runtimeDelegateDecorator));
      return this;
   }

   public InboundMessageContext headers(String name, Object... values) {
      this.getHeaders().addAll(name, HeaderUtils.asStringList(Arrays.asList(values), this.runtimeDelegateDecorator));
      return this;
   }

   public InboundMessageContext headers(String name, Iterable values) {
      this.getHeaders().addAll(name, this.iterableToList(values));
      return this;
   }

   public InboundMessageContext headers(MultivaluedMap newHeaders) {
      for(Map.Entry header : newHeaders.entrySet()) {
         this.headers.addAll((String)header.getKey(), (List)header.getValue());
      }

      return this;
   }

   public InboundMessageContext headers(Map newHeaders) {
      for(Map.Entry header : newHeaders.entrySet()) {
         this.headers.addAll((String)header.getKey(), (List)header.getValue());
      }

      return this;
   }

   public InboundMessageContext remove(String name) {
      this.getHeaders().remove(name);
      return this;
   }

   private List iterableToList(Iterable values) {
      LinkedList<String> linkedList = new LinkedList();

      for(Object element : values) {
         linkedList.add(HeaderUtils.asString(element, this.runtimeDelegateDecorator));
      }

      return linkedList;
   }

   public String getHeaderString(String name) {
      List<String> values = this.headers.get(name);
      if (values == null) {
         return null;
      } else if (values.isEmpty()) {
         return "";
      } else {
         Iterator<String> valuesIterator = values.iterator();
         StringBuilder buffer = new StringBuilder((String)valuesIterator.next());

         while(valuesIterator.hasNext()) {
            buffer.append(',').append((String)valuesIterator.next());
         }

         return buffer.toString();
      }
   }

   public HeaderValueException.Context getHeaderValueExceptionContext() {
      return HeaderValueException.Context.INBOUND;
   }

   public MultivaluedMap getHeaders() {
      return this.headers;
   }

   public Set getIfMatch() {
      String ifMatch = this.getHeaderString("If-Match");
      if (ifMatch != null && !ifMatch.isEmpty()) {
         try {
            return HttpHeaderReader.readMatchingEntityTag(ifMatch);
         } catch (ParseException e) {
            throw this.exception("If-Match", ifMatch, e);
         }
      } else {
         return null;
      }
   }

   public Set getIfNoneMatch() {
      String ifNoneMatch = this.getHeaderString("If-None-Match");
      if (ifNoneMatch != null && !ifNoneMatch.isEmpty()) {
         try {
            return HttpHeaderReader.readMatchingEntityTag(ifNoneMatch);
         } catch (ParseException e) {
            throw this.exception("If-None-Match", ifNoneMatch, e);
         }
      } else {
         return null;
      }
   }

   public MediaType getMediaType() {
      if (this.headers.isObservedAndReset("Content-Type") && this.contentTypeCache.isInitialized()) {
         this.contentTypeCache = this.contentTypeCache();
      }

      return (MediaType)this.contentTypeCache.get();
   }

   private LazyValue contentTypeCache() {
      return Values.lazy((Value)(() -> (MediaType)this.singleHeader("Content-Type", new Function() {
            public MediaType apply(String input) {
               try {
                  return (MediaType)InboundMessageContext.this.runtimeDelegateDecorator.createHeaderDelegate(MediaType.class).fromString(input);
               } catch (IllegalArgumentException iae) {
                  throw new ProcessingException(iae);
               }
            }
         }, false)));
   }

   public List getQualifiedAcceptableMediaTypes() {
      if (this.headers.isObservedAndReset("Accept") && this.acceptTypeCache.isInitialized()) {
         this.acceptTypeCache = this.acceptTypeCache();
      }

      return (List)this.acceptTypeCache.get();
   }

   private LazyValue acceptTypeCache() {
      return Values.lazy((Value)(() -> {
         String value = this.getHeaderString("Accept");
         if (value != null && !value.isEmpty()) {
            try {
               return Collections.unmodifiableList(HttpHeaderReader.readAcceptMediaType(value));
            } catch (ParseException e) {
               throw this.exception("Accept", value, e);
            }
         } else {
            return WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST;
         }
      }));
   }

   public List getQualifiedAcceptableLanguages() {
      String value = this.getHeaderString("Accept-Language");
      if (value != null && !value.isEmpty()) {
         try {
            return Collections.unmodifiableList(HttpHeaderReader.readAcceptLanguage(value));
         } catch (ParseException e) {
            throw this.exception("Accept-Language", value, e);
         }
      } else {
         return Collections.singletonList(new AcceptableLanguageTag("*", (String)null));
      }
   }

   public List getQualifiedAcceptCharset() {
      String acceptCharset = this.getHeaderString("Accept-Charset");

      try {
         return acceptCharset != null && !acceptCharset.isEmpty() ? HttpHeaderReader.readAcceptToken(acceptCharset) : Collections.singletonList(new AcceptableToken("*"));
      } catch (ParseException e) {
         throw this.exception("Accept-Charset", acceptCharset, e);
      }
   }

   public List getQualifiedAcceptEncoding() {
      String acceptEncoding = this.getHeaderString("Accept-Encoding");

      try {
         return acceptEncoding != null && !acceptEncoding.isEmpty() ? HttpHeaderReader.readAcceptToken(acceptEncoding) : Collections.singletonList(new AcceptableToken("*"));
      } catch (ParseException e) {
         throw this.exception("Accept-Encoding", acceptEncoding, e);
      }
   }

   public Set getLinks() {
      List<String> links = this.headers.get("Link");
      if (links != null && !links.isEmpty()) {
         try {
            Set<Link> result = new HashSet(links.size());

            for(String link : links) {
               StringBuilder linkString = new StringBuilder();
               StringTokenizer st = new StringTokenizer(link, "<>,", true);
               boolean linkOpen = false;

               while(st.hasMoreTokens()) {
                  String n = st.nextToken();
                  if (n.equals("<")) {
                     linkOpen = true;
                  } else if (n.equals(">")) {
                     linkOpen = false;
                  } else if (!linkOpen && n.equals(",")) {
                     result.add(Link.valueOf(linkString.toString().trim()));
                     linkString = new StringBuilder();
                     continue;
                  }

                  linkString.append(n);
               }

               if (linkString.length() > 0) {
                  result.add(Link.valueOf(linkString.toString().trim()));
               }
            }

            return result;
         } catch (IllegalArgumentException e) {
            throw this.exception("Link", links, e);
         }
      } else {
         return Collections.emptySet();
      }
   }

   public MessageBodyWorkers getWorkers() {
      if (this.workers == null) {
         throw new ProcessingException(LocalizationMessages.RESPONSE_CLOSED());
      } else {
         return this.workers;
      }
   }

   public void setWorkers(MessageBodyWorkers workers) {
      this.workers = workers;
   }

   public boolean hasEntity() {
      this.entityContent.ensureNotClosed();

      try {
         return this.entityContent.isBuffered() || !this.entityContent.isEmpty();
      } catch (IllegalStateException var2) {
         return false;
      }
   }

   public InputStream getEntityStream() {
      this.entityContent.ensureNotClosed();
      return this.entityContent.getWrappedStream();
   }

   public void setEntityStream(InputStream input) {
      this.entityContent.setContent(input, false);
   }

   public Object readEntity(Class rawType, PropertiesDelegate propertiesDelegate) {
      return this.readEntity(rawType, rawType, EMPTY_ANNOTATIONS, propertiesDelegate);
   }

   public Object readEntity(Class rawType, Annotation[] annotations, PropertiesDelegate propertiesDelegate) {
      return this.readEntity(rawType, rawType, annotations, propertiesDelegate);
   }

   public Object readEntity(Class rawType, Type type, PropertiesDelegate propertiesDelegate) {
      return this.readEntity(rawType, type, EMPTY_ANNOTATIONS, propertiesDelegate);
   }

   public Object readEntity(Class rawType, Type type, Annotation[] annotations, PropertiesDelegate propertiesDelegate) {
      boolean buffered = this.entityContent.isBuffered();
      if (buffered) {
         this.entityContent.reset();
      }

      this.entityContent.ensureNotClosed();
      if (this.workers == null) {
         return null;
      } else {
         MediaType mediaType = this.getMediaType();
         mediaType = mediaType == null ? MediaType.APPLICATION_OCTET_STREAM_TYPE : mediaType;
         boolean shouldClose = !buffered;

         Object var9;
         try {
            T t = (T)this.workers.readFrom(rawType, type, annotations, mediaType, this.headers, propertiesDelegate, this.entityContent.getWrappedStream(), (Iterable)(this.entityContent.hasContent() ? this.getReaderInterceptors() : Collections.emptyList()), this.translateNce);
            shouldClose = shouldClose && !(t instanceof Closeable) && !(t instanceof Source);
            var9 = t;
         } catch (IOException ex) {
            throw new ProcessingException(LocalizationMessages.ERROR_READING_ENTITY_FROM_INPUT_STREAM(), ex);
         } finally {
            if (shouldClose) {
               ReaderWriter.safelyClose(this.entityContent);
            }

         }

         return var9;
      }
   }

   public boolean bufferEntity() throws ProcessingException {
      this.entityContent.ensureNotClosed();

      try {
         if (!this.entityContent.isBuffered() && this.entityContent.hasContent()) {
            InputStream entityStream = this.entityContent.getWrappedStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
               ReaderWriter.writeTo((InputStream)entityStream, (OutputStream)baos);
            } finally {
               ReaderWriter.safelyClose(entityStream);
            }

            this.entityContent.setContent(new ByteArrayInputStream(baos.toByteArray()), true);
            return true;
         } else {
            return true;
         }
      } catch (IOException ex) {
         throw new ProcessingException(LocalizationMessages.MESSAGE_CONTENT_BUFFERING_FAILED(), ex);
      }
   }

   public void close() {
      this.entityContent.close(true);
      this.setWorkers((MessageBodyWorkers)null);
   }

   protected abstract Iterable getReaderInterceptors();

   public Configuration getConfiguration() {
      return this.configuration;
   }

   static {
      WILDCARD_ACCEPTABLE_TYPE_SINGLETON_LIST = Collections.singletonList(MediaTypes.WILDCARD_ACCEPTABLE_TYPE);
   }

   private static class EntityContent extends EntityInputStream {
      private boolean buffered;

      EntityContent() {
         super(InboundMessageContext.EMPTY);
      }

      void setContent(InputStream content, boolean buffered) {
         this.buffered = buffered;
         this.setWrappedStream(content);
      }

      boolean hasContent() {
         return this.getWrappedStream() != InboundMessageContext.EMPTY;
      }

      boolean isBuffered() {
         return this.buffered;
      }

      public void close() {
         this.close(false);
      }

      void close(boolean force) {
         if (!this.buffered || force) {
            try {
               super.close();
            } finally {
               this.buffered = false;
               this.setWrappedStream((InputStream)null);
            }

         }
      }
   }
}
