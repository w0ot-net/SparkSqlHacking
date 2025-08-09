package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class OwnerReferenceFluent extends BaseFluent {
   private String apiVersion;
   private Boolean blockOwnerDeletion;
   private Boolean controller;
   private String kind;
   private String name;
   private String uid;
   private Map additionalProperties;

   public OwnerReferenceFluent() {
   }

   public OwnerReferenceFluent(OwnerReference instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(OwnerReference instance) {
      instance = instance != null ? instance : new OwnerReference();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withBlockOwnerDeletion(instance.getBlockOwnerDeletion());
         this.withController(instance.getController());
         this.withKind(instance.getKind());
         this.withName(instance.getName());
         this.withUid(instance.getUid());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public OwnerReferenceFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public Boolean getBlockOwnerDeletion() {
      return this.blockOwnerDeletion;
   }

   public OwnerReferenceFluent withBlockOwnerDeletion(Boolean blockOwnerDeletion) {
      this.blockOwnerDeletion = blockOwnerDeletion;
      return this;
   }

   public boolean hasBlockOwnerDeletion() {
      return this.blockOwnerDeletion != null;
   }

   public Boolean getController() {
      return this.controller;
   }

   public OwnerReferenceFluent withController(Boolean controller) {
      this.controller = controller;
      return this;
   }

   public boolean hasController() {
      return this.controller != null;
   }

   public String getKind() {
      return this.kind;
   }

   public OwnerReferenceFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getName() {
      return this.name;
   }

   public OwnerReferenceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public String getUid() {
      return this.uid;
   }

   public OwnerReferenceFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public OwnerReferenceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public OwnerReferenceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public OwnerReferenceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public OwnerReferenceFluent removeFromAdditionalProperties(Map map) {
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

   public OwnerReferenceFluent withAdditionalProperties(Map additionalProperties) {
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
            OwnerReferenceFluent that = (OwnerReferenceFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.blockOwnerDeletion, that.blockOwnerDeletion)) {
               return false;
            } else if (!Objects.equals(this.controller, that.controller)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.uid, that.uid)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.blockOwnerDeletion, this.controller, this.kind, this.name, this.uid, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.blockOwnerDeletion != null) {
         sb.append("blockOwnerDeletion:");
         sb.append(this.blockOwnerDeletion + ",");
      }

      if (this.controller != null) {
         sb.append("controller:");
         sb.append(this.controller + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.uid != null) {
         sb.append("uid:");
         sb.append(this.uid + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public OwnerReferenceFluent withBlockOwnerDeletion() {
      return this.withBlockOwnerDeletion(true);
   }

   public OwnerReferenceFluent withController() {
      return this.withController(true);
   }
}
