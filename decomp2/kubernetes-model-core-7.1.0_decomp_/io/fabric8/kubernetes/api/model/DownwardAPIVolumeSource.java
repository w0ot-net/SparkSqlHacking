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
@JsonPropertyOrder({"defaultMode", "items"})
public class DownwardAPIVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("defaultMode")
   private Integer defaultMode;
   @JsonProperty("items")
   @JsonInclude(Include.NON_EMPTY)
   private List items = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DownwardAPIVolumeSource() {
   }

   public DownwardAPIVolumeSource(Integer defaultMode, List items) {
      this.defaultMode = defaultMode;
      this.items = items;
   }

   @JsonProperty("defaultMode")
   public Integer getDefaultMode() {
      return this.defaultMode;
   }

   @JsonProperty("defaultMode")
   public void setDefaultMode(Integer defaultMode) {
      this.defaultMode = defaultMode;
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
   public DownwardAPIVolumeSourceBuilder edit() {
      return new DownwardAPIVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public DownwardAPIVolumeSourceBuilder toBuilder() {
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
      Integer var10000 = this.getDefaultMode();
      return "DownwardAPIVolumeSource(defaultMode=" + var10000 + ", items=" + this.getItems() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DownwardAPIVolumeSource)) {
         return false;
      } else {
         DownwardAPIVolumeSource other = (DownwardAPIVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$defaultMode = this.getDefaultMode();
            Object other$defaultMode = other.getDefaultMode();
            if (this$defaultMode == null) {
               if (other$defaultMode != null) {
                  return false;
               }
            } else if (!this$defaultMode.equals(other$defaultMode)) {
               return false;
            }

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
      return other instanceof DownwardAPIVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $defaultMode = this.getDefaultMode();
      result = result * 59 + ($defaultMode == null ? 43 : $defaultMode.hashCode());
      Object $items = this.getItems();
      result = result * 59 + ($items == null ? 43 : $items.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
