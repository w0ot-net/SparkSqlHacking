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
@JsonPropertyOrder({"architecture", "bootID", "containerRuntimeVersion", "kernelVersion", "kubeProxyVersion", "kubeletVersion", "machineID", "operatingSystem", "osImage", "systemUUID"})
public class NodeSystemInfo implements Editable, KubernetesResource {
   @JsonProperty("architecture")
   private String architecture;
   @JsonProperty("bootID")
   private String bootID;
   @JsonProperty("containerRuntimeVersion")
   private String containerRuntimeVersion;
   @JsonProperty("kernelVersion")
   private String kernelVersion;
   @JsonProperty("kubeProxyVersion")
   private String kubeProxyVersion;
   @JsonProperty("kubeletVersion")
   private String kubeletVersion;
   @JsonProperty("machineID")
   private String machineID;
   @JsonProperty("operatingSystem")
   private String operatingSystem;
   @JsonProperty("osImage")
   private String osImage;
   @JsonProperty("systemUUID")
   private String systemUUID;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public NodeSystemInfo() {
   }

   public NodeSystemInfo(String architecture, String bootID, String containerRuntimeVersion, String kernelVersion, String kubeProxyVersion, String kubeletVersion, String machineID, String operatingSystem, String osImage, String systemUUID) {
      this.architecture = architecture;
      this.bootID = bootID;
      this.containerRuntimeVersion = containerRuntimeVersion;
      this.kernelVersion = kernelVersion;
      this.kubeProxyVersion = kubeProxyVersion;
      this.kubeletVersion = kubeletVersion;
      this.machineID = machineID;
      this.operatingSystem = operatingSystem;
      this.osImage = osImage;
      this.systemUUID = systemUUID;
   }

   @JsonProperty("architecture")
   public String getArchitecture() {
      return this.architecture;
   }

   @JsonProperty("architecture")
   public void setArchitecture(String architecture) {
      this.architecture = architecture;
   }

   @JsonProperty("bootID")
   public String getBootID() {
      return this.bootID;
   }

   @JsonProperty("bootID")
   public void setBootID(String bootID) {
      this.bootID = bootID;
   }

   @JsonProperty("containerRuntimeVersion")
   public String getContainerRuntimeVersion() {
      return this.containerRuntimeVersion;
   }

   @JsonProperty("containerRuntimeVersion")
   public void setContainerRuntimeVersion(String containerRuntimeVersion) {
      this.containerRuntimeVersion = containerRuntimeVersion;
   }

   @JsonProperty("kernelVersion")
   public String getKernelVersion() {
      return this.kernelVersion;
   }

   @JsonProperty("kernelVersion")
   public void setKernelVersion(String kernelVersion) {
      this.kernelVersion = kernelVersion;
   }

   @JsonProperty("kubeProxyVersion")
   public String getKubeProxyVersion() {
      return this.kubeProxyVersion;
   }

   @JsonProperty("kubeProxyVersion")
   public void setKubeProxyVersion(String kubeProxyVersion) {
      this.kubeProxyVersion = kubeProxyVersion;
   }

   @JsonProperty("kubeletVersion")
   public String getKubeletVersion() {
      return this.kubeletVersion;
   }

   @JsonProperty("kubeletVersion")
   public void setKubeletVersion(String kubeletVersion) {
      this.kubeletVersion = kubeletVersion;
   }

   @JsonProperty("machineID")
   public String getMachineID() {
      return this.machineID;
   }

   @JsonProperty("machineID")
   public void setMachineID(String machineID) {
      this.machineID = machineID;
   }

   @JsonProperty("operatingSystem")
   public String getOperatingSystem() {
      return this.operatingSystem;
   }

   @JsonProperty("operatingSystem")
   public void setOperatingSystem(String operatingSystem) {
      this.operatingSystem = operatingSystem;
   }

   @JsonProperty("osImage")
   public String getOsImage() {
      return this.osImage;
   }

   @JsonProperty("osImage")
   public void setOsImage(String osImage) {
      this.osImage = osImage;
   }

   @JsonProperty("systemUUID")
   public String getSystemUUID() {
      return this.systemUUID;
   }

   @JsonProperty("systemUUID")
   public void setSystemUUID(String systemUUID) {
      this.systemUUID = systemUUID;
   }

   @JsonIgnore
   public NodeSystemInfoBuilder edit() {
      return new NodeSystemInfoBuilder(this);
   }

