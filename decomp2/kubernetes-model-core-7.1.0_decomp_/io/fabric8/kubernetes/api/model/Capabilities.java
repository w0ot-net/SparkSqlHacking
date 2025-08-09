package io.fabric8.kubernetes.api.model;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"add", "drop"})
public class Capabilities implements Editable, KubernetesResource {
   @JsonProperty("add")
   @JsonInclude(Include.NON_EMPTY)
   private List add = new ArrayList();
   @JsonProperty("drop")
   @JsonInclude(Include.NON_EMPTY)
   private List drop = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Capabilities() {
   }

   public Capabilities(List add, List drop) {
      this.add = add;
      this.drop = drop;
   }

   @JsonProperty("add")
   @JsonInclude(Include.NON_EMPTY)
   public List getAdd() {
      return this.add;
   }

   @JsonProperty("add")
   public void setAdd(List add) {
      this.add = add;
   }

   @JsonProperty("drop")
   @JsonInclude(Include.NON_EMPTY)
   public List getDrop() {
      return this.drop;
   }

   @JsonProperty("drop")
   public void setDrop(List drop) {
      this.drop = drop;
   }

   @JsonIgnore
   public CapabilitiesBuilder edit() {
      return new CapabilitiesBuilder(this);
   }

   @JsonIgnore
   public CapabilitiesBuilder toBuilder() {
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
      List var10000 = this.getAdd();
      return "Capabilities(add=" + var10000 + ", drop=" + this.getDrop() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Capabilities)) {
         return false;
      } else {
         Capabilities other = (Capabilities)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$add = this.getAdd();
            Object other$add = other.getAdd();
            if (this$add == null) {
               if (other$add != null) {
                  return false;
               }
            } else if (!this$add.equals(other$add)) {
               return false;
            }

            Object this$drop = this.getDrop();
            Object other$drop = other.getDrop();
            if (this$drop == null) {
               if (other$drop != null) {
                  return false;
               }
            } else if (!this$drop.equals(other$drop)) {
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
      return other instanceof Capabilities;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $add = this.getAdd();
      result = result * 59 + ($add == null ? 43 : $add.hashCode());
      Object $drop = this.getDrop();
      result = result * 59 + ($drop == null ? 43 : $drop.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
