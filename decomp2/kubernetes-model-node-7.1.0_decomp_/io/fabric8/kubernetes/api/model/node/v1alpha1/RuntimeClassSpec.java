package io.fabric8.kubernetes.api.model.node.v1alpha1;

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
@JsonPropertyOrder({"overhead", "runtimeHandler", "scheduling"})
public class RuntimeClassSpec implements Editable, KubernetesResource {
   @JsonProperty("overhead")
   private Overhead overhead;
   @JsonProperty("runtimeHandler")
   private String runtimeHandler;
   @JsonProperty("scheduling")
   private Scheduling scheduling;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public RuntimeClassSpec() {
   }

   public RuntimeClassSpec(Overhead overhead, String runtimeHandler, Scheduling scheduling) {
      this.overhead = overhead;
      this.runtimeHandler = runtimeHandler;
      this.scheduling = scheduling;
   }

   @JsonProperty("overhead")
   public Overhead getOverhead() {
      return this.overhead;
   }

   @JsonProperty("overhead")
   public void setOverhead(Overhead overhead) {
      this.overhead = overhead;
   }

   @JsonProperty("runtimeHandler")
   public String getRuntimeHandler() {
      return this.runtimeHandler;
   }

   @JsonProperty("runtimeHandler")
   public void setRuntimeHandler(String runtimeHandler) {
      this.runtimeHandler = runtimeHandler;
   }

   @JsonProperty("scheduling")
   public Scheduling getScheduling() {
      return this.scheduling;
   }

   @JsonProperty("scheduling")
   public void setScheduling(Scheduling scheduling) {
      this.scheduling = scheduling;
   }

   @JsonIgnore
   public RuntimeClassSpecBuilder edit() {
      return new RuntimeClassSpecBuilder(this);
   }

   @JsonIgnore
   public RuntimeClassSpecBuilder toBuilder() {
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
      Overhead var10000 = this.getOverhead();
      return "RuntimeClassSpec(overhead=" + var10000 + ", runtimeHandler=" + this.getRuntimeHandler() + ", scheduling=" + this.getScheduling() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RuntimeClassSpec)) {
         return false;
      } else {
         RuntimeClassSpec other = (RuntimeClassSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$overhead = this.getOverhead();
            Object other$overhead = other.getOverhead();
            if (this$overhead == null) {
               if (other$overhead != null) {
                  return false;
               }
            } else if (!this$overhead.equals(other$overhead)) {
               return false;
            }

            Object this$runtimeHandler = this.getRuntimeHandler();
            Object other$runtimeHandler = other.getRuntimeHandler();
            if (this$runtimeHandler == null) {
               if (other$runtimeHandler != null) {
                  return false;
               }
            } else if (!this$runtimeHandler.equals(other$runtimeHandler)) {
               return false;
            }

            Object this$scheduling = this.getScheduling();
            Object other$scheduling = other.getScheduling();
            if (this$scheduling == null) {
               if (other$scheduling != null) {
                  return false;
               }
            } else if (!this$scheduling.equals(other$scheduling)) {
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
      return other instanceof RuntimeClassSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $overhead = this.getOverhead();
      result = result * 59 + ($overhead == null ? 43 : $overhead.hashCode());
      Object $runtimeHandler = this.getRuntimeHandler();
      result = result * 59 + ($runtimeHandler == null ? 43 : $runtimeHandler.hashCode());
      Object $scheduling = this.getScheduling();
      result = result * 59 + ($scheduling == null ? 43 : $scheduling.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
