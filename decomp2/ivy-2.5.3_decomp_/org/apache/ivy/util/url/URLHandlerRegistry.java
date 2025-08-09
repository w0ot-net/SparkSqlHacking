package org.apache.ivy.util.url;

import java.lang.reflect.Field;
import org.apache.ivy.util.Message;

public final class URLHandlerRegistry {
   private static URLHandler defaultHandler = new BasicURLHandler();

   private URLHandlerRegistry() {
   }

   public static URLHandler getDefault() {
      return defaultHandler;
   }

   public static void setDefault(URLHandler def) {
      defaultHandler = def;
   }

   public static TimeoutConstrainedURLHandler getHttp() {
      try {
         Class.forName("org.apache.http.client.HttpClient");
         Class<?> handler = Class.forName("org.apache.ivy.util.url.HttpClientHandler");
         Field instance = handler.getDeclaredField("DELETE_ON_EXIT_INSTANCE");
         return (TimeoutConstrainedURLHandler)instance.get((Object)null);
      } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
         Message.verbose("Using JDK backed URL handler for HTTP interaction since the Apache HttpComponents HttpClient backed handler couldn't be created due to: " + ((ReflectiveOperationException)e).getMessage());
         return new BasicURLHandler();
      }
   }
}
