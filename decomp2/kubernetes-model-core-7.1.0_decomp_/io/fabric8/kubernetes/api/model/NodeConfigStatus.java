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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"active", "assigned", "error", "lastKnownGood"})
public class NodeConfigStatus implements Editable, KubernetesResource {
   @JsonProperty("active")
   private NodeConfigSource active;
   @JsonProperty("assigned")
   private NodeConfigSource assigned;
   @JsonProperty("error")
   private String error;
   @JsonProperty("lastKnownGood")
   private NodeConfigSource lastKnownGood;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeConfigStatus() {
   }

   public NodeConfigStatus(NodeConfigSource active, NodeConfigSource assigned, String error, NodeConfigSource lastKnownGood) {
      this.active = active;
      this.assigned = assigned;
      this.error = error;
      this.lastKnownGood = lastKnownGood;
   }

   @JsonProperty("active")
   public NodeConfigSource getActive() {
      return this.active;
   }

   @JsonProperty("active")
   public void setActive(NodeConfigSource active) {
      this.active = active;
   }

   @JsonProperty("assigned")
   public NodeConfigSource getAssigned() {
      return this.assigned;
   }

   @JsonProperty("assigned")
   public void setAssigned(NodeConfigSource assigned) {
      this.assigned = assigned;
   }

   @JsonProperty("error")
   public String getError() {
      return this.error;
   }

   @JsonProperty("error")
   public void setError(String error) {
      this.error = error;
   }

   @JsonProperty("lastKnownGood")
   public NodeConfigSource getLastKnownGood() {
      return this.lastKnownGood;
   }

   @JsonProperty("lastKnownGood")
   public void setLastKnownGood(NodeConfigSource lastKnownGood) {
      this.lastKnownGood = lastKnownGood;
   }

   @JsonIgnore
   public NodeConfigStatusBuilder edit() {
      return new NodeConfigStatusBuilder(this);
   }

   @JsonIgnore
   public NodeConfigStatusBuilder toBuilder() {
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
      NodeConfigSource var10000 = this.getActive();
      return "NodeConfigStatus(active=" + var10000 + ", assigned=" + this.getAssigned() + ", error=" + this.getError() + ", lastKnownGood=" + this.getLastKnownGood() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeConfigStatus)) {
         return false;
      } else {
         NodeConfigStatus other = (NodeConfigStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$active = this.getActive();
            Object other$active = other.getActive();
            if (this$active == null) {
               if (other$active != null) {
                  return false;
               }
            } else if (!this$active.equals(other$active)) {
               return false;
            }

            Object this$assigned = this.getAssigned();
            Object other$assigned = other.getAssigned();
            if (this$assigned == null) {
               if (other$assigned != null) {
                  return false;
               }
            } else if (!this$assigned.equals(other$assigned)) {
               return false;
            }

            Object this$error = this.getError();
            Object other$error = other.getError();
            if (this$error == null) {
               if (other$error != null) {
                  return false;
               }
            } else if (!this$error.equals(other$error)) {
               return false;
            }

            Object this$lastKnownGood = this.getLastKnownGood();
            Object other$lastKnownGood = other.getLastKnownGood();
            if (this$lastKnownGood == null) {
               if (other$lastKnownGood != null) {
                  return false;
               }
            } else if (!this$lastKnownGood.equals(other$lastKnownGood)) {
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
      return other instanceof NodeConfigStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $active = this.getActive();
      result = result * 59 + ($active == null ? 43 : $active.hashCode());
      Object $assigned = this.getAssigned();
      result = result * 59 + ($assigned == null ? 43 : $assigned.hashCode());
      Object $error = this.getError();
      result = result * 59 + ($error == null ? 43 : $error.hashCode());
      Object $lastKnownGood = this.getLastKnownGood();
      result = result * 59 + ($lastKnownGood == null ? 43 : $lastKnownGood.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
