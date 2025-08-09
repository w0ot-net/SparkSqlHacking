package org.apache.commons.lang3.concurrent;

import java.util.Objects;

public class ConstantInitializer implements ConcurrentInitializer {
   private static final String FMT_TO_STRING = "ConstantInitializer@%d [ object = %s ]";
   private final Object object;

   public ConstantInitializer(Object obj) {
      this.object = obj;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof ConstantInitializer)) {
         return false;
      } else {
         ConstantInitializer<?> c = (ConstantInitializer)obj;
         return Objects.equals(this.getObject(), c.getObject());
      }
   }

   public Object get() throws ConcurrentException {
      return this.getObject();
   }

   public final Object getObject() {
      return this.object;
   }

   public int hashCode() {
      return Objects.hashCode(this.object);
   }

   public boolean isInitialized() {
      return true;
   }

   public String toString() {
      return String.format("ConstantInitializer@%d [ object = %s ]", System.identityHashCode(this), this.getObject());
   }
}
