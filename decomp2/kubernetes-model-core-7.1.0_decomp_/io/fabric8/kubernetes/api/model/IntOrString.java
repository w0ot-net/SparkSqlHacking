package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class IntOrString extends AnyType {
   public IntOrString() {
   }

   @JsonCreator
   public IntOrString(Object value) {
      this.setValue(value);
   }

   public void setValue(Object value) {
      if (value != null && !(value instanceof Integer) && !(value instanceof String)) {
         throw new IllegalArgumentException("Either integer or string value needs to be provided");
      } else {
         super.setValue(value);
      }
   }

   public Integer getIntVal() {
      return this.value instanceof Integer ? (Integer)this.value : null;
   }

   public String getStrVal() {
      return this.value instanceof String ? (String)this.value : null;
   }
}
