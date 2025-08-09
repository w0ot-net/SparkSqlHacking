package org.glassfish.jersey.server.spi;

public class ExternalRequestContext {
   private final Object context;

   public ExternalRequestContext(Object context) {
      this.context = context;
   }

   public Object getContext() {
      return this.context;
   }
}
