package org.apache.commons.lang3.mutable;

import java.io.Serializable;
import java.util.Objects;

public class MutableObject implements Mutable, Serializable {
   private static final long serialVersionUID = 86241875189L;
   private Object value;

   public MutableObject() {
   }

   public MutableObject(Object value) {
      this.value = value;
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (this == obj) {
         return true;
      } else if (this.getClass() == obj.getClass()) {
         MutableObject<?> that = (MutableObject)obj;
         return Objects.equals(this.value, that.value);
      } else {
         return false;
      }
   }

   public Object getValue() {
      return this.value;
   }

   public int hashCode() {
      return Objects.hashCode(this.value);
   }

   public void setValue(Object value) {
      this.value = value;
   }

   public String toString() {
      return Objects.toString(this.value);
   }
}
