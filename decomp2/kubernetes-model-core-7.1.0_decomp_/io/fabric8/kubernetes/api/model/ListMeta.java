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
@JsonPropertyOrder({"continue", "remainingItemCount", "resourceVersion", "selfLink"})
public class ListMeta implements Editable, KubernetesResource {
   @JsonProperty("continue")
   private String _continue;
   @JsonProperty("remainingItemCount")
   private Long remainingItemCount;
   @JsonProperty("resourceVersion")
   private String resourceVersion;
   @JsonProperty("selfLink")
   private String selfLink;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ListMeta() {
   }

   public ListMeta(String _continue, Long remainingItemCount, String resourceVersion, String selfLink) {
      this._continue = _continue;
      this.remainingItemCount = remainingItemCount;
      this.resourceVersion = resourceVersion;
      this.selfLink = selfLink;
   }

   @JsonProperty("continue")
   public String getContinue() {
      return this._continue;
   }

   @JsonProperty("continue")
   public void setContinue(String _continue) {
      this._continue = _continue;
   }

   @JsonProperty("remainingItemCount")
   public Long getRemainingItemCount() {
      return this.remainingItemCount;
   }

   @JsonProperty("remainingItemCount")
   public void setRemainingItemCount(Long remainingItemCount) {
      this.remainingItemCount = remainingItemCount;
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

   @JsonIgnore
   public ListMetaBuilder edit() {
      return new ListMetaBuilder(this);
   }

   @JsonIgnore
   public ListMetaBuilder toBuilder() {
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
      String var10000 = this.getContinue();
      return "ListMeta(_continue=" + var10000 + ", remainingItemCount=" + this.getRemainingItemCount() + ", resourceVersion=" + this.getResourceVersion() + ", selfLink=" + this.getSelfLink() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ListMeta)) {
         return false;
      } else {
         ListMeta other = (ListMeta)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$remainingItemCount = this.getRemainingItemCount();
            Object other$remainingItemCount = other.getRemainingItemCount();
            if (this$remainingItemCount == null) {
               if (other$remainingItemCount != null) {
                  return false;
               }
            } else if (!this$remainingItemCount.equals(other$remainingItemCount)) {
               return false;
            }

            Object this$_continue = this.getContinue();
            Object other$_continue = other.getContinue();
            if (this$_continue == null) {
               if (other$_continue != null) {
                  return false;
               }
            } else if (!this$_continue.equals(other$_continue)) {
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
      return other instanceof ListMeta;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $remainingItemCount = this.getRemainingItemCount();
      result = result * 59 + ($remainingItemCount == null ? 43 : $remainingItemCount.hashCode());
      Object $_continue = this.getContinue();
      result = result * 59 + ($_continue == null ? 43 : $_continue.hashCode());
      Object $resourceVersion = this.getResourceVersion();
      result = result * 59 + ($resourceVersion == null ? 43 : $resourceVersion.hashCode());
      Object $selfLink = this.getSelfLink();
      result = result * 59 + ($selfLink == null ? 43 : $selfLink.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