   @JsonIgnore
   public NodeSystemInfoBuilder toBuilder() {
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
      String var10000 = this.getArchitecture();
      return "NodeSystemInfo(architecture=" + var10000 + ", bootID=" + this.getBootID() + ", containerRuntimeVersion=" + this.getContainerRuntimeVersion() + ", kernelVersion=" + this.getKernelVersion() + ", kubeProxyVersion=" + this.getKubeProxyVersion() + ", kubeletVersion=" + this.getKubeletVersion() + ", machineID=" + this.getMachineID() + ", operatingSystem=" + this.getOperatingSystem() + ", osImage=" + this.getOsImage() + ", systemUUID=" + this.getSystemUUID() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NodeSystemInfo)) {
         return false;
      } else {
         NodeSystemInfo other = (NodeSystemInfo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$architecture = this.getArchitecture();
            Object other$architecture = other.getArchitecture();
            if (this$architecture == null) {
               if (other$architecture != null) {
                  return false;
               }
            } else if (!this$architecture.equals(other$architecture)) {
               return false;
            }

            Object this$bootID = this.getBootID();
            Object other$bootID = other.getBootID();
            if (this$bootID == null) {
               if (other$bootID != null) {
                  return false;
               }
            } else if (!this$bootID.equals(other$bootID)) {
               return false;
            }

            Object this$containerRuntimeVersion = this.getContainerRuntimeVersion();
            Object other$containerRuntimeVersion = other.getContainerRuntimeVersion();
            if (this$containerRuntimeVersion == null) {
               if (other$containerRuntimeVersion != null) {
                  return false;
               }
            } else if (!this$containerRuntimeVersion.equals(other$containerRuntimeVersion)) {
               return false;
            }

            Object this$kernelVersion = this.getKernelVersion();
            Object other$kernelVersion = other.getKernelVersion();
            if (this$kernelVersion == null) {
               if (other$kernelVersion != null) {
                  return false;
               }
            } else if (!this$kernelVersion.equals(other$kernelVersion)) {
               return false;
            }

            Object this$kubeProxyVersion = this.getKubeProxyVersion();
            Object other$kubeProxyVersion = other.getKubeProxyVersion();
            if (this$kubeProxyVersion == null) {
               if (other$kubeProxyVersion != null) {
                  return false;
               }
            } else if (!this$kubeProxyVersion.equals(other$kubeProxyVersion)) {
               return false;
            }

            Object this$kubeletVersion = this.getKubeletVersion();
            Object other$kubeletVersion = other.getKubeletVersion();
            if (this$kubeletVersion == null) {
               if (other$kubeletVersion != null) {
                  return false;
               }
            } else if (!this$kubeletVersion.equals(other$kubeletVersion)) {
               return false;
            }

            Object this$machineID = this.getMachineID();
            Object other$machineID = other.getMachineID();
            if (this$machineID == null) {
               if (other$machineID != null) {
                  return false;
               }
            } else if (!this$machineID.equals(other$machineID)) {
               return false;
            }

            Object this$operatingSystem = this.getOperatingSystem();
            Object other$operatingSystem = other.getOperatingSystem();
            if (this$operatingSystem == null) {
               if (other$operatingSystem != null) {
                  return false;
               }
            } else if (!this$operatingSystem.equals(other$operatingSystem)) {
               return false;
            }

            Object this$osImage = this.getOsImage();
            Object other$osImage = other.getOsImage();
            if (this$osImage == null) {
               if (other$osImage != null) {
                  return false;
               }
            } else if (!this$osImage.equals(other$osImage)) {
               return false;
            }

            Object this$systemUUID = this.getSystemUUID();
            Object other$systemUUID = other.getSystemUUID();
            if (this$systemUUID == null) {
               if (other$systemUUID != null) {
                  return false;
               }
            } else if (!this$systemUUID.equals(other$systemUUID)) {
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
      return other instanceof NodeSystemInfo;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $architecture = this.getArchitecture();
      result = result * 59 + ($architecture == null ? 43 : $architecture.hashCode());
      Object $bootID = this.getBootID();
      result = result * 59 + ($bootID == null ? 43 : $bootID.hashCode());
      Object $containerRuntimeVersion = this.getContainerRuntimeVersion();
      result = result * 59 + ($containerRuntimeVersion == null ? 43 : $containerRuntimeVersion.hashCode());
      Object $kernelVersion = this.getKernelVersion();
      result = result * 59 + ($kernelVersion == null ? 43 : $kernelVersion.hashCode());
      Object $kubeProxyVersion = this.getKubeProxyVersion();
      result = result * 59 + ($kubeProxyVersion == null ? 43 : $kubeProxyVersion.hashCode());
      Object $kubeletVersion = this.getKubeletVersion();
      result = result * 59 + ($kubeletVersion == null ? 43 : $kubeletVersion.hashCode());
      Object $machineID = this.getMachineID();
      result = result * 59 + ($machineID == null ? 43 : $machineID.hashCode());
      Object $operatingSystem = this.getOperatingSystem();
      result = result * 59 + ($operatingSystem == null ? 43 : $operatingSystem.hashCode());
      Object $osImage = this.getOsImage();
      result = result * 59 + ($osImage == null ? 43 : $osImage.hashCode());
      Object $systemUUID = this.getSystemUUID();
      result = result * 59 + ($systemUUID == null ? 43 : $systemUUID.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
