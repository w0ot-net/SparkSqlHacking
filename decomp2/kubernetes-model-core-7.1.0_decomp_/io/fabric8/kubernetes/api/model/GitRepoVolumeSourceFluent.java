package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GitRepoVolumeSourceFluent extends BaseFluent {
   private String directory;
   private String repository;
   private String revision;
   private Map additionalProperties;

   public GitRepoVolumeSourceFluent() {
   }

   public GitRepoVolumeSourceFluent(GitRepoVolumeSource instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GitRepoVolumeSource instance) {
      instance = instance != null ? instance : new GitRepoVolumeSource();
      if (instance != null) {
         this.withDirectory(instance.getDirectory());
         this.withRepository(instance.getRepository());
         this.withRevision(instance.getRevision());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getDirectory() {
      return this.directory;
   }

   public GitRepoVolumeSourceFluent withDirectory(String directory) {
      this.directory = directory;
      return this;
   }

   public boolean hasDirectory() {
      return this.directory != null;
   }

   public String getRepository() {
      return this.repository;
   }

   public GitRepoVolumeSourceFluent withRepository(String repository) {
      this.repository = repository;
      return this;
   }

   public boolean hasRepository() {
      return this.repository != null;
   }

   public String getRevision() {
      return this.revision;
   }

   public GitRepoVolumeSourceFluent withRevision(String revision) {
      this.revision = revision;
      return this;
   }

   public boolean hasRevision() {
      return this.revision != null;
   }

   public GitRepoVolumeSourceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GitRepoVolumeSourceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GitRepoVolumeSourceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GitRepoVolumeSourceFluent removeFromAdditionalProperties(Map map) {
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

   public GitRepoVolumeSourceFluent withAdditionalProperties(Map additionalProperties) {
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
            GitRepoVolumeSourceFluent that = (GitRepoVolumeSourceFluent)o;
            if (!Objects.equals(this.directory, that.directory)) {
               return false;
            } else if (!Objects.equals(this.repository, that.repository)) {
               return false;
            } else if (!Objects.equals(this.revision, that.revision)) {
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
      return Objects.hash(new Object[]{this.directory, this.repository, this.revision, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.directory != null) {
         sb.append("directory:");
         sb.append(this.directory + ",");
      }

      if (this.repository != null) {
         sb.append("repository:");
         sb.append(this.repository + ",");
      }

      if (this.revision != null) {
         sb.append("revision:");
         sb.append(this.revision + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
