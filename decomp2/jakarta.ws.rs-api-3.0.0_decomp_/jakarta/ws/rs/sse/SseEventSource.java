package jakarta.ws.rs.sse;

import jakarta.ws.rs.client.WebTarget;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public interface SseEventSource extends AutoCloseable {
   void register(Consumer var1);

   void register(Consumer var1, Consumer var2);

   void register(Consumer var1, Consumer var2, Runnable var3);

   static Builder target(WebTarget endpoint) {
      return SseEventSource.Builder.newBuilder().target(endpoint);
   }

   void open();

   boolean isOpen();

   default void close() {
      this.close(5L, TimeUnit.SECONDS);
   }

   boolean close(long var1, TimeUnit var3);

   public abstract static class Builder {
      public static final String JAXRS_DEFAULT_SSE_BUILDER_PROPERTY = "jakarta.ws.rs.sse.SseEventSource.Builder";

      protected Builder() {
      }

      static Builder newBuilder() {
         try {
            Object delegate = FactoryFinder.find("jakarta.ws.rs.sse.SseEventSource.Builder", Builder.class);
            if (!(delegate instanceof Builder)) {
               Class pClass = Builder.class;
               String classnameAsResource = pClass.getName().replace('.', '/') + ".class";
               ClassLoader loader = pClass.getClassLoader();
               if (loader == null) {
                  loader = ClassLoader.getSystemClassLoader();
               }

               URL targetTypeURL = loader.getResource(classnameAsResource);
               throw new LinkageError("ClassCastException: attempting to cast" + delegate.getClass().getClassLoader().getResource(classnameAsResource) + " to " + targetTypeURL);
            } else {
               return (Builder)delegate;
            }
         } catch (Exception ex) {
            throw new RuntimeException(ex);
         }
      }

      protected abstract Builder target(WebTarget var1);

      public abstract Builder reconnectingEvery(long var1, TimeUnit var3);

      public abstract SseEventSource build();
   }
}
