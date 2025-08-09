package org.glassfish.jersey.server.model;

import java.lang.reflect.Constructor;
import java.util.List;
import org.glassfish.jersey.model.Parameter.Source;

public final class HandlerConstructor implements Parameterized, ResourceModelComponent {
   private final Constructor constructor;
   private final List parameters;

   HandlerConstructor(Constructor constructor, List parameters) {
      this.constructor = constructor;
      this.parameters = parameters;
   }

   public Constructor getConstructor() {
      return this.constructor;
   }

   public List getParameters() {
      return this.parameters;
   }

   public boolean requiresEntity() {
      for(Parameter p : this.getParameters()) {
         if (Source.ENTITY == p.getSource()) {
            return true;
         }
      }

      return false;
   }

   public void accept(ResourceModelVisitor visitor) {
      visitor.visitResourceHandlerConstructor(this);
   }

   public List getComponents() {
      return null;
   }
}
