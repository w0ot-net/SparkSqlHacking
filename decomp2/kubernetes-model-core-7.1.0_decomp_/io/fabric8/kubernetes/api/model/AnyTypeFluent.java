package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.Objects;

public class AnyTypeFluent extends BaseFluent {
   private Object value;

   public AnyTypeFluent() {
   }

   public AnyTypeFluent(AnyType instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AnyType instance) {
      instance = instance != null ? instance : new AnyType();
      if (instance != null) {
         this.withValue(instance.getValue());
      }

   }

   public Object getValue() {
      return this.value;
   }

   public AnyTypeFluent withValue(Object value) {
      this.value = value;
      return this;
   }

   public boolean hasValue() {
      return this.value != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            AnyTypeFluent that = (AnyTypeFluent)o;
            return Objects.equals(this.value, that.value);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.value, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.value != null) {
         sb.append("value:");
         sb.append(this.value);
      }

      sb.append("}");
      return sb.toString();
   }
}
