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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"annotations", "creationTimestamp", "deletionGracePeriodSeconds", "deletionTimestamp", "finalizers", "generateName", "generation", "labels", "managedFields", "name", "namespace", "ownerReferences", "resourceVersion", "selfLink", "uid"})
public class ObjectMeta implements Editable, KubernetesResource {
   @JsonProperty("annotations")
   @JsonInclude(Include.NON_EMPTY)
   private Map annotations = new LinkedHashMap();
   @JsonProperty("creationTimestamp")
   private String creationTimestamp;
   @JsonProperty("deletionGracePeriodSeconds")
   private Long deletionGracePeriodSeconds;
   @JsonProperty("deletionTimestamp")
   private String deletionTimestamp;
   @JsonProperty("finalizers")
   @JsonInclude(Include.NON_EMPTY)
   private List finalizers = new ArrayList();
   @JsonProperty("generateName")
   private String generateName;
   @JsonProperty("generation")
   private Long generation;
   @JsonProperty("labels")
   @JsonInclude(Include.NON_EMPTY)
   private Map labels = new LinkedHashMap();
   @JsonProperty("managedFields")
   @JsonInclude(Include.NON_EMPTY)
   private List managedFields = new ArrayList();
   @JsonProperty("name")
   private String name;
   @JsonProperty("namespace")
   private String namespace;
   @JsonProperty("ownerReferences")
   @JsonInclude(Include.NON_EMPTY)
   private List ownerReferences = new ArrayList();
   @JsonProperty("resourceVersion")
   private String resourceVersion;
   @JsonProperty("selfLink")
   private String selfLink;
   @JsonProperty("uid")
   private String uid;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ObjectMeta() {
   }

   public ObjectMeta(Map annotations, String creationTimestamp, Long deletionGracePeriodSeconds, String deletionTimestamp, List finalizers, String generateName, Long generation, Map labels, List managedFields, String name, String namespace, List ownerReferences, String resourceVersion, String selfLink, String uid) {
      this.annotations = annotations;
      this.creationTimestamp = creationTimestamp;
      this.deletionGracePeriodSeconds = deletionGracePeriodSeconds;
      this.deletionTimestamp = deletionTimestamp;
      this.finalizers = finalizers;
      this.generateName = generateName;
      this.generation = generation;
      this.labels = labels;
      this.managedFields = managedFields;
      this.name = name;
      this.namespace = namespace;
      this.ownerReferences = ownerReferences;
      this.resourceVersion = resourceVersion;
      this.selfLink = selfLink;
      this.uid = uid;
   }

   @JsonProperty("annotations")
   @JsonInclude(Include.NON_EMPTY)
   public Map getAnnotations() {
      return this.annotations;
   }

   @JsonProperty("annotations")
   public void setAnnotations(Map annotations) {
      this.annotations = annotations;
   }

   @JsonProperty("creationTimestamp")
   public String getCreationTimestamp() {
      return this.creationTimestamp;
   }

   @JsonProperty("creationTimestamp")
   public void setCreationTimestamp(String creationTimestamp) {
      this.creationTimestamp = creationTimestamp;
   }

   @JsonProperty("deletionGracePeriodSeconds")
   public Long getDeletionGracePeriodSeconds() {
      return this.deletionGracePeriodSeconds;
   }

   @JsonProperty("deletionGracePeriodSeconds")
   public void setDeletionGracePeriodSeconds(Long deletionGracePeriodSeconds) {
      this.deletionGracePeriodSeconds = deletionGracePeriodSeconds;
   }

   @JsonProperty("deletionTimestamp")
   public String getDeletionTimestamp() {
      return this.deletionTimestamp;
   }

   @JsonProperty("deletionTimestamp")
   public void setDeletionTimestamp(String deletionTimestamp) {
      this.deletionTimestamp = deletionTimestamp;
   }

   @JsonProperty("finalizers")
   @JsonInclude(Include.NON_EMPTY)
   public List getFinalizers() {
      return this.finalizers;
   }

   @JsonProperty("finalizers")
   public void setFinalizers(List finalizers) {
      this.finalizers = finalizers;
   }

   @JsonProperty("generateName")
   public String getGenerateName() {
      return this.generateName;
   }

   @JsonProperty("generateName")
   public void setGenerateName(String generateName) {
      this.generateName = generateName;
   }

   @JsonProperty("generation")
   public Long getGeneration() {
      return this.generation;
   }

   @JsonProperty("generation")
   public void setGeneration(Long generation) {
      this.generation = generation;
   }

