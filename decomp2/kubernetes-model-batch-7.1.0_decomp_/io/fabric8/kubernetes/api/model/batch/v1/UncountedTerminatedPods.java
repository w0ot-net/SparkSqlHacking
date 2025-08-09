package io.fabric8.kubernetes.api.model.batch.v1;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"failed", "succeeded"})
public class UncountedTerminatedPods implements Editable, KubernetesResource {
   @JsonProperty("failed")
   @JsonInclude(Include.NON_EMPTY)
   private List failed = new ArrayList();
   @JsonProperty("succeeded")
   @JsonInclude(Include.NON_EMPTY)
   private List succeeded = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public UncountedTerminatedPods() {
   }

   public UncountedTerminatedPods(List failed, List succeeded) {
      this.failed = failed;
      this.succeeded = succeeded;
   }

   @JsonProperty("failed")
   @JsonInclude(Include.NON_EMPTY)
   public List getFailed() {
      return this.failed;
   }

   @JsonProperty("failed")
   public void setFailed(List failed) {
      this.failed = failed;
   }

   @JsonProperty("succeeded")
   @JsonInclude(Include.NON_EMPTY)
   public List getSucceeded() {
      return this.succeeded;
   }

   @JsonProperty("succeeded")
   public void setSucceeded(List succeeded) {
      this.succeeded = succeeded;
   }

   @JsonIgnore
   public UncountedTerminatedPodsBuilder edit() {
      return new UncountedTerminatedPodsBuilder(this);
   }

   @JsonIgnore
   public UncountedTerminatedPodsBuilder toBuilder() {
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
      List var10000 = this.getFailed();
      return "UncountedTerminatedPods(failed=" + var10000 + ", succeeded=" + this.getSucceeded() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UncountedTerminatedPods)) {
         return false;
      } else {
         UncountedTerminatedPods other = (UncountedTerminatedPods)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$failed = this.getFailed();
            Object other$failed = other.getFailed();
            if (this$failed == null) {
               if (other$failed != null) {
                  return false;
               }
            } else if (!this$failed.equals(other$failed)) {
               return false;
            }

            Object this$succeeded = this.getSucceeded();
            Object other$succeeded = other.getSucceeded();
            if (this$succeeded == null) {
               if (other$succeeded != null) {
                  return false;
               }
            } else if (!this$succeeded.equals(other$succeeded)) {
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
      return other instanceof UncountedTerminatedPods;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $failed = this.getFailed();
      result = result * 59 + ($failed == null ? 43 : $failed.hashCode());
      Object $succeeded = this.getSucceeded();
      result = result * 59 + ($succeeded == null ? 43 : $succeeded.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
