package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.Status;
import java.io.IOException;

public interface ExecListener {
   default void onOpen() {
   }

   default void onFailure(Throwable t, Response failureResponse) {
   }

   void onClose(int var1, String var2);

   default void onExit(int code, Status status) {
   }

   public interface Response {
      int code();

      String body() throws IOException;
   }
}
