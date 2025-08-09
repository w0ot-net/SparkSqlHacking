package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.io.Serializable;
import lombok.Generated;

@JsonSerialize(
   using = Serializer.class
)
@JsonInclude(Include.NON_NULL)
public class AnyType implements Serializable {
   protected Object value;

   public AnyType() {
   }

   @JsonCreator
   public AnyType(Object value) {
      this.value = value;
   }

   public Object getValue() {
      return this.value;
   }

   @Generated
   public String toString() {
      return "AnyType(value=" + this.getValue() + ")";
   }

   @Generated
   public void setValue(Object value) {
      this.value = value;
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AnyType)) {
         return false;
      } else {
         AnyType other = (AnyType)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$value = this.getValue();
            Object other$value = other.getValue();
            if (this$value == null) {
               if (other$value != null) {
                  return false;
               }
            } else if (!this$value.equals(other$value)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof AnyType;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $value = this.getValue();
      result = result * 59 + ($value == null ? 43 : $value.hashCode());
      return result;
   }

   public static class Serializer extends JsonSerializer {
      public void serialize(AnyType value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
         jgen.writeObject(value.value);
      }
   }
}
