package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SuccessPolicyRuleFluent extends BaseFluent {
   private Integer succeededCount;
   private String succeededIndexes;
   private Map additionalProperties;

   public SuccessPolicyRuleFluent() {
   }

   public SuccessPolicyRuleFluent(SuccessPolicyRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(SuccessPolicyRule instance) {
      instance = instance != null ? instance : new SuccessPolicyRule();
      if (instance != null) {
         this.withSucceededCount(instance.getSucceededCount());
         this.withSucceededIndexes(instance.getSucceededIndexes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getSucceededCount() {
      return this.succeededCount;
   }

   public SuccessPolicyRuleFluent withSucceededCount(Integer succeededCount) {
      this.succeededCount = succeededCount;
      return this;
   }

   public boolean hasSucceededCount() {
      return this.succeededCount != null;
   }

   public String getSucceededIndexes() {
      return this.succeededIndexes;
   }

   public SuccessPolicyRuleFluent withSucceededIndexes(String succeededIndexes) {
      this.succeededIndexes = succeededIndexes;
      return this;
   }

   public boolean hasSucceededIndexes() {
      return this.succeededIndexes != null;
   }

   public SuccessPolicyRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public SuccessPolicyRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public SuccessPolicyRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public SuccessPolicyRuleFluent removeFromAdditionalProperties(Map map) {
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

   public SuccessPolicyRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            SuccessPolicyRuleFluent that = (SuccessPolicyRuleFluent)o;
            if (!Objects.equals(this.succeededCount, that.succeededCount)) {
               return false;
            } else if (!Objects.equals(this.succeededIndexes, that.succeededIndexes)) {
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
      return Objects.hash(new Object[]{this.succeededCount, this.succeededIndexes, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.succeededCount != null) {
         sb.append("succeededCount:");
         sb.append(this.succeededCount + ",");
      }

      if (this.succeededIndexes != null) {
         sb.append("succeededIndexes:");
         sb.append(this.succeededIndexes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
