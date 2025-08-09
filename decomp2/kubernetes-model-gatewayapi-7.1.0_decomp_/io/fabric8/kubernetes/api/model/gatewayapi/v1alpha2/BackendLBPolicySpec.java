package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

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
import io.fabric8.kubernetes.api.model.gatewayapi.v1.SessionPersistence;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"sessionPersistence", "targetRefs"})
public class BackendLBPolicySpec implements Editable, KubernetesResource {
   @JsonProperty("sessionPersistence")
   private SessionPersistence sessionPersistence;
   @JsonProperty("targetRefs")
   @JsonInclude(Include.NON_EMPTY)
   private List targetRefs = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public BackendLBPolicySpec() {
   }

   public BackendLBPolicySpec(SessionPersistence sessionPersistence, List targetRefs) {
      this.sessionPersistence = sessionPersistence;
      this.targetRefs = targetRefs;
   }

   @JsonProperty("sessionPersistence")
   public SessionPersistence getSessionPersistence() {
      return this.sessionPersistence;
   }

   @JsonProperty("sessionPersistence")
   public void setSessionPersistence(SessionPersistence sessionPersistence) {
      this.sessionPersistence = sessionPersistence;
   }

   @JsonProperty("targetRefs")
   @JsonInclude(Include.NON_EMPTY)
   public List getTargetRefs() {
      return this.targetRefs;
   }

   @JsonProperty("targetRefs")
   public void setTargetRefs(List targetRefs) {
      this.targetRefs = targetRefs;
   }

   @JsonIgnore
   public BackendLBPolicySpecBuilder edit() {
      return new BackendLBPolicySpecBuilder(this);
   }

   @JsonIgnore
   public BackendLBPolicySpecBuilder toBuilder() {
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
      SessionPersistence var10000 = this.getSessionPersistence();
      return "BackendLBPolicySpec(sessionPersistence=" + var10000 + ", targetRefs=" + this.getTargetRefs() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BackendLBPolicySpec)) {
         return false;
      } else {
         BackendLBPolicySpec other = (BackendLBPolicySpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$sessionPersistence = this.getSessionPersistence();
            Object other$sessionPersistence = other.getSessionPersistence();
            if (this$sessionPersistence == null) {
               if (other$sessionPersistence != null) {
                  return false;
               }
            } else if (!this$sessionPersistence.equals(other$sessionPersistence)) {
               return false;
            }

            Object this$targetRefs = this.getTargetRefs();
            Object other$targetRefs = other.getTargetRefs();
            if (this$targetRefs == null) {
               if (other$targetRefs != null) {
                  return false;
               }
            } else if (!this$targetRefs.equals(other$targetRefs)) {
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
      return other instanceof BackendLBPolicySpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $sessionPersistence = this.getSessionPersistence();
      result = result * 59 + ($sessionPersistence == null ? 43 : $sessionPersistence.hashCode());
      Object $targetRefs = this.getTargetRefs();
      result = result * 59 + ($targetRefs == null ? 43 : $targetRefs.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
