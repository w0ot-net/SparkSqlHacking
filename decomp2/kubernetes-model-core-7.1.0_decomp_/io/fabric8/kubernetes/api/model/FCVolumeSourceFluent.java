package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class FCVolumeSourceFluent extends BaseFluent {
   private String fsType;
   private Integer lun;
   private Boolean readOnly;
   private List targetWWNs = new ArrayList();
   private List wwids = new ArrayList();
   private Map additionalProperties;

   public FCVolumeSourceFluent() {
   }

   public FCVolumeSourceFluent(FCVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(FCVolumeSource instance) {
      instance = instance != null ? instance : new FCVolumeSource();
      if (instance != null) {
         this.withFsType(instance.getFsType());
         this.withLun(instance.getLun());
         this.withReadOnly(instance.getReadOnly());
         this.withTargetWWNs(instance.getTargetWWNs());
         this.withWwids(instance.getWwids());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getFsType() {
      return this.fsType;
   }

   public FCVolumeSourceFluent withFsType(String fsType) {
      this.fsType = fsType;
      return this;
   }

   public boolean hasFsType() {
      return this.fsType != null;
   }

   public Integer getLun() {
      return this.lun;
   }

   public FCVolumeSourceFluent withLun(Integer lun) {
      this.lun = lun;
      return this;
   }

   public boolean hasLun() {
      return this.lun != null;
   }

   public Boolean getReadOnly() {
      return this.readOnly;
   }

   public FCVolumeSourceFluent withReadOnly(Boolean readOnly) {
      this.readOnly = readOnly;
      return this;
   }

   public boolean hasReadOnly() {
      return this.readOnly != null;
   }

   public FCVolumeSourceFluent addToTargetWWNs(int index, String item) {
      if (this.targetWWNs == null) {
         this.targetWWNs = new ArrayList();
      }

      this.targetWWNs.add(index, item);
      return this;
   }

   public FCVolumeSourceFluent setToTargetWWNs(int index, String item) {
      if (this.targetWWNs == null) {
         this.targetWWNs = new ArrayList();
      }

      this.targetWWNs.set(index, item);
      return this;
   }

   public FCVolumeSourceFluent addToTargetWWNs(String... items) {
      if (this.targetWWNs == null) {
         this.targetWWNs = new ArrayList();
      }

      for(String item : items) {
         this.targetWWNs.add(item);
      }

      return this;
   }

   public FCVolumeSourceFluent addAllToTargetWWNs(Collection items) {
      if (this.targetWWNs == null) {
         this.targetWWNs = new ArrayList();
      }

      for(String item : items) {
         this.targetWWNs.add(item);
      }

      return this;
   }

   public FCVolumeSourceFluent removeFromTargetWWNs(String... items) {
      if (this.targetWWNs == null) {
         return this;
      } else {
         for(String item : items) {
            this.targetWWNs.remove(item);
         }

         return this;
      }
   }

   public FCVolumeSourceFluent removeAllFromTargetWWNs(Collection items) {
      if (this.targetWWNs == null) {
         return this;
      } else {
         for(String item : items) {
            this.targetWWNs.remove(item);
         }

         return this;
      }
   }

   public List getTargetWWNs() {
      return this.targetWWNs;
   }

   public String getTargetWWN(int index) {
      return (String)this.targetWWNs.get(index);
   }

   public String getFirstTargetWWN() {
      return (String)this.targetWWNs.get(0);
   }

   public String getLastTargetWWN() {
      return (String)this.targetWWNs.get(this.targetWWNs.size() - 1);
   }

   public String getMatchingTargetWWN(Predicate predicate) {
      for(String item : this.targetWWNs) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingTargetWWN(Predicate predicate) {
      for(String item : this.targetWWNs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public FCVolumeSourceFluent withTargetWWNs(List targetWWNs) {
      if (targetWWNs != null) {
         this.targetWWNs = new ArrayList();

         for(String item : targetWWNs) {
            this.addToTargetWWNs(item);
         }
      } else {
         this.targetWWNs = null;
      }

      return this;
   }

   public FCVolumeSourceFluent withTargetWWNs(String... targetWWNs) {
      if (this.targetWWNs != null) {
         this.targetWWNs.clear();
         this._visitables.remove("targetWWNs");
      }

      if (targetWWNs != null) {
         for(String item : targetWWNs) {
            this.addToTargetWWNs(item);
         }
      }

      return this;
   }

   public boolean hasTargetWWNs() {
      return this.targetWWNs != null && !this.targetWWNs.isEmpty();
   }

   public FCVolumeSourceFluent addToWwids(int index, String item) {
      if (this.wwids == null) {
         this.wwids = new ArrayList();
      }

      this.wwids.add(index, item);
      return this;
   }

   public FCVolumeSourceFluent setToWwids(int index, String item) {
      if (this.wwids == null) {
         this.wwids = new ArrayList();
      }

      this.wwids.set(index, item);
      return this;
   }

   public FCVolumeSourceFluent addToWwids(String... items) {
      if (this.wwids == null) {
         this.wwids = new ArrayList();
      }

      for(String item : items) {
         this.wwids.add(item);
      }

      return this;
   }

   public FCVolumeSourceFluent addAllToWwids(Collection items) {
      if (this.wwids == null) {
         this.wwids = new ArrayList();
      }

      for(String item : items) {
         this.wwids.add(item);
      }

      return this;
   }

   public FCVolumeSourceFluent removeFromWwids(String... items) {
      if (this.wwids == null) {
         return this;
      } else {
         for(String item : items) {
            this.wwids.remove(item);
         }

         return this;
      }
   }

   public FCVolumeSourceFluent removeAllFromWwids(Collection items) {
      if (this.wwids == null) {
         return this;
      } else {
         for(String item : items) {
            this.wwids.remove(item);
         }

         return this;
      }
   }

   public List getWwids() {
      return this.wwids;
   }

   public String getWwid(int index) {
      return (String)this.wwids.get(index);
   }

   public String getFirstWwid() {
      return (String)this.wwids.get(0);
   }

   public String getLastWwid() {
      return (String)this.wwids.get(this.wwids.size() - 1);
   }

   public String getMatchingWwid(Predicate predicate) {
      for(String item : this.wwids) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingWwid(Predicate predicate) {
      for(String item : this.wwids) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public FCVolumeSourceFluent withWwids(List wwids) {
      if (wwids != null) {
         this.wwids = new ArrayList();

         for(String item : wwids) {
            this.addToWwids(item);
         }
      } else {
         this.wwids = null;
      }

      return this;
   }

   public FCVolumeSourceFluent withWwids(String... wwids) {
      if (this.wwids != null) {
         this.wwids.clear();
         this._visitables.remove("wwids");
      }

      if (wwids != null) {
         for(String item : wwids) {
            this.addToWwids(item);
         }
      }

      return this;
   }

   public boolean hasWwids() {
      return this.wwids != null && !this.wwids.isEmpty();
   }

   public FCVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public FCVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public FCVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public FCVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public FCVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            FCVolumeSourceFluent that = (FCVolumeSourceFluent)o;
            if (!Objects.equals(this.fsType, that.fsType)) {
               return false;
            } else if (!Objects.equals(this.lun, that.lun)) {
               return false;
            } else if (!Objects.equals(this.readOnly, that.readOnly)) {
               return false;
            } else if (!Objects.equals(this.targetWWNs, that.targetWWNs)) {
               return false;
            } else if (!Objects.equals(this.wwids, that.wwids)) {
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
      return Objects.hash(new Object[]{this.fsType, this.lun, this.readOnly, this.targetWWNs, this.wwids, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.fsType != null) {
         sb.append("fsType:");
         sb.append(this.fsType + ",");
      }

      if (this.lun != null) {
         sb.append("lun:");
         sb.append(this.lun + ",");
      }

      if (this.readOnly != null) {
         sb.append("readOnly:");
         sb.append(this.readOnly + ",");
      }

      if (this.targetWWNs != null && !this.targetWWNs.isEmpty()) {
         sb.append("targetWWNs:");
         sb.append(this.targetWWNs + ",");
      }

      if (this.wwids != null && !this.wwids.isEmpty()) {
         sb.append("wwids:");
         sb.append(this.wwids + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public FCVolumeSourceFluent withReadOnly() {
      return this.withReadOnly(true);
   }
}
