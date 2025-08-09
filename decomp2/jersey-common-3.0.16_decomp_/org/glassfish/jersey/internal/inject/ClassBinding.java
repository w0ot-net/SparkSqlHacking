package org.glassfish.jersey.internal.inject;

public class ClassBinding extends Binding {
   private final Class service;

   ClassBinding(Class service) {
      this.service = service;
      this.asType(service);
   }

   public Class getService() {
      return this.service;
   }
}
