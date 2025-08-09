package io.fabric8.kubernetes.api.model.discovery.v1beta1;

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
@JsonPropertyOrder({"ready", "serving", "terminating"})
public class EndpointConditions implements Editable, KubernetesResource {
   @JsonProperty("ready")
   private Boolean ready;
   @JsonProperty("serving")
   private Boolean serving;
   @JsonProperty("terminating")
   private Boolean terminating;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EndpointConditions() {
   }

   public EndpointConditions(Boolean ready, Boolean serving, Boolean terminating) {
      this.ready = ready;
      this.serving = serving;
      this.terminating = terminating;
   }

   @JsonProperty("ready")
   public Boolean getReady() {
      return this.ready;
   }

   @JsonProperty("ready")
   public void setReady(Boolean ready) {
      this.ready = ready;
   }

   @JsonProperty("serving")
   public Boolean getServing() {
      return this.serving;
   }

   @JsonProperty("serving")
   public void setServing(Boolean serving) {
      this.serving = serving;
   }

   @JsonProperty("terminating")
   public Boolean getTerminating() {
      return this.terminating;
   }

   @JsonProperty("terminating")
   public void setTerminating(Boolean terminating) {
      this.terminating = terminating;
   }

   @JsonIgnore
   public EndpointConditionsBuilder edit() {
      return new EndpointConditionsBuilder(this);
   }

   @JsonIgnore
   public EndpointConditionsBuilder toBuilder() {
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
      Boolean var10000 = this.getReady();
      return "EndpointConditions(ready=" + var10000 + ", serving=" + this.getServing() + ", terminating=" + this.getTerminating() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EndpointConditions)) {
         return false;
      } else {
         EndpointConditions other = (EndpointConditions)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$ready = this.getReady();
            Object other$ready = other.getReady();
            if (this$ready == null) {
               if (other$ready != null) {
                  return false;
               }
            } else if (!this$ready.equals(other$ready)) {
               return false;
            }

            Object this$serving = this.getServing();
            Object other$serving = other.getServing();
            if (this$serving == null) {
               if (other$serving != null) {
                  return false;
               }
            } else if (!this$serving.equals(other$serving)) {
               return false;
            }

            Object this$terminating = this.getTerminating();
            Object other$terminating = other.getTerminating();
            if (this$terminating == null) {
               if (other$terminating != null) {
                  return false;
               }
            } else if (!this$terminating.equals(other$terminating)) {
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
      return other instanceof EndpointConditions;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $ready = this.getReady();
      result = result * 59 + ($ready == null ? 43 : $ready.hashCode());
      Object $serving = this.getServing();
      result = result * 59 + ($serving == null ? 43 : $serving.hashCode());
      Object $terminating = this.getTerminating();
      result = result * 59 + ($terminating == null ? 43 : $terminating.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
