package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class WindowsSecurityContextOptionsFluent extends BaseFluent {
   private String gmsaCredentialSpec;
   private String gmsaCredentialSpecName;
   private Boolean hostProcess;
   private String runAsUserName;
   private Map additionalProperties;

   public WindowsSecurityContextOptionsFluent() {
   }

   public WindowsSecurityContextOptionsFluent(WindowsSecurityContextOptions instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(WindowsSecurityContextOptions instance) {
      instance = instance != null ? instance : new WindowsSecurityContextOptions();
      if (instance != null) {
         this.withGmsaCredentialSpec(instance.getGmsaCredentialSpec());
         this.withGmsaCredentialSpecName(instance.getGmsaCredentialSpecName());
         this.withHostProcess(instance.getHostProcess());
         this.withRunAsUserName(instance.getRunAsUserName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getGmsaCredentialSpec() {
      return this.gmsaCredentialSpec;
   }

   public WindowsSecurityContextOptionsFluent withGmsaCredentialSpec(String gmsaCredentialSpec) {
      this.gmsaCredentialSpec = gmsaCredentialSpec;
      return this;
   }

   public boolean hasGmsaCredentialSpec() {
      return this.gmsaCredentialSpec != null;
   }

   public String getGmsaCredentialSpecName() {
      return this.gmsaCredentialSpecName;
   }

   public WindowsSecurityContextOptionsFluent withGmsaCredentialSpecName(String gmsaCredentialSpecName) {
      this.gmsaCredentialSpecName = gmsaCredentialSpecName;
      return this;
   }

   public boolean hasGmsaCredentialSpecName() {
      return this.gmsaCredentialSpecName != null;
   }

   public Boolean getHostProcess() {
      return this.hostProcess;
   }

   public WindowsSecurityContextOptionsFluent withHostProcess(Boolean hostProcess) {
      this.hostProcess = hostProcess;
      return this;
   }

   public boolean hasHostProcess() {
      return this.hostProcess != null;
   }

   public String getRunAsUserName() {
      return this.runAsUserName;
   }

   public WindowsSecurityContextOptionsFluent withRunAsUserName(String runAsUserName) {
      this.runAsUserName = runAsUserName;
      return this;
   }

   public boolean hasRunAsUserName() {
      return this.runAsUserName != null;
   }

   public WindowsSecurityContextOptionsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public WindowsSecurityContextOptionsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public WindowsSecurityContextOptionsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public WindowsSecurityContextOptionsFluent removeFromAdditionalProperties(Map map) {
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

   public WindowsSecurityContextOptionsFluent withAdditionalProperties(Map additionalProperties) {
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
            WindowsSecurityContextOptionsFluent that = (WindowsSecurityContextOptionsFluent)o;
            if (!Objects.equals(this.gmsaCredentialSpec, that.gmsaCredentialSpec)) {
               return false;
            } else if (!Objects.equals(this.gmsaCredentialSpecName, that.gmsaCredentialSpecName)) {
               return false;
            } else if (!Objects.equals(this.hostProcess, that.hostProcess)) {
               return false;
            } else if (!Objects.equals(this.runAsUserName, that.runAsUserName)) {
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
      return Objects.hash(new Object[]{this.gmsaCredentialSpec, this.gmsaCredentialSpecName, this.hostProcess, this.runAsUserName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.gmsaCredentialSpec != null) {
         sb.append("gmsaCredentialSpec:");
         sb.append(this.gmsaCredentialSpec + ",");
      }

      if (this.gmsaCredentialSpecName != null) {
         sb.append("gmsaCredentialSpecName:");
         sb.append(this.gmsaCredentialSpecName + ",");
      }

      if (this.hostProcess != null) {
         sb.append("hostProcess:");
         sb.append(this.hostProcess + ",");
      }

      if (this.runAsUserName != null) {
         sb.append("runAsUserName:");
         sb.append(this.runAsUserName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public WindowsSecurityContextOptionsFluent withHostProcess() {
      return this.withHostProcess(true);
   }
}
