package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.SELinuxOptions;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SELinuxStrategyOptionsFluent extends BaseFluent {
   private String rule;
   private SELinuxOptions seLinuxOptions;
   private Map additionalProperties;

   public SELinuxStrategyOptionsFluent() {
   }

   public SELinuxStrategyOptionsFluent(SELinuxStrategyOptions instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SELinuxStrategyOptions instance) {
      instance = instance != null ? instance : new SELinuxStrategyOptions();
      if (instance != null) {
         this.withRule(instance.getRule());
         this.withSeLinuxOptions(instance.getSeLinuxOptions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getRule() {
      return this.rule;
   }

   public SELinuxStrategyOptionsFluent withRule(String rule) {
      this.rule = rule;
      return this;
   }

   public boolean hasRule() {
      return this.rule != null;
   }

   public SELinuxOptions getSeLinuxOptions() {
      return this.seLinuxOptions;
   }

   public SELinuxStrategyOptionsFluent withSeLinuxOptions(SELinuxOptions seLinuxOptions) {
      this.seLinuxOptions = seLinuxOptions;
      return this;
   }

   public boolean hasSeLinuxOptions() {
      return this.seLinuxOptions != null;
   }

   public SELinuxStrategyOptionsFluent withNewSeLinuxOptions(String level, String role, String type, String user) {
      return this.withSeLinuxOptions(new SELinuxOptions(level, role, type, user));
   }

   public SELinuxStrategyOptionsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SELinuxStrategyOptionsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SELinuxStrategyOptionsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SELinuxStrategyOptionsFluent removeFromAdditionalProperties(Map map) {
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

   public SELinuxStrategyOptionsFluent withAdditionalProperties(Map additionalProperties) {
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
            SELinuxStrategyOptionsFluent that = (SELinuxStrategyOptionsFluent)o;
            if (!Objects.equals(this.rule, that.rule)) {
               return false;
            } else if (!Objects.equals(this.seLinuxOptions, that.seLinuxOptions)) {
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
      return Objects.hash(new Object[]{this.rule, this.seLinuxOptions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.rule != null) {
         sb.append("rule:");
         sb.append(this.rule + ",");
      }

      if (this.seLinuxOptions != null) {
         sb.append("seLinuxOptions:");
         sb.append(this.seLinuxOptions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