   @JsonProperty("labels")
   @JsonInclude(Include.NON_EMPTY)
   public Map getLabels() {
      return this.labels;
   }

   @JsonProperty("labels")
   public void setLabels(Map labels) {
      this.labels = labels;
   }

   @JsonProperty("managedFields")
   @JsonInclude(Include.NON_EMPTY)
   public List getManagedFields() {
      return this.managedFields;
   }

   @JsonProperty("managedFields")
   public void setManagedFields(List managedFields) {
      this.managedFields = managedFields;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("namespace")
   public String getNamespace() {
      return this.namespace;
   }

   @JsonProperty("namespace")
   public void setNamespace(String namespace) {
      this.namespace = namespace;
   }

   @JsonProperty("ownerReferences")
   @JsonInclude(Include.NON_EMPTY)
   public List getOwnerReferences() {
      return this.ownerReferences;
   }

   @JsonProperty("ownerReferences")
   public void setOwnerReferences(List ownerReferences) {
      this.ownerReferences = ownerReferences;
   }

   @JsonProperty("resourceVersion")
   public String getResourceVersion() {
      return this.resourceVersion;
   }

   @JsonProperty("resourceVersion")
   public void setResourceVersion(String resourceVersion) {
      this.resourceVersion = resourceVersion;
   }

   @JsonProperty("selfLink")
   public String getSelfLink() {
      return this.selfLink;
   }

   @JsonProperty("selfLink")
   public void setSelfLink(String selfLink) {
      this.selfLink = selfLink;
   }

   @JsonProperty("uid")
   public String getUid() {
      return this.uid;
   }

   @JsonProperty("uid")
   public void setUid(String uid) {
      this.uid = uid;
   }

   @JsonIgnore
   public ObjectMetaBuilder edit() {
      return new ObjectMetaBuilder(this);
   }

   @JsonIgnore
   public ObjectMetaBuilder toBuilder() {
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
      Map var10000 = this.getAnnotations();
      return "ObjectMeta(annotations=" + var10000 + ", creationTimestamp=" + this.getCreationTimestamp() + ", deletionGracePeriodSeconds=" + this.getDeletionGracePeriodSeconds() + ", deletionTimestamp=" + this.getDeletionTimestamp() + ", finalizers=" + this.getFinalizers() + ", generateName=" + this.getGenerateName() + ", generation=" + this.getGeneration() + ", labels=" + this.getLabels() + ", managedFields=" + this.getManagedFields() + ", name=" + this.getName() + ", namespace=" + this.getNamespace() + ", ownerReferences=" + this.getOwnerReferences() + ", resourceVersion=" + this.getResourceVersion() + ", selfLink=" + this.getSelfLink() + ", uid=" + this.getUid() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ObjectMeta)) {
         return false;
      } else {
         ObjectMeta other = (ObjectMeta)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$deletionGracePeriodSeconds = this.getDeletionGracePeriodSeconds();
            Object other$deletionGracePeriodSeconds = other.getDeletionGracePeriodSeconds();
            if (this$deletionGracePeriodSeconds == null) {
               if (other$deletionGracePeriodSeconds != null) {
                  return false;
               }
            } else if (!this$deletionGracePeriodSeconds.equals(other$deletionGracePeriodSeconds)) {
               return false;
            }

            Object this$generation = this.getGeneration();
            Object other$generation = other.getGeneration();
            if (this$generation == null) {
               if (other$generation != null) {
                  return false;
               }
            } else if (!this$generation.equals(other$generation)) {
               return false;
            }

            Object this$annotations = this.getAnnotations();
            Object other$annotations = other.getAnnotations();
            if (this$annotations == null) {
               if (other$annotations != null) {
                  return false;
               }
            } else if (!this$annotations.equals(other$annotations)) {
               return false;
            }

            Object this$creationTimestamp = this.getCreationTimestamp();
            Object other$creationTimestamp = other.getCreationTimestamp();
            if (this$creationTimestamp == null) {
               if (other$creationTimestamp != null) {
                  return false;
               }
            } else if (!this$creationTimestamp.equals(other$creationTimestamp)) {
               return false;
            }

            Object this$deletionTimestamp = this.getDeletionTimestamp();
            Object other$deletionTimestamp = other.getDeletionTimestamp();
            if (this$deletionTimestamp == null) {
               if (other$deletionTimestamp != null) {
                  return false;
               }
            } else if (!this$deletionTimestamp.equals(other$deletionTimestamp)) {
               return false;
            }

            Object this$finalizers = this.getFinalizers();
            Object other$finalizers = other.getFinalizers();
            if (this$finalizers == null) {
               if (other$finalizers != null) {
                  return false;
               }
            } else if (!this$finalizers.equals(other$finalizers)) {
               return false;
            }

            Object this$generateName = this.getGenerateName();
            Object other$generateName = other.getGenerateName();
            if (this$generateName == null) {
               if (other$generateName != null) {
                  return false;
               }
            } else if (!this$generateName.equals(other$generateName)) {
               return false;
            }

            Object this$labels = this.getLabels();
            Object other$labels = other.getLabels();
            if (this$labels == null) {
               if (other$labels != null) {
                  return false;
               }
            } else if (!this$labels.equals(other$labels)) {
               return false;
            }

            Object this$managedFields = this.getManagedFields();
            Object other$managedFields = other.getManagedFields();
            if (this$managedFields == null) {
               if (other$managedFields != null) {
                  return false;
               }
            } else if (!this$managedFields.equals(other$managedFields)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$namespace = this.getNamespace();
            Object other$namespace = other.getNamespace();
            if (this$namespace == null) {
               if (other$namespace != null) {
                  return false;
               }
            } else if (!this$namespace.equals(other$namespace)) {
               return false;
            }

            Object this$ownerReferences = this.getOwnerReferences();
            Object other$ownerReferences = other.getOwnerReferences();
            if (this$ownerReferences == null) {
               if (other$ownerReferences != null) {
                  return false;
               }
            } else if (!this$ownerReferences.equals(other$ownerReferences)) {
               return false;
            }

            Object this$resourceVersion = this.getResourceVersion();
            Object other$resourceVersion = other.getResourceVersion();
            if (this$resourceVersion == null) {
               if (other$resourceVersion != null) {
                  return false;
               }
            } else if (!this$resourceVersion.equals(other$resourceVersion)) {
               return false;
            }

            Object this$selfLink = this.getSelfLink();
            Object other$selfLink = other.getSelfLink();
            if (this$selfLink == null) {
               if (other$selfLink != null) {
                  return false;
               }
            } else if (!this$selfLink.equals(other$selfLink)) {
               return false;
            }

            Object this$uid = this.getUid();
            Object other$uid = other.getUid();
            if (this$uid == null) {
               if (other$uid != null) {
                  return false;
               }
            } else if (!this$uid.equals(other$uid)) {
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
      return other instanceof ObjectMeta;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $deletionGracePeriodSeconds = this.getDeletionGracePeriodSeconds();
      result = result * 59 + ($deletionGracePeriodSeconds == null ? 43 : $deletionGracePeriodSeconds.hashCode());
      Object $generation = this.getGeneration();
      result = result * 59 + ($generation == null ? 43 : $generation.hashCode());
      Object $annotations = this.getAnnotations();
      result = result * 59 + ($annotations == null ? 43 : $annotations.hashCode());
      Object $creationTimestamp = this.getCreationTimestamp();
      result = result * 59 + ($creationTimestamp == null ? 43 : $creationTimestamp.hashCode());
      Object $deletionTimestamp = this.getDeletionTimestamp();
      result = result * 59 + ($deletionTimestamp == null ? 43 : $deletionTimestamp.hashCode());
      Object $finalizers = this.getFinalizers();
      result = result * 59 + ($finalizers == null ? 43 : $finalizers.hashCode());
      Object $generateName = this.getGenerateName();
      result = result * 59 + ($generateName == null ? 43 : $generateName.hashCode());
      Object $labels = this.getLabels();
      result = result * 59 + ($labels == null ? 43 : $labels.hashCode());
      Object $managedFields = this.getManagedFields();
      result = result * 59 + ($managedFields == null ? 43 : $managedFields.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $namespace = this.getNamespace();
      result = result * 59 + ($namespace == null ? 43 : $namespace.hashCode());
      Object $ownerReferences = this.getOwnerReferences();
      result = result * 59 + ($ownerReferences == null ? 43 : $ownerReferences.hashCode());
      Object $resourceVersion = this.getResourceVersion();
      result = result * 59 + ($resourceVersion == null ? 43 : $resourceVersion.hashCode());
      Object $selfLink = this.getSelfLink();
      result = result * 59 + ($selfLink == null ? 43 : $selfLink.hashCode());
      Object $uid = this.getUid();
      result = result * 59 + ($uid == null ? 43 : $uid.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
