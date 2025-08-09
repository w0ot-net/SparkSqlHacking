package org.glassfish.jersey.server.model.internal;

import jakarta.ws.rs.sse.SseEventSink;
import java.security.AccessController;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.glassfish.jersey.internal.jsr166.Flow;
import org.glassfish.jersey.internal.util.ReflectionHelper;

public final class SseTypeResolver {
   private static final Set SUPPORTED_SSE_SINK_TYPES;

   private SseTypeResolver() {
   }

   public static boolean isSseSinkParam(Class type) {
      return SUPPORTED_SSE_SINK_TYPES.contains(type);
   }

   static {
      Set<Class<?>> set = new HashSet(8);
      set.add(Flow.Subscriber.class);
      set.add(SseEventSink.class);
      Class<?> clazz = (Class)AccessController.doPrivileged(ReflectionHelper.classForNamePA("java.util.concurrent.Flow$Subscriber", (ClassLoader)null));
      if (clazz != null) {
         set.add(clazz);
      }

      SUPPORTED_SSE_SINK_TYPES = Collections.unmodifiableSet(set);
   }
}
