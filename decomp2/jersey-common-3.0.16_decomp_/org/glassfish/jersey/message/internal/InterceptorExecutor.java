package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.InterceptorContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import org.glassfish.jersey.internal.PropertiesDelegate;

abstract class InterceptorExecutor implements InterceptorContext, PropertiesDelegate {
   private final PropertiesDelegate propertiesDelegate;
   private Annotation[] annotations;
   private Class type;
   private Type genericType;
   private MediaType mediaType;
   private final TracingLogger tracingLogger;
   private InterceptorTimestampPair lastTracedInterceptor;

   public InterceptorExecutor(Class rawType, Type type, Annotation[] annotations, MediaType mediaType, PropertiesDelegate propertiesDelegate) {
      this.type = rawType;
      this.genericType = type;
      this.annotations = annotations;
      this.mediaType = mediaType;
      this.propertiesDelegate = propertiesDelegate;
      this.tracingLogger = TracingLogger.getInstance(propertiesDelegate);
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

   protected final TracingLogger getTracingLogger() {
      return this.tracingLogger;
   }

   protected final void traceBefore(Object interceptor, TracingLogger.Event event) {
      if (this.tracingLogger.isLogEnabled(event)) {
         if (this.lastTracedInterceptor != null && interceptor != null) {
            this.tracingLogger.logDuration(event, this.lastTracedInterceptor.getTimestamp(), this.lastTracedInterceptor.getInterceptor());
         }

         this.lastTracedInterceptor = new InterceptorTimestampPair(interceptor, System.nanoTime());
      }

   }

   protected final void traceAfter(Object interceptor, TracingLogger.Event event) {
      if (this.tracingLogger.isLogEnabled(event)) {
         if (this.lastTracedInterceptor != null && this.lastTracedInterceptor.getInterceptor() != null) {
            this.tracingLogger.logDuration(event, this.lastTracedInterceptor.getTimestamp(), interceptor);
         }

         this.lastTracedInterceptor = new InterceptorTimestampPair(interceptor, System.nanoTime());
      }

   }

   protected final void clearLastTracedInterceptor() {
      this.lastTracedInterceptor = null;
   }

   public Annotation[] getAnnotations() {
      return this.annotations;
   }

   public void setAnnotations(Annotation[] annotations) {
      if (annotations == null) {
         throw new NullPointerException("Annotations must not be null.");
      } else {
         this.annotations = annotations;
      }
   }

   public Class getType() {
      return this.type;
   }

   public void setType(Class type) {
      this.type = type;
   }

   public Type getGenericType() {
      return this.genericType;
   }

   public void setGenericType(Type genericType) {
      this.genericType = genericType;
   }

   public MediaType getMediaType() {
      return this.mediaType;
   }

   public void setMediaType(MediaType mediaType) {
      this.mediaType = mediaType;
   }

   private static class InterceptorTimestampPair {
      private final Object interceptor;
      private final long timestamp;

      private InterceptorTimestampPair(Object interceptor, long timestamp) {
         this.interceptor = interceptor;
         this.timestamp = timestamp;
      }

      private Object getInterceptor() {
         return this.interceptor;
      }

      private long getTimestamp() {
         return this.timestamp;
      }
   }
}
