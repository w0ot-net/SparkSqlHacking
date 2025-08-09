package io.fabric8.kubernetes.api.model.runtime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.AnyType;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
public class RawExtension extends AnyType implements KubernetesResource {
   public RawExtension() {
   }

   @JsonCreator
   public RawExtension(Object value) {
      super(value);
   }

   @Generated
   public String toString() {
      return "RawExtension(super=" + super.toString() + ")";
   }
}
