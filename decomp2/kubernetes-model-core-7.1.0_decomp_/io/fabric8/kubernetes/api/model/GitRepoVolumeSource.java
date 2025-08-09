package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"directory", "repository", "revision"})
public class GitRepoVolumeSource implements Editable, KubernetesResource {
   @JsonProperty("directory")
   private String directory;
   @JsonProperty("repository")
   private String repository;
   @JsonProperty("revision")
   private String revision;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GitRepoVolumeSource() {
   }

   public GitRepoVolumeSource(String directory, String repository, String revision) {
      this.directory = directory;
      this.repository = repository;
      this.revision = revision;
   }

   @JsonProperty("directory")
   public String getDirectory() {
      return this.directory;
   }

   @JsonProperty("directory")
   public void setDirectory(String directory) {
      this.directory = directory;
   }

   @JsonProperty("repository")
   public String getRepository() {
      return this.repository;
   }

   @JsonProperty("repository")
   public void setRepository(String repository) {
      this.repository = repository;
   }

   @JsonProperty("revision")
   public String getRevision() {
      return this.revision;
   }

   @JsonProperty("revision")
   public void setRevision(String revision) {
      this.revision = revision;
   }

   @JsonIgnore
   public GitRepoVolumeSourceBuilder edit() {
      return new GitRepoVolumeSourceBuilder(this);
   }

   @JsonIgnore
   public GitRepoVolumeSourceBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      String var10000 = this.getDirectory();
      return "GitRepoVolumeSource(directory=" + var10000 + ", repository=" + this.getRepository() + ", revision=" + this.getRevision() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GitRepoVolumeSource)) {
         return false;
      } else {
         GitRepoVolumeSource other = (GitRepoVolumeSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$directory = this.getDirectory();
            Object other$directory = other.getDirectory();
            if (this$directory == null) {
               if (other$directory != null) {
                  return false;
               }
            } else if (!this$directory.equals(other$directory)) {
               return false;
            }

            Object this$repository = this.getRepository();
            Object other$repository = other.getRepository();
            if (this$repository == null) {
               if (other$repository != null) {
                  return false;
               }
            } else if (!this$repository.equals(other$repository)) {
               return false;
            }

            Object this$revision = this.getRevision();
            Object other$revision = other.getRevision();
            if (this$revision == null) {
               if (other$revision != null) {
                  return false;
               }
            } else if (!this$revision.equals(other$revision)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof GitRepoVolumeSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $directory = this.getDirectory();
      result = result * 59 + ($directory == null ? 43 : $directory.hashCode());
      Object $repository = this.getRepository();
      result = result * 59 + ($repository == null ? 43 : $repository.hashCode());
      Object $revision = this.getRevision();
      result = result * 59 + ($revision == null ? 43 : $revision.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
