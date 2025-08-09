package org.glassfish.jersey.client;

import jakarta.ws.rs.client.AsyncInvoker;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;

abstract class CompletableFutureAsyncInvoker extends AbstractNonSyncInvoker implements AsyncInvoker {
   public CompletableFuture get(InvocationCallback callback) {
      return this.method("GET", callback);
   }

   public CompletableFuture put(Entity entity, InvocationCallback callback) {
      return this.method("PUT", entity, callback);
   }

   public CompletableFuture post(Entity entity, InvocationCallback callback) {
      return this.method("POST", entity, callback);
   }

   public CompletableFuture delete(InvocationCallback callback) {
      return this.method("DELETE", callback);
   }

   public CompletableFuture head(InvocationCallback callback) {
      return this.method("HEAD", callback);
   }

   public CompletableFuture options(InvocationCallback callback) {
      return this.method("OPTIONS", callback);
   }

   public CompletableFuture trace(InvocationCallback callback) {
      return this.method("TRACE", callback);
   }

   public abstract CompletableFuture method(String var1, InvocationCallback var2);

   public abstract CompletableFuture method(String var1, Entity var2, InvocationCallback var3);

   public abstract CompletableFuture method(String var1, Entity var2, Class var3);

   public abstract CompletableFuture method(String var1, Entity var2, GenericType var3);
}
