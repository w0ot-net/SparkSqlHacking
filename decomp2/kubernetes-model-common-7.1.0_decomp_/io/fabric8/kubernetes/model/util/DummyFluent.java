package io.fabric8.kubernetes.model.util;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.Objects;

public class DummyFluent extends BaseFluent {
   public DummyFluent() {
   }

   public DummyFluent(Dummy instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Dummy instance) {
      instance = instance != null ? instance : new Dummy();
      if (instance != null) {
      }

   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            DummyFluent that = (DummyFluent)o;
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
