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
@JsonPropertyOrder({"gmsaCredentialSpec", "gmsaCredentialSpecName", "hostProcess", "runAsUserName"})
public class WindowsSecurityContextOptions implements Editable, KubernetesResource {
   @JsonProperty("gmsaCredentialSpec")
   private String gmsaCredentialSpec;
   @JsonProperty("gmsaCredentialSpecName")
   private String gmsaCredentialSpecName;
   @JsonProperty("hostProcess")
   private Boolean hostProcess;
   @JsonProperty("runAsUserName")
   private String runAsUserName;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public WindowsSecurityContextOptions() {
   }

   public WindowsSecurityContextOptions(String gmsaCredentialSpec, String gmsaCredentialSpecName, Boolean hostProcess, String runAsUserName) {
      this.gmsaCredentialSpec = gmsaCredentialSpec;
      this.gmsaCredentialSpecName = gmsaCredentialSpecName;
      this.hostProcess = hostProcess;
      this.runAsUserName = runAsUserName;
   }

   @JsonProperty("gmsaCredentialSpec")
   public String getGmsaCredentialSpec() {
      return this.gmsaCredentialSpec;
   }

   @JsonProperty("gmsaCredentialSpec")
   public void setGmsaCredentialSpec(String gmsaCredentialSpec) {
      this.gmsaCredentialSpec = gmsaCredentialSpec;
   }

   @JsonProperty("gmsaCredentialSpecName")
   public String getGmsaCredentialSpecName() {
      return this.gmsaCredentialSpecName;
   }

   @JsonProperty("gmsaCredentialSpecName")
   public void setGmsaCredentialSpecName(String gmsaCredentialSpecName) {
      this.gmsaCredentialSpecName = gmsaCredentialSpecName;
   }

   @JsonProperty("hostProcess")
   public Boolean getHostProcess() {
      return this.hostProcess;
   }

   @JsonProperty("hostProcess")
   public void setHostProcess(Boolean hostProcess) {
      this.hostProcess = hostProcess;
   }

   @JsonProperty("runAsUserName")
   public String getRunAsUserName() {
      return this.runAsUserName;
   }

   @JsonProperty("runAsUserName")
   public void setRunAsUserName(String runAsUserName) {
      this.runAsUserName = runAsUserName;
   }

   @JsonIgnore
   public WindowsSecurityContextOptionsBuilder edit() {
      return new WindowsSecurityContextOptionsBuilder(this);
   }

   @JsonIgnore
   public WindowsSecurityContextOptionsBuilder toBuilder() {
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
      String var10000 = this.getGmsaCredentialSpec();
      return "WindowsSecurityContextOptions(gmsaCredentialSpec=" + var10000 + ", gmsaCredentialSpecName=" + this.getGmsaCredentialSpecName() + ", hostProcess=" + this.getHostProcess() + ", runAsUserName=" + this.getRunAsUserName() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof WindowsSecurityContextOptions)) {
         return false;
      } else {
         WindowsSecurityContextOptions other = (WindowsSecurityContextOptions)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$hostProcess = this.getHostProcess();
            Object other$hostProcess = other.getHostProcess();
            if (this$hostProcess == null) {
               if (other$hostProcess != null) {
                  return false;
               }
            } else if (!this$hostProcess.equals(other$hostProcess)) {
               return false;
            }

            Object this$gmsaCredentialSpec = this.getGmsaCredentialSpec();
            Object other$gmsaCredentialSpec = other.getGmsaCredentialSpec();
            if (this$gmsaCredentialSpec == null) {
               if (other$gmsaCredentialSpec != null) {
                  return false;
               }
            } else if (!this$gmsaCredentialSpec.equals(other$gmsaCredentialSpec)) {
               return false;
            }

            Object this$gmsaCredentialSpecName = this.getGmsaCredentialSpecName();
            Object other$gmsaCredentialSpecName = other.getGmsaCredentialSpecName();
            if (this$gmsaCredentialSpecName == null) {
               if (other$gmsaCredentialSpecName != null) {
                  return false;
               }
            } else if (!this$gmsaCredentialSpecName.equals(other$gmsaCredentialSpecName)) {
               return false;
            }

            Object this$runAsUserName = this.getRunAsUserName();
            Object other$runAsUserName = other.getRunAsUserName();
            if (this$runAsUserName == null) {
               if (other$runAsUserName != null) {
                  return false;
               }
            } else if (!this$runAsUserName.equals(other$runAsUserName)) {
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
      return other instanceof WindowsSecurityContextOptions;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $hostProcess = this.getHostProcess();
      result = result * 59 + ($hostProcess == null ? 43 : $hostProcess.hashCode());
      Object $gmsaCredentialSpec = this.getGmsaCredentialSpec();
      result = result * 59 + ($gmsaCredentialSpec == null ? 43 : $gmsaCredentialSpec.hashCode());
      Object $gmsaCredentialSpecName = this.getGmsaCredentialSpecName();
      result = result * 59 + ($gmsaCredentialSpecName == null ? 43 : $gmsaCredentialSpecName.hashCode());
      Object $runAsUserName = this.getRunAsUserName();
      result = result * 59 + ($runAsUserName == null ? 43 : $runAsUserName.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
