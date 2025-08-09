package org.glassfish.jersey.client;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;

abstract class AbstractNonSyncInvoker {
   public Object get() {
      return this.method("GET");
   }

   public Object get(Class responseType) {
      return this.method("GET", responseType);
   }

   public Object get(GenericType responseType) {
      return this.method("GET", responseType);
   }

   public Object put(Entity entity) {
      return this.method("PUT", entity);
   }

   public Object put(Entity entity, Class clazz) {
      return this.method("PUT", entity, clazz);
   }

   public Object put(Entity entity, GenericType type) {
      return this.method("PUT", entity, type);
   }

   public Object post(Entity entity) {
      return this.method("POST", entity);
   }

   public Object post(Entity entity, Class clazz) {
      return this.method("POST", entity, clazz);
   }

   public Object post(Entity entity, GenericType type) {
      return this.method("POST", entity, type);
   }

   public Object delete() {
      return this.method("DELETE");
   }

   public Object delete(Class responseType) {
      return this.method("DELETE", responseType);
   }

   public Object delete(GenericType responseType) {
      return this.method("DELETE", responseType);
   }

   public Object head() {
      return this.method("HEAD");
   }

   public Object options() {
      return this.method("OPTIONS");
   }

   public Object options(Class responseType) {
      return this.method("OPTIONS", responseType);
   }

   public Object options(GenericType responseType) {
      return this.method("OPTIONS", responseType);
   }

   public Object trace() {
      return this.method("TRACE");
   }

   public Object trace(Class responseType) {
      return this.method("TRACE", responseType);
   }

   public Object trace(GenericType responseType) {
      return this.method("TRACE", responseType);
   }

   public abstract Object method(String var1);

   public abstract Object method(String var1, Class var2);

   public abstract Object method(String var1, GenericType var2);

   public abstract Object method(String var1, Entity var2);

   public abstract Object method(String var1, Entity var2, Class var3);

   public abstract Object method(String var1, Entity var2, GenericType var3);
}
