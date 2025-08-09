package io.fabric8.kubernetes.api.model.flowcontrol.v1beta3;

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
@JsonPropertyOrder({"queuing", "type"})
public class LimitResponse implements Editable, KubernetesResource {
   @JsonProperty("queuing")
   private QueuingConfiguration queuing;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public LimitResponse() {
   }

   public LimitResponse(QueuingConfiguration queuing, String type) {
      this.queuing = queuing;
      this.type = type;
   }

   @JsonProperty("queuing")
   public QueuingConfiguration getQueuing() {
      return this.queuing;
   }

   @JsonProperty("queuing")
   public void setQueuing(QueuingConfiguration queuing) {
      this.queuing = queuing;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public LimitResponseBuilder edit() {
      return new LimitResponseBuilder(this);
   }

   @JsonIgnore
   public LimitResponseBuilder toBuilder() {
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
      QueuingConfiguration var10000 = this.getQueuing();
      return "LimitResponse(queuing=" + var10000 + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LimitResponse)) {
         return false;
      } else {
         LimitResponse other = (LimitResponse)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$queuing = this.getQueuing();
            Object other$queuing = other.getQueuing();
            if (this$queuing == null) {
               if (other$queuing != null) {
                  return false;
               }
            } else if (!this$queuing.equals(other$queuing)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof LimitResponse;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $queuing = this.getQueuing();
      result = result * 59 + ($queuing == null ? 43 : $queuing.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
