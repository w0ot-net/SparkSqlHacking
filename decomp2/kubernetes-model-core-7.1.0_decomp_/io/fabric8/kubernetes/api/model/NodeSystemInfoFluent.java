package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class NodeSystemInfoFluent extends BaseFluent {
   private String architecture;
   private String bootID;
   private String containerRuntimeVersion;
   private String kernelVersion;
   private String kubeProxyVersion;
   private String kubeletVersion;
   private String machineID;
   private String operatingSystem;
   private String osImage;
   private String systemUUID;
   private Map additionalProperties;

   public NodeSystemInfoFluent() {
   }

   public NodeSystemInfoFluent(NodeSystemInfo instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeSystemInfo instance) {
      instance = instance != null ? instance : new NodeSystemInfo();
      if (instance != null) {
         this.withArchitecture(instance.getArchitecture());
         this.withBootID(instance.getBootID());
         this.withContainerRuntimeVersion(instance.getContainerRuntimeVersion());
         this.withKernelVersion(instance.getKernelVersion());
         this.withKubeProxyVersion(instance.getKubeProxyVersion());
         this.withKubeletVersion(instance.getKubeletVersion());
         this.withMachineID(instance.getMachineID());
         this.withOperatingSystem(instance.getOperatingSystem());
         this.withOsImage(instance.getOsImage());
         this.withSystemUUID(instance.getSystemUUID());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getArchitecture() {
      return this.architecture;
   }

   public NodeSystemInfoFluent withArchitecture(String architecture) {
      this.architecture = architecture;
      return this;
   }

   public boolean hasArchitecture() {
      return this.architecture != null;
   }

   public String getBootID() {
      return this.bootID;
   }

   public NodeSystemInfoFluent withBootID(String bootID) {
      this.bootID = bootID;
      return this;
   }

   public boolean hasBootID() {
      return this.bootID != null;
   }

   public String getContainerRuntimeVersion() {
      return this.containerRuntimeVersion;
   }

   public NodeSystemInfoFluent withContainerRuntimeVersion(String containerRuntimeVersion) {
      this.containerRuntimeVersion = containerRuntimeVersion;
      return this;
   }

   public boolean hasContainerRuntimeVersion() {
      return this.containerRuntimeVersion != null;
   }

   public String getKernelVersion() {
      return this.kernelVersion;
   }

   public NodeSystemInfoFluent withKernelVersion(String kernelVersion) {
      this.kernelVersion = kernelVersion;
      return this;
   }

   public boolean hasKernelVersion() {
      return this.kernelVersion != null;
   }

   public String getKubeProxyVersion() {
      return this.kubeProxyVersion;
   }

   public NodeSystemInfoFluent withKubeProxyVersion(String kubeProxyVersion) {
      this.kubeProxyVersion = kubeProxyVersion;
      return this;
   }

   public boolean hasKubeProxyVersion() {
      return this.kubeProxyVersion != null;
   }

   public String getKubeletVersion() {
      return this.kubeletVersion;
   }

   public NodeSystemInfoFluent withKubeletVersion(String kubeletVersion) {
      this.kubeletVersion = kubeletVersion;
      return this;
   }

   public boolean hasKubeletVersion() {
      return this.kubeletVersion != null;
   }

   public String getMachineID() {
      return this.machineID;
   }

   public NodeSystemInfoFluent withMachineID(String machineID) {
      this.machineID = machineID;
      return this;
   }

   public boolean hasMachineID() {
      return this.machineID != null;
   }

   public String getOperatingSystem() {
      return this.operatingSystem;
   }

   public NodeSystemInfoFluent withOperatingSystem(String operatingSystem) {
      this.operatingSystem = operatingSystem;
      return this;
   }

   public boolean hasOperatingSystem() {
      return this.operatingSystem != null;
   }

   public String getOsImage() {
      return this.osImage;
   }

   public NodeSystemInfoFluent withOsImage(String osImage) {
      this.osImage = osImage;
      return this;
   }

   public boolean hasOsImage() {
      return this.osImage != null;
   }

   public String getSystemUUID() {
      return this.systemUUID;
   }

   public NodeSystemInfoFluent withSystemUUID(String systemUUID) {
      this.systemUUID = systemUUID;
      return this;
   }

   public boolean hasSystemUUID() {
      return this.systemUUID != null;
   }

   public NodeSystemInfoFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeSystemInfoFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeSystemInfoFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeSystemInfoFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public NodeSystemInfoFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            NodeSystemInfoFluent that = (NodeSystemInfoFluent)o;
            if (!Objects.equals(this.architecture, that.architecture)) {
               return false;
            } else if (!Objects.equals(this.bootID, that.bootID)) {
               return false;
            } else if (!Objects.equals(this.containerRuntimeVersion, that.containerRuntimeVersion)) {
               return false;
            } else if (!Objects.equals(this.kernelVersion, that.kernelVersion)) {
               return false;
            } else if (!Objects.equals(this.kubeProxyVersion, that.kubeProxyVersion)) {
               return false;
            } else if (!Objects.equals(this.kubeletVersion, that.kubeletVersion)) {
               return false;
            } else if (!Objects.equals(this.machineID, that.machineID)) {
               return false;
            } else if (!Objects.equals(this.operatingSystem, that.operatingSystem)) {
               return false;
            } else if (!Objects.equals(this.osImage, that.osImage)) {
               return false;
            } else if (!Objects.equals(this.systemUUID, that.systemUUID)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.architecture, this.bootID, this.containerRuntimeVersion, this.kernelVersion, this.kubeProxyVersion, this.kubeletVersion, this.machineID, this.operatingSystem, this.osImage, this.systemUUID, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.architecture != null) {
         sb.append("architecture:");
         sb.append(this.architecture + ",");
      }

      if (this.bootID != null) {
         sb.append("bootID:");
         sb.append(this.bootID + ",");
      }

      if (this.containerRuntimeVersion != null) {
         sb.append("containerRuntimeVersion:");
         sb.append(this.containerRuntimeVersion + ",");
      }

      if (this.kernelVersion != null) {
         sb.append("kernelVersion:");
         sb.append(this.kernelVersion + ",");
      }

      if (this.kubeProxyVersion != null) {
         sb.append("kubeProxyVersion:");
         sb.append(this.kubeProxyVersion + ",");
      }

      if (this.kubeletVersion != null) {
         sb.append("kubeletVersion:");
         sb.append(this.kubeletVersion + ",");
      }

      if (this.machineID != null) {
         sb.append("machineID:");
         sb.append(this.machineID + ",");
      }

      if (this.operatingSystem != null) {
         sb.append("operatingSystem:");
         sb.append(this.operatingSystem + ",");
      }

      if (this.osImage != null) {
         sb.append("osImage:");
         sb.append(this.osImage + ",");
      }

      if (this.systemUUID != null) {
         sb.append("systemUUID:");
         sb.append(this.systemUUID + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
