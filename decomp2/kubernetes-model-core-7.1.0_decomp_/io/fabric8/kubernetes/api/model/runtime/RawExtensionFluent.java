package io.fabric8.kubernetes.api.model.runtime;

import io.fabric8.kubernetes.api.model.AnyTypeFluent;
import java.util.Objects;

public class RawExtensionFluent extends AnyTypeFluent {
   public RawExtensionFluent() {
   }

   public RawExtensionFluent(RawExtension instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RawExtension instance) {
      instance = instance != null ? instance : new RawExtension();
      if (instance != null) {
         this.withValue(instance.getValue());
      }

   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            RawExtensionFluent that = (RawExtensionFluent)o;
            return true;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      sb.append("}");
      return sb.toString();
   }
}
