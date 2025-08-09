package io.fabric8.kubernetes.api.model.networking.v1beta1;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"parentRef"})
public class IPAddressSpec implements Editable, KubernetesResource {
   @JsonProperty("parentRef")
   private ParentReference parentRef;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public IPAddressSpec() {
   }

   public IPAddressSpec(ParentReference parentRef) {
      this.parentRef = parentRef;
   }

   @JsonProperty("parentRef")
   public ParentReference getParentRef() {
      return this.parentRef;
   }

   @JsonProperty("parentRef")
   public void setParentRef(ParentReference parentRef) {
      this.parentRef = parentRef;
   }

   @JsonIgnore
   public IPAddressSpecBuilder edit() {
      return new IPAddressSpecBuilder(this);
   }

   @JsonIgnore
   public IPAddressSpecBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      ParentReference var10000 = this.getParentRef();
      return "IPAddressSpec(parentRef=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof IPAddressSpec)) {
         return false;
      } else {
         IPAddressSpec other = (IPAddressSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$parentRef = this.getParentRef();
            Object other$parentRef = other.getParentRef();
            if (this$parentRef == null) {
               if (other$parentRef != null) {
                  return false;
               }
            } else if (!this$parentRef.equals(other$parentRef)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof IPAddressSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $parentRef = this.getParentRef();
      result = result * 59 + ($parentRef == null ? 43 : $parentRef.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
