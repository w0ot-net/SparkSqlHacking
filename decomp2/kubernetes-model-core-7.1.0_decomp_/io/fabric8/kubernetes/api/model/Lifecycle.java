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
@JsonPropertyOrder({"postStart", "preStop"})
public class Lifecycle implements Editable, KubernetesResource {
   @JsonProperty("postStart")
   private LifecycleHandler postStart;
   @JsonProperty("preStop")
   private LifecycleHandler preStop;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Lifecycle() {
   }

   public Lifecycle(LifecycleHandler postStart, LifecycleHandler preStop) {
      this.postStart = postStart;
      this.preStop = preStop;
   }

   @JsonProperty("postStart")
   public LifecycleHandler getPostStart() {
      return this.postStart;
   }

   @JsonProperty("postStart")
   public void setPostStart(LifecycleHandler postStart) {
      this.postStart = postStart;
   }

   @JsonProperty("preStop")
   public LifecycleHandler getPreStop() {
      return this.preStop;
   }

   @JsonProperty("preStop")
   public void setPreStop(LifecycleHandler preStop) {
      this.preStop = preStop;
   }

   @JsonIgnore
   public LifecycleBuilder edit() {
      return new LifecycleBuilder(this);
   }

   @JsonIgnore
   public LifecycleBuilder toBuilder() {
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
      LifecycleHandler var10000 = this.getPostStart();
      return "Lifecycle(postStart=" + var10000 + ", preStop=" + this.getPreStop() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Lifecycle)) {
         return false;
      } else {
         Lifecycle other = (Lifecycle)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$postStart = this.getPostStart();
            Object other$postStart = other.getPostStart();
            if (this$postStart == null) {
               if (other$postStart != null) {
                  return false;
               }
            } else if (!this$postStart.equals(other$postStart)) {
               return false;
            }

            Object this$preStop = this.getPreStop();
            Object other$preStop = other.getPreStop();
            if (this$preStop == null) {
               if (other$preStop != null) {
                  return false;
               }
            } else if (!this$preStop.equals(other$preStop)) {
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
      return other instanceof Lifecycle;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $postStart = this.getPostStart();
      result = result * 59 + ($postStart == null ? 43 : $postStart.hashCode());
      Object $preStop = this.getPreStop();
      result = result * 59 + ($preStop == null ? 43 : $preStop.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
