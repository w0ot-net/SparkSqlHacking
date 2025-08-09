package org.glassfish.jersey.internal.inject;

import java.lang.reflect.Type;

public class InstanceBinding extends Binding {
   private final Object service;

   InstanceBinding(Object service) {
      this(service, (Type)null);
   }

   InstanceBinding(Object service, Type contractType) {
      this.service = service;
      if (contractType != null) {
         this.to(contractType);
      }

      this.asType(service.getClass());
   }

   public Object getService() {
      return this.service;
   }
}
