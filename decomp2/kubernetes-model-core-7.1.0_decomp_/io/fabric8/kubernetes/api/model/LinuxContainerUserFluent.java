package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class LinuxContainerUserFluent extends BaseFluent {
   private Long gid;
   private List supplementalGroups = new ArrayList();
   private Long uid;
   private Map additionalProperties;

   public LinuxContainerUserFluent() {
   }

   public LinuxContainerUserFluent(LinuxContainerUser instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(LinuxContainerUser instance) {
      instance = instance != null ? instance : new LinuxContainerUser();
      if (instance != null) {
         this.withGid(instance.getGid());
         this.withSupplementalGroups(instance.getSupplementalGroups());
         this.withUid(instance.getUid());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Long getGid() {
      return this.gid;
   }

   public LinuxContainerUserFluent withGid(Long gid) {
      this.gid = gid;
      return this;
   }

   public boolean hasGid() {
      return this.gid != null;
   }

   public LinuxContainerUserFluent addToSupplementalGroups(int index, Long item) {
      if (this.supplementalGroups == null) {
         this.supplementalGroups = new ArrayList();
      }

      this.supplementalGroups.add(index, item);
      return this;
   }

   public LinuxContainerUserFluent setToSupplementalGroups(int index, Long item) {
      if (this.supplementalGroups == null) {
         this.supplementalGroups = new ArrayList();
      }

      this.supplementalGroups.set(index, item);
      return this;
   }

   public LinuxContainerUserFluent addToSupplementalGroups(Long... items) {
      if (this.supplementalGroups == null) {
         this.supplementalGroups = new ArrayList();
      }

      for(Long item : items) {
         this.supplementalGroups.add(item);
      }

      return this;
   }

   public LinuxContainerUserFluent addAllToSupplementalGroups(Collection items) {
      if (this.supplementalGroups == null) {
         this.supplementalGroups = new ArrayList();
      }

      for(Long item : items) {
         this.supplementalGroups.add(item);
      }

      return this;
   }

   public LinuxContainerUserFluent removeFromSupplementalGroups(Long... items) {
      if (this.supplementalGroups == null) {
         return this;
      } else {
         for(Long item : items) {
            this.supplementalGroups.remove(item);
         }

         return this;
      }
   }

   public LinuxContainerUserFluent removeAllFromSupplementalGroups(Collection items) {
      if (this.supplementalGroups == null) {
         return this;
      } else {
         for(Long item : items) {
            this.supplementalGroups.remove(item);
         }

         return this;
      }
   }

   public List getSupplementalGroups() {
      return this.supplementalGroups;
   }

   public Long getSupplementalGroup(int index) {
      return (Long)this.supplementalGroups.get(index);
   }

   public Long getFirstSupplementalGroup() {
      return (Long)this.supplementalGroups.get(0);
   }

   public Long getLastSupplementalGroup() {
      return (Long)this.supplementalGroups.get(this.supplementalGroups.size() - 1);
   }

   public Long getMatchingSupplementalGroup(Predicate predicate) {
      for(Long item : this.supplementalGroups) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingSupplementalGroup(Predicate predicate) {
      for(Long item : this.supplementalGroups) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public LinuxContainerUserFluent withSupplementalGroups(List supplementalGroups) {
      if (supplementalGroups != null) {
         this.supplementalGroups = new ArrayList();

         for(Long item : supplementalGroups) {
            this.addToSupplementalGroups(item);
         }
      } else {
         this.supplementalGroups = null;
      }

      return this;
   }

   public LinuxContainerUserFluent withSupplementalGroups(Long... supplementalGroups) {
      if (this.supplementalGroups != null) {
         this.supplementalGroups.clear();
         this._visitables.remove("supplementalGroups");
      }

      if (supplementalGroups != null) {
         for(Long item : supplementalGroups) {
            this.addToSupplementalGroups(item);
         }
      }

      return this;
   }

   public boolean hasSupplementalGroups() {
      return this.supplementalGroups != null && !this.supplementalGroups.isEmpty();
   }

   public Long getUid() {
      return this.uid;
   }

   public LinuxContainerUserFluent withUid(Long uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public LinuxContainerUserFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public LinuxContainerUserFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public LinuxContainerUserFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public LinuxContainerUserFluent removeFromAdditionalProperties(Map map) {
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

   public LinuxContainerUserFluent withAdditionalProperties(Map additionalProperties) {
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
            LinuxContainerUserFluent that = (LinuxContainerUserFluent)o;
            if (!Objects.equals(this.gid, that.gid)) {
               return false;
            } else if (!Objects.equals(this.supplementalGroups, that.supplementalGroups)) {
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
      return Objects.hash(new Object[]{this.gid, this.supplementalGroups, this.uid, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.gid != null) {
         sb.append("gid:");
         sb.append(this.gid + ",");
      }

      if (this.supplementalGroups != null && !this.supplementalGroups.isEmpty()) {
         sb.append("supplementalGroups:");
         sb.append(this.supplementalGroups + ",");
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
}
