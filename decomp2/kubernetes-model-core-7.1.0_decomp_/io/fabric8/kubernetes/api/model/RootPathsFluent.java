package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class RootPathsFluent extends BaseFluent {
   private List paths = new ArrayList();
   private Map additionalProperties;

   public RootPathsFluent() {
   }

   public RootPathsFluent(RootPaths instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RootPaths instance) {
      instance = instance != null ? instance : new RootPaths();
      if (instance != null) {
         this.withPaths(instance.getPaths());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public RootPathsFluent addToPaths(int index, String item) {
      if (this.paths == null) {
         this.paths = new ArrayList();
      }

      this.paths.add(index, item);
      return this;
   }

   public RootPathsFluent setToPaths(int index, String item) {
      if (this.paths == null) {
         this.paths = new ArrayList();
      }

      this.paths.set(index, item);
      return this;
   }

   public RootPathsFluent addToPaths(String... items) {
      if (this.paths == null) {
         this.paths = new ArrayList();
      }

      for(String item : items) {
         this.paths.add(item);
      }

      return this;
   }

   public RootPathsFluent addAllToPaths(Collection items) {
      if (this.paths == null) {
         this.paths = new ArrayList();
      }

      for(String item : items) {
         this.paths.add(item);
      }

      return this;
   }

   public RootPathsFluent removeFromPaths(String... items) {
      if (this.paths == null) {
         return this;
      } else {
         for(String item : items) {
            this.paths.remove(item);
         }

         return this;
      }
   }

   public RootPathsFluent removeAllFromPaths(Collection items) {
      if (this.paths == null) {
         return this;
      } else {
         for(String item : items) {
            this.paths.remove(item);
         }

         return this;
      }
   }

   public List getPaths() {
      return this.paths;
   }

   public String getPath(int index) {
      return (String)this.paths.get(index);
   }

   public String getFirstPath() {
      return (String)this.paths.get(0);
   }

   public String getLastPath() {
      return (String)this.paths.get(this.paths.size() - 1);
   }

   public String getMatchingPath(Predicate predicate) {
      for(String item : this.paths) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingPath(Predicate predicate) {
      for(String item : this.paths) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RootPathsFluent withPaths(List paths) {
      if (paths != null) {
         this.paths = new ArrayList();

         for(String item : paths) {
            this.addToPaths(item);
         }
      } else {
         this.paths = null;
      }

      return this;
   }

   public RootPathsFluent withPaths(String... paths) {
      if (this.paths != null) {
         this.paths.clear();
         this._visitables.remove("paths");
      }

      if (paths != null) {
         for(String item : paths) {
            this.addToPaths(item);
         }
      }

      return this;
   }

   public boolean hasPaths() {
      return this.paths != null && !this.paths.isEmpty();
   }

   public RootPathsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RootPathsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RootPathsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RootPathsFluent removeFromAdditionalProperties(Map map) {
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

   public RootPathsFluent withAdditionalProperties(Map additionalProperties) {
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
            RootPathsFluent that = (RootPathsFluent)o;
            if (!Objects.equals(this.paths, that.paths)) {
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
      return Objects.hash(new Object[]{this.paths, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.paths != null && !this.paths.isEmpty()) {
         sb.append("paths:");
         sb.append(this.paths + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
