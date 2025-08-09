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
@JsonPropertyOrder({"items"})
public class DownwardAPIProjection implements Editable, KubernetesResource {
   @JsonProperty("items")
   @JsonInclude(Include.NON_EMPTY)
   private List items = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DownwardAPIProjection() {
   }

   public DownwardAPIProjection(List items) {
      this.items = items;
   }

   @JsonProperty("items")
   @JsonInclude(Include.NON_EMPTY)
   public List getItems() {
      return this.items;
   }

   @JsonProperty("items")
   public void setItems(List items) {
      this.items = items;
   }

   @JsonIgnore
   public DownwardAPIProjectionBuilder edit() {
      return new DownwardAPIProjectionBuilder(this);
   }

   @JsonIgnore
   public DownwardAPIProjectionBuilder toBuilder() {
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
      List var10000 = this.getItems();
      return "DownwardAPIProjection(items=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DownwardAPIProjection)) {
         return false;
      } else {
         DownwardAPIProjection other = (DownwardAPIProjection)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$items = this.getItems();
            Object other$items = other.getItems();
            if (this$items == null) {
               if (other$items != null) {
                  return false;
               }
            } else if (!this$items.equals(other$items)) {
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
      return other instanceof DownwardAPIProjection;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $items = this.getItems();
      result = result * 59 + ($items == null ? 43 : $items.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
