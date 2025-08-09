package io.fabric8.kubernetes.api.model;

import java.util.Objects;

public class IntOrStringFluent extends AnyTypeFluent {
   public IntOrStringFluent() {
   }

   public IntOrStringFluent(IntOrString instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IntOrString instance) {
      instance = instance != null ? instance : new IntOrString();
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
            IntOrStringFluent that = (IntOrStringFluent)o;
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
