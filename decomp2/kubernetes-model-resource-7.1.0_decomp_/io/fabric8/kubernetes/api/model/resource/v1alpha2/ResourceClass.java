package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.NodeSelector;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "driverName", "parametersRef", "structuredParameters", "suitableNodes"})
@Version("v1alpha2")
@Group("resource.k8s.io")
public class ResourceClass implements Editable, HasMetadata {
   @JsonProperty("apiVersion")
   private String apiVersion = "resource.k8s.io/v1alpha2";
   @JsonProperty("driverName")
   private String driverName;
   @JsonProperty("kind")
   private String kind = "ResourceClass";
   @JsonProperty("metadata")
   private ObjectMeta metadata;
   @JsonProperty("parametersRef")
   private ResourceClassParametersReference parametersRef;
   @JsonProperty("structuredParameters")
   private Boolean structuredParameters;
   @JsonProperty("suitableNodes")
   private NodeSelector suitableNodes;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ResourceClass() {
   }

   public ResourceClass(String apiVersion, String driverName, String kind, ObjectMeta metadata, ResourceClassParametersReference parametersRef, Boolean structuredParameters, NodeSelector suitableNodes) {
      this.apiVersion = apiVersion;
      this.driverName = driverName;
      this.kind = kind;
      this.metadata = metadata;
      this.parametersRef = parametersRef;
      this.structuredParameters = structuredParameters;
      this.suitableNodes = suitableNodes;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("driverName")
   public String getDriverName() {
      return this.driverName;
   }

   @JsonProperty("driverName")
   public void setDriverName(String driverName) {
      this.driverName = driverName;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("metadata")
   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("metadata")
   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("parametersRef")
   public ResourceClassParametersReference getParametersRef() {
      return this.parametersRef;
   }

   @JsonProperty("parametersRef")
   public void setParametersRef(ResourceClassParametersReference parametersRef) {
      this.parametersRef = parametersRef;
   }

   @JsonProperty("structuredParameters")
   public Boolean getStructuredParameters() {
      return this.structuredParameters;
   }

   @JsonProperty("structuredParameters")
   public void setStructuredParameters(Boolean structuredParameters) {
      this.structuredParameters = structuredParameters;
   }

   @JsonProperty("suitableNodes")
   public NodeSelector getSuitableNodes() {
      return this.suitableNodes;
   }

   @JsonProperty("suitableNodes")
   public void setSuitableNodes(NodeSelector suitableNodes) {
      this.suitableNodes = suitableNodes;
   }

   @JsonIgnore
   public ResourceClassBuilder edit() {
      return new ResourceClassBuilder(this);
   }

   @JsonIgnore
   public ResourceClassBuilder toBuilder() {
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
      String var10000 = this.getApiVersion();
      return "ResourceClass(apiVersion=" + var10000 + ", driverName=" + this.getDriverName() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ", parametersRef=" + this.getParametersRef() + ", structuredParameters=" + this.getStructuredParameters() + ", suitableNodes=" + this.getSuitableNodes() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourceClass)) {
         return false;
      } else {
         ResourceClass other = (ResourceClass)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$structuredParameters = this.getStructuredParameters();
            Object other$structuredParameters = other.getStructuredParameters();
            if (this$structuredParameters == null) {
               if (other$structuredParameters != null) {
                  return false;
               }
            } else if (!this$structuredParameters.equals(other$structuredParameters)) {
               return false;
            }

            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$driverName = this.getDriverName();
            Object other$driverName = other.getDriverName();
            if (this$driverName == null) {
               if (other$driverName != null) {
                  return false;
               }
            } else if (!this$driverName.equals(other$driverName)) {
               return false;
            }

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
               return false;
            }

            Object this$metadata = this.getMetadata();
            Object other$metadata = other.getMetadata();
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
               return false;
            }

            Object this$parametersRef = this.getParametersRef();
            Object other$parametersRef = other.getParametersRef();
            if (this$parametersRef == null) {
               if (other$parametersRef != null) {
                  return false;
               }
            } else if (!this$parametersRef.equals(other$parametersRef)) {
               return false;
            }

            Object this$suitableNodes = this.getSuitableNodes();
            Object other$suitableNodes = other.getSuitableNodes();
            if (this$suitableNodes == null) {
               if (other$suitableNodes != null) {
                  return false;
               }
            } else if (!this$suitableNodes.equals(other$suitableNodes)) {
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
      return other instanceof ResourceClass;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $structuredParameters = this.getStructuredParameters();
      result = result * 59 + ($structuredParameters == null ? 43 : $structuredParameters.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $driverName = this.getDriverName();
      result = result * 59 + ($driverName == null ? 43 : $driverName.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $metadata = this.getMetadata();
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $parametersRef = this.getParametersRef();
      result = result * 59 + ($parametersRef == null ? 43 : $parametersRef.hashCode());
      Object $suitableNodes = this.getSuitableNodes();
      result = result * 59 + ($suitableNodes == null ? 43 : $suitableNodes.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
