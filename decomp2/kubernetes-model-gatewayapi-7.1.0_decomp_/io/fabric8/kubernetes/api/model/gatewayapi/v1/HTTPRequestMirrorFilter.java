package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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
@JsonPropertyOrder({"backendRef", "fraction", "percent"})
public class HTTPRequestMirrorFilter implements Editable, KubernetesResource {
   @JsonProperty("backendRef")
   private BackendObjectReference backendRef;
   @JsonProperty("fraction")
   private Fraction fraction;
   @JsonProperty("percent")
   private Integer percent;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public HTTPRequestMirrorFilter() {
   }

   public HTTPRequestMirrorFilter(BackendObjectReference backendRef, Fraction fraction, Integer percent) {
      this.backendRef = backendRef;
      this.fraction = fraction;
      this.percent = percent;
   }

   @JsonProperty("backendRef")
   public BackendObjectReference getBackendRef() {
      return this.backendRef;
   }

   @JsonProperty("backendRef")
   public void setBackendRef(BackendObjectReference backendRef) {
      this.backendRef = backendRef;
   }

   @JsonProperty("fraction")
   public Fraction getFraction() {
      return this.fraction;
   }

   @JsonProperty("fraction")
   public void setFraction(Fraction fraction) {
      this.fraction = fraction;
   }

   @JsonProperty("percent")
   public Integer getPercent() {
      return this.percent;
   }

   @JsonProperty("percent")
   public void setPercent(Integer percent) {
      this.percent = percent;
   }

   @JsonIgnore
   public HTTPRequestMirrorFilterBuilder edit() {
      return new HTTPRequestMirrorFilterBuilder(this);
   }

   @JsonIgnore
   public HTTPRequestMirrorFilterBuilder toBuilder() {
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
      BackendObjectReference var10000 = this.getBackendRef();
      return "HTTPRequestMirrorFilter(backendRef=" + var10000 + ", fraction=" + this.getFraction() + ", percent=" + this.getPercent() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HTTPRequestMirrorFilter)) {
         return false;
      } else {
         HTTPRequestMirrorFilter other = (HTTPRequestMirrorFilter)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$percent = this.getPercent();
            Object other$percent = other.getPercent();
            if (this$percent == null) {
               if (other$percent != null) {
                  return false;
               }
            } else if (!this$percent.equals(other$percent)) {
               return false;
            }

            Object this$backendRef = this.getBackendRef();
            Object other$backendRef = other.getBackendRef();
            if (this$backendRef == null) {
               if (other$backendRef != null) {
                  return false;
               }
            } else if (!this$backendRef.equals(other$backendRef)) {
               return false;
            }

            Object this$fraction = this.getFraction();
            Object other$fraction = other.getFraction();
            if (this$fraction == null) {
               if (other$fraction != null) {
                  return false;
               }
            } else if (!this$fraction.equals(other$fraction)) {
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
      return other instanceof HTTPRequestMirrorFilter;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $percent = this.getPercent();
      result = result * 59 + ($percent == null ? 43 : $percent.hashCode());
      Object $backendRef = this.getBackendRef();
      result = result * 59 + ($backendRef == null ? 43 : $backendRef.hashCode());
      Object $fraction = this.getFraction();
      result = result * 59 + ($fraction == null ? 43 : $fraction.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
