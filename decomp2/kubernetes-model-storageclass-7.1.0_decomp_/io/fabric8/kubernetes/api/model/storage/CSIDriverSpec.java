package io.fabric8.kubernetes.api.model.storage;

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
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"attachRequired", "fsGroupPolicy", "podInfoOnMount", "requiresRepublish", "seLinuxMount", "storageCapacity", "tokenRequests", "volumeLifecycleModes"})
public class CSIDriverSpec implements Editable, KubernetesResource {
   @JsonProperty("attachRequired")
   private Boolean attachRequired;
   @JsonProperty("fsGroupPolicy")
   private String fsGroupPolicy;
   @JsonProperty("podInfoOnMount")
   private Boolean podInfoOnMount;
   @JsonProperty("requiresRepublish")
   private Boolean requiresRepublish;
   @JsonProperty("seLinuxMount")
   private Boolean seLinuxMount;
   @JsonProperty("storageCapacity")
   private Boolean storageCapacity;
   @JsonProperty("tokenRequests")
   @JsonInclude(Include.NON_EMPTY)
   private List tokenRequests = new ArrayList();
   @JsonProperty("volumeLifecycleModes")
   @JsonInclude(Include.NON_EMPTY)
   private List volumeLifecycleModes = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public CSIDriverSpec() {
   }

   public CSIDriverSpec(Boolean attachRequired, String fsGroupPolicy, Boolean podInfoOnMount, Boolean requiresRepublish, Boolean seLinuxMount, Boolean storageCapacity, List tokenRequests, List volumeLifecycleModes) {
      this.attachRequired = attachRequired;
      this.fsGroupPolicy = fsGroupPolicy;
      this.podInfoOnMount = podInfoOnMount;
      this.requiresRepublish = requiresRepublish;
      this.seLinuxMount = seLinuxMount;
      this.storageCapacity = storageCapacity;
      this.tokenRequests = tokenRequests;
      this.volumeLifecycleModes = volumeLifecycleModes;
   }

   @JsonProperty("attachRequired")
   public Boolean getAttachRequired() {
      return this.attachRequired;
   }

   @JsonProperty("attachRequired")
   public void setAttachRequired(Boolean attachRequired) {
      this.attachRequired = attachRequired;
   }

   @JsonProperty("fsGroupPolicy")
   public String getFsGroupPolicy() {
      return this.fsGroupPolicy;
   }

   @JsonProperty("fsGroupPolicy")
   public void setFsGroupPolicy(String fsGroupPolicy) {
      this.fsGroupPolicy = fsGroupPolicy;
   }

   @JsonProperty("podInfoOnMount")
   public Boolean getPodInfoOnMount() {
      return this.podInfoOnMount;
   }

   @JsonProperty("podInfoOnMount")
   public void setPodInfoOnMount(Boolean podInfoOnMount) {
      this.podInfoOnMount = podInfoOnMount;
   }

   @JsonProperty("requiresRepublish")
   public Boolean getRequiresRepublish() {
      return this.requiresRepublish;
   }

   @JsonProperty("requiresRepublish")
   public void setRequiresRepublish(Boolean requiresRepublish) {
      this.requiresRepublish = requiresRepublish;
   }

   @JsonProperty("seLinuxMount")
   public Boolean getSeLinuxMount() {
      return this.seLinuxMount;
   }

   @JsonProperty("seLinuxMount")
   public void setSeLinuxMount(Boolean seLinuxMount) {
      this.seLinuxMount = seLinuxMount;
   }

   @JsonProperty("storageCapacity")
   public Boolean getStorageCapacity() {
      return this.storageCapacity;
   }

   @JsonProperty("storageCapacity")
   public void setStorageCapacity(Boolean storageCapacity) {
      this.storageCapacity = storageCapacity;
   }

   @JsonProperty("tokenRequests")
   @JsonInclude(Include.NON_EMPTY)
   public List getTokenRequests() {
      return this.tokenRequests;
   }

   @JsonProperty("tokenRequests")
   public void setTokenRequests(List tokenRequests) {
      this.tokenRequests = tokenRequests;
   }

   @JsonProperty("volumeLifecycleModes")
   @JsonInclude(Include.NON_EMPTY)
   public List getVolumeLifecycleModes() {
      return this.volumeLifecycleModes;
   }

   @JsonProperty("volumeLifecycleModes")
   public void setVolumeLifecycleModes(List volumeLifecycleModes) {
      this.volumeLifecycleModes = volumeLifecycleModes;
   }

   @JsonIgnore
   public CSIDriverSpecBuilder edit() {
      return new CSIDriverSpecBuilder(this);
   }

