package io.fabric8.kubernetes.api.model.coordination.v1alpha2;

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
import io.fabric8.kubernetes.api.model.MicroTime;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"binaryVersion", "emulationVersion", "leaseName", "pingTime", "renewTime", "strategy"})
public class LeaseCandidateSpec implements Editable, KubernetesResource {
   @JsonProperty("binaryVersion")
   private String binaryVersion;
   @JsonProperty("emulationVersion")
   private String emulationVersion;
   @JsonProperty("leaseName")
   private String leaseName;
   @JsonProperty("pingTime")
   private MicroTime pingTime;
   @JsonProperty("renewTime")
   private MicroTime renewTime;
   @JsonProperty("strategy")
   private String strategy;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public LeaseCandidateSpec() {
   }

   public LeaseCandidateSpec(String binaryVersion, String emulationVersion, String leaseName, MicroTime pingTime, MicroTime renewTime, String strategy) {
      this.binaryVersion = binaryVersion;
      this.emulationVersion = emulationVersion;
      this.leaseName = leaseName;
      this.pingTime = pingTime;
      this.renewTime = renewTime;
      this.strategy = strategy;
   }

   @JsonProperty("binaryVersion")
   public String getBinaryVersion() {
      return this.binaryVersion;
   }

   @JsonProperty("binaryVersion")
   public void setBinaryVersion(String binaryVersion) {
      this.binaryVersion = binaryVersion;
   }

   @JsonProperty("emulationVersion")
   public String getEmulationVersion() {
      return this.emulationVersion;
   }

   @JsonProperty("emulationVersion")
   public void setEmulationVersion(String emulationVersion) {
      this.emulationVersion = emulationVersion;
   }

   @JsonProperty("leaseName")
   public String getLeaseName() {
      return this.leaseName;
   }

   @JsonProperty("leaseName")
   public void setLeaseName(String leaseName) {
      this.leaseName = leaseName;
   }

   @JsonProperty("pingTime")
   public MicroTime getPingTime() {
      return this.pingTime;
   }

   @JsonProperty("pingTime")
   public void setPingTime(MicroTime pingTime) {
      this.pingTime = pingTime;
   }

   @JsonProperty("renewTime")
   public MicroTime getRenewTime() {
      return this.renewTime;
   }

   @JsonProperty("renewTime")
   public void setRenewTime(MicroTime renewTime) {
      this.renewTime = renewTime;
   }

   @JsonProperty("strategy")
   public String getStrategy() {
      return this.strategy;
   }

   @JsonProperty("strategy")
   public void setStrategy(String strategy) {
      this.strategy = strategy;
   }

   @JsonIgnore
   public LeaseCandidateSpecBuilder edit() {
      return new LeaseCandidateSpecBuilder(this);
   }

   @JsonIgnore
   public LeaseCandidateSpecBuilder toBuilder() {
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
      String var10000 = this.getBinaryVersion();
      return "LeaseCandidateSpec(binaryVersion=" + var10000 + ", emulationVersion=" + this.getEmulationVersion() + ", leaseName=" + this.getLeaseName() + ", pingTime=" + this.getPingTime() + ", renewTime=" + this.getRenewTime() + ", strategy=" + this.getStrategy() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LeaseCandidateSpec)) {
         return false;
      } else {
         LeaseCandidateSpec other = (LeaseCandidateSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$binaryVersion = this.getBinaryVersion();
            Object other$binaryVersion = other.getBinaryVersion();
            if (this$binaryVersion == null) {
               if (other$binaryVersion != null) {
                  return false;
               }
            } else if (!this$binaryVersion.equals(other$binaryVersion)) {
               return false;
            }

            Object this$emulationVersion = this.getEmulationVersion();
            Object other$emulationVersion = other.getEmulationVersion();
            if (this$emulationVersion == null) {
               if (other$emulationVersion != null) {
                  return false;
               }
            } else if (!this$emulationVersion.equals(other$emulationVersion)) {
               return false;
            }

            Object this$leaseName = this.getLeaseName();
            Object other$leaseName = other.getLeaseName();
            if (this$leaseName == null) {
               if (other$leaseName != null) {
                  return false;
               }
            } else if (!this$leaseName.equals(other$leaseName)) {
               return false;
            }

            Object this$pingTime = this.getPingTime();
            Object other$pingTime = other.getPingTime();
            if (this$pingTime == null) {
               if (other$pingTime != null) {
                  return false;
               }
            } else if (!this$pingTime.equals(other$pingTime)) {
               return false;
            }

            Object this$renewTime = this.getRenewTime();
            Object other$renewTime = other.getRenewTime();
            if (this$renewTime == null) {
               if (other$renewTime != null) {
                  return false;
               }
            } else if (!this$renewTime.equals(other$renewTime)) {
               return false;
            }

            Object this$strategy = this.getStrategy();
            Object other$strategy = other.getStrategy();
            if (this$strategy == null) {
               if (other$strategy != null) {
                  return false;
               }
            } else if (!this$strategy.equals(other$strategy)) {
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
      return other instanceof LeaseCandidateSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $binaryVersion = this.getBinaryVersion();
      result = result * 59 + ($binaryVersion == null ? 43 : $binaryVersion.hashCode());
      Object $emulationVersion = this.getEmulationVersion();
      result = result * 59 + ($emulationVersion == null ? 43 : $emulationVersion.hashCode());
      Object $leaseName = this.getLeaseName();
      result = result * 59 + ($leaseName == null ? 43 : $leaseName.hashCode());
      Object $pingTime = this.getPingTime();
      result = result * 59 + ($pingTime == null ? 43 : $pingTime.hashCode());
      Object $renewTime = this.getRenewTime();
      result = result * 59 + ($renewTime == null ? 43 : $renewTime.hashCode());
      Object $strategy = this.getStrategy();
      result = result * 59 + ($strategy == null ? 43 : $strategy.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
