package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.NoContentException;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.glassfish.jersey.innate.io.InputStreamWrapper;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.InjectionManagerSupplier;
import org.glassfish.jersey.message.MessageBodyWorkers;

public final class ReaderInterceptorExecutor extends InterceptorExecutor implements ReaderInterceptorContext, InjectionManagerSupplier {
   private static final Logger LOGGER = Logger.getLogger(ReaderInterceptorExecutor.class.getName());
   private final MultivaluedMap headers;
   private final Iterator interceptors;
   private final MessageBodyWorkers workers;
   private final boolean translateNce;
   private final InjectionManager injectionManager;
   private InputStream inputStream;
   private int processedCount;

   ReaderInterceptorExecutor(Class rawType, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap headers, PropertiesDelegate propertiesDelegate, InputStream inputStream, MessageBodyWorkers workers, Iterable readerInterceptors, boolean translateNce, InjectionManager injectionManager) {
      super(rawType, type, annotations, mediaType, propertiesDelegate);
      this.headers = headers;
      this.inputStream = inputStream;
      this.workers = workers;
      this.translateNce = translateNce;
      this.injectionManager = injectionManager;
      List<ReaderInterceptor> effectiveInterceptors = (List)StreamSupport.stream(readerInterceptors.spliterator(), false).collect(Collectors.toList());
      effectiveInterceptors.add(new TerminalReaderInterceptor());
      this.interceptors = effectiveInterceptors.iterator();
      this.processedCount = 0;
   }

   public Object proceed() throws IOException {
      if (!this.interceptors.hasNext()) {
         throw new ProcessingException(LocalizationMessages.ERROR_INTERCEPTOR_READER_PROCEED());
      } else {
         ReaderInterceptor interceptor = (ReaderInterceptor)this.interceptors.next();
         this.traceBefore(interceptor, MsgTraceEvent.RI_BEFORE);

         Object var2;
         try {
            var2 = interceptor.aroundReadFrom(this);
         } finally {
            ++this.processedCount;
            this.traceAfter(interceptor, MsgTraceEvent.RI_AFTER);
         }

         return var2;
      }
   }

   public InputStream getInputStream() {
      return this.inputStream;
   }

   public void setInputStream(InputStream is) {
      this.inputStream = is;
   }

   public MultivaluedMap getHeaders() {
      return this.headers;
   }

   int getProcessedCount() {
      return this.processedCount;
   }

   public InjectionManager getInjectionManager() {
      return this.injectionManager;
   }

   public static InputStream closeableInputStream(InputStream inputStream) {
      return inputStream instanceof UnCloseableInputStream ? ((UnCloseableInputStream)inputStream).getWrapped() : inputStream;
   }

   private class TerminalReaderInterceptor implements ReaderInterceptor {
      private TerminalReaderInterceptor() {
      }

      public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
         ReaderInterceptorExecutor.this.processedCount--;
         ReaderInterceptorExecutor.this.traceBefore((Object)null, MsgTraceEvent.RI_BEFORE);

         Object entity;
         try {
            TracingLogger tracingLogger = ReaderInterceptorExecutor.this.getTracingLogger();
            if (tracingLogger.isLogEnabled(MsgTraceEvent.MBR_FIND)) {
               tracingLogger.log(MsgTraceEvent.MBR_FIND, context.getType().getName(), context.getGenericType() instanceof Class ? ((Class)context.getGenericType()).getName() : context.getGenericType(), String.valueOf(context.getMediaType()), Arrays.toString(context.getAnnotations()));
            }

            MessageBodyReader bodyReader = ReaderInterceptorExecutor.this.workers.getMessageBodyReader(context.getType(), context.getGenericType(), context.getAnnotations(), context.getMediaType(), ReaderInterceptorExecutor.this);
            EntityInputStream input = new EntityInputStream(context.getInputStream());
            if (bodyReader != null) {
               entity = this.invokeReadFrom(context, bodyReader, input);
               if (bodyReader instanceof CompletableReader) {
                  entity = ((CompletableReader)bodyReader).complete(entity);
               }

               Object var6 = entity;
               return var6;
            }

            if (!input.isEmpty() || context.getHeaders().containsKey("Content-Type")) {
               ReaderInterceptorExecutor.LOGGER.log(Level.FINE, LocalizationMessages.ERROR_NOTFOUND_MESSAGEBODYREADER(context.getMediaType(), context.getType(), context.getGenericType()));
               throw new MessageBodyProviderNotFoundException(LocalizationMessages.ERROR_NOTFOUND_MESSAGEBODYREADER(context.getMediaType(), context.getType(), context.getGenericType()));
            }

            entity = null;
         } finally {
            ReaderInterceptorExecutor.this.clearLastTracedInterceptor();
            ReaderInterceptorExecutor.this.traceAfter((Object)null, MsgTraceEvent.RI_AFTER);
         }

         return entity;
      }

      private Object invokeReadFrom(ReaderInterceptorContext context, MessageBodyReader reader, EntityInputStream input) throws WebApplicationException, IOException {
         TracingLogger tracingLogger = ReaderInterceptorExecutor.this.getTracingLogger();
         long timestamp = tracingLogger.timestamp(MsgTraceEvent.MBR_READ_FROM);
         InputStream stream = new UnCloseableInputStream(input, reader);

         Object var8;
         try {
            var8 = reader.readFrom(context.getType(), context.getGenericType(), context.getAnnotations(), context.getMediaType(), context.getHeaders(), stream);
         } catch (NoContentException ex) {
            if (ReaderInterceptorExecutor.this.translateNce) {
               throw new BadRequestException(ex);
            }

            throw ex;
         } finally {
            tracingLogger.logDuration(MsgTraceEvent.MBR_READ_FROM, timestamp, reader);
         }

         return var8;
      }
   }

   private static class UnCloseableInputStream extends InputStreamWrapper {
      private final InputStream original;
      private final MessageBodyReader reader;

      private UnCloseableInputStream(InputStream original, MessageBodyReader reader) {
         this.original = original;
         this.reader = reader;
      }

      protected InputStream getWrapped() {
         return this.original;
      }

      public void close() throws IOException {
         if (ReaderInterceptorExecutor.LOGGER.isLoggable(Level.FINE)) {
            ReaderInterceptorExecutor.LOGGER.log(Level.FINE, LocalizationMessages.MBR_TRYING_TO_CLOSE_STREAM(this.reader.getClass()));
         }

      }
   }
}
