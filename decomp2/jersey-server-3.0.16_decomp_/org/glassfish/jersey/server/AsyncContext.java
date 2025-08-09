package org.glassfish.jersey.server;

import jakarta.ws.rs.container.AsyncResponse;
import org.glassfish.jersey.internal.util.Producer;

public interface AsyncContext extends AsyncResponse {
   boolean suspend();

   void invokeManaged(Producer var1);

   public static enum State {
      RUNNING,
      SUSPENDED,
      RESUMED,
      COMPLETED;
   }
}
