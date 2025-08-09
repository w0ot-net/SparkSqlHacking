package io.fabric8.kubernetes.api.model.coordination.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.MicroTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LeaseCandidateSpecFluent extends BaseFluent {
   private String binaryVersion;
   private String emulationVersion;
   private String leaseName;
   private MicroTime pingTime;
   private MicroTime renewTime;
   private String strategy;
   private Map additionalProperties;

   public LeaseCandidateSpecFluent() {
   }

   public LeaseCandidateSpecFluent(LeaseCandidateSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(LeaseCandidateSpec instance) {
      instance = instance != null ? instance : new LeaseCandidateSpec();
      if (instance != null) {
         this.withBinaryVersion(instance.getBinaryVersion());
         this.withEmulationVersion(instance.getEmulationVersion());
         this.withLeaseName(instance.getLeaseName());
         this.withPingTime(instance.getPingTime());
         this.withRenewTime(instance.getRenewTime());
         this.withStrategy(instance.getStrategy());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getBinaryVersion() {
      return this.binaryVersion;
   }

   public LeaseCandidateSpecFluent withBinaryVersion(String binaryVersion) {
      this.binaryVersion = binaryVersion;
      return this;
   }

   public boolean hasBinaryVersion() {
      return this.binaryVersion != null;
   }

   public String getEmulationVersion() {
      return this.emulationVersion;
   }

   public LeaseCandidateSpecFluent withEmulationVersion(String emulationVersion) {
      this.emulationVersion = emulationVersion;
      return this;
   }

   public boolean hasEmulationVersion() {
      return this.emulationVersion != null;
   }

   public String getLeaseName() {
      return this.leaseName;
   }

   public LeaseCandidateSpecFluent withLeaseName(String leaseName) {
      this.leaseName = leaseName;
      return this;
   }

   public boolean hasLeaseName() {
      return this.leaseName != null;
   }

   public MicroTime getPingTime() {
      return this.pingTime;
   }

   public LeaseCandidateSpecFluent withPingTime(MicroTime pingTime) {
      this.pingTime = pingTime;
      return this;
   }

   public boolean hasPingTime() {
      return this.pingTime != null;
   }

   public LeaseCandidateSpecFluent withNewPingTime(String time) {
      return this.withPingTime(new MicroTime(time));
   }

   public MicroTime getRenewTime() {
      return this.renewTime;
   }

   public LeaseCandidateSpecFluent withRenewTime(MicroTime renewTime) {
      this.renewTime = renewTime;
      return this;
   }

   public boolean hasRenewTime() {
      return this.renewTime != null;
   }

   public LeaseCandidateSpecFluent withNewRenewTime(String time) {
      return this.withRenewTime(new MicroTime(time));
   }

   public String getStrategy() {
      return this.strategy;
   }

   public LeaseCandidateSpecFluent withStrategy(String strategy) {
      this.strategy = strategy;
      return this;
   }

   public boolean hasStrategy() {
      return this.strategy != null;
   }

   public LeaseCandidateSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public LeaseCandidateSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public LeaseCandidateSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public LeaseCandidateSpecFluent removeFromAdditionalProperties(Map map) {
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

   public LeaseCandidateSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            LeaseCandidateSpecFluent that = (LeaseCandidateSpecFluent)o;
            if (!Objects.equals(this.binaryVersion, that.binaryVersion)) {
               return false;
            } else if (!Objects.equals(this.emulationVersion, that.emulationVersion)) {
               return false;
            } else if (!Objects.equals(this.leaseName, that.leaseName)) {
               return false;
            } else if (!Objects.equals(this.pingTime, that.pingTime)) {
               return false;
            } else if (!Objects.equals(this.renewTime, that.renewTime)) {
               return false;
            } else if (!Objects.equals(this.strategy, that.strategy)) {
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
      return Objects.hash(new Object[]{this.binaryVersion, this.emulationVersion, this.leaseName, this.pingTime, this.renewTime, this.strategy, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.binaryVersion != null) {
         sb.append("binaryVersion:");
         sb.append(this.binaryVersion + ",");
      }

      if (this.emulationVersion != null) {
         sb.append("emulationVersion:");
         sb.append(this.emulationVersion + ",");
      }

      if (this.leaseName != null) {
         sb.append("leaseName:");
         sb.append(this.leaseName + ",");
      }

      if (this.pingTime != null) {
         sb.append("pingTime:");
         sb.append(this.pingTime + ",");
      }

      if (this.renewTime != null) {
         sb.append("renewTime:");
         sb.append(this.renewTime + ",");
      }

      if (this.strategy != null) {
         sb.append("strategy:");
         sb.append(this.strategy + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