   @JsonIgnore
   public CSIDriverSpecBuilder toBuilder() {
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
      Boolean var10000 = this.getAttachRequired();
      return "CSIDriverSpec(attachRequired=" + var10000 + ", fsGroupPolicy=" + this.getFsGroupPolicy() + ", podInfoOnMount=" + this.getPodInfoOnMount() + ", requiresRepublish=" + this.getRequiresRepublish() + ", seLinuxMount=" + this.getSeLinuxMount() + ", storageCapacity=" + this.getStorageCapacity() + ", tokenRequests=" + this.getTokenRequests() + ", volumeLifecycleModes=" + this.getVolumeLifecycleModes() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CSIDriverSpec)) {
         return false;
      } else {
         CSIDriverSpec other = (CSIDriverSpec)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$attachRequired = this.getAttachRequired();
            Object other$attachRequired = other.getAttachRequired();
            if (this$attachRequired == null) {
               if (other$attachRequired != null) {
                  return false;
               }
            } else if (!this$attachRequired.equals(other$attachRequired)) {
               return false;
            }

            Object this$podInfoOnMount = this.getPodInfoOnMount();
            Object other$podInfoOnMount = other.getPodInfoOnMount();
            if (this$podInfoOnMount == null) {
               if (other$podInfoOnMount != null) {
                  return false;
               }
            } else if (!this$podInfoOnMount.equals(other$podInfoOnMount)) {
               return false;
            }

            Object this$requiresRepublish = this.getRequiresRepublish();
            Object other$requiresRepublish = other.getRequiresRepublish();
            if (this$requiresRepublish == null) {
               if (other$requiresRepublish != null) {
                  return false;
               }
            } else if (!this$requiresRepublish.equals(other$requiresRepublish)) {
               return false;
            }

            Object this$seLinuxMount = this.getSeLinuxMount();
            Object other$seLinuxMount = other.getSeLinuxMount();
            if (this$seLinuxMount == null) {
               if (other$seLinuxMount != null) {
                  return false;
               }
            } else if (!this$seLinuxMount.equals(other$seLinuxMount)) {
               return false;
            }

            Object this$storageCapacity = this.getStorageCapacity();
            Object other$storageCapacity = other.getStorageCapacity();
            if (this$storageCapacity == null) {
               if (other$storageCapacity != null) {
                  return false;
               }
            } else if (!this$storageCapacity.equals(other$storageCapacity)) {
               return false;
            }

            Object this$fsGroupPolicy = this.getFsGroupPolicy();
            Object other$fsGroupPolicy = other.getFsGroupPolicy();
            if (this$fsGroupPolicy == null) {
               if (other$fsGroupPolicy != null) {
                  return false;
               }
            } else if (!this$fsGroupPolicy.equals(other$fsGroupPolicy)) {
               return false;
            }

            Object this$tokenRequests = this.getTokenRequests();
            Object other$tokenRequests = other.getTokenRequests();
            if (this$tokenRequests == null) {
               if (other$tokenRequests != null) {
                  return false;
               }
            } else if (!this$tokenRequests.equals(other$tokenRequests)) {
               return false;
            }

            Object this$volumeLifecycleModes = this.getVolumeLifecycleModes();
            Object other$volumeLifecycleModes = other.getVolumeLifecycleModes();
            if (this$volumeLifecycleModes == null) {
               if (other$volumeLifecycleModes != null) {
                  return false;
               }
            } else if (!this$volumeLifecycleModes.equals(other$volumeLifecycleModes)) {
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
      return other instanceof CSIDriverSpec;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $attachRequired = this.getAttachRequired();
      result = result * 59 + ($attachRequired == null ? 43 : $attachRequired.hashCode());
      Object $podInfoOnMount = this.getPodInfoOnMount();
      result = result * 59 + ($podInfoOnMount == null ? 43 : $podInfoOnMount.hashCode());
      Object $requiresRepublish = this.getRequiresRepublish();
      result = result * 59 + ($requiresRepublish == null ? 43 : $requiresRepublish.hashCode());
      Object $seLinuxMount = this.getSeLinuxMount();
      result = result * 59 + ($seLinuxMount == null ? 43 : $seLinuxMount.hashCode());
      Object $storageCapacity = this.getStorageCapacity();
      result = result * 59 + ($storageCapacity == null ? 43 : $storageCapacity.hashCode());
      Object $fsGroupPolicy = this.getFsGroupPolicy();
      result = result * 59 + ($fsGroupPolicy == null ? 43 : $fsGroupPolicy.hashCode());
      Object $tokenRequests = this.getTokenRequests();
      result = result * 59 + ($tokenRequests == null ? 43 : $tokenRequests.hashCode());
      Object $volumeLifecycleModes = this.getVolumeLifecycleModes();
      result = result * 59 + ($volumeLifecycleModes == null ? 43 : $volumeLifecycleModes.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
