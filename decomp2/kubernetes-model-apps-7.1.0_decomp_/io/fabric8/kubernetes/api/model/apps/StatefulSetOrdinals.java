package io.fabric8.kubernetes.api.model.apps;

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
@JsonPropertyOrder({"start"})
public class StatefulSetOrdinals implements Editable, KubernetesResource {
   @JsonProperty("start")
   private Integer start;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public StatefulSetOrdinals() {
   }

   public StatefulSetOrdinals(Integer start) {
      this.start = start;
   }

   @JsonProperty("start")
   public Integer getStart() {
      return this.start;
   }

   @JsonProperty("start")
   public void setStart(Integer start) {
      this.start = start;
   }

   @JsonIgnore
   public StatefulSetOrdinalsBuilder edit() {
      return new StatefulSetOrdinalsBuilder(this);
   }

   @JsonIgnore
   public StatefulSetOrdinalsBuilder toBuilder() {
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
      Integer var10000 = this.getStart();
      return "StatefulSetOrdinals(start=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StatefulSetOrdinals)) {
         return false;
      } else {
         StatefulSetOrdinals other = (StatefulSetOrdinals)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$start = this.getStart();
            Object other$start = other.getStart();
            if (this$start == null) {
               if (other$start != null) {
                  return false;
               }
            } else if (!this$start.equals(other$start)) {
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
      return other instanceof StatefulSetOrdinals;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $start = this.getStart();
      result = result * 59 + ($start == null ? 43 : $start.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
